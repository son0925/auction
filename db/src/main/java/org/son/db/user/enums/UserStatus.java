package org.son.db.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    REGISTER("등록"),
    UNREGISTER("등록해제"),


    ;

    private final String description;
}
