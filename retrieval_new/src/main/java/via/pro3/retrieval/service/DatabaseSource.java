package via.pro3.retrieval.service;

import io.grpc.Status;
import via.pro3.retrieval.generated.AnimalRef;
import via.pro3.retrieval.generated.ProductRef;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseSource implements DataSource
{
  private static final String URL  =
      System.getenv().getOrDefault("DB_URL",  "jdbc:postgresql://localhost:5432/postgres");
  private static final String USER =
      System.getenv().getOrDefault("DB_USER", "postgres");
  private static final String PASS =
      System.getenv().getOrDefault("DB_PASS", "password");

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(URL, USER, PASS);
  }

  private static final String SQL_ANIMALS_BY_PRODUCT = """
      SELECT DISTINCT p.animal_id
      FROM pro3_assignment.part p
      JOIN pro3_assignment.tray t       ON p.tray_id = t.id
      JOIN pro3_assignment.product_tray pt ON pt.tray_id = t.id
      WHERE pt.product_id = ?
      """;

  private static final String SQL_PRODUCTS_BY_ANIMAL = """
      SELECT DISTINCT pt.product_id
      FROM pro3_assignment.product_tray pt
      JOIN pro3_assignment.tray t ON pt.tray_id = t.id
      JOIN pro3_assignment.part p ON p.tray_id = t.id
      WHERE p.animal_id = ?
      """;

  @Override public List<Integer> getAnimalsByProduct(int productId)
      throws SQLException
  {
    try (Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_ANIMALS_BY_PRODUCT))
    {

      ps.setInt(1, productId);
      List<Integer> ids = new ArrayList<>();
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) ids.add(rs.getInt(1));
      }
      catch (SQLException e) {
        System.out.println("SQL exception: " + e.getMessage());
      }

      return ids;

    } catch (SQLException e) {
      System.out.println("Error:"+e.getMessage());
      throw e;
    }
  }

  @Override public List<Integer> getProductsByAnimal(int animalId)
      throws SQLException
  {
    try (Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_PRODUCTS_BY_ANIMAL)) {

      ps.setInt(1, animalId);
      List<Integer> ids = new ArrayList<>(8);
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) ids.add(rs.getInt(1));
      }
      catch (SQLException e) {
        System.out.println("SQL exception: " + e.getMessage());
      }

      return ids;
    } catch (SQLException e) {
      System.out.println("Error:"+e.getMessage());
      throw e;
    }
  }
}
