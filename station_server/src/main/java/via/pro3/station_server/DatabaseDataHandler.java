package via.pro3.station_server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import via.pro3.station_server.Model.Animal;
import via.pro3.station_server.Model.AnimalRepository;

import java.sql.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component public class DatabaseDataHandler implements DataHandler
{
  private final AnimalRepository animalRepository;

  // Use Constructor Injection (best practice)
  public DatabaseDataHandler(AnimalRepository animalRepository)
  {
    this.animalRepository = animalRepository;
  }

  @Override public List<Animal> getAnimals() throws SQLException
  {
    return animalRepository.findAll();
  }

  @Override public Animal addAnimal(Animal animal) throws SQLException
  {
    return animalRepository.save(animal);
  }
}
