package org.son.webserver.common.domain.user;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.son.webapplicationserver.common.domain.user.business.UserBusiness;
import org.son.webapplicationserver.common.domain.user.model.UserLoginRequest;
import org.son.webapplicationserver.common.domain.user.model.UserRegisterRequest;
import org.son.webapplicationserver.common.domain.user.model.UserResponse;
import org.son.webserver.common.api.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open-api/user")
@RequiredArgsConstructor
public class UserOpenApiController {

    private final UserBusiness userBusiness;


    // 로그인
    @PostMapping("/login")
    public Api<UserResponse> login(
            @RequestBody
            UserLoginRequest userLoginRequest,
            HttpServletResponse httpServletResponse
    ) {
        var response = userBusiness.login(userLoginRequest, httpServletResponse);

        return Api.OK(response);
    }


    // 회원가입
    @PostMapping("/register")
    public Api<UserResponse> register(
            @RequestBody
            UserRegisterRequest userRegisterRequest
    ) {
        var response = userBusiness.register(userRegisterRequest);

        System.out.println(response);
        return Api.OK(response);
    }

}
