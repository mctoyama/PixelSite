package org.pixelndice.table.pixelsite.repository

import org.pixelndice.table.pixelsite.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model

@Repository
interface AccountRepository : JpaRepository<Account, Long> {

    fun findByEmail(email: String): Account?

}