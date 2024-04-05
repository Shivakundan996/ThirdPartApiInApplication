package com.cgi.ThirdPartyAPISpringBoot.postService.Impl;

import com.cgi.ThirdPartyAPISpringBoot.postService.PostService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    String baseUrl = "https://jsonplaceholder.typicode.com/";
    StringBuilder stringBuilder = new StringBuilder(baseUrl);


    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Map<String, Object>> getPosts() {
        String POST = "/posts";
        HttpEntity<Void> httpEntity = new HttpEntity<>(getHttpHeaders());
        String url = stringBuilder.append(POST).toString();
        val exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, List.class);
        return exchange.getBody();
    }

    @Override
    public Map<String, Object> getPostById(int id) {
        String POSTBYID = "/posts/";
        HttpEntity<Void> httpEntity = new HttpEntity<>(getHttpHeaders());
        String url = stringBuilder.append(POSTBYID).append(id).toString();
        val exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Map.class);
        return exchange.getBody();
    }

    @Override
    public Map<String, Object> insertPosts(Map<String, Object> payload) {
        String POSTS = "/posts";
        HttpEntity<Map> httpEntity = new HttpEntity<>(payload,getHttpHeaders());
        String url = stringBuilder.append(POSTS).toString();
        val exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);
        return exchange.getBody();    }

    private HttpHeaders getHttpHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
