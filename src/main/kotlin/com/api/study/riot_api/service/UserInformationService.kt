package com.api.study.riot_api.service

import com.api.study.riot_api.config.SecurityConfig
import com.api.study.riot_api.domain.dto.JwtDto
import com.api.study.riot_api.domain.dto.UpdateUserDto
import com.api.study.riot_api.domain.entity.LolUser
import com.api.study.riot_api.domain.entity.Token
import com.api.study.riot_api.domain.entity.User
import com.api.study.riot_api.domain.entity.ValUser
import com.api.study.riot_api.exception.CustomException
import com.api.study.riot_api.exception.ErrorCode
import com.api.study.riot_api.repository.AccountRepository
import com.api.study.riot_api.repository.LolRepository
import com.api.study.riot_api.repository.TokenRepository
import com.api.study.riot_api.repository.ValRepository
import com.api.study.riot_api.security.JwtToken
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserInformationService(
    private var accountRepository: AccountRepository,
    private var tokenRepository: TokenRepository,
    private var lolRepository: LolRepository,
    private var valRepository: ValRepository,
    private var jwtToken: JwtToken
) {
    @Transactional
    fun update(userDto: UpdateUserDto, id: String, accessToken: String) {
        val user = accountRepository.findById(id).get()
        if (jwtToken.validateToken(accessToken)) {
            jwtToken.makeJwtAccessToken(user.idx, user.mail)
        }

//        user.updateUserRiotName(
//            LolUser(),
//            userDto.valName
//        );

        lolRepository.save(LolUser(user.idx, lolUserName = userDto.lolName))
        accountRepository.save(user)
        valRepository.save(ValUser(user.idx, valUserName = userDto.valName))
    }

    fun deleteUser(id: Long, accessToken: String) {
        if (jwtToken.validateToken(accessToken)) {
            throw CustomException(ErrorCode.TOKEN_NOT_FOUND_FORBIDDEN_ERROR)
        }

        tokenRepository.deleteById(id)
        lolRepository.deleteById(id)
        valRepository.deleteById(id)
        accountRepository.deleteById(id)
    }


}