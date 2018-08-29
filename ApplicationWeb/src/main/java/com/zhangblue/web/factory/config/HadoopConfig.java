package com.zhangblue.web.factory.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.zhangblue.hadoop")
public class HadoopConfig {

  private String coreSite;
  private String hdfsSite;
  private String hbaseSite;

  public String getHbaseSite() {
    return hbaseSite;
  }

  public void setHbaseSite(String hbaseSite) {
    this.hbaseSite = hbaseSite;
  }

  public String getCoreSite() {
    return coreSite;
  }

  public void setCoreSite(String coreSite) {
    this.coreSite = coreSite;
  }

  public String getHdfsSite() {
    return hdfsSite;
  }

  public void setHdfsSite(String hdfsSite) {
    this.hdfsSite = hdfsSite;
  }
}
