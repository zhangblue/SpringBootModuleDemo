package com.zhangblue.starter;

import com.google.common.base.Joiner;
import com.zhangblue.kafka.repository.KafkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaServiceAutoConfiguration {

  @Autowired
  KafkaProperties kafkaProperties;

  @Bean
  public KafkaRepository getElasticSearchRepository() throws Exception {
    String brokerLine = Joiner.on(",").join(kafkaProperties.getBrokers());
    KafkaRepository kafkaRepository = new KafkaRepository(brokerLine);
    return kafkaRepository;
  }
}
