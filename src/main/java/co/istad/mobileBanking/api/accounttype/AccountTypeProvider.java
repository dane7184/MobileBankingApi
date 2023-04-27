package co.istad.mobileBanking.api.accounttype;

import org.apache.ibatis.jdbc.SQL;

public class AccountTypeProvider {
    public String buildSelectSql(){
        return new SQL(){{
            SELECT("*");
            FROM("account_types");
        }}.toString();
    }
    public String buildInsertSql(){
        return new SQL(){{
            INSERT_INTO("account_types")
                    .VALUES("name","#{name}");
        }}.toString();
    }
    public String buildDeleteSql(){
        return new SQL(){{
            DELETE_FROM("account_types")
                    .WHERE("id = #{id}");
        }}.toString();
    }

    public  String buildUpdateSql(){
        return new SQL(){{
            UPDATE("account_types")
                    .SET("name = #{name}")
                    .WHERE("id =#{id}");
        }}.toString();
    }
}
