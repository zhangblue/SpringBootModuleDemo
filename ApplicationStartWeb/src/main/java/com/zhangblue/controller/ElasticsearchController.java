package com.zhangblue.controller;

import com.zhangblue.elasticsearch.repository.ElasticSearchRepository;
import com.zhangblue.kafka.repository.KafkaRepository;
import java.util.Properties;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElasticsearchController {

  @Autowired
  private ElasticSearchRepository elasticSearchRepository;
  @Autowired
  private KafkaRepository kafkaRepository;

  @RequestMapping("/es_index")
  public String getAllIndex() {
    String result = elasticSearchRepository.clustorAdministration();
    return result;
  }

  @RequestMapping("/kafa_consumer")
  public String getKafkaConsumer() {
    Properties propsConsumer = kafkaRepository.getPropsConsumer("test-zhangd", "earliest");
    KafkaConsumer<String, String> consumer = kafkaRepository.createConsumer(propsConsumer, new String[]{""});

    int size = kafkaRepository.readKafka(consumer, 1000).count();

    return "" + size;
  }


}
