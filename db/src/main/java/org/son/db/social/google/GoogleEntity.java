package org.son.db.social.google;

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
@Table(name = "google_user")
public class GoogleEntity {

    @Id
    private String googleId; // 구글 제공자의 고유 ID

    private String email; // 구글 계정 이메일

    private String name; // 사용자 이름

    private String profileImageUrl; // 프로필 이미지 URL

    private LocalDateTime connectedAt; // 구글 계정 연결 시간
}
