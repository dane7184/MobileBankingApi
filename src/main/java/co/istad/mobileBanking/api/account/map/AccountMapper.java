package co.istad.mobileBanking.api.account.map;

import co.istad.mobileBanking.api.account.AccountProvider;
import co.istad.mobileBanking.api.account.model.Account;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface AccountMapper {
    @InsertProvider(type = AccountProvider.class, method = "createAccSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insertAcc(@Param("a") Account account);

    @SelectProvider(type = AccountProvider.class, method = "buildSelectAccByIdSql")
    @Results(id = "accountResulMap", value = {
            @Result(column = "account_name",property = "accountName"),
            @Result(column = "account_no", property = "accountNumber"),
            @Result(column = "profile", property = "profile"),
            @Result(column = "pin", property = "pin"),
            @Result(column = "password", property = "password"),
            @Result(column = "phone_number", property = "phoneNumber"),
            @Result(column = "transfer_limit", property = "transferLimit"),
            @Result(column = "account_type", property = "accountType.id")
    })
    Optional<Account> selectAccById(@Param("id") Integer id);

    @Select("SELECT EXISTS(SELECT * FROM accounts WHERE id=#{id})")
    boolean existsById(@Param("id") Integer id);

    @DeleteProvider(type = AccountProvider.class, method = "buildDeleteAccountById")
    void deleteAccountById(Integer id);

    @UpdateProvider(type = AccountProvider.class, method = "buildUpdateAccountById")
    void updateAccountById(@Param("a") Account account);
}
