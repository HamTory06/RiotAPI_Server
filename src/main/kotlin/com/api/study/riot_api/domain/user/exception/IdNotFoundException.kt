package com.api.study.riot_api.domain.user.exception

import com.api.study.riot_api.domain.user.error.UserErrorCode
import com.api.study.riot_api.infrastructure.error.custom.CustomException

object IdNotFoundException: CustomException(
    UserErrorCode.ID_NOT_FOUND
)