package com.glenncai.kafkaeventdrivensample.product.event;

import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ProductCreatedEvent extends BaseEvent {

  private String productId;
  private String title;
  private BigDecimal price;
  private Integer quantity;
}
