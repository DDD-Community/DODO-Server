package com.dodo.dodolistserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class DodolistServerApplication

fun main(args: Array<String>) {
    runApplication<DodolistServerApplication>(*args)
}
