package com.glenncai.kafkaeventdrivensample.email.config;

import static com.glenncai.kafkaeventdrivensample.core.constant.KafkaConstant.PRODUCT_CREATED_EVENTS_CONSUMER_GROUP;

import com.glenncai.kafkaeventdrivensample.core.exception.NotRetryableException;
import com.glenncai.kafkaeventdrivensample.core.exception.RetryableException;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {

  @Bean
  public ConsumerFactory<String, Object> consumerFactory() {
    Map<String, Object> config = new HashMap<>();
    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9094");
    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
    config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
    config.put(ConsumerConfig.GROUP_ID_CONFIG, PRODUCT_CREATED_EVENTS_CONSUMER_GROUP);
    config.put(JsonDeserializer.TRUSTED_PACKAGES, "com.glenncai.kafkaeventdrivensample.core.event");

    return new DefaultKafkaConsumerFactory<>(config);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(
      ConsumerFactory<String, Object> consumerFactory,
      KafkaTemplate<String, Object> kafkaTemplate) {
    DefaultErrorHandler errorHandler =
        new DefaultErrorHandler(new DeadLetterPublishingRecoverer(kafkaTemplate),
                                new FixedBackOff(5000, 3));
    errorHandler.addNotRetryableExceptions(NotRetryableException.class);
    errorHandler.addRetryableExceptions(RetryableException.class);

    ConcurrentKafkaListenerContainerFactory<String, Object> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory);
    factory.setCommonErrorHandler(errorHandler);
    return factory;
  }

  @Bean
  public KafkaTemplate<String, Object> kafkaTemplate(
      ProducerFactory<String, Object> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }

  @Bean
  public ProducerFactory<String, Object> producerFactory() {
    Map<String, Object> config = new HashMap<>();
    config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9094");
    config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

    return new DefaultKafkaProducerFactory<>(config);
  }
}
