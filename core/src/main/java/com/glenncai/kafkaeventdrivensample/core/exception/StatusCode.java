package com.glenncai.kafkaeventdrivensample.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusCode {

  SUCCESS(0, "ok"),
  INTERNAL_SERVER_ERROR(50000, "Internal Server Error"),
  KAFKA_INTERNAL_ERROR(50001, "Kafka Internal Error");

  private final int code;
  private final String message;
}
