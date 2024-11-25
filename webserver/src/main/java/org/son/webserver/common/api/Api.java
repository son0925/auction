package org.son.webserver.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.son.webapplicationserver.common.error.ErrorCodeIfs;

@Getter
@AllArgsConstructor
@Builder
public class Api<T> {

    private Result result;

    private T body;


    public static <T>Api<T> OK(T data) {
        return Api.<T>builder()
                .result(Result.OK())
                .body(data)
                .build();
    }

    public static Api<Object> ERROR() {
        return Api.builder()
                .result(Result.ERROR())
                .body(null)
                .build()
                ;
    }

    public static Api<Object> ERROR(ErrorCodeIfs errorCode) {
        return Api.builder()
                .result(Result.ERROR(errorCode))
                .body(null)
                .build()
                ;
    }

    public static Api<Object> ERROR(ErrorCodeIfs errorCode, String description) {
        return Api.builder()
                .result(Result.ERROR(errorCode, description))
                .body(null)
                .build()
                ;
    }

}
