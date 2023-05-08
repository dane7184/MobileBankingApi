package co.istad.mobileBanking.api.account.service;

import co.istad.mobileBanking.api.account.model.Account;
import co.istad.mobileBanking.api.account.map.AccountMapStruct;
import co.istad.mobileBanking.api.account.map.AccountMapper;
import co.istad.mobileBanking.api.account.wep.AccountDto;
import co.istad.mobileBanking.api.account.wep.CreateAccountDto;
import co.istad.mobileBanking.api.account.wep.UpdateAccountDtO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;
    private final AccountMapStruct accountMapStruct;

    @Override
    public AccountDto createNewAcc(CreateAccountDto createAccountDto) {
        Account account = accountMapStruct.createAccDtoToAcc(createAccountDto);
        accountMapper.insertAcc(account);
        log.info("account + {}",account.getId());
        return this.findAccById(account.getId());
    }

    @Override
    public AccountDto findAccById(Integer id) {
        Account account = accountMapper.selectAccById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with %d is not found",id)));
        return  accountMapStruct.accToAccDto(account);
    }

    @Override
    public void deleteAccById(Integer id) throws Exception{
        accountMapper.deleteAccountById(id);
    }

    @Override
    public AccountDto updateById(Integer id, UpdateAccountDtO updateAccountDtO) {
        if (accountMapper.existsById(id)){
            Account account = accountMapStruct.updateAccountDtoToAccount(updateAccountDtO);
            account.setId(id);
            accountMapper.updateAccountById(account);
            return this.findAccById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d is not found",id));
    }
}
