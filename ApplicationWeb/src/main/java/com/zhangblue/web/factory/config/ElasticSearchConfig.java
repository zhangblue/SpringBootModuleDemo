package com.zhangblue.web.factory.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.zhangblue.elasticsearch")
public class ElasticSearchConfig {

  private String clusterName;
  private List<String> transportHostNames;
  private String type;
  private int port;

  private int bulkActions;
  private int bulkByteSizeValueAsMB;
  private int bulkFlushSeconds;
  private int bulkConcurrentRequests;


  public String getClusterName() {
    return clusterName;
  }

  public void setClusterName(String clusterName) {
    this.clusterName = clusterName;
  }

  public List<String> getTransportHostNames() {
    return transportHostNames;
  }

  public void setTransportHostNames(List<String> transportHostNames) {
    this.transportHostNames = transportHostNames;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public int getBulkActions() {
    return bulkActions;
  }

  public void setBulkActions(int bulkActions) {
    this.bulkActions = bulkActions;
  }

  public int getBulkByteSizeValueAsMB() {
    return bulkByteSizeValueAsMB;
  }

  public void setBulkByteSizeValueAsMB(int bulkByteSizeValueAsMB) {
    this.bulkByteSizeValueAsMB = bulkByteSizeValueAsMB;
  }

  public int getBulkFlushSeconds() {
    return bulkFlushSeconds;
  }

  public void setBulkFlushSeconds(int bulkFlushSeconds) {
    this.bulkFlushSeconds = bulkFlushSeconds;
  }

  public int getBulkConcurrentRequests() {
    return bulkConcurrentRequests;
  }

  public void setBulkConcurrentRequests(int bulkConcurrentRequests) {
    this.bulkConcurrentRequests = bulkConcurrentRequests;
  }
}
