package co.istad.mobileBanking.api.user;

import co.istad.mobileBanking.api.user.wep.CreateUserDto;
import co.istad.mobileBanking.api.user.wep.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapStruct {
    User createUserDtoToUser(CreateUserDto createUserDto);

    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
}
