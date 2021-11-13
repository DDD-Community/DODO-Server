package com.dodo.dodolistserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
class DodolistServerApplication

fun main(args: Array<String>) {
    runApplication<DodolistServerApplication>(*args)
}
