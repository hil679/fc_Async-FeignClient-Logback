package dev.be.feign.feign.config;

import dev.be.feign.feign.interceptor.DemoFeignInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// just for Feign declared client
public class DemoFeignConfig {
    @Bean
    public DemoFeignInterceptor feignInterceptor() {
        return DemoFeignInterceptor.of();
    }
}
