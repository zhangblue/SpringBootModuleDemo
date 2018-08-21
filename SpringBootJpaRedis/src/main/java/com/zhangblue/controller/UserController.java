package com.zhangblue.controller;

import com.zhangblue.jpa.DaoImpl.DatabaseCache;
import com.zhangblue.jpa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


  @Autowired
  DatabaseCache databaseCache;

  @GetMapping("/get_user/{id}")
  public User getUser(@PathVariable("id") Integer id) {
    return databaseCache.getUserById(id);
  }


  @GetMapping("/insert_user")
  public User insertUser(User user) {
    return databaseCache.insertUser(user);
  }
}
