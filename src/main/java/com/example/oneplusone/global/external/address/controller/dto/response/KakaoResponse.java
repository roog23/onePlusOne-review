package com.example.oneplusone.global.external.address.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.util.List;

@Getter
public class KakaoResponse {
    private List<Document> documents;

    @Getter
    public static class Document {
        @JsonProperty("address_name")
        private String addressName;

        @JsonProperty("address_type")
        private String addressType;

        @JsonProperty("road_address")
        private RoadAddress roadAddress;
    }

    @Getter
    public static class RoadAddress {
        @JsonProperty("zone_no")
        private String zoneNo;
    }
}
