package com.api.study.riot_api.global.security.exception

import com.api.study.riot_api.global.error.GlobalErrorCode


class InvalidTokenException: BusinessException(GlobalErrorCode.INVALID_TOKEN)