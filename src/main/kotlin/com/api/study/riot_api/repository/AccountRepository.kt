package com.api.study.riot_api.repository

import com.api.study.riot_api.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
public interface AccountRepository: JpaRepository<User, Long> {

}