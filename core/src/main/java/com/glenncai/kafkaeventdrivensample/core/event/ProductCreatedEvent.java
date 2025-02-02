package com.glenncai.kafkaeventdrivensample.core.event;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class ProductCreatedEvent extends BaseEvent {

  private String productId;
  private String title;
  private BigDecimal price;
  private Integer quantity;
}
