package org.pixelndice.table.pixelsite.service

import org.pixelndice.table.pixelsite.model.Account
import org.pixelndice.table.pixelsite.web.dto.UserRegistrationDto
import org.springframework.security.core.userdetails.UserDetailsService

import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model

interface AccountService : UserDetailsService {

    fun findByEmail(email: String): Account?

    fun save(registration: UserRegistrationDto): Account
}