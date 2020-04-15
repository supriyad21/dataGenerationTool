package com.synerzip.dataGenerationTool.base.controller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;


import org.json.JSONArray;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.google.common.collect.Lists;

import com.synerzip.dataGenerationTool.base.dataFactoryImpl.DataFactory;
import com.synerzip.dataGenerationTool.base.model.TbMytb;
import com.synerzip.dataGenerationTool.base.model.Tutorial;
import com.synerzip.dataGenerationTool.base.repository.TbMytbRepository;
import com.synerzip.dataGenerationTool.base.repository.TutorialRepository;
import com.synerzip.dataGenerationTool.base.utility.ExcelGenerator;






/**
 * @author supriyad
 *@since April 2020
 */
@CrossOrigin(maxAge=3600)
@RestController
@RequestMapping("/api")

public class Controller {


	  @Autowired
	  TutorialRepository tutorialRepository;
	  
	  @Autowired
	  TbMytbRepository tbMytbRepo;
	 /* @Autowired
	  TestDao testDao;*/
	  
	  @GetMapping("/mytb")
	  public ResponseEntity<List<TbMytb>> getMyTb(@RequestParam(required = false)String name)
	  {
		  try {
		      List<TbMytb> tbList = new ArrayList<TbMytb>();

		      if (name == null)
		        tbMytbRepo.findAll().forEach(tbList::add);
		      else
		        tbMytbRepo.findByName(name).forEach(tbList::add);

		      if (tbList.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }

		      return new ResponseEntity<>(tbList, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }


	  @GetMapping("/mydata/customers.xlsx")
	  public ResponseEntity<InputStreamResource> getMyData(@RequestParam(required = false)String name)
	  {
		  //trial for datafactory
			DataFactory df=new DataFactory();
			List list=new ArrayList<>();
			//JSONArray jsonArray=new JSONArray();
			for(int i=0;i<500;i++)
			{
				String address=df.getAddress()+","+df.getCity()+","+df.getNumberText(5);
				String buss=df.getBusinessName();
				System.out.println(buss+" located at  "+address);
				list.add(buss+" located at  "+address);
				
			}
			
			
			//end  
			
		
			    
			List<TbMytb> mytbList=tbMytbRepo.findAll();
			List<Tutorial> tut=tutorialRepository.findAll();
			
			
			
		
			//trail combine
			
			 List<Collection> finalList = Lists.newArrayList();
			 finalList.add(mytbList);
			 finalList.add(tut);
			 Collection<List<Object>> resultList = permutations(finalList);
			//List result1= ListUtils.union(mytbList, tut);
		//	System.out.println(result1);
			
			 
			 
			 System.out.println(finalList);
				
			 List<HashMap<String, Object>> myList = new ArrayList<HashMap<String, Object>>();
			 
			 
			 
			 /*   for (Object str : resultList) {
			       
			       
			       Map<String, Object> json = new HashMap(); 
			       json.put("abcd", str);
			       myList.add((HashMap<String, Object>) json);
			    }
			    */
				
				//trial end
				
			 Random objGenerator = new Random();
			    for(int i=0;i<100;i++)
				{
			    	 Map<String, Object> json = new HashMap(); 
					
			    	
		           
		              int randomTb = objGenerator.nextInt(mytbList.size());
		              int randomTut = objGenerator.nextInt(tut.size());
					//mytbList.get(randomTb);
		              
					System.out.println(mytbList.get(randomTb));
					System.out.println(tut.get(randomTut));
					
					json.put("name", mytbList.get(randomTb).getName());
					json.put("title", tut.get(randomTut).getTitle());
					myList.add((HashMap<String, Object>) json);
				}
			
			    
			    ByteArrayInputStream in;
				try {
					in = ExcelGenerator.customersToExcel(myList);
					 
				    HttpHeaders headers = new HttpHeaders();
				        headers.add("Content-Disposition", "attachment; filename=customers.xlsx");
				    
				     return ResponseEntity
				                  .ok()
				                  .headers(headers)
				                  .body(new InputStreamResource(in));
				    
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    // return IOUtils.toByteArray(in);
			   
				return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
			    
			//return new ResponseEntity<>(myList,HttpStatus.OK);
			
			
			
		}

	  public static <T> Collection<List<T>> permutations(List<Collection> collections) {
		    if (collections == null || collections.isEmpty()) {
		        return Collections.emptyList();
		    } else {
		        Collection<List<T>> result = Lists.newLinkedList();
		        findPermutations(collections, result, 0, new LinkedList<T>());
		        return result;
		    }
		}
	  private static <T> void findPermutations(List<Collection> inputList, Collection<List<T>> outputList, int d, List<T> tempList) {
		    if (d == inputList.size()) {
		        outputList.add(tempList);
		        return;
		    }

		    Collection<T> currentCollection = inputList.get(d);
		    for (T currElement : currentCollection) {
		        List<T> copy = Lists.newLinkedList(tempList);
		        copy.add(currElement);
		        findPermutations(inputList, outputList, d + 1, copy);
		    }
		}

	  
	  @PostMapping(value="/mytb",consumes = "application/json", produces = "application/json")
		 // @CrossOrigin
		//  @RequestMapping(value="/tutorials", method = RequestMethod.POST)
		 
		  public ResponseEntity<TbMytb> createMyTb(@RequestBody TbMytb tb) {
		    try {
		    	System.out.println("abc");
		 	
		
		  
		      
		    	TbMytb _tb = tbMytbRepo
		          .save(new TbMytb(tb.getCt(),  tb.getName(),tb.getEmail(), tb.getPh()));
		      return new ResponseEntity<>(_tb, HttpStatus.CREATED);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		    }
		  }
	  
	  @GetMapping("/new")
	  public ResponseEntity<List<Tutorial>> getTutorial(@RequestParam(required = false)String title)
	  {
		  try {
		      List<Tutorial> tutorials = new ArrayList<Tutorial>();

		      if (title == null)
		        tutorialRepository.findAll().forEach(tutorials::add);
		      else
		        tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

		      if (tutorials.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }

		      return new ResponseEntity<>(tutorials, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	  
	  @GetMapping("/tutorials")
	  public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
	    try {
	      List<Tutorial> tutorials = new ArrayList<Tutorial>();

	      if (title == null)
	        tutorialRepository.findAll().forEach(tutorials::add);
	      else
	        tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

	      if (tutorials.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(tutorials, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @GetMapping("/tutorials/{id}")
	  public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
	    Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

	    if (tutorialData.isPresent()) {
	      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  @GetMapping("/tutorials/description/{description}")
	  public ResponseEntity<List<Tutorial>> getTutorialById(@PathVariable("description") String description) {
		  List<Tutorial> tutorials = new ArrayList<Tutorial>();
	  tutorialRepository.findByDescriptionContaining(description).forEach(tutorials::add);

	    if (!tutorials.isEmpty()) {
	      return new ResponseEntity<>(tutorials, HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  @GetMapping("/tutorials/title/{title}")
	  public ResponseEntity<List<Tutorial>> getTutorialByTitle(@PathVariable("title") String title) {
		
		  List<Tutorial> tutorials = new ArrayList<Tutorial>();
		  
		  tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

	    if (!tutorials.isEmpty()) {
	      return new ResponseEntity<>(tutorials, HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  
	  
	  @PostMapping(value="/tutorials1",consumes = "application/json", produces = "application/json")
	 // @CrossOrigin
	//  @RequestMapping(value="/tutorials", method = RequestMethod.POST)
	 
	  public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
	    try {
	    	System.out.println("abc");
	 	
	
	  
	      
	    	Tutorial _tutorial = tutorialRepository
	          .save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished()));
	      return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    }
	  }

	  @PutMapping("/tutorials/{id}")
	  public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
	    Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

	    if (tutorialData.isPresent()) {
	      Tutorial _tutorial = tutorialData.get();
	      _tutorial.setTitle(tutorial.getTitle());
	      _tutorial.setDescription(tutorial.getDescription());
	      _tutorial.setPublished(tutorial.isPublished());
	      return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @DeleteMapping("/tutorials/{id}")
	  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
	    try {
	      tutorialRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	  

	  @DeleteMapping("/tutorials")
	  public ResponseEntity<HttpStatus> deleteAllTutorials() {
	    try {
	      tutorialRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	  }

	  @GetMapping("/tutorials/published")
	  public ResponseEntity<List<Tutorial>> findByPublished() {
	    try {
	      List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

	      if (tutorials.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(tutorials, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	  }

	  @RequestMapping(value= "/api/**", method=RequestMethod.OPTIONS)
	  public void corsHeaders(HttpServletResponse response) {
	      response.addHeader("Access-Control-Allow-Origin", "*");
	      response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	      response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
	      response.addHeader("Access-Control-Max-Age", "3600");
	     
	  }
}