package co.istad.mobileBanking.api.account;

import co.istad.mobileBanking.api.account.wep.AccountDto;
import co.istad.mobileBanking.api.account.wep.CreateAccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapStruct {

    Account createAccDtoToAcc(CreateAccountDto createAccountDto);

    AccountDto accToAccDto(Account account);
}
