package co.istad.mobileBanking.api.accounttype;

import jakarta.validation.constraints.NotBlank;

public record UpdateAccTypeDto(@NotBlank(message = "Name is required") String name) {
}
