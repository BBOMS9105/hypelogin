package com.study.springboot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.springboot.User;
import com.study.springboot.lsc_Functions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class lsc_MainController {
	
	// 입력받은 form 데이터 저장 컨트롤러
	@RequestMapping("/user")
	public String postUser(@ModelAttribute User user,
							HttpServletResponse response) {
		
		try {
			FileInputStream file = new FileInputStream(new File("C:\\Users\\User\\Desktop\\HypeMusic\\src\\main\\resources\\metadata\\user_test.xlsx"));
			
			// 엑셀 파일의 워크북 로드
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			
			// 시트 선정
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			// 새로운 데이터가 들어가기 위해 엑셀 파일의 마지막 행 찾기
			int rowNum = sheet.getLastRowNum();
			
			// 새로운 열 생성 및 데이터 입력
	        // rank는 기본으로 1로 설정
	        Row row = sheet.createRow(rowNum + 1);
	        row.createCell(0).setCellValue(user.getId());
	        row.createCell(1).setCellValue(user.getPw());
	        row.createCell(2).setCellValue(user.getAge());
	        row.createCell(3).setCellValue(user.getPreference());
	        row.createCell(4).setCellValue(user.getEmail());
	        row.createCell(5).setCellValue(user.getNickname());
	        row.createCell(6).setCellValue(1);

	        // 바뀐 workbook을 파일에 작성
	        FileOutputStream out = new FileOutputStream(new File("C:\\Users\\User\\Desktop\\HypeMusic\\src\\main\\resources\\metadata\\user_test.xlsx"));
	        workbook.write(out);
	        out.close();
	        workbook.close();
		} catch (Exception e) {
	        e.printStackTrace();
	    }

	    return "redirect:/success";
	}
		
	
	// mainpage
	@RequestMapping("/main")
	public String main() {
	    return "lsc_main";
	}
	    
	// chart
	@RequestMapping("/chart")
	public String chart() {
	    return "lsc_chart";
	}
}
