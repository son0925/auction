package org.son.webserver.common.domain.auth;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.son.webapplicationserver.common.domain.user.business.UserBusiness;
import org.son.webapplicationserver.common.domain.user.model.UserLoginRequest;
import org.son.webapplicationserver.common.domain.user.model.UserResponse;
import org.son.webserver.common.api.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApiController {

    private final UserBusiness userBusiness;

    @PostMapping("/login")
    public Api<UserResponse> login(
            @RequestBody
            UserLoginRequest userLoginRequest,
            HttpServletResponse httpServletResponse
    ) {
        var response = userBusiness.login(userLoginRequest, httpServletResponse);

        return Api.OK(response);
    }

}
