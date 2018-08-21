package com.zhangblue.starter;

import com.zhangblue.elasticsearch.repository.ElasticSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ElasticSearchProperties.class)
public class ElasticSearchServiceAutoConfiguration {

  @Autowired
  ElasticSearchProperties elasticSearchProperties;

  @Bean
  public ElasticSearchRepository getElasticSearchRepository() throws Exception {

    ElasticSearchRepository elasticSearchRepository = new ElasticSearchRepository();
    elasticSearchRepository.setClientHosts(elasticSearchProperties.getClientHosts());
    elasticSearchRepository.setClusterName(elasticSearchProperties.getClusterName());
    elasticSearchRepository.setPort(elasticSearchProperties.getPort());
    elasticSearchRepository.setStrType(elasticSearchProperties.getType());
    elasticSearchRepository.setBulkActions(elasticSearchProperties.getBulkActions());
    elasticSearchRepository.setBulkByteSizeValueAsMB(elasticSearchProperties.getBulkByteSizeValueAsMB());
    elasticSearchRepository.setBulkConcurrentRequests(elasticSearchProperties.getBulkConcurrentRequests());
    elasticSearchRepository.setBulkFlushSeconds(elasticSearchProperties.getBulkFlushSeconds());

    elasticSearchRepository.buildClient();

    return elasticSearchRepository;
  }


}
