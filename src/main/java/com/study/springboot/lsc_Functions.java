package com.study.springboot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class lsc_Functions {
	
	
	/**
	 * @author 이승찬
	 * @return 유저 정보 리스트
	 * @throws IOException, ParseException
	 * 
	 * **/
		public List<User> getUserFromXlsx() throws IOException, ParseException {
    	// 엑셀 파일을 읽어들일 FileInputStream 객체 생성
    	// 경로는 사용자 환경에 맞게 세팅해주셔야합니다
		
    	FileInputStream fis = new FileInputStream(new File("C:\\Users\\User\\Desktop\\HypeMusic\\src\\main\\resources\\metadata\\user_test.xlsx"));
    	
    	// XSSFWorkbook 객체를 생성하여 엑셀 파일을 읽음
    	XSSFWorkbook workbook = new XSSFWorkbook(fis);
    	
    	// sheetIndex 변수에 0을 할당하여 첫 번째 시트를 선택함
    	int sheetIndex = 0;
    	
    	// sheet 변수에 XSSFWorkbook 객체에서 getSheetAt 메소드를 사용하여 SheetIndex에 해당하는
    	// 시트를 가져옴
    	XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
    	
    	// 헤더 정보 추출
    	int colCnt = sheet.getRow(0).getPhysicalNumberOfCells();
    	String[] headers = new String[colCnt];
    	XSSFRow headerRow = sheet.getRow(0);
    	
    	// headerRow에 있는 셀 정보를 이용하여 헤더 정보를 추출 한 뒤 headers 배열에 저장
    	for (int i = 0; i<colCnt; i++) {
    		XSSFCell cell = headerRow.getCell(i);
    		headers[i] = cell.getStringCellValue();
    	}
    
    	List<User> users = new ArrayList<User>();
    	
    	// rowIterator 객체 생성
    	Iterator<Row> rowIterator = sheet.iterator();
    	// 첫 번째 줄(헤더) 제거
    	rowIterator.next();
    	
    	// rowIterator를 이용하여 엑셀 파일의 데이터를 추출하여 객체 생성
    	while(rowIterator.hasNext()) {
    		XSSFRow row = (XSSFRow)rowIterator.next();
    		User user = new User();
    		
    		// rowVariable을 이용하여 각 열 데이터를 추출하여 TrackInfo 객체의 변수에 세팅
    		for(int i = 0; i<headers.length; i++) {
    			XSSFCell cell = row.getCell(i);
    			// cell 값이 null일 경우 continue
    			if(cell == null) {
    				continue;
    			}
    			// cell의 type에 따라 처리할 범위 지정
    			switch (cell.getCellType()) {
    			case NUMERIC:
    				//release_date 값은 DATE타입으로 받아오기
    				if (headers[i].equals("age")) {
    					user.setAge((int)cell.getNumericCellValue());
    				} else if (headers[i].equals("rank")) {
    					user.setRank((int)cell.getNumericCellValue());
    				}
    				break;
    			case STRING:
    				if (headers[i].equals("id")) {
    					user.setId(cell.getStringCellValue());
    				} else if(headers[i].equals("pw")) {
    					user.setPw(cell.getStringCellValue());
    				} else if(headers[i].equals("preference")) {
    					user.setPreference(cell.getStringCellValue());
    				} else if(headers[i].equals("email")) {
    					user.setEmail(cell.getStringCellValue());
    				} else if(headers[i].equals("nickname")) {
    					user.setNickname(cell.getStringCellValue());
    				}
    				break;
    			}
    		}
    		users.add(user);
    	}
    	
    	workbook.close(); // XSSFWorkbook 닫기
    	fis.close(); // FileInputStream 닫기
    	
    	return users;
    }
}
