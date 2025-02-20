package dev.be.feign.service;

import dev.be.feign.common.dto.BaseRequestInfo;
import dev.be.feign.common.dto.BaseResponseInfo;
import dev.be.feign.feign.client.DemoFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemoService {
    private final DemoFeignClient demoFeignClient;
    public String get() {
        ResponseEntity<BaseResponseInfo> response = demoFeignClient.callGet("CustomHeader", "CustomNm", 1L);
        System.out.println("Name : " + response.getBody().getName());
        return "get";
    }
    public String post() {
        BaseRequestInfo baseRequstInfo = BaseRequestInfo.builder()
                .name("CustomNm")
                .age(2L)
                .build();

        ResponseEntity<BaseResponseInfo> response = demoFeignClient.callPost("CustomHeader", baseRequstInfo);
        System.out.println("Name : " + response.getBody().getName());
        return "post";
    }
}
