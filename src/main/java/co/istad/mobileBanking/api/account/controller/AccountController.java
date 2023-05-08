package co.istad.mobileBanking.api.account.controller;

import co.istad.mobileBanking.api.account.service.AccountService;
import co.istad.mobileBanking.api.account.wep.AccountDto;
import co.istad.mobileBanking.api.account.wep.CreateAccountDto;
import co.istad.mobileBanking.api.account.wep.UpdateAccountDtO;
import co.istad.mobileBanking.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
@Slf4j
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public BaseRest<?> createNewAcc(@RequestBody @Valid CreateAccountDto createAccountDto){
        AccountDto accountDto = accountService.createNewAcc(createAccountDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("User have been created successfully")
                .timestamp(LocalDateTime.now())
                .data(accountDto)
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> selectUserById(@PathVariable Integer id){
        AccountDto accountDto = accountService.findAccById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("User have been found successfully")
                .timestamp(LocalDateTime.now())
                .data(accountDto)
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseRest<?> deleteAccountById(@PathVariable Integer id){
        try {
            AccountDto accountDto = accountService.findAccById(id);
            if (accountDto==null){
                return BaseRest.builder()
                        .status(false)
                        .code(HttpStatus.BAD_REQUEST.value())
                        .massage("Account don't exit")
                        .timestamp(LocalDateTime.now())
                        .data("Delete fail")
                        .build();
            }
            accountService.deleteAccById(id);
        } catch (Exception e) {
            return BaseRest.builder()
                    .status(false)
                    .code(HttpStatus.BAD_REQUEST.value())
                    .massage("Account fail to delete")
                    .timestamp(LocalDateTime.now())
                    .data("Delete fail")
                    .build();
        }
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("Account delete successfully")
                .timestamp(LocalDateTime.now())
                .data("Delete successful")
                .build();
    }

    @PutMapping("/{id}")
    public BaseRest<?> updateAccountById(@PathVariable Integer id, @RequestBody UpdateAccountDtO updateAccountDtO){
        AccountDto accountDto =accountService.updateById(id, updateAccountDtO);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("Account have been update successfully")
                .timestamp(LocalDateTime.now())
                .data(accountDto)
                .build();
    }
}
