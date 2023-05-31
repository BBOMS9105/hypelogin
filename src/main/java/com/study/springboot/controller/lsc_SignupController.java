package com.study.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springboot.User;
import com.study.springboot.lsc_Functions;

@Controller
public class lsc_SignupController {
	
	//회원가입
	@RequestMapping("/signup")
	public String readWrite(Model model) {
							
		try {
			lsc_Functions lscFunctions = new lsc_Functions();
			List<User> userList = lscFunctions.getUserFromXlsx();
			model.addAttribute("userList", userList);
			System.out.println(userList);
		} catch (Exception e) {
			e.printStackTrace();
			}
					
			return "lsc_signup";
				
		}
	// 회원가입 성공 페이지
	@RequestMapping("/success")
	public String success() {
		return "lsc_success";
	}
}
