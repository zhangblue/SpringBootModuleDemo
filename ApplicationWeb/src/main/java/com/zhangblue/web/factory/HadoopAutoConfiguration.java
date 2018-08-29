package com.zhangblue.web.factory;


import com.zhangblue.hadoop.repository.HadoopRepository;
import com.zhangblue.web.factory.config.HadoopConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnClass({HadoopRepository.class, HadoopConfig.class})
@EnableConfigurationProperties(HadoopConfig.class)
public class HadoopAutoConfiguration {

  private HadoopConfig hadoopConfig;

  public HadoopAutoConfiguration(HadoopConfig hadoopConfig) {
    this.hadoopConfig = hadoopConfig;
  }

  @Bean
  @ConditionalOnProperty(prefix = "com.zhangblue.hadoop", name = {"hbase-site", "core-site", "hdfs-site"}, matchIfMissing = false)
  public HadoopRepository getHadoopRepository() {
    System.out.println("---------------------");
    System.out.println(hadoopConfig.getCoreSite());
    System.out.println(hadoopConfig.getHbaseSite());
    System.out.println(hadoopConfig.getHdfsSite());
    System.out.println("---------------------");


    HadoopRepository hadoopRepository = new HadoopRepository();
    hadoopRepository.initHDFSConfiguration(hadoopConfig.getCoreSite(), hadoopConfig.getHdfsSite(), hadoopConfig.getHbaseSite());
    return hadoopRepository;
  }
}
