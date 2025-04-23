package com.ust.otpVerification1.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ust.otpVerification1.service.OtpService;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/send")
    public String sendOtp(@RequestBody Map<String, String> request) {
        String phone = request.get("phone");
    	String otp = otpService.sendOtp(phone);
        return "OTP sent to " + phone + " (Test OTP: " + otp + ")";
    }

    @PostMapping("/verify")
    public String verifyOtp(@RequestBody Map<String, String> request) {
        String phone = request.get("phone");
        String otp = request.get("otp");
        boolean valid = otpService.verifyOtp(phone, otp);
        return valid ? "OTP Verified!" : "Invalid or Expired OTP!";
    }
}