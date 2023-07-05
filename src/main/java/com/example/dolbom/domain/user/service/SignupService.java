package com.example.dolbom.domain.user.service;

import com.example.dolbom.domain.user.domain.User;
import com.example.dolbom.domain.user.domain.repository.UserRepository;
import com.example.dolbom.domain.user.exception.AlreadyJoinedException;
import com.example.dolbom.domain.user.present.dto.request.SignUpRequest;
import com.example.dolbom.global.enums.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void signup(SignUpRequest request){
        if(userRepository.findByAccountId(request.getAccountId()).isPresent())
            throw AlreadyJoinedException.EXCEPTION;


        userRepository.save(
                User.builder()
                        .accountId(request.getAccountId())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .nickname(request.getNickname())
                        .callNumber(request.getPhoneNumber())
                        .authority(Authority.USER)
                        .build()
        );
    }
}
