package com.example.dolbom.domain.auth.present;

import com.example.dolbom.domain.auth.present.dto.request.SendSmsRequest;
import com.example.dolbom.domain.auth.present.dto.response.SmsCodeResponse;
import com.example.dolbom.domain.auth.present.dto.response.SmsResponse;
import com.example.dolbom.domain.auth.present.dto.response.UserRefreshTokenResponse;
import com.example.dolbom.domain.auth.service.AuthService;
import com.example.dolbom.domain.auth.service.SmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final SmsService smsService;

    @PutMapping("/refresh")
    public UserRefreshTokenResponse reissue(@RequestHeader("Refresh-Token") String refreshToken){
        return authService.reissue(refreshToken);
    }

    @PostMapping("/sms")
    public SmsCodeResponse send(@RequestBody SendSmsRequest request) throws NoSuchAlgorithmException, URISyntaxException, UnsupportedEncodingException, InvalidKeyException, JsonProcessingException {
        log.info(request.getPhoneNumber());
        String code = smsService.sendSms(request.getPhoneNumber());
        return SmsCodeResponse.builder()
                .code(code)
                .build();
    }
}