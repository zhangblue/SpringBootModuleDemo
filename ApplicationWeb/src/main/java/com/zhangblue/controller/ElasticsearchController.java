package com.zhangblue.controller;

import com.zhangblue.elasticsearch.repository.ElasticSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElasticsearchController {

  @Autowired
  private ElasticSearchRepository elasticSearchRepository;

  @RequestMapping("/index")
  public String getAllIndex() {

    String result = elasticSearchRepository.clustorAdministration();
    return result;
  }
}
