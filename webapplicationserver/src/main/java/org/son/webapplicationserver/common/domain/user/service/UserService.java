package org.son.webapplicationserver.common.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.son.db.user.UserEntity;
import org.son.webapplicationserver.common.domain.user.model.UserLoginRequest;
import org.son.webapplicationserver.common.domain.user.model.UserResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    // 로그인
    public UserResponse login(UserLoginRequest userLoginRequest) {
        return UserResponse.builder()
                .userId(userLoginRequest.getId())
                .name("테스트 유저입니다.")
                .build();
    }

    // 회원가입
    public UserEntity register() {
        return null;
    }

    // 아이디 중복 체크
    public Boolean checkIDDuplication() {
        return null;
    }

    // 비밀번호 변경
    public UserEntity ChangePassword() {
        return null;
    }

    // 프로필 사진 변경
    public UserEntity changeProfileUrl() {
        return null;
    }

    // 계정 탈퇴
    public void deleteAccount() {

    }

    // 내 정보 변경
    public UserEntity changeUserInfo() {
        return null;
    }

}
