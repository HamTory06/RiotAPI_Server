package com.api.study.riot_api.controller

import com.api.study.riot_api.domain.dto.UpdateUserDto
import com.api.study.riot_api.domain.entity.User
import com.api.study.riot_api.service.LoginService
import com.api.study.riot_api.service.UserInformationService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/update")
class UserInformationController {
    @Autowired
    private lateinit var userInformationService: UserInformationService

    private val logger: Logger = LoggerFactory.getLogger(UserInformationController::class.java)

    @PutMapping("/users/{id}")
    fun userUpdate(
        @RequestHeader("Authorization") accessToken: String,
        @PathVariable("id") id: Long,
        @RequestBody updateUserDto: UpdateUserDto
    ): UpdateUserDto {
        return userInformationService.update(updateUserDto, id, accessToken)
    }
}