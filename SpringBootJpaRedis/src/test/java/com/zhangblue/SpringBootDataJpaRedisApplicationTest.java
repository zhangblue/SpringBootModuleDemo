package com.zhangblue;

import com.zhangblue.jpa.entity.User;
import com.zhangblue.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDataJpaRedisApplication.class)
public class SpringBootDataJpaRedisApplicationTest {

  @Autowired
  UserRepository userRepository;
  @Autowired
  StringRedisTemplate stringRedisTemplate; //操作redis k-v都是字符串的
  @Autowired
  RedisTemplate redisTemplate; //操作k-v都是对象的

  @Autowired
  RedisTemplate<Object, User> userRedisTemplate;

  @Test
  public void contentLoads(){
    User user = userRepository.findOne(1);
    System.out.println(user.toString());
  }

  /**
   * 测试保存字符串
   */
  @Test
  public void test01(){
    stringRedisTemplate.opsForValue().set("test1","value1");
  }

  /**
   * 测试保存对象
   */
  @Test
  public void test02(){
    User user = userRepository.findOne(1);
    userRedisTemplate.opsForValue().set("user01",user);
  }


}
