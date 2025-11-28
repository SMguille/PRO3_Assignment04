package via.pro3.station_server.Controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import via.pro3.station_server.Model.AnimalRepository;
import via.pro3.station_server.Model.Animal;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController public class RetrievalController
{
  private final AnimalRepository animalRepository;

  public RetrievalController(AnimalRepository animalRepository)
  {
    this.animalRepository = animalRepository;
  }

  @GetMapping("/animals") public List<Animal> getAnimals(
      @RequestParam(required = false) Integer id,
      @RequestParam(required = false) Integer origin,
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate arrivalAt)
      throws SQLException
  {
    return animalRepository.findByOptionalParameters(id, origin, arrivalAt);
  }

  @GetMapping("/animals/{id}") public Animal getAnimalById(@PathVariable int id)
      throws SQLException
  {
    return animalRepository.findDistinctById(id);
  }
}
