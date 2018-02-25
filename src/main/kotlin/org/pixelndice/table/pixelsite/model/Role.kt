package org.pixelndice.table.pixelsite.model

import javax.persistence.*

@Entity
class Role {

    @Id
    var id: Long = 0L

    @Column
    var name: String = ""

    @Override
    override fun toString(): String {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}'
    }
}