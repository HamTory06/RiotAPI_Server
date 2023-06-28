package com.api.study.riot_api.repository

import com.api.study.riot_api.domain.entity.Participants
import org.springframework.data.jpa.repository.JpaRepository

interface ParticipantsRepository:JpaRepository<Participants, Long> {

}