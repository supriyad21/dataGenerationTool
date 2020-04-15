package com.synerzip.dataGenerationTool.base.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.synerzip.dataGenerationTool.base.model.Tutorial;



public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
  List<Tutorial> findByPublished(boolean published);
  List<Tutorial> findByTitleContaining(String title);
  List<Tutorial>  findByDescriptionContaining(String description);
  
}