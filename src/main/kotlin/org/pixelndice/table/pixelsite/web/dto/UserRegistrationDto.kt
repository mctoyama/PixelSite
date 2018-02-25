package org.pixelndice.table.pixelsite.web.dto

import org.pixelndice.table.pixelsite.constraint.FieldMatch
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotEmpty
import org.pixelndice.table.pixelsite.constraint.FieldsMatches
import javax.validation.constraints.AssertTrue


@FieldsMatches([FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
                FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")])
class UserRegistrationDto {

    @NotEmpty
    var firstName: String? = null

    @NotEmpty
    var lastName: String? = null

    @NotEmpty
    var password: String? = null

    @NotEmpty
    var confirmPassword: String? = null

    @Email
    @NotEmpty
    var email: String? = null

    @Email
    @NotEmpty
    var confirmEmail: String? = null

    @AssertTrue
    var terms: Boolean? = null

}