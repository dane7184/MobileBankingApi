package co.istad.mobileBanking.api.user;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    private static final String tableName = "users";

    public String buildInsertSql(@Param("u" )User user){
        return new SQL(){{
            INSERT_INTO(tableName);
            VALUES("name","#{u.name}");
            VALUES("gender","#{u.gender}");
            VALUES("one_signal_id","#{u.oneSignalId}");
            VALUES("student_card_id","#{u.studentCardId}");
            VALUES("is_student","#{u.isStudent}");
            VALUES("is_deleted","FALSE");
        }}.toString();
    }

    public String buildSelectSql(){
        return new SQL() {{
            SELECT("*");
            FROM("users");
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildDeleteSql(){
        return  new SQL() {{
            DELETE_FROM("users");
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildUpdateIsDeletedByIdSql(){
        return  new SQL(){{
            UPDATE("users");
            SET("is_deleted=#{status}");
            WHERE("id=#{id}");
        }}.toString();
    }
}
