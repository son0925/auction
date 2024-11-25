package org.son.webserver.common.domain.user;

import lombok.extern.slf4j.Slf4j;
import org.son.webapplicationserver.common.domain.user.model.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @GetMapping("/info")
    public UserResponse userInfo() {

        return null;
    }

}
