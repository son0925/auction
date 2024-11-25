package org.son.webapplicationserver.common.error;

public interface ErrorCodeIfs {

    Integer getHttpStatusCode();

    Integer getServerStatusCode();

    String getDescription();
}
