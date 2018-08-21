package com.zhangblue.repository;

import com.zhangblue.jpa.entity.User;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//继承JpaRepository 来完成对数据库的操作
public interface UserRepository extends JpaRepository<User, Integer> {

  @Transactional
  @Query(value = "SELECT * FROM tbl_user WHERE last_name=:lastName",nativeQuery = true)
  public User findUserByLastName(String lastName);

}
