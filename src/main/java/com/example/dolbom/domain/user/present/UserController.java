package com.example.dolbom.domain.user.present;

import com.example.dolbom.domain.user.present.dto.request.SignInRequest;
import com.example.dolbom.domain.user.present.dto.request.SignUpRequest;
import com.example.dolbom.domain.user.present.dto.response.TokenResponse;
import com.example.dolbom.domain.user.service.SignInService;
import com.example.dolbom.domain.user.service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final SignupService signupService;
    private final SignInService signInService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid SignUpRequest request){
        signupService.signup(request);
    }

    @PostMapping("/signin")
    public TokenResponse signIn(@RequestBody @Valid SignInRequest request){
        return signInService.signIn(request);
    }
}
