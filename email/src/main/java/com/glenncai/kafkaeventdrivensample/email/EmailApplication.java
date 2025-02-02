package com.glenncai.kafkaeventdrivensample.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.glenncai.kafkaeventdrivensample.email")
public class EmailApplication {

  public static void main(String[] args) {
    SpringApplication.run(EmailApplication.class, args);
  }
}
