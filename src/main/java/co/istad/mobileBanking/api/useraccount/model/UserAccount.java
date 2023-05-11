package co.istad.mobileBanking.api.useraccount.model;

import co.istad.mobileBanking.api.account.model.Account;
import co.istad.mobileBanking.api.user.User;

import java.time.LocalDateTime;

public class UserAccount {
    private Integer id;
    private User user;
    private Account account;
    private LocalDateTime time;
    private Boolean isDisabled;

}
