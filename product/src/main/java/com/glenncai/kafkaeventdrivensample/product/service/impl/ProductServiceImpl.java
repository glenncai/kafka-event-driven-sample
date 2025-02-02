package com.glenncai.kafkaeventdrivensample.product.service.impl;

import static com.glenncai.kafkaeventdrivensample.product.config.KafkaConfig.PRODUCT_CREATED_EVENTS_TOPIC;

import com.glenncai.kafkaeventdrivensample.product.dto.request.CreateProductRequest;
import com.glenncai.kafkaeventdrivensample.product.entity.Product;
import com.glenncai.kafkaeventdrivensample.product.event.ProductCreatedEvent;
import com.glenncai.kafkaeventdrivensample.product.exception.BizException;
import com.glenncai.kafkaeventdrivensample.product.exception.StatusCode;
import com.glenncai.kafkaeventdrivensample.product.repository.ProductRepository;
import com.glenncai.kafkaeventdrivensample.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

  @Override
  public String createProduct(CreateProductRequest request) {
    log.info("Creating product: {}", request);
    Product product = new Product();
    product.setTitle(request.getTitle());
    product.setPrice(request.getPrice());
    product.setQuantity(request.getQuantity());
    Product createdProduct = productRepository.save(product);
    log.info("Product created: {}", product);

    ProductCreatedEvent event = ProductCreatedEvent.builder()
                                                   .productId(createdProduct.getId())
                                                   .title(createdProduct.getTitle())
                                                   .price(createdProduct.getPrice())
                                                   .quantity(createdProduct.getQuantity())
                                                   .build();

    SendResult<String, ProductCreatedEvent> result;
    try {
      result = kafkaTemplate.send(PRODUCT_CREATED_EVENTS_TOPIC, createdProduct.getId(), event).get();
      log.info("Product created event sent: {}", result);
    } catch (Exception e) {
      throw new BizException(StatusCode.KAFKA_INTERNAL_ERROR, "Failed to send product created event");
    }

    return createdProduct.getId();
  }
}
