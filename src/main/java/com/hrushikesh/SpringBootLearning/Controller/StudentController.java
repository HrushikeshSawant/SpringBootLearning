package com.hrushikesh.SpringBootLearning.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hrushikesh.SpringBootLearning.Entity.Student;
import com.hrushikesh.SpringBootLearning.ServiceImpl.StudentServiceImpl;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	StudentServiceImpl studentServiceImpl;
	
	private final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@RequestMapping("add-student")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Student addStudent(@RequestBody Student student)
	{
		logger.info("Student Object {}", student.toString());
		return studentServiceImpl.addStudent(student);
	}
	
	@RequestMapping(path="add-student-xml", consumes = "application/xml")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Student addStudentXml(@RequestBody Student student)
	{
		logger.info("Student Object {}", student.toString());
		return studentServiceImpl.addStudent(student);
	}
	
	@RequestMapping("add-students")
	@ResponseStatus(value = HttpStatus.CREATED)
	public List<Student> addStudents(@RequestBody List<Student> students)
	{
		return studentServiceImpl.addStudents(students);
	}
	
	@GetMapping("get-student/{studentId}")
	public ResponseEntity<Student> getStudent(@PathVariable("studentId") int studentId){
		
		return new ResponseEntity<Student>(studentServiceImpl.getStudent(studentId), HttpStatus.OK);
	}
	
	@GetMapping(path="get-students", produces = "application/json")
	public ResponseEntity<List<Student>> getAllStudent(){
		
		return new ResponseEntity<List<Student>>(studentServiceImpl.getAllStudent(), HttpStatus.OK);
	}
	
	@GetMapping("get-student-by-name/{studentName}")
	public ResponseEntity<List<Student>> getStudentByName(@PathVariable("studentName") String studentName){
		
		return new ResponseEntity<List<Student>>(studentServiceImpl.getStudentByName(studentName), HttpStatus.OK);
	}
	
	@GetMapping("get-student-by-name-surname")
	public ResponseEntity<List<Student>> getStudentByNameSurname(@RequestParam("studentName") String studentName, @RequestParam("studentSurname") String studentSurname){
		
		return new ResponseEntity<List<Student>>(studentServiceImpl.getStudentByNameSurname(studentName, studentSurname), HttpStatus.OK);
	}
	
	@GetMapping("get-student-by-surname/{studentSurname}")
	public ResponseEntity<List<Student>> findStudentBySurname(@PathVariable("studentSurname") String studentSurname){
		
		return new ResponseEntity<List<Student>>(studentServiceImpl.findStudentBySurname(studentSurname), HttpStatus.OK);
	}
}
