package co.istad.mobileBanking.api.user;

import co.istad.mobileBanking.api.user.wep.CreateUserDto;
import co.istad.mobileBanking.api.user.wep.UserDto;

public interface UserService {

    UserDto createNewUser(CreateUserDto createUserDto);

    UserDto findUserById(Integer id);

    Integer deleteUserById(Integer id);

    Integer updateIsDeletedStatus(Integer id, boolean status);
}
