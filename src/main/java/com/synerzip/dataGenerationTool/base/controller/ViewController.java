package com.synerzip.dataGenerationTool.base.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

 
/**
 * @author supriyad
 *@since April 2020
 */
@Controller
@RequestMapping("/home")
public class ViewController {
 
  @RequestMapping("/")
  public String home() {
   System.out.println("home");
	  return "home";
  }
}