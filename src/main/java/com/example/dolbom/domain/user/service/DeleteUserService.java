package com.example.dolbom.domain.user.service;

import com.example.dolbom.domain.user.domain.User;
import com.example.dolbom.domain.user.domain.repository.UserRepository;
import com.example.dolbom.domain.user.exception.UserAccessDeniedException;
import com.example.dolbom.domain.user.exception.UserNotFoundException;
import com.example.dolbom.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteUserService {
    private final UserRepository userRepository;

    private final UserFacade userFacade;

    @Transactional
    public void deleteUser(String accountId){
        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);

        User currentUser = userFacade.getCurrentUser();

        if(!currentUser.getAccountId().equals(user.getAccountId())){
            throw UserAccessDeniedException.EXCEPTION;
        }

        userRepository.deleteUserByAccountId(accountId);

    }
}
