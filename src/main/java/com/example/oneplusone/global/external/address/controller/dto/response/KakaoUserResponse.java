package com.example.oneplusone.global.external.address.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class KakaoUserResponse {
    @JsonProperty("id")
    private String KakaoId;

}
