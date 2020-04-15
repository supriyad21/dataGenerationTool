package com.synerzip.dataGenerationTool.base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synerzip.dataGenerationTool.base.model.TbCity;
import com.synerzip.dataGenerationTool.base.model.TbMytb;




@Repository
public interface CityRepository extends JpaRepository<TbCity, Long>  {

	List<TbMytb> findByCity(String city);
}
