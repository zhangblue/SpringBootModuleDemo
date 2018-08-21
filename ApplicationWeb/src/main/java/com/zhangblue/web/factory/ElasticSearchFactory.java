package com.zhangblue.web.factory;

import com.zhangblue.elasticsearch.repository.ElasticSearchRepository;
import com.zhangblue.web.factory.config.ElasticSearchConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ElasticSearchConfig.class)
@ConditionalOnClass({ElasticSearchRepository.class, ElasticSearchConfig.class})
public class ElasticSearchFactory {

  private final ElasticSearchConfig elasticSearchConfig;

  public ElasticSearchFactory(ElasticSearchConfig elasticSearchConfig) {
    this.elasticSearchConfig = elasticSearchConfig;
  }

  @Bean
  @ConditionalOnMissingBean(ElasticSearchRepository.class)
  public ElasticSearchRepository elasticSearchRepository() throws Exception {
    ElasticSearchRepository elasticSearchRepository = new ElasticSearchRepository();
    elasticSearchRepository.setClientHosts(elasticSearchConfig.getTransportHostNames());
    elasticSearchRepository.setClusterName(elasticSearchConfig.getClusterName());
    elasticSearchRepository.setPort(elasticSearchConfig.getPort());
    elasticSearchRepository.setStrType(elasticSearchConfig.getType());
    elasticSearchRepository.setBulkActions(elasticSearchConfig.getBulkActions());
    elasticSearchRepository.setBulkByteSizeValueAsMB(elasticSearchConfig.getBulkByteSizeValueAsMB());
    elasticSearchRepository.setBulkConcurrentRequests(elasticSearchConfig.getBulkConcurrentRequests());
    elasticSearchRepository.setBulkFlushSeconds(elasticSearchConfig.getBulkFlushSeconds());

    elasticSearchRepository.buildClient();
    elasticSearchRepository.buildBulkProcessor();

    return elasticSearchRepository;
  }
}
