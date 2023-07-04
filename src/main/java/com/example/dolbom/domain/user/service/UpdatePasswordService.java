package com.example.dolbom.domain.user.service;

import com.example.dolbom.domain.user.domain.User;
import com.example.dolbom.domain.user.domain.repository.UserRepository;
import com.example.dolbom.domain.user.exception.PasswordMisMatchException;
import com.example.dolbom.domain.user.exception.UserNotFoundException;
import com.example.dolbom.domain.user.facade.UserFacade;
import com.example.dolbom.domain.user.present.dto.request.UpdatePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePasswordService {
    private final UserRepository userRepository;
    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;
    public void passwordUpdate(UpdatePasswordRequest request){
        User currentUser = userFacade.getCurrentUser();

        User user = userRepository.findByAccountId(currentUser.getAccountId())
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw PasswordMisMatchException.EXCEPTION;
        }

        user.updatePass(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);
    }
}
