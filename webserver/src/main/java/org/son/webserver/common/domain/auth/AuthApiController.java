package org.son.webserver.common.domain.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.son.webapplicationserver.common.domain.user.model.UserLoginRequest;
import org.son.webapplicationserver.common.domain.user.model.UserResponse;
import org.son.webapplicationserver.common.domain.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApiController {

    private final UserService userService;

    @PostMapping("/login")
    public UserResponse login(
            @RequestBody
            UserLoginRequest userLoginRequest
    ) {
        log.info("hi");
        return userService.login(userLoginRequest);
    }

}
