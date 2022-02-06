package me.bingbingpa.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@Configuration
@EnableAsync
class SingleAsyncConfig {

    @Bean
    fun singleAsync(): Executor {
        return ThreadPoolTaskExecutor().apply {
            corePoolSize = 10
            maxPoolSize = 50
            setQueueCapacity(1000)
            setThreadNamePrefix("sample-async")
            initialize()
        }
    }
}
