package com.manipal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manipal.model.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

}
