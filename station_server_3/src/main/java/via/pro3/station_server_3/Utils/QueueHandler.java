package via.pro3.station_server_3.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeoutException;

@Component
public class QueueHandler {
    private final TypeAdapter<LocalDate> LOCAL_DATE_ADAPTER = new TypeAdapter<>() {
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

        @Override
        public void write(JsonWriter out, LocalDate value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(value.format(FORMATTER));
            }
        }

        @Override
        public LocalDate read(JsonReader in) throws IOException {
            if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            return LocalDate.parse(in.nextString(), FORMATTER);
        }
    };

    private final Gson gson;
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private boolean connectionEstablished = false;

    @Value("${rabbitmq.host}")
    private String host;

    @Value("${rabbitmq.port}")
    private int port;

    private Queue<String> queue = new ConcurrentLinkedQueue<>();

    public QueueHandler() {
        // We register the HibernateProxyTypeAdapter.FACTORY so Gson can handle JPA entities
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, LOCAL_DATE_ADAPTER)
                .registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                .create();
    }

    @PostConstruct
    private void init() {
        factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        tryEstablishConnection();
    }

    private void tryEstablishConnection() {
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            connectionEstablished = true;
        } catch (Exception e) {
            System.out.println("Could not establish a connection to the RabbitMQ server. ");
        }
    }

    public void addToQueue(String queueName, Object obj) throws IOException, TimeoutException {
        if (!connectionEstablished) {
            tryEstablishConnection();
        }
        if (!connectionEstablished) {
            throw new RuntimeException("Connection not established.");
        }
        addToRemoteQueue(queueName, obj);
    }

    private void addToRemoteQueue(String queueName, Object obj) throws IOException {
        channel.queueDeclare(queueName, false, false, false, null);
        String message = objToString(obj);

        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .contentType("application/json")
                .deliveryMode(2)
                .priority(0)
                .build();

        channel.basicPublish("", queueName, props, message.getBytes());
        System.out.println(" [Queue: " + queueName + "] Sent '" + message + "'");
    }

    private String objToString(Object obj) {
        return gson.toJson(obj);
    }
}