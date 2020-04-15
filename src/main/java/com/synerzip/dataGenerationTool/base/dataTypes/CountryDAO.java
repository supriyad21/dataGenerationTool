package com.synerzip.dataGenerationTool.base.dataTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synerzip.dataGenerationTool.base.model.TbCountry;

import com.synerzip.dataGenerationTool.base.repository.CountryRepository;

@Component
public class CountryDAO {

	@Autowired
	CountryRepository tbCountryRepo;
	
	
	public CountryDAO() {
		super();
	}
	
	public List<Object>  generateCountryData(int rows) {
		
			
		List<TbCountry> tbcountry = tbCountryRepo.findAll();
		
	    Random random = new Random();
	    
	    List<Object> ls  = new ArrayList<>();
	    
	    for(int i = 0; i<rows; i++) {
	    int randomitr = random.nextInt(tbcountry.size());
	    String country  = tbcountry.get(randomitr).getCountry();
	    ls.add(country);
	    
	    }
	    
		return ls;
		
		
		
		
		
	}




	
	
}
