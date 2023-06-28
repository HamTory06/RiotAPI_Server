package com.api.study.riot_api.repository

import com.api.study.riot_api.domain.entity.ParticipantsUserPuuid
import org.springframework.data.jpa.repository.JpaRepository

interface ParticipantsUserPuuidRepository: JpaRepository<ParticipantsUserPuuid, String> {
}