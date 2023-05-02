package co.istad.mobileBanking.api.accounttype;

import co.istad.mobileBanking.api.accounttype.web.AccountTypeDto;
import co.istad.mobileBanking.api.accounttype.web.UpdateAccTypeDto;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeDto> findAll();

    AccountTypeDto findById(int id);

    AccountType insert(AccountType accountType);

    AccountType delete(int id);

    AccountTypeDto updateById(int id, UpdateAccTypeDto updateAccTypeDto);
}
