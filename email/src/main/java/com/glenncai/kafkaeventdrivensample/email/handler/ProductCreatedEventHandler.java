package com.glenncai.kafkaeventdrivensample.email.handler;

import static com.glenncai.kafkaeventdrivensample.core.constant.KafkaConstant.PRODUCT_CREATED_EVENTS_TOPIC;

import com.glenncai.kafkaeventdrivensample.core.event.ProductCreatedEvent;
import com.glenncai.kafkaeventdrivensample.core.exception.NotRetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@KafkaListener(topics = PRODUCT_CREATED_EVENTS_TOPIC)
public class ProductCreatedEventHandler {

  @KafkaHandler
  public void handle(ProductCreatedEvent productCreatedEvent) {
    log.info("Received product created event: {}", productCreatedEvent);
  }
}
