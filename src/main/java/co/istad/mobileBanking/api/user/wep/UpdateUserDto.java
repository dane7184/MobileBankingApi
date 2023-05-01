package co.istad.mobileBanking.api.user.wep;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserDto(@NotBlank(message = "Name is required") String name,
                            @NotBlank(message = "Gender is required") String gender) {
}
