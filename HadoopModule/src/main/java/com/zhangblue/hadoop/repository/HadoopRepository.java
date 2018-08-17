package com.zhangblue.hadoop.repository;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

public class HadoopRepository {

  private Configuration configuration;

  public void initHDFSConfiguration(String coreSite, String hdfsSite) {
    configuration = new Configuration();
    configuration.addResource(new Path(coreSite));
    configuration.addResource(new Path(hdfsSite));
  }


}
