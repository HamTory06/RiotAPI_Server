package com.api.study.riot_api.service

import com.api.study.riot_api.config.SecurityConfig
import com.api.study.riot_api.domain.dto.JwtDto
import com.api.study.riot_api.domain.dto.LoginRequestDto
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
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class LoginService(
    private var accountRepository: AccountRepository,
    private var lolRepository: LolRepository,
    private var valRepository: ValRepository,
    private var jwtToken: JwtToken,
    private var tokenRepository: TokenRepository,
    private var securityConfig: SecurityConfig
) {

    private val logger: Logger = LoggerFactory.getLogger(LoginService::class.java)

    private lateinit var userData: User
    private var lolUserData: LolUser? = null
    private var valUserData: ValUser? = null
    private var tokenUserData: Token? = null

    fun execute(requestDTO: LoginRequestDto): UserInformationRequestDto {
        val user = accountRepository.findById(requestDTO.id)
        if (user.isPresent) {
            userData = user.get()
            if (securityConfig.passwordEncoder().matches(requestDTO.password, userData.password)) {
                val lolUser: Optional<LolUser> = lolRepository.findById(userData.idx)
                val valUser: Optional<ValUser> = valRepository.findById(userData.idx)
                val token: Optional<Token> = tokenRepository.findById(userData.idx)

                if (lolUser.isPresent)
                    lolUserData = lolUser.get()
                if (valUser.isPresent)
                    valUserData = valUser.get()
                if (token.isPresent)
                    tokenUserData = token.get()

                val accountToken = jwtToken.makeJwtAccessToken(userData.idx, userData.id)
                val refreshToken = jwtToken.makeJwtRefreshToken()

                tokenRepository.save(
                    Token(
                        idx = userData.idx,
                        accessToken = accountToken,
                        refreshToken = refreshToken,
                    )
                )

                return UserInformationRequestDto(
                    idx = userData.idx,
                    id = userData.id,
                    name = userData.name,
                    lolUserId = userData.lolId,
                    valUserId = userData.valId,
                    token = JwtDto(accountToken, refreshToken)
                )
            }
            throw CustomException(ErrorCode.LOGIN_BAD_REQUEST)
        }
        throw CustomException(ErrorCode.LOGIN_BAD_REQUEST)
    }

}