package co.istad.mobileBanking.api.user.wep;

import co.istad.mobileBanking.api.user.UserService;
import co.istad.mobileBanking.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping
    public BaseRest<?> createNewUser(@RequestBody @Valid CreateUserDto createUserDto){
        UserDto userDto = userService.createNewUser(createUserDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("User have been created successfully")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }
    @GetMapping("/{id}")
    public BaseRest<?> selectUserById(@PathVariable Integer id){
        UserDto userDto =userService.findUserById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("User have been found successfully")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }

    @GetMapping
    public BaseRest<?> findAllUsers(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                    @RequestParam(name = "limit", required = false, defaultValue = "20") int limit){
        PageInfo<UserDto> userDtoPageInfo = userService.findAllUsers(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("User have been found successfully")
                .timestamp(LocalDateTime.now())
                .data(userDtoPageInfo)
                .build();
    }

    @DeleteMapping("{id}")
    public BaseRest<?> deleteUserById(@PathVariable Integer id){
        Integer deleteId = userService.deleteUserById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("User have been delete successfully")
                .timestamp(LocalDateTime.now())
                .data(deleteId)
                .build();
    }

    @PutMapping("{id}/is-deleted")
    public BaseRest<?> updateUserStatusById(@PathVariable Integer id, @RequestBody IsDeleteDto isDeleteDto){
        Integer deleted = userService.updateIsDeletedStatus(id, isDeleteDto.status());
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("User have been update status successfully")
                .timestamp(LocalDateTime.now())
                .data(deleted)
                .build();
    }

    @PutMapping("/{id}")
    public BaseRest<?> updateUserById(@PathVariable("id") Integer id,@RequestBody UpdateUserDto updateUserDto){
        UserDto userDto = userService.updateUserById(id, updateUserDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .massage("User have been update successfully")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }
}
