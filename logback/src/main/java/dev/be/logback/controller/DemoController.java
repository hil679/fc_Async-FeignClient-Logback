package dev.be.logback.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DemoController {
    //console2 appender에서 아래와 같은 로그 확인 가능, level이 INFO라 INFO이상만 확인 가능
    /*
    CONSOLE2] [INFO ] 2025-03-17 22:29:41 [http-nio-8080-exec-2] [d.be.logback.controller.DemoController:14] - LOG INFO
    [CONSOLE2] [WARN ] 2025-03-17 22:29:41 [http-nio-8080-exec-2] [d.be.logback.controller.DemoController:15] - LOG WARN
    [CONSOLE2] [ERROR] 2025-03-17 22:29:41 [http-nio-8080-exec-2] [d.be.logback.controller.DemoController:16] - LOG ERROR
     */
    @GetMapping("/demo")
    public String demo() {
        log.trace("LOG TRACE");
        log.debug("LOG DEBUG");
        log.info("LOG INFO");
        log.warn("LOG WARN");
        log.error("LOG ERROR");

        return "demo";
    }
}
