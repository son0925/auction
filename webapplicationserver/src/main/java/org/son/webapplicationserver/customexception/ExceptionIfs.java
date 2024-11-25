package org.son.webapplicationserver.customexception;

import org.son.webapplicationserver.common.error.ErrorCodeIfs;

public interface ExceptionIfs {

    ErrorCodeIfs getErrorCodeIfs();

    String getDescription();

}
