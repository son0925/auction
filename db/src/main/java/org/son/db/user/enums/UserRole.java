package org.son.db.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

    ADMIN("관리자"),
    USER("일반 유저"),

    ;

    private final String role;
}
