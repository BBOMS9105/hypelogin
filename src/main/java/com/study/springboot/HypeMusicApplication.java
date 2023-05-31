package com.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HypeMusicApplication {

	public static void main(String[] args) {
		SpringApplication.run(HypeMusicApplication.class, args);
		System.out.println("서버 연결 완료");
	}

}
