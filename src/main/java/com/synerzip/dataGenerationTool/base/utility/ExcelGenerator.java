package com.synerzip.dataGenerationTool.base.utility;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 

 
/**
 * @author supriyad
 *@since APril 2020
 */
public class ExcelGenerator {
  
  public static ByteArrayInputStream customersToExcel(List<HashMap<String, Object>> general) throws IOException {
    String[] COLUMNs = {"name","title"};
    try(
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
    ){
      CreationHelper createHelper = workbook.getCreationHelper();
   
      Sheet sheet = workbook.createSheet("GenerateData");
   
      Font headerFont = workbook.createFont();
      headerFont.setBold(true);
      headerFont.setColor(IndexedColors.BLUE.getIndex());
   
      CellStyle headerCellStyle = workbook.createCellStyle();
      headerCellStyle.setFont(headerFont);
   
      // Row for Header
      Row headerRow = sheet.createRow(0);
   
      // Header
      for (int col = 0; col < COLUMNs.length; col++) {
        Cell cell = headerRow.createCell(col);
        cell.setCellValue(COLUMNs[col]);
        cell.setCellStyle(headerCellStyle);
      }
   
      // CellStyle for Age
      CellStyle ageCellStyle = workbook.createCellStyle();
      ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));
   
      int rowIdx = 1;
      for (HashMap<String, Object> data : general) {
        Row row = sheet.createRow(rowIdx++);
   
        row.createCell(0).setCellValue((String)data.get("name"));
        row.createCell(1).setCellValue((String)data.get("title"));
       // row.createCell(2).setCellValue(customer.getAddress());
   
      
      }
   
      workbook.write(out);
      return new ByteArrayInputStream(out.toByteArray());
    }
  }
  
  public static void writeIntoColumn(List<Object> list, Sheet sheet, int columnNumber,String nameOfColumn, Row headerRow) {
		 
		
		 // Row for Header
	  //  Row headerRow = sheet.createRow(0);
	    Cell cell = headerRow.createCell(columnNumber);
	    cell.setCellValue(nameOfColumn);
	   
		int rowIdx = 1;
	      for (Object data : list) {
	    	  
	    	  Row row = sheet.getRow(rowIdx);
	    	 
	   if(row==null)
	   {
		   row=sheet.createRow(rowIdx);
	   }
	   rowIdx=rowIdx+1;
	        row.createCell(columnNumber).setCellValue((String)data);
	      
	      }
		
	}
  
}