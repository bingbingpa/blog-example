package me.bingbingpa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
class SpringAsyncConfigApplication

fun main(args: Array<String>) {
    runApplication<SpringAsyncConfigApplication>(*args)
}
