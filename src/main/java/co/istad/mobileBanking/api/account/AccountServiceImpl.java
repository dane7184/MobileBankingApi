package co.istad.mobileBanking.api.account;

import co.istad.mobileBanking.api.account.wep.AccountDto;
import co.istad.mobileBanking.api.account.wep.CreateAccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService{

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
}
