package org.son.db.social.kakao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "kakao_user")
public class KakaoEntity {

    @Id
    private String kakaoId; // 카카오 제공자의 고유 ID

    private String email; // 카카오 계정 이메일

    private String nickname; // 사용자 닉네임

    private String profileImageUrl; // 프로필 이미지 URL

    private LocalDateTime connectedAt; // 카카오 계정 연결 시간
}
