package via.pro3.station_server.Controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import via.pro3.station_server.DatabaseDataHandler;
import via.pro3.station_server.Model.Animal;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController public class RetrievalController
{
  private final DatabaseDataHandler databaseSource;

  public RetrievalController(DatabaseDataHandler databaseSource)
  {
    this.databaseSource = databaseSource;
  }

  @GetMapping("/animals") public List<Animal> getAnimals(
      @RequestParam(required = false) Integer id,
      @RequestParam(required = false) Integer origin,
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate arrivalAt)
      throws SQLException
  {
    return selectAnimals(id, origin, arrivalAt);
  }

  @GetMapping("/animals/{id}") public Animal getAnimalById(@PathVariable int id)
      throws SQLException
  {
    var res = selectAnimals(id, null, null);
    if (res != null && res.size() > 0)
      return res.get(0);
    else
      return null;
  }

  private static final ZoneId BUSINESS_ZONE = ZoneId.of("Europe/Copenhagen");

  private List<Animal> selectAnimals(Integer id, Integer origin,
      LocalDate arrivalAt) throws SQLException
  {
    var result = new ArrayList<Animal>();
    for (var a : getAllAnimals())
    {
      if (id != null && a.getId() != id)
        continue;
      if (origin != null && a.getOrigin() != origin)
        continue;
      if (arrivalAt != null && !a.getArrivalAt()
          .atZoneSameInstant(BUSINESS_ZONE).toLocalDate().equals(arrivalAt))
        continue;
      result.add(a);
    }
    return result;
  }

  private List<Animal> getAllAnimals()
  {
    try
    {
      return databaseSource.getAnimals();
    }
    catch (SQLException e)
    {
      System.out.println(e.getMessage());
      return new ArrayList<>();
    }
  }
}
