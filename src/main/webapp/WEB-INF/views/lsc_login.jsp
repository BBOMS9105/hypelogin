<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div id="wrap">
		<header class="pageHeader">
			<a href="http://localhost:8081/main" class="logo">
				<img width="180px" height="100px" src="/img/logo_ex.jpg">
			</a>
		</header>
	<form id="boxes" method="post" action="login">
		<div id="inputGroup">
			<div id="input">
				<div class="label-wrapper">
					<label for="id" id="lb">아이디</label><br>
				</div>
				<input type="text" name="id"><br>	
			</div>
			<div id="input">
				<div class="label-wrapper">
					<label for="pw" id="lb">비밀번호</label><br>
				</div>
				<input type="password" name="pw"><br>
			</div>
   			<div id="find">
  				<button class="find-a" onclick="location.href='#'">아이디 찾기</button>&nbsp;<a id="divide">|</a>&nbsp;<button class="find-a" onclick="location.href='#'">비밀번호 찾기</button>
			</div>
   			<div id="input">
				<input type="submit" id="submit" value="로그인">
			</div>
			<div id="input">
				<input type="button" id="signup" value="회원가입" onclick="location.href='http://localhost:8081/signup'">
			</div>
		</div>
      </form>      
   </div>
   
<script>

//컨트롤러에서 /users로 보낸 데이터를 읽어와서 대조하는 jquery
function checkLogin(id, pw){
	$.ajax({
		url:"http://localhost:8081/users",
		type:"GET",
		dataType:"JSON",
		success:function(data){
			let isMatch = false;
			for (let i = 0; i<data.length; i++){
				if(data[i].id == id && data[i].pw == pw) {
					isMatch = true;
					break;
				}
			}
			
			if (!isMatch){
				alert("아이디 혹은 비밀번호가 일치하지 않습니다.");
			}
		}
	})
	
}

$(document).ready(function(){
	$("#submit").on("click", function(event){
		let id = $('input[name="id"]').val().trim();
		let pw = $('input[name="pw"]').val();
		
		if (id.length === 0){
			alert("아이디를 입력해주세요.");
			event.preventDefault();
		} else if ( pw.length === 0) {
			alert("비밀번호를 입력해주세요.");
			event.preventDefault();
		} else {
			checkLogin(id, pw);
		}
	})
})
</script>
</html>