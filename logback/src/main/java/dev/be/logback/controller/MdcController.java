package dev.be.logback.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MdcController {
    /*
    Mdc: multithread환경에서 로그를 남기기 위해 사용하는 개념
    thread별로 MDC에 들은 값 관리
     */

    @GetMapping("/mdc")
    public String mdc() {
        /*
        다음에 다른 요청 때 아래 put한 값을 정리하지 않을 경우,
        다음 thread가 get job하면 dev 얻어버림.
        따라서 put, clear를 pair단위로 하기

        log에서 저장 값 동적으로 가져옴
         */
        MDC.put("job", "dev");

        log.trace("TRACE");
        log.warn("WARN");
        log.error("ERR");
        MDC.clear();
        return  "mdc";
    }
}
