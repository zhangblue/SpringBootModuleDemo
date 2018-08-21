package com.zhangblue.hadoop.repository;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HadoopRepository {

  private Configuration configuration;

  /**
   * 初始化配置文件
   * @param coreSite
   * @param hdfsSite
   * @param hbaseSite
   */
  public void initHDFSConfiguration(String coreSite, String hdfsSite, String hbaseSite) {
    configuration = new Configuration();
    configuration.addResource(new Path(coreSite));
    configuration.addResource(new Path(hdfsSite));
    configuration.addResource(new Path(hbaseSite));
  }

  public Configuration getConfiguration() {
    return configuration;
  }

  /**
   * 获取fileSystem对象
   *
   * @return fileSystem
   */
  public FileSystem getFileSystem() throws IOException {
    FileSystem fileSystem = FileSystem.newInstance(configuration);
    return fileSystem;
  }
}
