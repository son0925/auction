package org.son.webapplicationserver.common.domain.auth;

import lombok.extern.slf4j.Slf4j;
import org.son.db.user.UserEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

    public UserEntity login(UserEntity userEntity) {
        return userEntity;
    }

}
