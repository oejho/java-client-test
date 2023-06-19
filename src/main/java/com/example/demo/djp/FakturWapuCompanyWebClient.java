package com.example.demo.djp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Component
@Slf4j
public class FakturWapuCompanyWebClient {

    @Value("${efaktur.wapu.host}")
    String EFAKTUR_WAPU_HOST;

    @Value("${efaktur.wapu.auth}")
    String EFAKTUR_WAPU_AUTH;

    private final RestTemplate restTemplate;

    public FakturWapuCompanyWebClient() {
        this.restTemplate = new RestTemplate();
    }


    public ResponseEntity<String> checkNpwp(String jsonReq) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic " + EFAKTUR_WAPU_AUTH);

        String endpoint = UriComponentsBuilder.fromUriString(EFAKTUR_WAPU_HOST + "/api/v1/batch-validate-npwp").toUriString();

        log.info("check npwp to DJP: " + endpoint);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(endpoint, new HttpEntity<>(jsonReq, headers), String.class);

        log.info("response : " + responseEntity);

        return responseEntity;


    }


}
