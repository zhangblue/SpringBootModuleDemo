package com.zhangblue.elasticsearch.bean;

public class IndexStatus {

  private String indexName;
  private int shards;
  private int replicas;
  private String status;

  public IndexStatus(String indexName, int shards, int replicas, String status) {
    this.indexName = indexName;
    this.shards = shards;
    this.replicas = replicas;
    this.status = status;
  }

  public String getIndexName() {
    return indexName;
  }

  public int getShards() {
    return shards;
  }

  public int getReplicas() {
    return replicas;
  }

  public String getStatus() {
    return status;
  }
}
