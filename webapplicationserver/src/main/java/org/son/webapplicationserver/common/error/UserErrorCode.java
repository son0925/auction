package org.son.webapplicationserver.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCodeIfs{

    USER_NOT_FOUND(400, 404, "유저를 찾을 수 없습니다."),
    ID_PASSWORD_WRONG(400, 400, "아이디 혹은 비밀번호가 틀립니다."),
    EXISTS_ID(400, 400, "이미 존재하는 아이디입니다.")
    ;

    private final Integer httpStatusCode;

    private final Integer serverStatusCode;

    private final String description;
}
