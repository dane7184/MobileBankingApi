package co.istad.mobileBanking.api.account;

import co.istad.mobileBanking.api.accounttype.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Integer id;
    private String accountNumber;
    private String accountName;
    private String profile;
    private Integer pin;
    private String phoneNumber;
    private String password;
    private Integer transferLimit;
    private List<AccountType> accountType;
}
