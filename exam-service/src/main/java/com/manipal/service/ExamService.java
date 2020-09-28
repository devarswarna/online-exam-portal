package com.manipal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manipal.model.Exam;
import com.manipal.repository.IExamRepository;

@Service
public class ExamService implements IExamService{

	@Autowired
	IExamRepository repository;
	
	@Override
	public void addExam(Exam exam) {
		repository.save(exam);
	}

	@Override
	public List<Exam> showAllExam() {
		return repository.findAll();
	}

	@Override
	public void deleteExam(int qBankId) {
		repository.deleteById(qBankId);
	}

	@Override
	public Exam getExamById(int qBankId) {
		return repository.findById(qBankId).orElse(null);
	}

}
