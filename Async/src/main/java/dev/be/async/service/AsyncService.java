package dev.be.async.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {
    private final EmailService emailService;
    /*
         bean injection
         result: each thread executors run
                 [asyncCall_1] :: http-nio-8080-exec-1
                 [sendMail] :: defaultTaskExecutor-1
                 [messagingTaskExecutor] :: messagingTaskExecutor-1

         => emailService is registered as bean.
            And this bean works asynchronously, so this bean is wrapped as proxy object
            Then, AsyncService would recieved wrapped EmailService bean, not a pure one.

            - 비동기로 동작할 수 있게 Sub Thread에게 위임해준다.
            - emailService.sendMail()

            !!!important!!! : need to use bean registered in Spring Container
     */
    public void asyncCall_1() {
        System.out.println("[asyncCall_1] :: " + Thread.currentThread().getName());
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();
    }
    /*
         not bean injection, just making local instance
         result: same thread process works  (synchronously because Spring framework can't assist
                 [messagingTaskExecutor] :: http-nio-8080-exec-2)
                 [asyncCall_2] :: http-nio-8080-exec-2
                 [sendMail] :: http-nio-8080-exec-2
     */
    public void asyncCall_2() {
        System.out.println("[asyncCall_2] :: " + Thread.currentThread().getName());
        EmailService emailService1 = new EmailService();
        emailService1.sendMail();
        emailService1.sendMailWithCustomThreadPool();
    }

    /*
        Async annotation in same class
        result: same thread process works
                [asyncCall_3] :: http-nio-8080-exec-3
                [sendMail] :: http-nio-8080-exec-3
        
        AsyncService already gets bean(AsyncService),
        and if it access method in current bean directly,
        Spring framework can't assist (wrapping as a proxy object)
     */
    public void asyncCall_3() {
        System.out.println("[asyncCall_3] :: " + Thread.currentThread().getName());
        sendMail();
    }

    @Async // => working Synchronously in same bean(AsyncService)
    public void sendMail() {
        System.out.println("[sendMail] :: " + Thread.currentThread().getName());
    }
}
