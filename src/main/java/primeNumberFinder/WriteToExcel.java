package primeNumberFinder;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class WriteToExcel {
	private String[] columns = {"Prime Numbers"};

	public void createExcel(ArrayList<String> numbers, long maxNum, int numOfPrimes, String method, long sunTime, ArrayList<String> eNumbers, String erato, long eratoTime) {
		//public void createExcel(ArrayList<String> numbers, int maxNum, String method, totalTime){
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Prime Numbers");

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.BLUE.getIndex());

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);    
		Row headerRow = sheet.createRow(0);

		for(int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(0);
			cell.setCellValue(columns[0]);
			cell.setCellStyle(headerCellStyle);
		}

		Row max = sheet.createRow(1);
		max.createCell(0).setCellValue("Max Number: ");
		max.createCell(1).setCellValue(maxNum); 

		if(numOfPrimes < 15000) {

			if(erato.equalsIgnoreCase("erato")){
				Row Eratosthenes = sheet.createRow(2);
				Eratosthenes.createCell(0).setCellValue("Eratosthenes method:");
				Eratosthenes.createCell(1).setCellValue(eNumbers.size() + " prime numbers");
				Eratosthenes.createCell(4).setCellValue("Time: "+ eratoTime );
				Row row = sheet.createRow(3);
				for(int i=0; i < eNumbers.size(); i ++) {          
					row.createCell(i).setCellValue(eNumbers.get(i));
				}
			} 
			if(method.equalsIgnoreCase("sun")){
				Row Sun = sheet.createRow(4);
				Sun.createCell(0).setCellValue("Sundaram method: ");
				Sun.createCell(1).setCellValue(numbers.size() + " prime numbers");
				Sun.createCell(4).setCellValue("Time: "+ sunTime);
				Row row = sheet.createRow(5);
				for(int i=0; i < numbers.size(); i ++) {          
					row.createCell(i).setCellValue(numbers.get(i));
				}
			}
		} else {
			//Too large for one column of output
			System.out.println("TOO LARGE FOR ONE COLUMN");
			if(erato.equalsIgnoreCase("erato")){
				Row Eratosthenes = sheet.createRow(2);
				Eratosthenes.createCell(0).setCellValue("Eratosthenes method:");
				Eratosthenes.createCell(1).setCellValue(eNumbers.size() + " prime numbers");
				Eratosthenes.createCell(4).setCellValue("Time: "+ eratoTime );
				
				Row row = sheet.createRow(3);
				for(int i=0; i < 15000; i ++) {          
					row.createCell(i).setCellValue(eNumbers.get(i));
				}
				row = sheet.createRow(4);
				int j=1;
				for(int i=15001; i <30000; i++) {
					row.createCell(j).setCellValue(eNumbers.get(i));
					j++;
				}
				j=1;
				row = sheet.createRow(5);
				for(int i = 30001; i <eNumbers.size(); i++) {
					row.createCell(j).setCellValue(eNumbers.get(i));
					j++;
				}
				
			} 
			if(method.equalsIgnoreCase("sun")){
				Row Sun = sheet.createRow(7);
				Sun.createCell(0).setCellValue("Sundaram method: ");
				Sun.createCell(1).setCellValue(numbers.size() + " prime numbers");
				Sun.createCell(4).setCellValue("Time: "+ sunTime);
				Row row = sheet.createRow(8);
				for(int i=0; i < 15000; i ++) {          
					row.createCell(i).setCellValue(numbers.get(i));
				}
				int j = 1;
				row = sheet.createRow(9);
				for(int i = 15001; i < 30000; i++) {
					row.createCell(j).setCellValue(numbers.get(i));
					j++;
				}
				j=1;
				row = sheet.createRow(10);
				for(int i = 30001; i <eNumbers.size(); i++) {
					row.createCell(j).setCellValue(numbers.get(i));
					j++;
				}
			}
		}

		for(int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}

		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream("PrimeNumbers.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.write(fileOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
