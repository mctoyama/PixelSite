package org.pixelndice.table.pixelsite.service

import org.pixelndice.table.pixelsite.model.Account
import org.pixelndice.table.pixelsite.model.Role
import org.pixelndice.table.pixelsite.repository.AccountRepository
import org.pixelndice.table.pixelsite.web.dto.UserRegistrationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class AccountServiceImpl : AccountService {

    @Autowired
    private var accountRepository: AccountRepository? = null

    @Autowired
    private var passwordEncoder: BCryptPasswordEncoder? = null

    override fun loadUserByUsername(email: String): UserDetails {
        val user = accountRepository!!.findByEmail(email)
        if (user == null){
            throw UsernameNotFoundException("Invalid username or password.")
        }
        return org.springframework.security.core.userdetails.User(user.email,
                user.password,
                mapRolesToAuthorities(user.roles))
    }

    override fun findByEmail(email: String): Account? {
        return accountRepository?.findByEmail(email)
    }

    override fun save(registration: UserRegistrationDto): Account{
        val account = Account()
        account.firstName = registration.firstName
        account.lastName = registration.lastName
        account.email = registration.email
        account.password = passwordEncoder!!.encode(registration.password)

        val role = Role()
        role.name = "ROLE_USER"

        account.roles = mutableSetOf(role)
        return accountRepository!!.save(account);
    }

    fun mapRolesToAuthorities(roles: MutableSet<Role>): List<GrantedAuthority>{

        return roles.stream().map{SimpleGrantedAuthority(it.name)}.collect(Collectors.toList())
    }
}