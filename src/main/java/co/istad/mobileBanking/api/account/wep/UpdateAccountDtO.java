package co.istad.mobileBanking.api.account.wep;

import jakarta.validation.constraints.NotBlank;

public record UpdateAccountDtO(@NotBlank(message = "Pin is required") Integer pin,
                               @NotBlank(message = "Password id required") String password) {
}
