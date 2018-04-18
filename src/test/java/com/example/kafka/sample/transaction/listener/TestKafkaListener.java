package com.example.kafka.sample.transaction.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TestKafkaListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(TestKafkaListener.class);

  public static final String INPUT_TEST_TOPIC = "transaction-sample-topic-in";
  public static final String OUTPUT_TEST_TOPIC = "transaction-sample-topic-out";

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @KafkaListener(topics = INPUT_TEST_TOPIC)
  @Transactional("kafkaTransactionManager")
  public void listen(ConsumerRecord<String, String> record) {
    LOGGER.info("Received Kafka record from {}: {}", INPUT_TEST_TOPIC, record);
    kafkaTemplate.send(OUTPUT_TEST_TOPIC, record.key(), record.value());
    LOGGER.info("Forwarded Kafka record to {}: {}", OUTPUT_TEST_TOPIC, record);
    stubMethod(record.value());
  }

  private void stubMethod(String data) {
    if (data.equals("invalid_data")) {
      throw new RuntimeException("Invalid data");
    }
  }
}
