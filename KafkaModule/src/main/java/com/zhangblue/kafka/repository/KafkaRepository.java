package com.zhangblue.kafka.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaRepository {


  private String brokerLine;

  public KafkaRepository(String brokerLine) {
    this.brokerLine = brokerLine;
  }

  public void setBrokerLine(String brokerLine) {
    this.brokerLine = brokerLine;
  }

  /**
   * 初始化consumer的Properties信息
   */
  public Properties getPropsConsumer(String strGroupId, String offsetReset) {
    Properties propsConsumer = new Properties();
    propsConsumer
        .put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerLine);
    propsConsumer.put(ConsumerConfig.GROUP_ID_CONFIG, strGroupId);
    propsConsumer.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
    propsConsumer.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
    propsConsumer.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
    propsConsumer.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    propsConsumer.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    propsConsumer.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, offsetReset);//latest, earliest, none
    propsConsumer.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, 2097152);
    return propsConsumer;
  }

  /**
   * 创建KafkaConsumer
   *
   * @param properties 配置参数
   * @param topics topic列表
   */
  public KafkaConsumer<String, String> createConsumer(Properties properties, List<String> topics) {
    KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
    kafkaConsumer.subscribe(topics);
    return kafkaConsumer;
  }

  /**
   * 创建KafkaConsumer
   *
   * @param properties 配置参数
   * @param topics topic列表
   */
  public KafkaConsumer<String, String> createConsumer(Properties properties, String[] topics) {
    KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
    kafkaConsumer.subscribe(Arrays.asList(topics));
    return kafkaConsumer;
  }

  /**
   * 读取kafka数据，返回数据集
   *
   * @param kafkaConsumer consumer连接
   * @param pollTime 等待事件
   */
  public ConsumerRecords<String, String> readKafka(KafkaConsumer<String, String> kafkaConsumer, long pollTime) {
    ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(pollTime);
    return consumerRecords;
  }


  /**
   * 关闭kafkaConsumer连接
   *
   * @param kafkaConsumer 连接
   */
  public void closeConsumer(KafkaConsumer<String, String> kafkaConsumer) {
    kafkaConsumer.close();
  }
}
