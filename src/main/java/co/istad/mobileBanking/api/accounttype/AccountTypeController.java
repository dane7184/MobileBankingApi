package co.istad.mobileBanking.api.accounttype;

import co.istad.mobileBanking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account_types")
@RequiredArgsConstructor
public class AccountTypeController {

    private final AccountTypeService accountTypeService;

    @GetMapping
    public BaseRest<?> findAll(){
        var accountTypeDtoList = accountTypeService.findAll();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("Account types have been found")
                .data(accountTypeDtoList)
                .build();
    }

    @PostMapping
    public BaseRest<?> createAcc(@RequestBody AccountType accountType){
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("Account have been created by user")
                .data(accountTypeService.insert(accountType))
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseRest<?> deleteById(@PathVariable("id") int id){
        return BaseRest.builder()
                .massage("Account have been delete Id = [ "+ id + " ]")
                .data(accountTypeService.delete(id))
                .build();
    }

//    @PutMapping("/{id}")
//    public BaseRest<?> updateById(@RequestBody int id){
//        return BaseRest.builder()
//                .massage("Account have been update Id = [ "+ id + " ]")
//                .data(accountTypeService.update(id))
//                .build();
//    }

}
