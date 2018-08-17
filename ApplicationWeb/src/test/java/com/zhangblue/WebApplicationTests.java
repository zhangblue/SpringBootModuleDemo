package com.zhangblue;

import com.zhangblue.elasticsearch.repository.ElasticSearchRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class WebApplicationTests {

  @Autowired
  ElasticSearchRepository elasticSearchRepository;

  @Test
  public void test(){
    System.out.println(elasticSearchRepository.clustorAdministration());
  }

}