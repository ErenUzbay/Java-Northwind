package com.northwind.northwind.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.northwind.northwind.core.entites.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
