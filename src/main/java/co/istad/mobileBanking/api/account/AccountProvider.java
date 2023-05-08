package co.istad.mobileBanking.api.account;

import org.apache.ibatis.jdbc.SQL;

public class AccountProvider {

    private static final String tableName = "accounts";
    public String createAccSql(){
        return new SQL(){{
            INSERT_INTO(tableName);
            VALUES("account_no","#{a.accountNumber}");
            VALUES("account_name","#{a.accountName}");
            VALUES("profile", "#{a.profile}");
            VALUES("pin","#{a.pin}");
            VALUES("password", "#{a.password}");
            VALUES("phone_number", "#{a.phoneNumber}");
            VALUES("transfer_limit", "#{a.transferLimit}");
            VALUES("account_type", "#{a.accountType.id}");
        }}.toString();
    }

    public String buildSelectAccByIdSql(){
        return new SQL() {{
            SELECT("*");
            FROM(tableName);
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildDeleteAccountById(){
        return  new SQL() {{
            DELETE_FROM(tableName);
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildUpdateAccountById(){
        return new SQL() {{
            UPDATE(tableName);
            SET("pin=#{a.pin}");
            SET("password=#{a.password}");
            WHERE("id=#{a.id}");
        }}.toString();
    }
}
