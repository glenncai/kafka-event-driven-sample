package com.glenncai.kafkaeventdrivensample.product.dto.response;

import com.glenncai.kafkaeventdrivensample.product.exception.StatusCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BaseResponse<T> {

  private final int code;
  private final T data;
  private final String message;

  private BaseResponse(int code, T data, String message) {
    this.code = code;
    this.data = data;
    this.message = message;
  }

  public static <T> BaseResponse<T> success(T data) {
    return new BaseResponse<>(StatusCode.SUCCESS.getCode(), data, StatusCode.SUCCESS.getMessage());
  }

  public static <T> BaseResponse<T> error(int code, String message) {
    return new BaseResponse<>(code, null, message);
  }

  public static <T> BaseResponse<T> error(StatusCode statusCode) {
    return new BaseResponse<>(statusCode.getCode(), null, statusCode.getMessage());
  }
}
