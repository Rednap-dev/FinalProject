package com.rednap.finalproject.mapper;

import com.rednap.finalproject.model.dto.UserInfo;
import com.rednap.finalproject.model.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserInfo toUserInfo(final UserEntity userEntity) {
        final UserInfo userInfo = new UserInfo(
                userEntity.getID(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getRole().name()
        );

        return userInfo;
    }

}
