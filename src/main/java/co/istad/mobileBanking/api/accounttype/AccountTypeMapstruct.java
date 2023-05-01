package co.istad.mobileBanking.api.accounttype;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapstruct {

    List<AccountTypeDto> toDtoList(List<AccountType> model);
    AccountTypeDto toDto(AccountType model);
    AccountType updateAccTypeById(UpdateAccTypeDto updateAccTypeDto);

}
