package com.api.study.riot_api.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.Getter

@Entity
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idx: Long,
    val name: String,
    val id: String,
    val password: String,
    val lolName: String,
    val valName: String
)