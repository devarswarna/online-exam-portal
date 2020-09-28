package com.manipal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manipal.model.Exam;

@Repository
public interface IExamRepository extends JpaRepository<Exam, Integer>{

}
