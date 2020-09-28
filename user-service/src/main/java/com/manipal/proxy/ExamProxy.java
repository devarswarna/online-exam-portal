package com.manipal.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.manipal.model.Exam;


@FeignClient(name="exam-service", url="localhost:8500")
public interface ExamProxy {

	@GetMapping("exam-service/getbyid/{qBankId}")
	public Exam getExam(@PathVariable int qBankId);
	
	@GetMapping("exam-service/showexam")
	public List<Exam> showAllExam();
}

