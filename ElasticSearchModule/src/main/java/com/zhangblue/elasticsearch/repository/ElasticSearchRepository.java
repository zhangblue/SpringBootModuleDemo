package com.zhangblue.elasticsearch.repository;

import com.alibaba.fastjson.JSON;
import com.zhangblue.elasticsearch.bean.ClusterStatus;
import com.zhangblue.elasticsearch.bean.IndexStatus;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.health.ClusterIndexHealth;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ElasticSearchRepository {

  private TransportClient client = null;
  private BulkProcessor bulkProcessor = null;

  //-----集群参数-------
  private String strType;//写死的值，后续会废弃
  private String clusterName;
  private List<String> clientHosts;
  private int port;

  //-----bulk参数------
  private int bulkActions;
  private int bulkByteSizeValueAsMB;
  private int bulkFlushSeconds;
  private int bulkConcurrentRequests;


  @Override
  public String toString() {
    return "ElasticSearchRepository{" +
        "  strType='" + strType + '\'' +
        ", clusterName='" + clusterName + '\'' +
        ", clientHosts=" + clientHosts +
        ", port=" + port +
        ", bulkActions=" + bulkActions +
        ", bulkByteSizeValueAsMB=" + bulkByteSizeValueAsMB +
        ", bulkFlushSeconds=" + bulkFlushSeconds +
        ", bulkConcurrentRequests=" + bulkConcurrentRequests +
        '}';
  }

  /**
   * build es client.
   */
  public void buildClient() throws Exception {
    if (client != null) {
      client.close();
    }
    Settings settings = Settings.builder()
        .put("cluster.name", clusterName)
        .put("client.transport.sniff", false)//5.4.0版本设置为false，不然会告警，不知道为啥
        .build();
    client = new PreBuiltTransportClient(settings);

    for (String strTransportHostName : clientHosts) {
      client.addTransportAddress(
          new TransportAddress(InetAddress.getByName(strTransportHostName), port));
    }
  }

  /**
   * build bulk processor.
   */
  public void buildBulkProcessor() {
    if (bulkProcessor != null) {
      bulkProcessor.close();
    }
    bulkProcessor = BulkProcessor.builder(client, new BulkProcessor.Listener() {
      @Override
      public void beforeBulk(long executionId, BulkRequest request) {
      }

      @Override
      public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
      }

      @Override
      public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
      }
    }).setBulkActions(bulkActions)
        .setBulkSize(new ByteSizeValue(bulkByteSizeValueAsMB, ByteSizeUnit.MB))
        .setFlushInterval(TimeValue.timeValueSeconds(bulkFlushSeconds))
        .setConcurrentRequests(bulkConcurrentRequests)
        .setBackoffPolicy(
            BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
        .build();
  }

  public String clustorAdministration() {
    ClusterHealthResponse healths = client.admin().cluster().prepareHealth().get();
    String clusterName = healths.getClusterName();
    int dataNodeNum = healths.getNumberOfDataNodes();//获得集群数据节点个数
    int nodeNum = healths.getNumberOfNodes();//获取集群所有节点个数

    List<IndexStatus> listIndex = new ArrayList<>();
    for (ClusterIndexHealth health : healths.getIndices().values()) {
      IndexStatus indexStatus = new IndexStatus(health.getIndex(), health.getNumberOfShards(), health.getNumberOfReplicas(), health.getStatus().name());
      listIndex.add(indexStatus);
    }

    ClusterStatus clusterStatus = new ClusterStatus(clusterName, dataNodeNum, nodeNum, listIndex);
    return JSON.toJSONString(clusterStatus);
  }




  public TransportClient getClient() {
    return client;
  }

  public BulkProcessor getBulkProcessor() {
    return bulkProcessor;
  }

  public String getStrType() {
    return strType;
  }

  public void setStrType(String strType) {
    this.strType = strType;
  }

  public String getClusterName() {
    return clusterName;
  }

  public void setClusterName(String clusterName) {
    this.clusterName = clusterName;
  }

  public List<String> getClientHosts() {
    return clientHosts;
  }

  public void setClientHosts(List<String> clientHosts) {
    this.clientHosts = clientHosts;
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
