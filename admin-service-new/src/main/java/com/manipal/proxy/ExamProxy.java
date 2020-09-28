package com.manipal.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.manipal.model.Exam;

//@FeignClient(name="exam-service")
@FeignClient(name="exam-service", url="localhost:8500")
public interface ExamProxy {
	
	@GetMapping("exam-service/showexam")
	public List<Exam> showAllExam();


	@GetMapping("exam-service/getbyid/{qBankId}")
	public Exam getExamById(@PathVariable int qBankId);

	  @DeleteMapping("exam-service/deleteexam/{qBankId}")
	  public String deleteExam(@PathVariable int qBankId);


	@PutMapping("exam-service/addexam/update")
	public String updateExam(@RequestBody Exam exam);


	@PostMapping("exam-service/addexam")
	public String addExam(@RequestBody Exam exam);


	@GetMapping("exam-service/getbyid/{qBankId}")
	public Exam getExam(@PathVariable int qBankId);


	



}
