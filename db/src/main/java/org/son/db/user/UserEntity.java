package org.son.db.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
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
    private String status;

    // enum 으로 관리 (USER, ADMIN)
    private String role;

    // 2차 인증 완료 여부
    private Boolean isVerified;

    // 알림 설정 enum 관리 예정 (전화번호, 이메일 카톡 등등)
    private String notificationPreferences;

    // 사용자의 시간대 enum 관리 예정 (KOREA, USA 등등)
    private String languagePreference;

    // 찾아보고 관리 예정
    private String timezone;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime lastLoginAt;
}
