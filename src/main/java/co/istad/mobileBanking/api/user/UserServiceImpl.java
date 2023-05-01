package co.istad.mobileBanking.api.user;

import co.istad.mobileBanking.api.user.wep.CreateUserDto;
import co.istad.mobileBanking.api.user.wep.UpdateUserDto;
import co.istad.mobileBanking.api.user.wep.UserDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;
    private final UserMapStruct userMapStruct;

    @Override
    public UserDto createNewUser(CreateUserDto createUserDto) {
        User user = userMapStruct.createUserDtoToUser(createUserDto);
        userMapper.insert(user);
        log.info("user + {}",user.getId());
        return this.findUserById(user.getId());
    }

    @Override
    public UserDto findUserById(Integer id) {
        User user = userMapper.selectById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with %d is not found",id))
                );
        return userMapStruct.userToUserDto(user);
    }

    @Override
    public UserDto findUserByCardId(String studentCardId) {
        User user = userMapper.selectByCardId(studentCardId).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with %s is not found",studentCardId)));
        return userMapStruct.userToUserDto(user);
    }

    @Override
    public PageInfo<UserDto> findAllUsers(int page, int limit) {
        PageInfo<User> userPageInfo =PageHelper.startPage(page, limit)
                .doSelectPageInfo(userMapper::select);
        return userMapStruct.userPageInfoToUserDtoPageInfo(userPageInfo);
    }

    @Override
    public Integer deleteUserById(Integer id) {
        boolean isFound = userMapper.existsById(id);
        if (isFound){
            userMapper.deleteById(id);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d is not found",id));
    }

    @Override
    public Integer updateIsDeletedStatus(Integer id, boolean status) {
        boolean isExisted = userMapper.existsById(id);
        if (isExisted){
            userMapper.updateIsDeletedById(id,status);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d is not found",id));
    }

    @Override
    public UserDto updateUserById(Integer id, UpdateUserDto updateUserDto) {
        if (userMapper.existsById(id)){
            User user = userMapStruct.updateUserDtoToUpdateUser(updateUserDto);
            user.setId(id);
            userMapper.updateById(user);
            return this.findUserById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d is not found",id));
    }
}
