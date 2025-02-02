package com.glenncai.kafkaeventdrivensample.product.config;

import com.glenncai.kafkaeventdrivensample.product.event.ProductCreatedEvent;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {

  public static final String PRODUCT_CREATED_EVENTS_TOPIC = "product-created-events-topic";

  Map<String, Object> productConfig() {
    Map<String, Object> config = new HashMap<>();
    config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9094");
    config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
    config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.springframework.kafka.support.serializer.JsonSerializer");
    config.put(ProducerConfig.ACKS_CONFIG, "all");
    config.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, 120000);
    config.put(ProducerConfig.LINGER_MS_CONFIG, 0);
    config.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
    config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
    config.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 5);

    return config;
  }

  @Bean
  ProducerFactory<String, ProductCreatedEvent> producerFactory() {
    return new DefaultKafkaProducerFactory<>(productConfig());
  }

  @Bean
  KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  NewTopic createTopic() {
    return TopicBuilder.name(PRODUCT_CREATED_EVENTS_TOPIC)
                       .partitions(3)
                       .replicas(3)
                       .configs(Map.of("min.insync.replicas", "2"))
                       .build();
  }
}
