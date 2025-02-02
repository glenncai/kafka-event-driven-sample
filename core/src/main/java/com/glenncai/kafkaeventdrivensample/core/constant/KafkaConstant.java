package com.glenncai.kafkaeventdrivensample.core.constant;

public final class KafkaConstant {

  private KafkaConstant() {
    throw new IllegalStateException("Constant class");
  }

  // Kafka topics
  public static final String PRODUCT_CREATED_EVENTS_TOPIC = "product-created-events-topic";

  // Kafka consumer groups
  public static final String PRODUCT_CREATED_EVENTS_CONSUMER_GROUP = "product-created-events-consumer-group";
}
