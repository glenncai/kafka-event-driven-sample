package com.glenncai.kafkaeventdrivensample.core.exception;

import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

  private final int code;

  public BizException(int code, String message) {
    super(message);
    this.code = code;
  }

  public BizException(StatusCode statusCode) {
    super(statusCode.getMessage());
    this.code = statusCode.getCode();
  }

  public BizException(StatusCode statusCode, String message) {
    super(message);
    this.code = statusCode.getCode();
  }
}
