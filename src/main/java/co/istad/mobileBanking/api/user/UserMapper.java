package co.istad.mobileBanking.api.user;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface UserMapper {
    /**
     * Insert User 27/04/23
     * @param user
     */
    @InsertProvider(type = UserProvider.class, method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyColumn = "id",keyProperty = "id")
    void insert(@Param("u") User user);

    /**
     * select by ID
     * @param id
     * @return
     */
    @SelectProvider(type = UserProvider.class, method = "buildSelectByIdSql")
    @Results(id = "userResultMap", value = {
            @Result(column = "student_card_id",property = "studentCardId"),
            @Result(column = "is_student",property = "isStudent")
    })
    Optional<User> selectById(@Param("id") Integer id);

    /**
     * select by CardId from user
     * @param studentCardId
     * @return
     */
    @SelectProvider(type = UserProvider.class, method = "buildSelectByCardId")
    @ResultMap("userResultMap")
    Optional<User> selectByCardId(@Param("studentCardId") String studentCardId);

    /**
     * select All user
     * @return
     */
    @SelectProvider(type = UserProvider.class, method = "buildSelectSql")
    @ResultMap("userResultMap")
    List<User> select();

    /**
     * Select By Id form user
     * 27/04/23
     * @param id
     * @return
     */
    @Select("SELECT EXISTS(SELECT * FROM users WHERE id=#{id})")
    boolean existsById(@Param("id") Integer id);

    /**
     * Select email make sure email can register one time
     * 17/05/23
     * @param email
     * @return boolean
     */
    @Select("SELECT EXISTS(SELECT * FROM users WHERE email=#{email})")
    boolean existsByEmail(String email);

    /**
     * Select role if role not found it can not register
     *  17/05/23
     * @param roleId
     * @return boolean
     */

    @Select("SELECT EXISTS(SELECT * FROM roles WHERE id=#{roleId})")
    boolean existByRole(Integer roleId);

    /**
     * Delete user by id 27/04/23
     * @param id
     */
    @DeleteProvider(type = UserProvider.class, method = "buildDeleteSql")
    void deleteById(@Param("id") Integer id);


    /**
     * Update user By Status delete False -> True27/04/23
     * @param id
     * @param status
     */
    @UpdateProvider(type = UserProvider.class, method = "buildUpdateIsDeletedByIdSql")
    void updateIsDeletedById(@Param("id") Integer id,@Param("status") boolean status);

    /**
     * Update user By Id 27/04/23
     * @param user
     */
    @UpdateProvider(type = UserProvider.class, method = "buildUpdateByIdSql")
    void updateById(@Param("u") User user);

}
