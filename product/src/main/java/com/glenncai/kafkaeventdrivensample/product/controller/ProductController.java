package com.glenncai.kafkaeventdrivensample.product.controller;

import com.glenncai.kafkaeventdrivensample.core.dto.response.BaseResponse;
import com.glenncai.kafkaeventdrivensample.product.dto.request.CreateProductRequest;
import com.glenncai.kafkaeventdrivensample.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public BaseResponse<String> createProduct(
      @RequestBody @Validated CreateProductRequest request) {
    return BaseResponse.success(productService.createProduct(request));
  }
}
