package com.hrushikesh.SpringBootLearning.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hrushikesh.SpringBootLearning.Entity.Student;
import com.hrushikesh.SpringBootLearning.Exception.ResourseNotFoundException;
import com.hrushikesh.SpringBootLearning.Repository.StudentRepo;

@Service
public class StudentService {

	@Autowired
	StudentRepo studentRepo;
	
	public Student addStudent(Student student) {
		
		return studentRepo.save(student);
	}

	public List<Student> addStudents(List<Student> students) {
		
		return studentRepo.saveAll(students);
	}

	public Student getStudent(int studentId) {

		return studentRepo.findById(studentId).orElseThrow(() -> new ResourseNotFoundException("Student", "Id", studentId));
	}

	public List<Student> getAllStudent() {
		
		List<Student> students = studentRepo.findAll();
				
		return students;
	}

	public List<Student> getStudentByName(String studentName) {

		List<Student> students = studentRepo.findByNameOrderByIdDesc(studentName);
		
		if(students.isEmpty())
			throw new ResourseNotFoundException("Student", "name", studentName );
		
		return students;
	}

	public List<Student> getStudentByNameSurname(String studentName, String studentSurname) {

		List<Student> students = studentRepo.findByNameAndSurname(studentName, studentSurname);
		
		if(students.isEmpty())
			throw new ResourseNotFoundException("Student", "name, surname", studentName + " " + studentSurname );
		
		return students;
	}

	public List<Student> findStudentBySurname(String studentSurname) {

		List<Student> students = studentRepo.findStudentBySurname(studentSurname);
		
		if(students.isEmpty())
			throw new ResourseNotFoundException("Student", "surname", studentSurname );
		
		return students;
	}

}
