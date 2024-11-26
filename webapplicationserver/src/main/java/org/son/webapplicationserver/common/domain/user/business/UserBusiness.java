package org.son.webapplicationserver.common.domain.user.business;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.son.webapplicationserver.common.annotation.Business;
import org.son.webapplicationserver.common.domain.user.model.UserLoginRequest;
import org.son.webapplicationserver.common.domain.user.model.UserRegisterRequest;
import org.son.webapplicationserver.common.domain.user.model.UserResponse;
import org.son.webapplicationserver.common.domain.user.service.UserConverter;
import org.son.webapplicationserver.common.domain.user.service.UserService;
import org.son.webapplicationserver.common.error.ErrorCode;
import org.son.webapplicationserver.customexception.ApiException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;

    // 로그인
    public UserResponse login(UserLoginRequest userLoginRequest, HttpServletResponse response) {
        if (Objects.isNull(userLoginRequest) || userLoginRequest.getUserId() == null || userLoginRequest.getPassword() == null) {
            throw new ApiException(ErrorCode.NULL_POINT_EXCEPTION);
        }

        var userId = userLoginRequest.getUserId();
        var password = userLoginRequest.getPassword();

        var userEntity = userService.login(userId, password, response);
        return userConverter.toResponse(userEntity);
    }

    // 회원가입
    @Transactional
    public UserResponse register(UserRegisterRequest userRegisterRequest) {
        var entity = userService.register(userRegisterRequest);

        return userConverter.toResponse(entity);
    }

    // 회원 정보
    public UserResponse info(String userId) {
        var entity = userService.findByUserIdWithThrow(userId);

        return userConverter.toResponse(entity);
    }

    // 회원 정보 수정
    public UserResponse updateUserInfo() {
        return null;
    }
}
