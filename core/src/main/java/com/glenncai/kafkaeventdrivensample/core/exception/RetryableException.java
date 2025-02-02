package com.glenncai.kafkaeventdrivensample.core.exception;

import lombok.Getter;

@Getter
public class RetryableException extends RuntimeException {

  private final int code;

  public RetryableException(int code, String message) {
    super(message);
    this.code = code;
  }

  public RetryableException(StatusCode statusCode) {
    super(statusCode.getMessage());
    this.code = statusCode.getCode();
  }

  public RetryableException(StatusCode statusCode, String message) {
    super(message);
    this.code = statusCode.getCode();
  }
}
