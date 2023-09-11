package com.api.study.riot_api.domain.auth.exception

import com.api.study.riot_api.domain.auth.error.AuthErrorCode
import com.api.study.riot_api.infrastructure.error.custom.CustomException

object ExistIdException: CustomException(
    AuthErrorCode.ID_EXIST_EXCEPTION
)