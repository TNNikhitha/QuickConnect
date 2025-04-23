package com.ust.otpVerification1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.otpVerification1.entity.OtpEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpEntity, Long> {
    Optional<OtpEntity> findTopByPhoneNumberOrderByCreatedAtDesc(String phoneNumber);
    OtpEntity findByPhoneNumber(String phoneNumber);
    void deleteByCreatedAtBefore(LocalDateTime timestamp);
}