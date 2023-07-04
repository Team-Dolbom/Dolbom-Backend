package com.example.dolbom.domain.user.present;

import com.example.dolbom.domain.user.present.dto.request.SignUpRequestDto;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid SignUpRequestDto request){
        signupService.signup(request);
    }
}
