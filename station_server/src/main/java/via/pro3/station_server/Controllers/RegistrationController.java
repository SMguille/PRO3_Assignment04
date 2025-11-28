package via.pro3.station_server.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import via.pro3.station_server.Model.Animal;
import via.pro3.station_server.Model.AnimalRepository;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import via.pro3.station_server.Utils.QueueHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

@RestController public class RegistrationController
{
  private final static String QUEUE_NAME = "register_animal";
  private final static String HOST = "localhost";
  private final AnimalRepository animalRepository;

  public RegistrationController(AnimalRepository animalRepository)
  {
    this.animalRepository = animalRepository;
  }

  @PostMapping("/animals") public Animal addAnimal(@RequestBody Animal animal)
  {
    try
    {
      Animal addedAnimal = animalRepository.save(animal);
      QueueHandler.addToQueue(HOST, QUEUE_NAME, animal);
      return addedAnimal;
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
    catch (TimeoutException e)
    {
      throw new RuntimeException(e);
    }
  }
}
