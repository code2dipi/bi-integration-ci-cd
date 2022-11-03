package com.dipi.biintegration.repository;

import com.dipi.biintegration.model.UserDetail;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserDetail,Integer> {
    UserDetail findById(int id);
    List<UserDetail> findAllByName(String name);
    UserDetail findByName(String name);
}
