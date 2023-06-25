package com.api.study.riot_api.service

import com.api.study.riot_api.controller.AccountController
import com.api.study.riot_api.domain.dto.UpdateUserDto
import com.api.study.riot_api.domain.entity.LolUser
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
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
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
    @Autowired
    private lateinit var riotAPIService: RiotAPIService

    private val logger: Logger = LoggerFactory.getLogger(AccountController::class.java)

    @Value("\${app.apiKey}")
    private val riotAPIKey: String = ""

    @Transactional
    fun update(userDto: UpdateUserDto, id: Long, accessToken: String): UpdateUserDto {
        val user = accountRepository.findById(id).get()
        if (jwtToken.validateToken(accessToken)) {
            jwtToken.makeJwtAccessToken(user.idx, user.mail)
        }

        if (userDto.valName != "") {
            logger.info(userDto.valName)
            valRepository.save(ValUser(idx = user.idx, valUserName = userDto.valName))
        }

        if (userDto.lolName != "" && userDto.lolName != null) {
            logger.info(userDto.lolName)
            val riotLolUserData = riotAPIService.getUserInformation(
                accessToken = accessToken,
                apiKey = riotAPIKey,
                userName = userDto.lolName
            )
            logger.info(riotLolUserData.toString())
            lolRepository.save(
                LolUser(
                    idx = user.idx,
                    lolUserName = userDto.lolName,
                    lolUserLevel = riotLolUserData.summonerLevel,
                    lolUserPuuId = riotLolUserData.puuid,
                    lolUserAccountId = riotLolUserData.accountId,
                    lolUserId = riotLolUserData.id
                )
            )
        }

        val userData = User(
            idx = user.idx,
            name = user.name,
            id = user.id,
            password = user.password,
            mail = user.mail,
            lolName = userDto.lolName?:"",
            valName = userDto.valName?:""
        )

        accountRepository.save(userData)

        return userDto
    }

    fun deleteUser(id: Long, accessToken: String) {
        if (jwtToken.validateToken(accessToken)) {
            throw CustomException(ErrorCode.TOKEN_NOT_FOUND_FORBIDDEN_ERROR)
        }

        val userData = accountRepository.findById(id)
        val valUserData = valRepository.findById(id)
        val lolUserData = lolRepository.findById(id)
        val tokenData = tokenRepository.findById(id)
        if (userData.isPresent)
            accountRepository.deleteById(id)
        else
            throw CustomException(ErrorCode.NOT_FOUND_USER_IDX_BAD_REQUEST)
        if (tokenData.isPresent)
            tokenRepository.deleteById(id)
        if (valUserData.isPresent)
            valRepository.deleteById(id)
        if (lolUserData.isPresent)
            lolRepository.deleteById(id)


    }


}