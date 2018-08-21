package com.zhangblue.jpa.DaoImpl;

import com.zhangblue.jpa.entity.User;
import com.zhangblue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseCache {

  @Autowired
  UserRepository userRepository;

  @Cacheable(cacheNames = {"emp"}, key = "#id")
  public User getUserById(Integer id) {
    System.out.println("查询" + id + "号员工");
    return userRepository.findOne(id);
  }


  @CachePut(cacheNames = {"emp"}, key = "#result.id")
  public User updateUser(User user) {
    return userRepository.save(user);
  }

  @CacheEvict(cacheNames = {"emp"}, key = "#id")
  public void deleteUser(Integer id) {
    userRepository.delete(id);
  }

  @CachePut(cacheNames = {"emp"}, key = "#result.id")
  public User insertUser(User user) {
    return userRepository.save(user);
  }

}
