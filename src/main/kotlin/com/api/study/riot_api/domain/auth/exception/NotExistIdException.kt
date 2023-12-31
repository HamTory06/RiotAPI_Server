package com.api.study.riot_api.domain.auth.exception

import com.api.study.riot_api.domain.auth.error.AuthErrorCode
import com.api.study.riot_api.global.error.GlobalErrorCode
import com.api.study.riot_api.infrastructure.error.custom.CustomException

object NotExistIdException: CustomException(
    GlobalErrorCode.NOT_EXIST_USER
)