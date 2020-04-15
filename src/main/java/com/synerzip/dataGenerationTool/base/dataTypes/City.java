package com.synerzip.dataGenerationTool.base.dataTypes;

import java.util.ArrayList;

import java.util.List;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synerzip.dataGenerationTool.base.model.TbCity;

import com.synerzip.dataGenerationTool.base.repository.CityRepository;


/**
 * @author supriyad
 *@since April 2020
 */
@Component
public class City {
	
	String cityNames;

	@Autowired
	CityRepository tbCityRepo;
	public String getCityNames() {
		return cityNames;
	}

	public void setCityNames(String cityNames) {
		this.cityNames = cityNames;
	}

	public City()
	{
		
	}
	public City(String cityNames) {
		super();
		this.cityNames = cityNames;
		
	}
	
	public  List<Object> generateCityData(int rows)
	{
		List<TbCity> mytbList=tbCityRepo.findAll();
		List<Object> result=new ArrayList<Object>();
	
		Random objGenerator = new Random();
       
		for(int i=0;i<rows;i++)
		{
			int randomTb = objGenerator.nextInt(mytbList.size());
			result.add(mytbList.get(randomTb).getCity());
		}
		return result;
		
	}
	
	public  List<Object> generateCountrySlug(int rows){
		
		List<TbCity> mytbList=tbCityRepo.findAll();
		System.out.println(mytbList);
		List<Object> result=new ArrayList<Object>();
		
		Random objGenerator = new Random();
	       
		for(int i=0;i<rows;i++)
		{
			int randomTb = objGenerator.nextInt(mytbList.size());
			result.add(mytbList.get(randomTb).getCountrySlug());
		}
		return result;
	}
	

}
