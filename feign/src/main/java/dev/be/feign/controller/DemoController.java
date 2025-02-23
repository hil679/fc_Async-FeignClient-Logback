package dev.be.feign.controller;

import dev.be.feign.common.dto.BaseRequestInfo;
import dev.be.feign.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
/*
DemoController -> DemoService -> DemoFeignClient -> TargetController -> interceptor -> DemoService -> DemoController
 */
public class DemoController {
    private final DemoService demoService;

    @GetMapping("/get")
    public String get() {
        return demoService.get();
    }
    @PostMapping("/post") //PostMapping이면 "curl -X POST http://localhost:8080/post" 로 test
    public String post() {
        return demoService.post();
    }
    @GetMapping("/error")
    public String error() {
        return demoService.errorDecoder();
    }
}
