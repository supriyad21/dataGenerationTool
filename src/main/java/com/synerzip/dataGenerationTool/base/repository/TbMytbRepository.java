package com.synerzip.dataGenerationTool.base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synerzip.dataGenerationTool.base.model.TbMytb;




public interface TbMytbRepository   extends JpaRepository<TbMytb, Long> {
	
	
	List<TbMytb> findByName(String name);
	 // List<Tutorial> findByTitleContaining(String title);
	 // List<Tutorial>  findByDescriptionContaining(String description);

}
