package via.pro3.station_server;

import org.springframework.stereotype.Component;
import via.pro3.station_server.Model.Animal;

import java.sql.SQLException;
import java.util.List;

@Component
public interface DataHandler
{
  List<Animal> getAnimals() throws SQLException;
  Animal addAnimal(Animal animal) throws SQLException;
}
