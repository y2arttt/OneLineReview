package com.oneLineReview.oneLineReview.external;

import com.oneLineReview.oneLineReview.common.exception.customException.BasicException;
import com.oneLineReview.oneLineReview.common.exception.errorCode.BasicErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
@PropertySource("classpath:apikey.properties")
public class NaverAPI {

    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    private final RestTemplate restTemplate;

    public NaverAPI() {
        this.restTemplate = new RestTemplate();
    }

    public BookItemDTO ISBNSearch(String isbn) {
        log.info("NaverAPI.searchAPI - keyword: {}, page: {}", isbn, 1);
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/book.json")
                .queryParam("query", isbn)
                .queryParam("display", 1)
                .encode()
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<NaverResult> response = restTemplate.exchange(
                uri, HttpMethod.GET, entity, NaverResult.class);
        return response.getBody().getItems().get(0);

    }

    public NaverResult searchAPI(String keyword, int page) {
        log.info("NaverAPI.searchAPI - keyword: {}, page: {}", keyword, page);

        int start = (page - 1) * 10 + 1;

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/book.json")
                .queryParam("query", keyword)
                .queryParam("display", 10)
                .queryParam("start", start)
                .queryParam("sort", "sim")
                .encode()
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<NaverResult> response = restTemplate.exchange(
                uri, HttpMethod.GET, entity, NaverResult.class);


        return response.getBody();

    }
}
