package via.pro3.station_server.Utils;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import via.pro3.station_server.Model.Animal;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class QueueHandler
{
  private static final Gson gson = new Gson();

  public static void addToQueue(String host, String queueName, Object obj) throws IOException, TimeoutException
  {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(host);
    try (Connection connection = factory.newConnection();
        Channel channel = connection.createChannel())
    {
      channel.queueDeclare(queueName, false, false, false, null);
      String message = obj.toString();
      channel.basicPublish("", queueName, null, message.getBytes());
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
