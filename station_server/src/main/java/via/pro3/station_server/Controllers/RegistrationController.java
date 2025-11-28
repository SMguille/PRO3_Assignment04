package via.pro3.station_server.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import via.pro3.station_server.Model.Animal;
import via.pro3.station_server.Model.AnimalRepository;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

@RestController public class RegistrationController
{
  private final static String QUEUE_NAME = "register_animal";
  private final AnimalRepository animalRepository;

  public RegistrationController(AnimalRepository animalRepository)
  {
    this.animalRepository = animalRepository;
  }

  @PostMapping("/animals") public Animal addAnimal(@RequestBody Animal animal)
  {
    Animal addedAnimal = animalRepository.save(animal);
    addToQueue(addedAnimal);
    return addedAnimal;
  }

  private void addToQueue(Animal animal)
  {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    try (Connection connection = factory.newConnection();
        Channel channel = connection.createChannel())
    {
      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
      String message = animal.toString();
      channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
      System.out.println(" [x] Sent '" + message + "'");
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
