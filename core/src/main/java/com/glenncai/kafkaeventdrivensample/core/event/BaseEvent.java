package com.glenncai.kafkaeventdrivensample.core.event;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class BaseEvent {

  @Builder.Default
  private String version = "1.0";

  @Builder.Default
  private LocalDateTime localDateTime = LocalDateTime.now();
}
