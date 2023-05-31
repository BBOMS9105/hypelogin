package com.study.springboot.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.springboot.User;
import com.study.springboot.lsc_Functions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class lsc_LoginController {
	
	// 로그인 페이지
	@GetMapping("/login")
	public String signin() {
			
		return "lsc_login";
	}
		
	// 로그인 처리용 컨트롤러
	@RequestMapping("/login")
	public String loginUser(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpServletRequest req,
			Model model) throws IOException, ParseException {
		HttpSession session = req.getSession();
		lsc_Functions lscFunctions = new lsc_Functions();
		List<User> userList = lscFunctions.getUserFromXlsx();
		for (User user : userList) {
			if (user != null && !user.getId().isEmpty()
			        && !user.getPw().isEmpty()
			        && user.getId().equals(id)
			        && user.getPw().equals(pw)) { // id, password가 일치한다면
			    session.setAttribute("id", id);
			    session.setAttribute("pw", pw);
			    session.setAttribute("age", user.getAge());
			    session.setAttribute("preference", user.getPreference());
			    session.setAttribute("email", user.getEmail());
			    session.setAttribute("nickname", user.getNickname());
			    session.setAttribute("rank", user.getRank());
			    System.out.println(user.getId() + " 로그인 성공, " + "rank : " + user.getRank());

			    return "redirect:/main?success=true";
			}
		}

			model.addAttribute("err_msg", "아이디 또는 패스워드가 일치하지 않습니다.");
			return "redirect:/login?error=true"; // 실패시 로그인페이지에 에러메세지와 함께 리다이렉트
		}
		
	// 로그아웃 컨트롤러
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate(); // 세션초기화
		System.out.println("로그아웃 성공");
		return "redirect:/main"; // 메인페이지로 리다이렉트
		}
			
	// 로그인 시 csv파일을 ajax로 변환해 /users로 보낸 후	
	// login.jsp에 스크립트 단에서 /users로 간 데이터를 읽어서 대조하기위한 컨트롤러
	@RequestMapping("/users")
	@ResponseBody
	public String ajaxUsers() throws ParseException {
		List<User> userList = null;

		try {
			lsc_Functions lscFunctions = new lsc_Functions();
			userList = lscFunctions.getUserFromXlsx();
		} catch (IOException e) {
			e.printStackTrace();
			}
				        
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = "";

		try {
			jsonResult = mapper.writeValueAsString(userList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			}

			return jsonResult;
		}

}
