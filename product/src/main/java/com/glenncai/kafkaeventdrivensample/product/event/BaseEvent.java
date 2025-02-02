package com.glenncai.kafkaeventdrivensample.product.event;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BaseEvent {

  @Builder.Default
  private String version = "1.0";

  @Builder.Default
  private LocalDateTime localDateTime = LocalDateTime.now();
}
