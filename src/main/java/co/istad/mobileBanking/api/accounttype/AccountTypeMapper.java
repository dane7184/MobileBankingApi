package co.istad.mobileBanking.api.accounttype;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountTypeMapper {


    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectSql")
    List<AccountType> select();

    @InsertProvider(type = AccountTypeProvider.class, method = "buildInsertSql")
    void insert(AccountType accountType);

    @DeleteProvider(type = AccountTypeProvider.class, method = "buildDeleteSql")
    void delete(int id);

//    @UpdateProvider(type = AccountTypeProvider.class, method = "buildUpdateSql")
//    void update(int id);
}
