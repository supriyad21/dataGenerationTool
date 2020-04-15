package com.synerzip.dataGenerationTool.base.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.synerzip.dataGenerationTool.base.model.TbCountry;


public interface CountryRepository extends JpaRepository<TbCountry,Long>{
		List<TbCountry> findByCountry(String country);
		
		@Query("select c from TbCountry c where c.country like %?1")
		List<TbCountry> findByNameEndsWith(String country);

}
