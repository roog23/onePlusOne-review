package com.example.oneplusone.global.external.address.client;

import com.example.oneplusone.global.external.address.controller.dto.response.KakaoTokenResponse;
import com.example.oneplusone.global.external.address.controller.dto.response.KakaoUserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class KakaoAuthClient {

    private final RestClient restClient;
    @Value("${Kakao.api-key}")
    private String apiKey;
    @Value("${Kakao.clientSecret}")
    private String clientSecret;

    public KakaoAuthClient() {
        this.restClient = RestClient.builder().baseUrl("https://kauth.kakao.com").build();
    }

    public KakaoTokenResponse getAccessToken(String code) {
        return restClient.post().uri(uriBuilder -> uriBuilder.path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", apiKey)
                        .queryParam("client_secret", clientSecret)
                        .queryParam("redirect_uri", "http://localhost:8080/auth/kakao")
                        .queryParam("code", code)
                        .build())
                .retrieve()
                .body(KakaoTokenResponse.class);

    }

    public KakaoUserResponse getUser(String accessToken) {
        return RestClient.create()
                .get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .body(KakaoUserResponse.class);
    }
}
