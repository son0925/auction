package org.son.db.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    REGISTER("회원 등록 상태"),
    UNREGISTER("회원 탈퇴 상태"),


    ;

    private final String status;

}
