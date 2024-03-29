package it.polito.wa2.g07.loginservice.entities

import it.polito.wa2.g07.loginservice.utils.Role
import org.springframework.security.crypto.bcrypt.BCrypt
import javax.persistence.*

/*
    FOR DEGUB PURPOSES
    - INSERT INTO users(id, activated, email, password, roles, username) VALUES (0, TRUE, 'admin@admin.com', '$2a$10$ginDrMw5Ixsow8MzDF1X9ujvv1jbhR2B6dGvftTDqDmJ1B4mweQeG', 'SUPERADMIN', 'admin')

 */

@Entity
@Table(name = "users")
class User(
    @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long?=null,
    var username:String="",
    var roles: String = Role.CUSTOMER.printableName,
    var email:String="",
    password: String = "",
    var activated : Boolean = false,
    @OneToOne(mappedBy = "user", cascade = arrayOf(CascadeType.ALL))
    var activation: Activation?=null
): EntityBase<Long>(){
    @Column
    var password = ""
    init {
        this.password = BCrypt.hashpw(password,BCrypt.gensalt())
    }
    /*
    fun setPasswd(pwd: String){
        this.password = BCrypt.hashpw(pwd,BCrypt.gensalt())
    }
    */

    fun addRole(role: Role){
        if(!this.roles.contains(role.printableName))
            this.roles += "|${role.printableName}"
    }
}
