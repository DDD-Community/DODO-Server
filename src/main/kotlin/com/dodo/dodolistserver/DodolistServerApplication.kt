package com.dodo.dodolistserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DodolistServerApplication

fun main(args: Array<String>) {
    runApplication<DodolistServerApplication>(*args)
}
