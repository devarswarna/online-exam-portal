package com.manipal.service;

import java.util.List;

import com.manipal.model.Exam;

public interface IExamService {
	
	void addExam(Exam exam);
	
	List<Exam> showAllExam();
	
	void deleteExam(int qBankId);
	
	Exam getExamById(int qBankId);

}
