package com.study.springboot;

public class User {
	private String id;
	private String pw;
	private int age;
	private String preference;
	private String email;
	private String nickname;
	private int rank;
	
	public String getId() {
		if (id == null ) {
	        // id 값이 null인 경우 빈 문자열 반환
	        return "";
	    } else {
	        return id;
	    }
	}
	public void setId(String id) {
		if (id == null || id.isEmpty()) {
	        // 만약 전달된 id 값이 null이거나 비어있다면, 빈 문자열로 설정
	        this.id = "";
	    } else {
	        // 그렇지 않으면 전달된 값으로 설정 
	        this.id = id;
	    }
	}
	public String getPw() {
		if (pw == null ) {
	        // id 값이 null인 경우 빈 문자열 반환
	        return "";
	    } else {
	        return pw;
	    }
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPreference() {
		return preference;
	}
	public void setPreference(String preference) {
		this.preference = preference;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", pw=" + pw + ", age=" + age + ", preference=" + preference + ", email=" + email
				+ ", nickname=" + nickname + ", rank=" + rank + ", + ]";
	}
	
	// rank까지 설정하는 생성자
	public User(String id, String pw, int age, String preference, String email, String nickname, int rank) {
		super();
		this.id = id;
		this.pw = pw;
		this.age = age;
		this.preference = preference;
		this.email = email;
		this.nickname = nickname;
		this.rank = rank;
	}
	
	// rank의 기본값을 1로 지정하고 나머지를 설정하는 생성자
	public User(String id, String pw, int age, String preference, String email, String nickname) {
        this(id, pw, age, preference, email, nickname, 1); // rank의 디폴트값으로 1을 지정함
    }
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public void add(User users) {
		// TODO Auto-generated method stub
		
	}
	
	
}
