package dev.be.feign.service;

import org.springframework.stereotype.Service;

@Service
public class DemoService {
    public String get() {
        return "get";
    }
}
