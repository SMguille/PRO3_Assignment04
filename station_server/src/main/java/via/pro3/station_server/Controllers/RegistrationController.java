package via.pro3.station_server.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import via.pro3.station_server.Model.Animal;
import via.pro3.station_server.Model.AnimalRepository;

import java.sql.SQLException;

@RestController public class RegistrationController
{
  private final AnimalRepository animalRepository;

  public RegistrationController(AnimalRepository animalRepository)
  {
    this.animalRepository = animalRepository;
  }

  @PostMapping("/animals") public Animal addAnimal(@RequestBody Animal animal)
  {
    return animalRepository.save(animal);
  }
}
