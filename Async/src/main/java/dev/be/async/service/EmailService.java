package dev.be.async.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    // no matter what, public (never private)
    @Async("defaultTaskExecutor")
    public void sendMail() {
        System.out.println("[sendMail] :: " + Thread.currentThread().getName());
    }
    @Async("messagingTaskExecutor")
    public void sendMailWithCustomThreadPool() {
        System.out.println("[messagingTaskExecutor] :: " + Thread.currentThread().getName());
    }
}
