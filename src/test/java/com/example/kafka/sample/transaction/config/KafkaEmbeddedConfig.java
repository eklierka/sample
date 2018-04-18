package com.example.kafka.sample.transaction.config;

import static com.example.kafka.sample.transaction.listener.TestKafkaListener.INPUT_TEST_TOPIC;
import static com.example.kafka.sample.transaction.listener.TestKafkaListener.OUTPUT_TEST_TOPIC;

import kafka.server.KafkaConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.test.rule.KafkaEmbedded;

@Configuration
public class KafkaEmbeddedConfig {

  @Bean
  public KafkaEmbedded kafkaEmbedded() {
    KafkaEmbedded kafkaEmbedded = new KafkaEmbedded(1, false, 1,
        INPUT_TEST_TOPIC, OUTPUT_TEST_TOPIC);
    kafkaEmbedded.setKafkaPorts(9092);
    kafkaEmbedded.brokerProperty(KafkaConfig.TransactionsTopicReplicationFactorProp(), "1");
    kafkaEmbedded.brokerProperty(KafkaConfig.TransactionsTopicMinISRProp(), "1");
    return kafkaEmbedded;
  }
}
