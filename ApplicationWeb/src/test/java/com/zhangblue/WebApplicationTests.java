package com.zhangblue;

import com.zhangblue.elasticsearch.repository.ElasticSearchRepository;
import com.zhangblue.hadoop.repository.HadoopRepository;
import com.zhangblue.web.WebApplication;
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
  @Autowired
  HadoopRepository hadoopRepository;

  @Test
  public void testElasticSearch() {
    System.out.println(elasticSearchRepository.clustorAdministration());
  }


  @Test
  public void testHadoop() {
    System.out.println(hadoopRepository.getConfiguration().get("fs.defaultFS"));
  }
}
