package com.api.study.riot_api.service

import com.api.study.riot_api.config.SecurityConfig
import com.api.study.riot_api.domain.dto.JwtDto
import com.api.study.riot_api.domain.dto.LoginRequestDTO
import com.api.study.riot_api.domain.dto.UserInformationRequestDto
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
import java.util.*

@Service
class LoginService(
    private var accountRepository: AccountRepository,
    private var lolRepository: LolRepository,
    private var valRepository: ValRepository,
    private var securityConfig: SecurityConfig,
    private var jwtToken: JwtToken,
    private var tokenRepository: TokenRepository
) {

    private var lolUserData: LolUser? = null
    private var valUserData: ValUser? = null

    fun execute(requestDTO: LoginRequestDTO): UserInformationRequestDto {
        val user: User = accountRepository.findById(requestDTO.id)
            .orElseThrow { CustomException(ErrorCode.USER_NOT_FOUND_BAD_REQUEST) }
        val lolUser: Optional<LolUser> = lolRepository.findById(user.idx)
        val valUser: Optional<ValUser> = valRepository.findById(user.idx)
        if(lolUser.isPresent){
            lolUserData = lolUser.get()
        }
        if(valUser.isPresent){
            valUserData = valUser.get()
        }

        tokenRepository.save(Token(accessToken = jwtToken.makeJwtAccessToken(user.idx, user.mail), refreshToken = jwtToken.makeJwtRefreshToken(), idx = user.idx))
        val token: Token = tokenRepository.findById(user.idx).get()

        return UserInformationRequestDto(user.idx, user.id, user.name, user.mail, lolUserName = lolUserData?.lolUserName?:"", valUserData?.valUserName?:"",  JwtDto(token.accessToken!!,token.refreshToken!!))

    }

}