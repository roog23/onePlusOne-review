package com.example.oneplusone.global.external.address.controller;

import com.example.oneplusone.domain.common.dto.ApiResponse;
import com.example.oneplusone.global.external.address.controller.dto.response.AddressResponse;
import com.example.oneplusone.global.external.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AddressResponse>>> searchAddress(@RequestParam String address) {
        List<AddressResponse> addressResponseList = addressService.search(address);
        return ResponseEntity.ok(ApiResponse.ok("주소가 전달되었습니다.", addressResponseList));
    }
}
