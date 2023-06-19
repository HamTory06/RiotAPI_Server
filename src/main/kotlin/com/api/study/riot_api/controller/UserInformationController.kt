package com.api.study.riot_api.controller

import com.api.study.riot_api.domain.dto.UpdateUserDto
import com.api.study.riot_api.domain.entity.User
import com.api.study.riot_api.service.UserInformationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/update")
class UserInformationController() {
    @Autowired
    private lateinit var userInformationService: UserInformationService
    @PutMapping("/users/{id}")
    fun userUpdate(
        @RequestHeader("Authorization") accessToken: String,
        @PathVariable("id") id: String,
        @RequestBody updateUserDto: UpdateUserDto
    ) {
        userInformationService.update(updateUserDto, id, accessToken)
    }
}