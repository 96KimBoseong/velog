package com.example.velog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VelogApplication

fun main(args: Array<String>) {
    runApplication<VelogApplication>(*args)
}
