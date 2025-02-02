package com.glenncai.kafkaeventdrivensample.product.service;

import com.glenncai.kafkaeventdrivensample.product.dto.request.CreateProductRequest;

public interface ProductService {

  String createProduct(CreateProductRequest createProductRequest);
}
