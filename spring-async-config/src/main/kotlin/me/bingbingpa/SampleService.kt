package me.bingbingpa

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class SampleService {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(SampleService::class.java.name)
    }

    @Async
    fun async1() {
        Thread.sleep(3000)
        logger.info(Thread.currentThread().name)
    }

    @Async("secondAsyncExecutor")
    fun async2() {
        Thread.sleep(10000)
        logger.info(Thread.currentThread().name)
    }
}