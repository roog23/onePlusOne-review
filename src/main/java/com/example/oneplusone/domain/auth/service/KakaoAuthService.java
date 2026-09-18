package com.example.oneplusone.domain.auth.service;

import com.example.oneplusone.domain.auth.controller.dto.LoginResponse;
import com.example.oneplusone.domain.auth.entity.User;
import com.example.oneplusone.domain.auth.enums.UserRole;
import com.example.oneplusone.domain.auth.repository.UserRepository;
import com.example.oneplusone.domain.common.security.JwtUtil;
import com.example.oneplusone.global.external.address.client.KakaoAuthClient;
import com.example.oneplusone.global.external.address.controller.dto.response.KakaoTokenResponse;
import com.example.oneplusone.global.external.address.controller.dto.response.KakaoUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {
    private final KakaoAuthClient kakaoAuthClient;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public LoginResponse kakaoLogin(String code) {

        KakaoTokenResponse accessToken = kakaoAuthClient.getAccessToken(code);

        KakaoUserResponse userResponse = kakaoAuthClient.getUser(accessToken.getAccessToken());

        String kakaoId = String.valueOf(userResponse.getKakaoId());

        User user = userRepository.findByLoginId(kakaoId)
                .orElseGet(() -> {
                    User newUser = new User(
                            "Kakao User " + kakaoId,
                            kakaoId,
                            "",
                            UserRole.BUYER,
                            "",
                            "",
                            ""
                    );

                    return userRepository.save(newUser);
                });

        String token = jwtUtil.createAccessToken(user.getId(), user.getLoginId(), user.getUserRole());

        return new LoginResponse(token);

    }

}
