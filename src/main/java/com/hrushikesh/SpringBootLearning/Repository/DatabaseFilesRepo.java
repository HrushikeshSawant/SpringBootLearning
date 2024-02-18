package com.hrushikesh.SpringBootLearning.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrushikesh.SpringBootLearning.Entity.DatabaseFiles;


public interface DatabaseFilesRepo extends JpaRepository<DatabaseFiles, Integer>{

	Optional<DatabaseFiles> findByoriginalFileName(String fileName);
	
	Optional<DatabaseFiles> findByNewName(String fileName);

}
