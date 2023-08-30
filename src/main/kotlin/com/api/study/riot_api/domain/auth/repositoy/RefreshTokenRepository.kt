package com.api.study.riot_api.domain.auth.repositoy

import com.api.study.riot_api.domain.auth.RefreshTokenEntity
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository: CrudRepository<RefreshTokenEntity, String> {

}