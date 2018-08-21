package com.zhangblue.starter;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.zhangblue.kafka")
public class KafkaProperties {

  private List<String> brokers;
  private String offsetReset;

  public List<String> getBrokers() {
    return brokers;
  }

  public void setBrokers(List<String> brokers) {
    this.brokers = brokers;
  }

  public String getOffsetReset() {
    return offsetReset;
  }

  public void setOffsetReset(String offsetReset) {
    this.offsetReset = offsetReset;
  }
}
