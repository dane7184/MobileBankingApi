package co.istad.mobileBanking.api.user;

import co.istad.mobileBanking.api.user.wep.CreateUserDto;
import co.istad.mobileBanking.api.user.wep.UpdateUserDto;
import co.istad.mobileBanking.api.user.wep.UserDto;
import com.github.pagehelper.PageInfo;

public interface UserService {

    UserDto createNewUser(CreateUserDto createUserDto);

    UserDto findUserById(Integer id);

    PageInfo<UserDto> findAllUsers(int page, int limit);

    Integer deleteUserById(Integer id);

    Integer updateIsDeletedStatus(Integer id, boolean status);

    UserDto updateUserById(Integer id, UpdateUserDto updateUserDto);
}
