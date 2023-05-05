package co.istad.mobileBanking.api.account.wep;

import co.istad.mobileBanking.api.account.AccountService;
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
}
