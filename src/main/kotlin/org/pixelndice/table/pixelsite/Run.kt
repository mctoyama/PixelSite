package org.pixelndice.table.pixelsite

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Run {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Run::class.java, *args)
        }
    }
}