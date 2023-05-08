package co.istad.mobileBanking.api.account.map;

import co.istad.mobileBanking.api.account.model.Account;
import co.istad.mobileBanking.api.account.wep.AccountDto;
import co.istad.mobileBanking.api.account.wep.CreateAccountDto;
import co.istad.mobileBanking.api.account.wep.UpdateAccountDtO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapStruct {

    Account createAccDtoToAcc(CreateAccountDto createAccountDto);

    AccountDto accToAccDto(Account account);

    Account updateAccountDtoToAccount(UpdateAccountDtO updateAccountDtO);
}
