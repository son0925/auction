package org.son.webserver.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.son.webapplicationserver.common.error.ErrorCodeIfs;

@Getter
@AllArgsConstructor
@Builder
public class Result {

    private Integer httpStatusCode;

    private Integer serverStatusCode;

    private String description;


    public static Result OK() {
        return Result.builder()
                .httpStatusCode(200)
                .serverStatusCode(200)
                .description("응답 성공")
                .build()
                ;
    }

    public static Result ERROR() {
        return Result.builder()
                .httpStatusCode(500)
                .serverStatusCode(500)
                .description("서버 에러 발생")
                .build()
                ;
    }

    public static Result ERROR(ErrorCodeIfs errorCode) {
        return Result.builder()
                .httpStatusCode(errorCode.getHttpStatusCode())
                .serverStatusCode(errorCode.getServerStatusCode())
                .description(errorCode.getDescription())
                .build()
                ;
    }

    public static Result ERROR(ErrorCodeIfs errorCode, String description) {
        return Result.builder()
                .httpStatusCode(errorCode.getHttpStatusCode())
                .serverStatusCode(errorCode.getServerStatusCode())
                .description(description)
                .build()
                ;
    }
}
