package via.pro3.central_registration_server.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue registerPartQueue() {
        return new Queue("register_part", true);
    }

    @Bean
    public Queue registerPartTypeQueue() {
        return new Queue("register_part_type", true);
    }

    @Bean
    public Queue registerTrayQueue() {
        return new Queue("register_tray", true);
    }

    @Bean
    public Queue registerAnimalQueue() {
        return new Queue("register_animal", true);
    }

    @Bean
    public Queue registerProductQueue() {
        return new Queue("register_product", true);
    }
}