package co.istad.mobileBanking.api.accounttype;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService{

    private final AccountTypeMapper accountTypeMapper;
    private final AccountTypeMapstruct accountTypeMapstruct;
    @Override
    public List<AccountTypeDto> findAll() {
        List<AccountType> accountTypes = accountTypeMapper.select();
        return accountTypeMapstruct.toDtoList(accountTypes);
    }

    @Override
    public AccountType insert(AccountType accountType) {
        accountTypeMapper.insert(accountType);
        return accountType;
    }

    @Override
    public AccountType delete(int id) {
        accountTypeMapper.delete(id);
        return null;
    }

//    @Override
//    public AccountType update(int id) {
//        accountTypeMapper.update(id);
//        return null;
//    }
}
