package co.istad.mobileBanking.api.user;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface UserMapper {

    @InsertProvider(type = UserProvider.class, method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyColumn = "id",keyProperty = "id")
    void insert(@Param("u") User user);

    @SelectProvider(type = UserProvider.class, method = "buildSelectByIdSql")
    @Results(id = "userResultMap", value = {
            @Result(column = "student_card_id",property = "studentCardId"),
            @Result(column = "is_student",property = "isStudent")
    })
    Optional<User> selectById(@Param("id") Integer id);

    @SelectProvider(type = UserProvider.class, method = "buildSelectByCardId")
    @ResultMap("userResultMap")
    Optional<User> selectByCardId(@Param("studentCardId") String studentCardId);

    @SelectProvider(type = UserProvider.class, method = "buildSelectSql")
    @ResultMap("userResultMap")
    void select();

    @Select("SELECT EXISTS(SELECT * FROM users WHERE id=#{id})")
    boolean existsById(@Param("id") Integer id);

    @DeleteProvider(type = UserProvider.class, method = "buildDeleteSql")
    void deleteById(@Param("id") Integer id);


    @UpdateProvider(type = UserProvider.class, method = "buildUpdateIsDeletedByIdSql")
    void updateIsDeletedById(@Param("id") Integer id,@Param("status") boolean status);

    @UpdateProvider(type = UserProvider.class, method = "buildUpdateByIdSql")
    void updateById(@Param("u") User user);
}
