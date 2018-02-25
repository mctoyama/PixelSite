package org.pixelndice.table.pixelsite.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {

    @GetMapping("/")
    fun root(): String {
        return "index"
    }

    @GetMapping("/login")
    fun login(model: Model): String {
        return "login"
    }

    @GetMapping("/user")
    fun userIndex(): String {
        return "user/index"
    }
}