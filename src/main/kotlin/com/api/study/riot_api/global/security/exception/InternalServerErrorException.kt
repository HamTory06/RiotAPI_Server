package com.api.study.riot_api.global.security.exception

import com.api.study.riot_api.global.error.GlobalErrorCode

class InternalServerErrorException: BusinessException(GlobalErrorCode.INTERNAL_SERVER_ERROR)