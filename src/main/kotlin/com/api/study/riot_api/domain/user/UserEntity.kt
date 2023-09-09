package com.api.study.riot_api.domain.user

import org.springframework.security.crypto.password.PasswordEncoder
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "User")
data class UserEntity (
    @Id
    val userId: UUID,
    val id: String,
    val name: String,
    var password: String
){
   fun hashPassword(passwordEncoder: PasswordEncoder){
       this.password = passwordEncoder.encode(this.password)
   }

}