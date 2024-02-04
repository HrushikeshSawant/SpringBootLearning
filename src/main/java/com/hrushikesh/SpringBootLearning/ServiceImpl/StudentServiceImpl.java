package com.hrushikesh.SpringBootLearning.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrushikesh.SpringBootLearning.Entity.Student;
import com.hrushikesh.SpringBootLearning.Exception.ResourseNotFoundException;
import com.hrushikesh.SpringBootLearning.Repository.StudentRepo;
import com.hrushikesh.SpringBootLearning.Service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepo studentRepo;
	
	@Override
	public Student addStudent(Student student) {
		
		return studentRepo.save(student);
	}

	@Override
	public List<Student> addStudents(List<Student> students) {
		
		return studentRepo.saveAll(students);
	}

	@Override
	public Student getStudent(int studentId) {

		return studentRepo.findById(studentId).orElseThrow(() -> new ResourseNotFoundException("Student", "Id", studentId));
	}

	@Override
	public List<Student> getAllStudent() {
		
		List<Student> students = studentRepo.findAll();
				
		return students;
	}

	@Override
	public List<Student> getStudentByName(String studentName) {

		List<Student> students = studentRepo.findByNameOrderByIdDesc(studentName);
		
		if(students.isEmpty())
			throw new ResourseNotFoundException("Student", "name", studentName );
		
		return students;
	}

	@Override
	public List<Student> getStudentByNameSurname(String studentName, String studentSurname) {

		List<Student> students = studentRepo.findByNameAndSurname(studentName, studentSurname);
		
		if(students.isEmpty())
			throw new ResourseNotFoundException("Student", "name, surname", studentName + " " + studentSurname );
		
		return students;
	}

	@Override
	public List<Student> findStudentBySurname(String studentSurname) {

		List<Student> students = studentRepo.findStudentBySurname(studentSurname);
		
		if(students.isEmpty())
			throw new ResourseNotFoundException("Student", "surname", studentSurname );
		
		return students;
	}
	
}
