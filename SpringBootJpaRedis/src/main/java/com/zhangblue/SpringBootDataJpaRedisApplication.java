package com.zhangblue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootDataJpaRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaRedisApplication.class, args);
	}
}
