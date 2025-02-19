package dev.be.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AppConfig {
    @Bean(name = "defaultTaskExecutor", destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor defaultTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(200); // the number of minimum thread
        executor.setMaxPoolSize(300); // the number of maximum thread
//        executor.setQueueCapacity ->default: Integer max
//        executor.setKeepAliveSeconds -> default : 60

        return executor;
    }
    @Bean(name = "messagingTaskExecutor", destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor messagingTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(200); // the number of minimum thread
        executor.setMaxPoolSize(300); // the number of maximum thread
//        executor.setQueueCapacity ->default: Integer max
//        executor.setKeepAliveSeconds -> default : 60

        return executor;
    }
}
