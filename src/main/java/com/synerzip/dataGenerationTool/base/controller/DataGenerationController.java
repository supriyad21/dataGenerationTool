package com.synerzip.dataGenerationTool.base.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.CellStyle;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synerzip.dataGenerationTool.base.RequestUtility.Columns;
import com.synerzip.dataGenerationTool.base.RequestUtility.RequestHelper;
import com.synerzip.dataGenerationTool.base.dataTypes.City;
import com.synerzip.dataGenerationTool.base.dataTypes.CountryDAO;
import com.synerzip.dataGenerationTool.base.model.TbCountry;
import com.synerzip.dataGenerationTool.base.repository.CountryRepository;
import com.synerzip.dataGenerationTool.base.utility.ExcelGenerator;

/**
 * @author supriyad
 * @since April 2020
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/generator")

public class DataGenerationController {

	@Autowired
	City city;

	@Autowired
	CountryDAO country;

	@Autowired
	CountryRepository ctrrepo;

	/**
	 * @return InputStreamResource
	 * 
	 *         desription:this method is used for download excelfile of random
	 *         data for multiple datatypes
	 */
//	@GetMapping(value = "/excel/dataGeneration.xlsx")
//	// @CrossOrigin
//	// @RequestMapping(value="/tutorials", method = RequestMethod.POST)
//
//	public ResponseEntity<InputStreamResource> generateExcelData(RequestHelper helper) {
//		// try with resources
//		try (
//
//				Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
//
//			// this will be user input in future
//
//			// Columns[] clm=new Columns[3];
//
//			// clm[0]=new Columns("City", "City_title","");
//			// clm[1]=new Columns("Names", "names_ofcolumn2","");
//			// clm[2]=new Columns("City", "city_2","");
//			// RequestHelper helper=new RequestHelper(100, clm);
//			// //end user inpu
//
//			Sheet sheet = workbook.createSheet("dataGeneration");
//
//			Font headerFont = workbook.createFont();
//			headerFont.setBold(true);
//			headerFont.setColor(IndexedColors.BLUE.getIndex());
//
//			CellStyle headerCellStyle = workbook.createCellStyle();
//			headerCellStyle.setFont(headerFont);
//
//			// Row for Header
//			Row headerRow = sheet.createRow(0);
//
//			// Header
//
//			Columns[] columns = helper.getColumn();
//
//			int columnNumber = 0;
//
//			for (Columns column : columns) {
//
//				switch (column.getType()) {
//				case "City": {
//					List<Object> list;
//					if (column.getOption().equalsIgnoreCase("CountrySlug")) {
//						list = city.generateCountrySlug(helper.getNumRows());
//					} else {
//
//						list = city.generateCityData(helper.getNumRows());
//					}
//
//					ExcelGenerator.writeIntoColumn(list, sheet, columnNumber, column.getTitle(), headerRow);
//					columnNumber++;
//
//				}
//					break;
//
//				case "Names": {
//
//					List<Object> list = city.generateCityData(helper.getNumRows());
//
//					ExcelGenerator.writeIntoColumn(list, sheet, columnNumber, column.getTitle(), headerRow);
//					columnNumber++;
//
//				}
//					break;
//
//				case "LastNames": {
//
//				}
//					break;
//				default:
//					System.out.println("error");
//				}
//
//			}
//			workbook.write(out);
//
//			ByteArrayInputStream in;
//
//			in = new ByteArrayInputStream(out.toByteArray());
//			
//
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("Content-Disposition", "attachment; filename=customers.xlsx");
//
//			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
//		}
//	}

	
	
// Actual Logic used for data generation using end users input and according
// to that generate output	
// This post mapping takes input from user and generated the desire output
// according to user

	@PostMapping(value = "/excel/dataGeneration.xlsx", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	// @CrossOrigin
	// @RequestMapping(value="/tutorials", method = RequestMethod.POST)

	public ResponseEntity<InputStreamResource> generateExcel(RequestHelper helper) {
		// try with resources
		try (

				Workbook workbook = new XSSFWorkbook(); 
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				) {

			/*
			 * Columns[] clm=new Columns[1]; clm[0]=new Columns(type, title);
			 * RequestHelper helper=new RequestHelper(numRows, clm);
			 * 
			 */

			List<TbCountry> ctr = ctrrepo.findByNameEndsWith("ia");

			for (TbCountry tbCountry : ctr) {
				System.out.println(tbCountry.getCountry());
			}

			Sheet sheet = workbook.createSheet("dataGeneration");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header

			Columns[] columns = helper.getColumn();

			int columnNumber = 0;

			for (Columns column : columns) {

				switch (column.getType().toLowerCase()) {
				case "city": {

					List<Object> list;
					if (column.getOption()!=null && column.getOption().equalsIgnoreCase("CountrySlug")) {
						list = city.generateCountrySlug(helper.getNumRows());
					} else {

						list = city.generateCityData(helper.getNumRows());
					}

					ExcelGenerator.writeIntoColumn(list, sheet, columnNumber, column.getTitle(), headerRow);
					columnNumber++;

				}
				
					break;

				case "names": {

					List<Object> list = city.generateCityData(helper.getNumRows());
					ExcelGenerator.writeIntoColumn(list, sheet, columnNumber, column.getTitle(), headerRow);
					columnNumber++;
				}
					break;

				case "country": {
					List<Object> list = country.generateCountryData(helper.getNumRows());
					ExcelGenerator.writeIntoColumn(list, sheet, columnNumber, column.getTitle(), headerRow);
					columnNumber++;
				}
					break;
				default:
					System.out.println("error");
				}

			}
			workbook.write(out);

			ByteArrayInputStream in;

			in = new ByteArrayInputStream(out.toByteArray());
			

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=customers.xlsx");

			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	
	
	
	/**
	 * @param helper
	 * 
	 * 
	 */
	// Just for Testing purpose : - this post does not use the html view it only
	// post the predefined data
	@PostMapping(value = "/json/dataGeneration")
	// @CrossOrigin
	// @RequestMapping(value="/tutorials", method = RequestMethod.POST)

	public ResponseEntity<List<HashMap<String, Object>[]>> generateJsonData(@RequestBody RequestHelper helper) {
		try {

			Columns[] columns = helper.getColumn();

			List<HashMap<String, Object>[]> myList = new ArrayList<HashMap<String, Object>[]>();

			Map<String, Object>[] obj = new HashMap[helper.getNumRows()];

			for (Columns column : columns) {

				switch (column.getType()) {
				case "City": {

					List<Object> list = city.generateCityData(helper.getNumRows());
					for (int i = 0; i < list.size(); i++) {
						if (obj[i] == null) {
							obj[i] = new HashMap<>();
						}
						obj[i].put(column.getTitle(), list.get(i).toString());
					}

				}
					break;

				case "Names": {
					List<Object> list = new ArrayList<>();

					list = city.generateCityData(helper.getNumRows());

					for (int i = 0; i < list.size(); i++) {
						if (obj[i] == null) {
							obj[i] = new HashMap<>();
						}
						obj[i].put(column.getTitle(), list.get(i).toString());
					}

				}
					break;

				case "LastNames": {

				}
					break;
				default:
					System.out.println("error");
				}

			}

			myList.add((HashMap<String, Object>[]) obj);
			return new ResponseEntity<>(myList, HttpStatus.EXPECTATION_FAILED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
	
	
}
