package com.example.dolbom.domain.user.service;

import com.example.dolbom.domain.user.domain.User;
import com.example.dolbom.domain.user.domain.repository.UserRepository;
import com.example.dolbom.domain.user.exception.UserNotFoundException;
import com.example.dolbom.domain.user.facade.UserFacade;
import com.example.dolbom.domain.user.present.dto.request.UpdateProfileRequest;
import com.example.dolbom.domain.user.present.dto.response.MyPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserRepository userRepository;
    private final UserFacade userFacade;

    public MyPageResponse loadByProfile() {

        User currentUser = userFacade.getCurrentUser();

        return MyPageResponse.builder()
                .nickName(currentUser.getNickname())
                .certification(currentUser.getCertification())
                .build();
    }

    @Transactional
    public void patchProfile(UpdateProfileRequest request){
        User user = userFacade.getCurrentUser();
        User find = userRepository.findByAccountId(user.getAccountId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        find.updateProfile(request.getNickname(), request.getCertification());
    }
}
