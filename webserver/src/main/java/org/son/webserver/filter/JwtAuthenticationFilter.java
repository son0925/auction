package org.son.webserver.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.son.webapplicationserver.common.domain.token.helper.JwtTokenHelper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var url = request.getRequestURI();

        if (url.startsWith("/open-api")) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = getJwtFromRequest(request, "accessToken");

        // 유효성 체크
        if (accessToken != null && jwtTokenHelper.validateToken(accessToken)) {
            String userId = jwtTokenHelper.getUserIdFromToken(accessToken);

//            setAuthentication(userId);
            // TODO 추가적인 인증 정보 설정
        }
        // accessToken 없거나 유효하지 않을 때 refreshToken 유효성 검사
        else {
            String refreshToken = getJwtFromRequest(request, "refreshToken");

            if (refreshToken != null && jwtTokenHelper.validateToken(refreshToken)) {
                String userId = jwtTokenHelper.getUserIdFromToken(refreshToken);
                String newAccessToken = jwtTokenHelper.createAccessToken(userId);

//                setAuthentication(userId);
                addAccessTokenToCookie(response, newAccessToken);
            }
            // refreshToken 없거나 유효하지 않으면 인증 실패 처리
            else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired tokens");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    /*private void setAuthentication(String userId) {
        // ROLE_USER 역할 부여

        // 사용자 식별, 인증된 사용자의 자격 증명, 권한 리스트
        var authentication = new UsernamePasswordAuthenticationToken(userId
                , null
                , Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }*/

    private String getJwtFromRequest(HttpServletRequest request, String key) {
        String bearerToken = request.getHeader(key);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);  // "Bearer " 이후의 토큰만 반환
        }
        return null;
    }

    private void addAccessTokenToCookie(HttpServletResponse response, String newAccessToken) {
        Cookie cookie = new Cookie("accessToken", newAccessToken);
        cookie.setHttpOnly(true);  // JS에서 접근 불가능하도록 설정
//        cookie.setSecure(true);    // HTTPS 환경에서만 쿠키가 전송되도록 설정 (권장)
        cookie.setPath("/");       // 모든 경로에서 쿠키를 사용할 수 있도록 설정
        cookie.setMaxAge(60 * 15);    // 쿠키 유효 기간 (15분)

        response.addCookie(cookie);  // 쿠키 응답에 추가
    }
}
