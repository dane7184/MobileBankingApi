package co.istad.mobileBanking.api.account.wep;

import co.istad.mobileBanking.api.accounttype.AccountType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;


public record CreateAccountDto(@NotBlank (message = "AccountName is not fount") String accountNumber,
                               @NotBlank (message = "AccountName is not fount") String accountName,
                               String profile,
                               Integer pin,
                               String password,
                               String phoneNumber,
                               Integer transferLimit,
                               AccountType accountType) {
}
