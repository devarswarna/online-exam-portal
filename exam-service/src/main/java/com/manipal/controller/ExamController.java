package com.manipal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.manipal.model.Exam;
import com.manipal.service.IExamService;

@CrossOrigin("http://localhost:3000")
@RestController
public class ExamController {

	@Autowired
	IExamService examService;

	@PostMapping("exam-service/addexam")
	public String addExam(@RequestBody Exam exam) {
		examService.addExam(exam);
		return "Exam added successfully";
	}

	@GetMapping("exam-service/showexam")
	public List<Exam> showAllExam() {
		return examService.showAllExam();
	}

	@DeleteMapping("exam-service/deleteexam/{qBankId}")
	public String deleteExam(@PathVariable int qBankId) {
		Exam exam = examService.getExamById(qBankId);
		if (exam != null) {
			examService.deleteExam(qBankId);
			return "Deleted successfully";
		} else {
			return "No such Question Bank ID exists";
		}
	}
	
	@GetMapping("exam-service/getbyid/{qBankId}")
	public Exam getExam(@PathVariable int qBankId)
	{
		Exam exam = examService.getExamById(qBankId);
		return exam;
	}
	
	@PutMapping("exam-service/addexam/update")
	public String updateExam(@RequestBody Exam exam) {
		Exam dummy =examService.getExamById(exam.getqBankId());
		if (dummy != null) {
			dummy.setQ1(exam.getQ1());
			dummy.setOp11(exam.getOp11());
			dummy.setOp12(exam.getOp12());
			dummy.setOp13(exam.getOp13());
			dummy.setOp14(exam.getOp14());
			dummy.setA1(exam.getA1());

			dummy.setQ2(exam.getQ2());
			dummy.setOp21(exam.getOp21());
			dummy.setOp22(exam.getOp22());
			dummy.setOp23(exam.getOp23());
			dummy.setOp24(exam.getOp24());
			dummy.setA2(exam.getA2());
			
			dummy.setQ3(exam.getQ3());
			dummy.setOp31(exam.getOp31());
			dummy.setOp32(exam.getOp32());
			dummy.setOp33(exam.getOp33());
			dummy.setOp34(exam.getOp34());
			dummy.setA3(exam.getA3());
			
			dummy.setQ4(exam.getQ4());
			dummy.setOp41(exam.getOp41());
			dummy.setOp42(exam.getOp42());
			dummy.setOp43(exam.getOp43());
			dummy.setOp44(exam.getOp44());
			dummy.setA4(exam.getA4());
			
			dummy.setQ5(exam.getQ5());
			dummy.setOp51(exam.getOp51());
			dummy.setOp52(exam.getOp52());
			dummy.setOp53(exam.getOp53());
			dummy.setOp54(exam.getOp54());
			dummy.setA5(exam.getA5());
			examService.addExam(dummy);
			return "EXAM UPDATED";
		} else {
			return "EXAM does not exist";
		}
	}
}
