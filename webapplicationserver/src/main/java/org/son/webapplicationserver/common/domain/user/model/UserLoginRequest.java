package org.son.webapplicationserver.common.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.son.webapplicationserver.common.annotation.Password;
import org.son.webapplicationserver.common.annotation.UserId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {

    @UserId
    private String userId;

    @Password
    private String password;

}
