package org.son.webserver.common.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.son.webapplicationserver.common.domain.user.business.UserBusiness;
import org.son.webapplicationserver.common.domain.user.model.UserResponse;
import org.son.webapplicationserver.common.error.UserErrorCode;
import org.son.webapplicationserver.customexception.ApiException;
import org.son.webserver.common.api.Api;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserBusiness userBusiness;

    @GetMapping("/info")
    public Api<UserResponse> userInfo() {
        var userId = getUserIdWithThrow();
        var response = userBusiness.info(userId);

        return Api.OK(response);
    }



    public String getUserIdWithThrow() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof String)) {
            throw new ApiException(UserErrorCode.LOGIN_AGAIN);
        }

        return (String) authentication.getPrincipal();
    }

}
