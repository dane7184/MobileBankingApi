package co.istad.mobileBanking.api.account.wep;

import co.istad.mobileBanking.api.accounttype.AccountType;
import lombok.Builder;

@Builder
public record AccountDto(String accountNumber,
                         String accountName,
                         Integer pin,
                         String password,
                         String phoneNumber,
                         Integer transferLimit,
                         AccountType accountType) {
}
