package com.hrushikesh.SpringBootLearning.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrushikesh.SpringBootLearning.Entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{

	List<Student> findByName(String studentName);
	
	List<Student> findByNameOrderByIdDesc(String studentName);	//QUERY DSL
	
	List<Student> findByNameAndSurname(String studentName, String studentSurname);	//QUERY DSL
	
//	@Query("FROM Student WHERE surname = :surname")
//	List<Student> findStudentBySurname(String surname);
	
	@Query("FROM Student WHERE surname = :studentSurname")
	List<Student> findStudentBySurname(@Param("studentSurname") String surname);

}
