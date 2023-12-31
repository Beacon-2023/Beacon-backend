package com.BEACON.beacon.shelter.mapper;

import com.BEACON.beacon.shelter.domain.Shelter;
import com.BEACON.beacon.shelter.dto.ShelterDto;
import com.BEACON.beacon.shelter.dto.ShelterResponseDto;

public class ShelterMapper {
    public static Shelter toEntity(ShelterDto dto) {
        return Shelter.builder()
                .id(dto.getId())
                .shelterCategory(dto.getShelterCategory())
                .name(dto.getName())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .tel(dto.getTel())
                .detailAddress(dto.getDetailAddress())
                .regionCode(dto.getRegionCode())
                .build();
    }

    public static ShelterDto toDto(Shelter shelter) {
        return ShelterDto.builder()
                .id(shelter.getId())
                .shelterCategory(shelter.getShelterCategory())
                .name(shelter.getName())
                .latitude(shelter.getLatitude())
                .longitude(shelter.getLongitude())
                .tel(shelter.getTel())
                .detailAddress(shelter.getDetailAddress())
                .regionCode(shelter.getRegionCode())
                .build();
    }

    public static ShelterResponseDto toResponseDto(ShelterDto dto) {
        return ShelterResponseDto.builder()
                .name(dto.getName())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .tel(dto.getTel())
                .detailAddress(dto.getDetailAddress())
                .build();
    }
}
