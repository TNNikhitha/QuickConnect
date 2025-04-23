package com.ust.otpVerification1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OtpVerification1Application {

	public static void main(String[] args) {
		SpringApplication.run(OtpVerification1Application.class, args);
		
	}

}
