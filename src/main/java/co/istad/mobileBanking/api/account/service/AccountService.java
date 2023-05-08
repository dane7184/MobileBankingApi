package co.istad.mobileBanking.api.account.service;

import co.istad.mobileBanking.api.account.wep.AccountDto;
import co.istad.mobileBanking.api.account.wep.CreateAccountDto;
import co.istad.mobileBanking.api.account.wep.UpdateAccountDtO;

public interface AccountService {
    AccountDto createNewAcc(CreateAccountDto createAccountDto);

    AccountDto findAccById(Integer id);

    void deleteAccById(Integer id) throws Exception;

    AccountDto updateById(Integer id, UpdateAccountDtO updateAccountDtO);
}
