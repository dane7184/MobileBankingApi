package co.istad.mobileBanking.api.user;

import co.istad.mobileBanking.api.user.wep.CreateUserDto;
import co.istad.mobileBanking.api.user.wep.UpdateUserDto;
import co.istad.mobileBanking.api.user.wep.UserDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapStruct {
    User createUserDtoToUser(CreateUserDto createUserDto);
    User updateUserDtoToUpdateUser(UpdateUserDto updateUserDto);
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
    PageInfo<UserDto> userPageInfoToUserDtoPageInfo(PageInfo<User> pageInfo);
}
