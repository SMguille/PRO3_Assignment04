package via.pro3.retrieval.service;

import java.sql.SQLException;
import java.util.List;

public interface DataSource
{
  List<Integer> getAnimalsByProduct(int productId) throws SQLException;
  List<Integer> getProductsByAnimal(int animalId) throws SQLException;
}
