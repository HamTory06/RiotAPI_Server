package com.api.study.riot_api.service

import com.api.study.riot_api.api.ExternalKrApiClient
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

@Service
class UserInformationService(
    private var accountRepository: AccountRepository,
    private var lolRepository: LolRepository,
    private var valRepository: ValRepository,
    private var tokenRepository: TokenRepository,
    private var externalKrApiClient: ExternalKrApiClient,
    private var jwtToken: JwtToken
) {
    @Autowired
    private lateinit var riotAPIService: RiotAPIService

    private val logger: Logger = LoggerFactory.getLogger(UserInformationService::class.java)

    @Value("\${app.apiKey}")
    private val riotAPIKey: String = ""

    private lateinit var userData: User
    private var lolUser: LolUser? = null
    private var valUser: ValUser? = null

    //    @Transactional
    fun update(userDto: UpdateUserDto, id: String, accessToken: String): UpdateUserDto {

        if (jwtToken.validateToken(accessToken)) {
            throw CustomException(ErrorCode.TOKEN_NOT_FOUND_FORBIDDEN_ERROR)
        }

        if (accountRepository.findById(id).isPresent) {
            userData = accountRepository.findById(id).get()
        } else {
            throw CustomException(ErrorCode.USER_NOT_FOUND_BAD_REQUEST)
        }

        //TODO(발로란트는 나중에 롤 완성하고 구현하기)
//        if (userDto.valName != "" && userDto.valName != null) {
//            val valUserData = riotAPIService.getValUserInformation(
//                apiKey = riotAPIKey,
//                userName = userDto.valName
//            )
//            valRepository.save(valUserData)
//            if (valRepository.findByValUserName(userDto.valName).isPresent)
//                valUser = valRepository.findByValUserName(userDto.valName).get()
//        }

        if (userDto.lolName != "" && userDto.lolName != null && !lolRepository.findByLolUserName(userDto.lolName).isPresent) {
            logger.info("데이터 베이스에 롤유저 정보 없음")
            val riotLolUserData = riotAPIService.getLolUserInformation(
                apiKey = riotAPIKey,
                userName = userDto.lolName
            )
        }

        if (lolRepository.findByLolUserName(userDto.lolName!!).isPresent) {
            lolUser = lolRepository.findByLolUserName(userDto.lolName).get()
        }

        userData = User(
            idx = userData.idx,
            name = userData.name,
            id = userData.id,
            password = userData.password,
            lolId = lolUser?.idx,
            valId = valUser?.idx
        )

        accountRepository.save(userData)

        return userDto

    }

    fun addValUser(valName: String) {
        val valUserData = ValUser(
            valUserName = valName
        )

        valRepository.save(
            valUserData
        )

        if (valRepository.findByValUserName(valName).isPresent)
            valUser = valRepository.findByValUserName(valName).get()
    }

    fun addLolUser(lolName: String) {
        val riotLolUserData = externalKrApiClient.getUserInformationName(
            apiKey = riotAPIKey,
            username = lolName
        )

        val lolUserData = LolUser(
            lolUserName = riotLolUserData.name,
            lolUserLevel = riotLolUserData.summonerLevel,
            lolUserPuuId = riotLolUserData.puuid,
            lolUserAccountId = riotLolUserData.accountId,
            lolUserId = riotLolUserData.id
        )

        lolRepository.save(
            lolUserData
        )

        if (lolRepository.findByLolUserName(lolName).isPresent)
            lolUser = lolRepository.findByLolUserName(lolName).get()
    }

    fun deleteUser(id: String, accessToken: String) {
        if (jwtToken.validateToken(accessToken)) {
            throw CustomException(ErrorCode.TOKEN_NOT_FOUND_FORBIDDEN_ERROR)
        }

        val userData = accountRepository.findById(id)
        val tokenData = tokenRepository.findById(userData.get().idx)
        if (userData.isPresent)
            accountRepository.deleteById(userData.get().idx)
        else
            throw CustomException(ErrorCode.NOT_FOUND_USER_IDX_BAD_REQUEST)
        if (tokenData.isPresent)
            tokenRepository.deleteById(userData.get().idx)

    }
}