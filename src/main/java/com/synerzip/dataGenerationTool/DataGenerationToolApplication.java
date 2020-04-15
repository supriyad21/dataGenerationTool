package com.synerzip.dataGenerationTool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.synerzip.dataGenerationTool.controller")
@ComponentScan("com.synerzip.dataGenerationTool.base.dao")
public class DataGenerationToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataGenerationToolApplication.class, args);
	}
}
