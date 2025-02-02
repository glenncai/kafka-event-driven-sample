package com.glenncai.kafkaeventdrivensample.email.exception;

import com.glenncai.kafkaeventdrivensample.core.dto.response.BaseResponse;
import com.glenncai.kafkaeventdrivensample.core.exception.BizException;
import com.glenncai.kafkaeventdrivensample.core.exception.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BizException.class)
  public BaseResponse<Void> handleBizException(BizException e) {
    log.error("BizException: {}", e.getMessage());
    return BaseResponse.error(e.getCode(), e.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public BaseResponse<Void> handleException(Exception e) {
    log.error("Exception: {}", e.getMessage());
    return BaseResponse.error(StatusCode.INTERNAL_SERVER_ERROR.getCode(), StatusCode.INTERNAL_SERVER_ERROR.getMessage());
  }
}
