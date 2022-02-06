package me.bingbingpa

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController(
    private val sampleService: SampleService
) {

    @GetMapping("/async1")
    fun async1(): String {
        sampleService.async1()
        return "async1"
    }

    @GetMapping("/async2")
    fun async2(): String {
        sampleService.async2()
        return "async2"
    }
}