package co.istad.mobileBanking.api.account;

import co.istad.mobileBanking.api.account.wep.AccountDto;
import co.istad.mobileBanking.api.account.wep.CreateAccountDto;

public interface AccountService {
    AccountDto createNewAcc(CreateAccountDto createAccountDto);

    AccountDto findAccById(Integer id);
}
