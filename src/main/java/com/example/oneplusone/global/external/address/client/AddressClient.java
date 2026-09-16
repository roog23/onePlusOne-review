package com.example.oneplusone.global.external.address.client;

import com.example.oneplusone.global.external.address.controller.dto.response.KakaoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AddressClient {

    private final RestClient restClient;
    @Value("${Kakao.api-key}")
    private String apiKey;

    public AddressClient() {
        this.restClient = RestClient.builder().baseUrl("https://dapi.kakao.com/v2/local/search/address.json?").build();
    }
    public KakaoResponse searchAddress(String address) {
        return restClient.get().uri(uriBuilder -> uriBuilder.queryParam("query",address).build())
                .header("Authorization", "KakaoAK " + apiKey)
                .retrieve()
                .body(KakaoResponse.class);
    }
}
