package co.istad.mobileBanking.api.accounttype;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeDto> findAll();

    AccountType insert(AccountType accountType);

    AccountType delete(int id);

//    AccountType update(int id);
}
