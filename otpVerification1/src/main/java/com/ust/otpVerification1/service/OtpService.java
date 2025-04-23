package com.ust.otpVerification1.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ust.otpVerification1.entity.OtpEntity;
import com.ust.otpVerification1.repository.OtpRepository;

import java.time.LocalDateTime;

@Service
public class OtpService {

    private static final int EXPIRE_MINUTES = 5;

    @Autowired
    private OtpRepository otpRepository;

    public String generateOtp() {
        return String.valueOf((int)(Math.random() * 900000) + 100000);
    }

    public String sendOtp(String phoneNumber) {
        String otp = generateOtp();
        OtpEntity entity = new OtpEntity(phoneNumber, otp, LocalDateTime.now());
        otpRepository.save(entity);
        return otp;
    }

    public boolean verifyOtp(String phoneNumber, String otp) {
        OtpEntity entity = otpRepository.findByPhoneNumber(phoneNumber);
        if (entity != null && entity.getOtp().equals(otp) &&
            entity.getCreatedAt().plusMinutes(EXPIRE_MINUTES).isAfter(LocalDateTime.now())) {

            otpRepository.delete(entity); // Delete after verification
            return true;
        }
        return false;
    }
    
    @Scheduled(fixedRate = 300000) // 5 minutes
    @Transactional
    public void deleteExpiredOtps() {
        LocalDateTime cutoff = LocalDateTime.now().minusMinutes(EXPIRE_MINUTES);
        otpRepository.deleteByCreatedAtBefore(cutoff);
        System.out.println("Expired OTPs deleted before: " + cutoff);
    }
}