package com.spring.dao;

import com.spring.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<Users, String> {

    Users findById( Integer id);

}
