package co.istad.mobileBanking.api.auth.controller;

import co.istad.mobileBanking.api.auth.service.AuthService;
import co.istad.mobileBanking.api.auth.web.AuthDto;
import co.istad.mobileBanking.api.auth.web.LogInDto;
import co.istad.mobileBanking.api.auth.web.RegisterDto;
import co.istad.mobileBanking.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthRestController {

    private final AuthService authService;
    @PostMapping("/login")
    public BaseRest<?> login(@Valid @RequestBody LogInDto logInDto){

        AuthDto authDto = authService.login(logInDto);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("You have login successfully")
                .timestamp(LocalDateTime.now())
                .data(authDto)
                .build();
    }

    @PostMapping("/register")
    public BaseRest<?> register(@Valid @RequestBody RegisterDto registerDto){

        authService.register(registerDto);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("You have been registered successfully")
                .timestamp(LocalDateTime.now())
                .data(registerDto.email())
                .build();
    }

    @PostMapping("/verify")
    public BaseRest<?> verify(@RequestParam String email){
        authService.verify(email);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("Please check your email and verify")
                .timestamp(LocalDateTime.now())
                .data(email)
                .build();
    }

    @GetMapping("/check-verify")
    public BaseRest<?> checkVerify(@RequestParam String email,
                                   @RequestParam String verifiedCode){
        log.info("Email.: {}",email);
        log.info("Verified Code: {}", verifiedCode);

        authService.checkVerify(email, verifiedCode);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("You have been successfully")
                .timestamp(LocalDateTime.now())
                .data(email)
                .build();
    }

}
