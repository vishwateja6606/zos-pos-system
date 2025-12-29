package com.zosh.zosh.pos.system.service;

import com.zosh.zosh.pos.system.payload.dto.UserDto;

public interface UserService {

    UserDto getLoggedInUser();

    UserDto updateLoggedInUser(UserDto userDto);
}
