package org.son.webapplicationserver.common.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    private String userId;

    private String password;

    private String name;

    private LocalDateTime birthdate;

    private String phoneNumber;

    private String email;

}
