package via.pro3.station_server.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import via.pro3.station_server.DatabaseDataHandler;
import via.pro3.station_server.Model.Animal;

import java.sql.SQLException;

@RestController public class RegistrationController
{
  private final DatabaseDataHandler databaseSource;

  public RegistrationController(DatabaseDataHandler databaseSource)
      throws SQLException
  {
    this.databaseSource = databaseSource;
  }

  @PostMapping("/animals") public Animal addAnimal(@RequestBody Animal animal)
  {
    try
    {
      return databaseSource.addAnimal(animal);
    }
    catch (SQLException ex)
    {
      System.out.println(ex.getMessage());
      return null;
    }
  }
}
