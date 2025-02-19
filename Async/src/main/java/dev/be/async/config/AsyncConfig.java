package dev.be.async.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync // only need this for async
// It's config file only for EnableAsync
public class AsyncConfig {
}

// for change default TaskExecutor(instead of SimpleAsyncTaskExecutor(default)
//@Configuration
//@EnableAsync
//public class AsyncConfig implements AsyncConfigurer {
//
//    @Bean
//    public TaskExecutor defaultTaskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(3);
//        executor.setMaxPoolSize(6);
//        executor.setQueueCapacity(20);
//        executor.setThreadNamePrefix("DefaultExecutor-");
//        executor.initialize();
//        return executor;
//    }
//
//    @Override
//    public Executor getAsyncExecutor() {
//        return defaultTaskExecutor();
//    }
//}

