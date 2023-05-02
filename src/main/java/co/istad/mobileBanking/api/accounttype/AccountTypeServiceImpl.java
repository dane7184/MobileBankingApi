package co.istad.mobileBanking.api.accounttype;

import co.istad.mobileBanking.api.accounttype.web.AccountTypeDto;
import co.istad.mobileBanking.api.accounttype.web.UpdateAccTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public AccountTypeDto findById(int id) {
        AccountType accountType = accountTypeMapper.selectByID(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with %d is not found",id))
        );
        return accountTypeMapstruct.toDto(accountType);
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

    @Override
    public AccountTypeDto updateById(int id, UpdateAccTypeDto updateAccTypeDto) {
        AccountType accountType;
        if (accountTypeMapper.existsById(id)){
            accountType = accountTypeMapstruct.updateAccTypeById(updateAccTypeDto);
            accountType.setId(id);
            accountTypeMapper.updateById(accountType);
            return this.findById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d is not found",id));
    }

}
