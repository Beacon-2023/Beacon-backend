package com.BEACON.beacon.region.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class RegionDto {

    private Integer code;

    private String sido;

    private String sigungu;

    private String dong;

    private String legalDongCode;

    public RegionDto(Integer code, String sido, String sigungu, String dong, String legalDongCode) {
        this.code = code;
        this.sido = sido;
        this.sigungu = sigungu;
        this.dong = dong;
        this.legalDongCode = legalDongCode;
    }
}
