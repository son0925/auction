package org.son.webapplicationserver.customexception;

import lombok.Getter;
import org.son.webapplicationserver.common.error.ErrorCode;
import org.son.webapplicationserver.common.error.ErrorCodeIfs;

@Getter
public class ApiException extends RuntimeException implements ExceptionIfs {

    private ErrorCodeIfs errorCodeIfs;

    private String description;

    public ApiException() {
        super();
    }

    public ApiException(ErrorCodeIfs errorCodeIfs) {
        super(errorCodeIfs.getDescription());
        this.errorCodeIfs = errorCodeIfs;
        this.description = errorCodeIfs.getDescription();
    }

    public ApiException(ErrorCodeIfs errorCodeIfs, String description) {
        super(description);
        this.errorCodeIfs = errorCodeIfs;
        this.description = description;
    }

    public ApiException(ErrorCodeIfs errorCodeIfs, Exception e) {
        super(e);
        this.errorCodeIfs = errorCodeIfs;
        this.description = e.getMessage();
    }

}
