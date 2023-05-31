<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<table>
    <thead>
        <tr>
            <th>id</th>
            <th>pw</th>
            <th>age</th>
            <th>preference</th>
            <th>email</th>
            <th>nickname</th>
            <th>rank</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.pw}"/></td>
                <td><c:out value="${user.age}"/></td>
                <td><c:out value="${user.preference}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.nickname}"/></td>
                <td><c:out value="${user.rank}"/></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
    <form method="post" action="/user">
    <label for="id">아이디 : </label>
    <input type="text" id="id" name="id" required><button id="id-check">중복확인</button><br>
    <label for="pw">비밀번호 : </label>
    <input type="password" id="pw" name="pw" required><br>
    <label for="pw">비밀번호 확인 : </label>
    <input type="password" id="pw-confirm" name="pw-confirm" required><br>
    <label for="age">나이 : </label>
    <input type="text" id="age" name="age" required><br>
    <label for="preference">선호 장르 : </label>
    <input type="text" id="preference" name="preference" required><br>
    <label for="email">이메일 : </label>
    <input type="text" id="email" name="email" required><button id="email-check">중복확인</button><br>
    <label for="nickname">닉네임 : </label>
    <input type="text" id="nickname" name="nickname" required><button id="nickname-check">중복확인</button><br>
    <button type="submit" id="submit">회원가입</button>
</form>
<script>
    function validateInput(input, regex, errorMessage) {
        if (input.length === 0) {
            alert(errorMessage);
            return false;
        } else if (!input.match(regex)) {
            alert(errorMessage);
            return false;
        }
        return true;
    }

    function handleIdCheck() {
        let id = document.querySelector('input[name="id"]').value.trim();
        if (!validateInput(id, /^[a-zA-Z0-9]{1,10}$/, "아이디는 영문 또는 숫자의 조합으로 10자리 이하여야 합니다.")) {
            return;
        }
        duplicateCheck(id, "", "");
    }

    function handleEmailCheck() {
        let email = document.querySelector('input[name="email"]').value.trim();
        if (!validateInput(email, /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/, "이메일 형식이 올바르지 않습니다.")) {
            return;
        }
        duplicateCheck("", email, "");
    }

    function handleNicknameCheck() {
        let nickname = document.querySelector('input[name="nickname"]').value.trim();
        if (!validateInput(nickname, /^[가-힣a-zA-Z0-9]{2,10}$/, "닉네임은 특수문자와 공백을 제외한 2~10자 이하여야합니다.")) {
            return;
        }
        duplicateCheck("", "", nickname);
    }

    document.querySelector("#submit").onclick = function(event) {
        event.preventDefault();

        let id = document.querySelector('input[name="id"]').value.trim();
        let password = document.querySelector('input[name="pw"]').value;
        let password_confirm = document.querySelector('input[name="pw-confirm"]').value.trim();
        let age = document.querySelector('input[name="age"]').value.trim();
        let email = document.querySelector('input[name="email"]').value.trim();
        let nickname = document.querySelector('input[name="nickname"]').value.trim();

        if (!validateInput(id, /^[a-zA-Z0-9]{1,10}$/, "아이디를 입력해주세요.")) {
            return;
        }
        if (!validateInput(password, /^[a-zA-Z0-9]{4,10}$/, "비밀번호를 입력해주세요.")) {
            return;
        }
        if (password !== password_confirm) {
            alert("비밀번호가 일치하지 않습니다.");
            return;
        }
        if (!validateInput(age, /^([0-9]|[0-9][0-9]|1[01][0-9]|120)$/, "나이를 입력해주세요.")) {
            return;
        }
        if (!validateInput(email, /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/, "이메일을 입력해주세요.")) {
            return;
        }
        if (!validateInput(nickname, /^[가-힣a-zA-Z0-9]{2,10}$/, "닉네임을 입력해주세요.")) {
            return;
        }

        // 중복 체크 요청 보내기
        duplicateCheck(id, email, nickname);
    }

    function duplicateCheck(id, email, nickname) {
        let isMatch = false;
        
        if (id) {
            $.ajax({
                url: "http://localhost:8081/users",
                type: "GET",
                dataType: "JSON",
                success: function(data) {
                    for (let i = 0; i < data.length; i++) {
                        if (data[i].id == id) {
                            alert("사용중인 아이디입니다.");
                            isMatch = true;
                            break;
                        }
                    }
                    if (!isMatch) {
                        alert("사용가능한 아이디입니다.");
                    }
                }
            });
        } else if (email) {
            $.ajax({
                url: "http://localhost:8081/users",
                type: "GET",
                dataType: "JSON",
                success: function(data) {
                    for (let i = 0; i < data.length; i++) {
                        if (data[i].email == email) {
                            alert("사용중인 이메일입니다.");
                            isMatch = true;
                            break;
                        }
                    }
                    if (!isMatch) {
                        alert("사용가능한 이메일입니다.");
                    }
                }
            });
        } else if (nickname) {
            $.ajax({
                url: "http://localhost:8081/users",
                type: "GET",
                dataType: "JSON",
                success: function(data) {
                    for (let i = 0; i < data.length; i++) {
                        if (data[i].nickname == nickname) {
                            alert("사용중인 닉네임입니다.");
                            isMatch = true;
                            break;
                        }
                    }
                    if (!isMatch) {
                        alert("사용가능한 닉네임입니다.");
                    }
                }
            });
        }
    }

    document.querySelector("#id-check").addEventListener("click", handleIdCheck);
    document.querySelector("#email-check").addEventListener("click", handleEmailCheck);
    document.querySelector("#nickname-check").addEventListener("click", handleNicknameCheck);
</script>


</body>
</html>