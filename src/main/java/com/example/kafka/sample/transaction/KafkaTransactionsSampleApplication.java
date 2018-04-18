package com.example.kafka.sample.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;

@SpringBootApplication
public class KafkaTransactionsSampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(KafkaTransactionsSampleApplication.class, args);
  }
}
