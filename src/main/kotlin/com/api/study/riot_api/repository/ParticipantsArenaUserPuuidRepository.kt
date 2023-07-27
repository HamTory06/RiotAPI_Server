package com.api.study.riot_api.repository

import com.api.study.riot_api.domain.entity.ParticipantsArenaUserPuuid
import org.springframework.data.jpa.repository.JpaRepository

interface ParticipantsArenaUserPuuidRepository: JpaRepository<ParticipantsArenaUserPuuid, String> {

}