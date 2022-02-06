package me.bingbingpa.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.scheduling.annotation.AsyncConfigurerSupport
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Component
import java.lang.reflect.Method
import java.util.concurrent.Executor
import java.util.concurrent.RejectedExecutionHandler
import java.util.concurrent.ThreadPoolExecutor

@EnableAsync
@Configuration
class MultipleAsyncConfig : AsyncConfigurerSupport() {

    @Autowired
    lateinit var customRejectedExecutionHandler: CustomRejectedExecutionHandler
    @Autowired
    lateinit var asyncExceptionHandler: AsyncExceptionHandler

    @Primary
    @Bean("firstAsyncExecutor")
    override fun getAsyncExecutor(): Executor {
        return ThreadPoolTaskExecutor().apply {
            corePoolSize = 10
            maxPoolSize = 50
            setQueueCapacity(1000)
            setThreadNamePrefix("first-async")
            setRejectedExecutionHandler(customRejectedExecutionHandler)
            initialize()
        }
    }

    @Bean("secondAsyncExecutor")
    fun getSecondAsyncExecutor(): Executor {
        return ThreadPoolTaskExecutor().apply {
            corePoolSize = 5
            maxPoolSize = 30
            setQueueCapacity(100)
            setThreadNamePrefix("second-async")
            setRejectedExecutionHandler(customRejectedExecutionHandler)
            initialize()
        }
    }

    override fun getAsyncUncaughtExceptionHandler() = asyncExceptionHandler
}

@Component
class CustomRejectedExecutionHandler : RejectedExecutionHandler {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(CustomRejectedExecutionHandler::class.java.name)
    }

    override fun rejectedExecution(r: Runnable?, executor: ThreadPoolExecutor?) {
        logger.error("reject execution. maximumPoolSize = ${executor?.maximumPoolSize}")
    }
}

@Component
class AsyncExceptionHandler : AsyncUncaughtExceptionHandler {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(AsyncExceptionHandler::class.java.name)
    }

    override fun handleUncaughtException(ex: Throwable, method: Method, vararg params: Any?) {
        logger.error("Unexpected asynchronous exception at: ${method.declaringClass.name}.${method.name}, params: $params", ex)
    }
}