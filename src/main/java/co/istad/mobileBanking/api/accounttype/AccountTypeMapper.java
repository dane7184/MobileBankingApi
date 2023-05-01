package co.istad.mobileBanking.api.accounttype;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface AccountTypeMapper {


    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectSql")
    List<AccountType> select();

    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectByIdSql")
    @Result(column = "id", property = "id")
    Optional<AccountType> selectByID(@Param("id") int id);

    @Select("SELECT EXISTS (SELECT * FROM account_types WHERE id = #{id})")
    boolean existsById(@Param("id") int id);

    @InsertProvider(type = AccountTypeProvider.class, method = "buildInsertSql")
    void insert(AccountType accountType);

    @DeleteProvider(type = AccountTypeProvider.class, method = "buildDeleteSql")
    void delete(int id);

    @UpdateProvider(type = AccountTypeProvider.class, method = "buildUpdateSql")
    void updateById(@Param("a") AccountType accountType);
}
