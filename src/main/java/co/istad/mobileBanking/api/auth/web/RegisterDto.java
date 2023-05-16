package co.istad.mobileBanking.api.auth.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RegisterDto(@NotBlank(message = "Email is required!")
                          String email,
                          @NotBlank(message = "Password is required")
                          String password,
                          @NotBlank(message = "Confirmed password is required")
                          String confirmedPassword,
                          @NotNull(message = "Roles are required")
                          List<Integer> roleIds
                          ) {
}
