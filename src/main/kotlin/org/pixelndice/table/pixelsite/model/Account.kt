package org.pixelndice.table.pixelsite.model

import javax.persistence.*

@Entity
class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column
    var firstName: String? = null

    @Column
    var lastName: String? = null

    @Column(unique=true)
    var email: String? = null

    @Column
    var password: String? = null

    @ManyToMany(fetch = FetchType.EAGER)
    var roles: MutableSet<Role> = mutableSetOf<Role>()

    constructor() {}

    constructor(firstName: String, lastName: String, email: String, password: String) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
    }

    constructor(firstName: String, lastName: String, email: String, password: String, roles: MutableSet<Role>) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
        this.roles = roles
    }

    override fun toString(): String {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\''.toString() +
                ", lastName='" + lastName + '\''.toString() +
                ", email='" + email + '\''.toString() +
                ", password='" + "*********" + '\''.toString() +
                ", roles=" + roles +
                '}'.toString()
    }
}
