package co.istad.mobileBanking.api.account;

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
            @Result(column = "name",property = "name"),
            @Result(column = "account_name",property = "accountName"),
            @Result(column = "phone_number", property = "phoneNumber")
    })
    Optional<Account> selectAccById(@Param("id") Integer id);
}
