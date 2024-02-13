package com.hrushikesh.SpringBootLearning.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrushikesh.SpringBootLearning.Entity.DatabaseFiles;

public interface DatabaseFilesRepo extends JpaRepository<DatabaseFiles, Integer>{

	List<DatabaseFiles> findByoriginalFileName(String fileName);

}
