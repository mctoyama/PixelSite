package org.pixelndice.table.pixelsite.web

import org.pixelndice.table.pixelsite.model.Account
import org.pixelndice.table.pixelsite.service.AccountService
import org.pixelndice.table.pixelsite.web.dto.UserRegistrationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/registration")
class AccountRegistrationController {

    @Autowired
    private var accountService: AccountService? = null

    @ModelAttribute("user")
    fun userRegistrationDto(): UserRegistrationDto {
        return UserRegistrationDto()
    }

    @GetMapping
    fun showRegistrationForm(model: Model): String {
        return "registration"
    }

    @PostMapping
    fun registerUserAccount(@ModelAttribute("user")
                            @Valid
                            userDto: UserRegistrationDto,
                            result: BindingResult): String{

        val existing: Account? = accountService?.findByEmail(userDto.email!!)
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email")
        }

        if (result.hasErrors()){
            return "registration"
        }

        accountService!!.save(userDto)
        return "redirect:/registration?success"
    }

}