package via.pro3.station_server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import via.pro3.station_server.Model.Animal;

import java.sql.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component public class DatabaseDataHandler implements DataHandler
{
  @Value("${db.url}")
  private String URL;
  @Value("${db.usr}")
  private String USER;
  @Value("${db.pwd}")
  private String PASS;

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(URL, USER, PASS);
  }

  private static final String SQL_GET_ANIMALS = """
      SELECT  a.id,
              a.arrival_at,
              a.live_weight_kg,
              a.origin_id
      FROM    pro3_assignment.animal a
      ORDER BY a.id
      """;

  private static final String SQL_ADD_ANIMAL = """
      INSERT INTO pro3_assignment.animal
              (arrival_at, live_weight_kg, origin_id)
            VALUES (?, ?, ?)
            RETURNING id
      """;

  @Override public List<Animal> getAnimals() throws SQLException
  {
    try (Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_GET_ANIMALS);
        ResultSet rs = ps.executeQuery())
    {

      List<Animal> animals = new ArrayList<>(32);
      while (rs.next())
      {
        animals.add(mapAnimal(rs));
      }
      return animals;
    }
  }

  @Override public Animal addAnimal(Animal animal) throws SQLException
  {
    try (Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_ADD_ANIMAL))
    {
      ps.setObject(1, animal.getArrivalAt());
      ps.setFloat(2, animal.getLiveWeight());
      if (animal.getOrigin() == -1)
        ps.setNull(3, Types.INTEGER);
      else
        ps.setInt(3, animal.getOrigin());

      try (ResultSet rs = ps.executeQuery())
      {
        if (!rs.next())
          throw new SQLException("insert failed");
        final int newId = rs.getInt(1);
        return new Animal(newId, animal.getArrivalAt(), animal.getLiveWeight(),
            animal.getOrigin());
      }
    }
  }

  private Animal mapAnimal(ResultSet rs) throws SQLException
  {
    final int id = rs.getInt("id");

    final OffsetDateTime arrivalAt = rs.getObject("arrival_at",
        OffsetDateTime.class);

    final float liveWeightKg = rs.getFloat("live_weight_kg");
    final Integer originId = (Integer) rs.getObject("origin_id");

    return new Animal(id, arrivalAt, liveWeightKg, originId);
  }
}
