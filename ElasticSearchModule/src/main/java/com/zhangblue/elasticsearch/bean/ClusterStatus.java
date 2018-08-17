package com.zhangblue.elasticsearch.bean;

import java.util.List;

public class ClusterStatus {

  private String clusterName;
  private int dataNodeNum;
  private int nodeNum;
  private List<IndexStatus> indexStatusList;


  public ClusterStatus(String clusterName, int dataNodeNum, int nodeNum, List<IndexStatus> indexStatusList) {
    this.clusterName = clusterName;
    this.dataNodeNum = dataNodeNum;
    this.nodeNum = nodeNum;
    this.indexStatusList = indexStatusList;
  }

  public String getClusterName() {
    return clusterName;
  }

  public int getDataNodeNum() {
    return dataNodeNum;
  }

  public int getNodeNum() {
    return nodeNum;
  }

  public List<IndexStatus> getIndexStatusList() {
    return indexStatusList;
  }
}
