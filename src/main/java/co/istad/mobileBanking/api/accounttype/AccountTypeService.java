package co.istad.mobileBanking.api.accounttype;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeDto> findAll();

    AccountTypeDto findById(int id);

    AccountType insert(AccountType accountType);

    AccountType delete(int id);

    AccountTypeDto updateById(int id, UpdateAccTypeDto updateAccTypeDto);
}
