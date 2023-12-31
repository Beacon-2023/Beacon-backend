package com.BEACON.beacon.location.service;

import com.BEACON.beacon.location.dao.RegionTokenRepository;
import com.BEACON.beacon.location.domain.RegionTokenEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class LocationApiServiceTest {

    @Autowired
    private  LocationApiService locationApiService;

    @Autowired
    private  RegionTokenRepository regionTokenRepository;

    static Double longitude;
    static Double latitude;
    static String fcmToken;


    @BeforeAll
    static void setUp(){
        longitude = 127.1086228;
        latitude = 37.4012191;
        fcmToken = "ab123ce3as";
    }


    @Test
    void 법정동코드반환() throws Exception {
        String legalCode = locationApiService.findLegalDongCode(longitude,latitude);


        assertEquals("정확한 법정동코드가 반환되지 않음","4113510900",legalCode);

    }

    @Test
    void 법정동코드와FCM토큰저장(){
        locationApiService.storeRegionAndFcmToken(fcmToken,"4113510900");
        Optional<RegionTokenEntity> code = regionTokenRepository.findById(fcmToken);
        RegionTokenEntity entity = code.get();

        assertNotNull(entity);
        assertEquals("원하는 토큰을 못가져옴",fcmToken, entity.getFcmToken());



    }

    @Test
    void FCM토큰이미존재할시_교체(){
        locationApiService.storeRegionAndFcmToken(fcmToken,"4113510900");
        locationApiService.storeRegionAndFcmToken(fcmToken,"1235322245");
        Optional<RegionTokenEntity> code = regionTokenRepository.findById(fcmToken);
        RegionTokenEntity entity = code.get();

        assertEquals("법정동코드 교체가 안됨","1235322245",entity.getLegalDongCode());

    }

    @Test
    void 법정동코드로FCM토큰들반환(){
        locationApiService.storeRegionAndFcmToken("abcdef","4113510900");
        locationApiService.storeRegionAndFcmToken("123456","4113510900");
        locationApiService.storeRegionAndFcmToken("1w2q3e4r","411312345");
        locationApiService.storeRegionAndFcmToken("ab123ef","333333333");
        locationApiService.storeRegionAndFcmToken("ab411f","4113510900");

        //같은 legalDongCode에 속하는 fcm토큰들 반환
        List<String> fcmTokenList = locationApiService.findFcmTokenByLegalDongList(Arrays.asList("4113510900","411312345"));



        for (String token : fcmTokenList) {
            assertThat(token).isIn("abcdef", "ab411f", "123456", "1w2q3e4r");
        }
        assertThat(fcmTokenList).isNotIn("ab123ef");



    }

    @Test
    void 존재하지않은법정동코드일때(){
       List<String> fcmTokenList =  locationApiService.findFcmTokenByLegalDongList(Arrays.asList("1234"));
        System.out.println("fcmTokenList = " + fcmTokenList);
    }

}