package com.example.dolbom.domain.auth.service;

import com.example.dolbom.domain.auth.domain.RefreshToken;
import com.example.dolbom.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.dolbom.domain.auth.exception.RefreshTokenNotFoundException;
import com.example.dolbom.domain.auth.present.dto.response.UserRefreshTokenResponse;
import com.example.dolbom.domain.user.domain.User;
import com.example.dolbom.domain.user.facade.UserFacade;
import com.example.dolbom.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserFacade userFacade;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserRefreshTokenResponse reissue(String refreshToken){
        User user = userFacade.getCurrentUser();

        if(!jwtTokenProvider.getTokenBody(refreshToken).get("type").equals("refresh"))
            throw RefreshTokenNotFoundException.EXCEPTION;

        RefreshToken refreshToken1 = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        String userAccountId = refreshToken1.getAccountId();

        String newRefreshToken = jwtTokenProvider.generateRefreshToken(userAccountId);
        refreshToken1.updateToken(newRefreshToken);

        String accessToken = jwtTokenProvider.generateAccessToken(userAccountId);

        return UserRefreshTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .authority(user.getAuthority())
                .build();
    }
}

