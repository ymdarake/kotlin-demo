package com.example.ymdarake.kotlinspringdemo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {
    @GetMapping("")
    fun index(): String = "hello, world!"
}