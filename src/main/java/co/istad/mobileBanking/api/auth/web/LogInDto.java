package co.istad.mobileBanking.api.auth.web;

import co.istad.mobileBanking.api.user.validator.passowrd.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LogInDto(@Email
                       @NotBlank
                       String email,
                       @Password
                       @NotBlank
                       String password) {
}
