package org.son.webapplicationserver.common.domain.user.service;

import org.son.db.user.UserEntity;
import org.son.webapplicationserver.common.annotation.Converter;
import org.son.webapplicationserver.common.domain.user.model.UserResponse;

@Converter
public class UserConverter {

    public UserResponse toResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .userId(userEntity.getId())
                .name(userEntity.getName())
                .profileImageUrl(userEntity.getProfileImageUrl())
                .build()
                ;
    }

}
