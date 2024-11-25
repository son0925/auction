package org.son.db.user;

import jakarta.persistence.*;
import lombok.*;
import org.son.db.user.enums.Language;
import org.son.db.user.enums.UserRole;
import org.son.db.user.enums.UserStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
@ToString
public class UserEntity {

    @Id
    private String id;

    private String password;

    private String name;

    private String email;

    private LocalDateTime birthdate;

    private String phoneNumber;

    private String profileImageUrl;

    private BigDecimal currentBalance;

    private String city;

    private String district;

    private String detailAddress;

    // enum 으로 관리 (REGISTER, UNREGISTER)
    @Setter
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    // enum 으로 관리 (USER, ADMIN)
    @Setter
    @Enumerated(EnumType.STRING)
    private UserRole role;

    // 2차 인증 완료 여부
    @Setter
    private Boolean isVerified;

    // 알림 설정 enum 관리 예정 (전화번호, 이메일 카톡 등등)
    @Setter
    private String notificationPreferences;

    // 사용자 언어 설정
    @Enumerated(EnumType.STRING)
    private Language languagePreference;

    // 찾아보고 관리 예정
    private String timezone;

    private LocalDateTime createdAt;

    @Setter
    private LocalDateTime updatedAt;

    @Setter
    private LocalDateTime lastLoginAt;

}
