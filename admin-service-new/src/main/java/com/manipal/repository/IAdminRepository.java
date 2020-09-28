package com.manipal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manipal.model.Admin;

public interface IAdminRepository extends JpaRepository<Admin, Integer> {

}
