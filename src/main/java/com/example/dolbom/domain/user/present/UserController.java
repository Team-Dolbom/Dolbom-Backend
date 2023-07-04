package com.example.dolbom.domain.user.present;

import com.example.dolbom.domain.user.present.dto.request.SignInRequest;
import com.example.dolbom.domain.user.present.dto.request.SignUpRequest;
import com.example.dolbom.domain.user.present.dto.request.UpdatePasswordRequest;
import com.example.dolbom.domain.user.present.dto.request.UpdateProfileRequest;
import com.example.dolbom.domain.user.present.dto.response.MyPageResponse;
import com.example.dolbom.domain.user.present.dto.response.TokenResponse;
import com.example.dolbom.domain.user.service.MyPageService;
import com.example.dolbom.domain.user.service.SignInService;
import com.example.dolbom.domain.user.service.SignupService;
import com.example.dolbom.domain.user.service.UpdatePasswordService;
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
    private final UpdatePasswordService updatePasswordService;
    private final MyPageService myPageService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid SignUpRequest request){
        signupService.signup(request);
    }

    @PostMapping("/signin")
    public TokenResponse signIn(@RequestBody @Valid SignInRequest request){
        return signInService.signIn(request);
    }

    @PutMapping("/password")
    public void updatePass(@RequestBody @Valid UpdatePasswordRequest request){
        updatePasswordService.passwordUpdate(request);
    }

    @GetMapping("/mypage")
    public MyPageResponse myPage(){
        return myPageService.loadByProfile();
    }

    @PutMapping("/mypage")
    public void updateProfile(@RequestBody UpdateProfileRequest request){
        myPageService.patchProfile(request);
    }
}
