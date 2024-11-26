package org.son.webapplicationserver.common.domain.user.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.son.db.user.UserEntity;
import org.son.db.user.enums.Language;
import org.son.db.user.enums.UserRole;
import org.son.db.user.enums.UserStatus;
import org.son.webapplicationserver.common.domain.token.business.TokenBusiness;
import org.son.db.user.UserRepository;
import org.son.webapplicationserver.common.domain.user.model.UserRegisterRequest;
import org.son.webapplicationserver.common.error.UserErrorCode;
import org.son.webapplicationserver.customexception.ApiException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final TokenBusiness tokenBusiness;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    // 로그인
    public UserEntity login(String userId, String password, HttpServletResponse response) {
        // 유저가 존재하는지
        if (!userRepository.existsById(userId)) {
            throw new ApiException(UserErrorCode.ID_PASSWORD_WRONG);
        }

        var userEntity = findByUserIdWithThrow(userId);

        // 비밀번호가 맞는지
        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new ApiException(UserErrorCode.ID_PASSWORD_WRONG);
        }

        // 쿠키에 유저 값 저장하기
        saveToCookie(userId, response);

        userEntity.setLastLoginAt(LocalDateTime.now());

        return userEntity;
    }

    // 회원가입
    public UserEntity register(UserRegisterRequest req) {

        // 아이디 확인
        if (userRepository.existsById(req.getUserId())) {
            throw new ApiException(UserErrorCode.EXISTS_ID);
        }

        // 비밀번호 암호화
        var encodingPassword = passwordEncoder.encode(req.getPassword());

        var saveEntity = UserEntity.builder()
                .id(req.getUserId())
                .password(encodingPassword)
                .name(req.getName())
                .email(req.getEmail())
                .phoneNumber(req.getPhoneNumber())
                .birthdate(req.getBirthdate())
                .profileImageUrl("/profile/default.png")
                .isVerified(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .currentBalance(BigDecimal.valueOf(0))
                .languagePreference(Language.KOREAN)
                .role(UserRole.USER)
                .status(UserStatus.REGISTER)
                .build()
                ;


        return userRepository.save(saveEntity);
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


    // 쿠키에 저장하기
    public void saveToCookie(String userId, HttpServletResponse response) {
        var accessToken = tokenBusiness.createAccessToken(userId);
        Cookie accessCookie = new Cookie("accessToken", accessToken);
        accessCookie.setHttpOnly(true);
        accessCookie.setPath("/");
        accessCookie.setMaxAge(60 * 15);


        var refreshToken = tokenBusiness.createRefreshToken(userId);
        var refreshCookie = new Cookie("refreshToken", refreshToken);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(60 * 60);

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);
    }


    public UserEntity findByUserIdWithThrow(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }
}
