package com.example.oneplusone.global.external.address.service;

import com.example.oneplusone.global.external.address.client.AddressClient;
import com.example.oneplusone.global.external.address.controller.dto.response.AddressResponse;
import com.example.oneplusone.global.external.address.controller.dto.response.KakaoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressClient addressClient;

    public List<AddressResponse> search(String address) {
        KakaoResponse kakaoResponse = addressClient.searchAddress(address);

        return kakaoResponse.getDocuments().stream()
                .map(document -> new AddressResponse(
                        document.getAddressName(), document.getRoadAddress().getZoneNo()))
                    .toList();

    }
}
