package com.hrushikesh.SpringBootLearning.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hrushikesh.SpringBootLearning.Entity.Student;
import com.hrushikesh.SpringBootLearning.Exception.ResourseNotFoundException;
import com.hrushikesh.SpringBootLearning.Repository.StudentRepo;

@Service
public interface StudentService {

	Student addStudent(Student student);

	List<Student> addStudents(List<Student> students);

	Student getStudent(int studentId);

	List<Student> getAllStudent();

	List<Student> getStudentByName(String studentName);

	List<Student> getStudentByNameSurname(String studentName, String studentSurname);

	List<Student> findStudentBySurname(String studentSurname);

}
