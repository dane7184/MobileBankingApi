package co.istad.mobileBanking.api.auth.web;

import co.istad.mobileBanking.api.user.validator.email.EmailUnique;
import co.istad.mobileBanking.api.user.validator.passowrd.Password;
import co.istad.mobileBanking.api.user.validator.passowrd.PasswordMatch;
import co.istad.mobileBanking.api.user.validator.role.RoleIdConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@PasswordMatch(message = "password is not match", password = "password", confirmPassword = "confirmedPassword")
public record RegisterDto(@NotBlank(message = "Email is required!")
                          @EmailUnique
                          @Email
                          String email,
                          @NotBlank(message = "Password is required")
                          @Password
                          String password,
                          @NotBlank(message = "Confirmed password is required")
                          @Password
                          String confirmedPassword,
                          @NotNull(message = "Roles are required")
                          @RoleIdConstraint
                          List<Integer> roleIds
                          ) {
}
