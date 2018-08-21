package com.zhangblue.jpa.DaoImpl;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyCacheConfig {

  @Bean("mykeyGenerator")
  public KeyGenerator keyGenerator() {
    return new KeyGenerator() {
      @Override
      public Object generate(Object o, Method method, Object... objects) {
        return method.getName() + "[" + Arrays.asList(objects).toString() + "]";
      }
    };
  }
}
