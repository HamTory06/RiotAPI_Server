package com.api.study.riot_api.global.security.exception

import com.api.study.riot_api.global.error.GlobalErrorCode

class UnexpectedTokenException: BusinessException(GlobalErrorCode.UNEXPECTED_TOKEN)