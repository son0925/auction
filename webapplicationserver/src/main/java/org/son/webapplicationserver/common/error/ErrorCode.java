package org.son.webapplicationserver.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ErrorCodeIfs {

    SERVER_ERROR(500, 500, "서버 에러 발생"),
    NULL_POINT_EXCEPTION(400, 404, "Null Point Exception"),
    BAD_REQUEST(400, 400, "잘못된 요청"),

    ;


    private final Integer httpStatusCode;

    private final Integer serverStatusCode;

    private final String description;
}
