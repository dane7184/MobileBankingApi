package co.istad.mobileBanking.api.account.wep;

import co.istad.mobileBanking.api.accounttype.AccountType;

public record AccountDto(String accountNumber,
                         String accountName,
                         Integer pin,
                         String password,
                         String phoneNumber,
                         Integer transferLimit) {
}
