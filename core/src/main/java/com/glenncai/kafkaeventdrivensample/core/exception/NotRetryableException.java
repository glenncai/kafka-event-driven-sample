package com.glenncai.kafkaeventdrivensample.core.exception;

import lombok.Getter;

@Getter
public class NotRetryableException extends RuntimeException {

  private final int code;

  public NotRetryableException(int code, String message) {
    super(message);
    this.code = code;
  }

  public NotRetryableException(StatusCode statusCode) {
    super(statusCode.getMessage());
    this.code = statusCode.getCode();
  }

  public NotRetryableException(StatusCode statusCode, String message) {
    super(message);
    this.code = statusCode.getCode();
  }
}
