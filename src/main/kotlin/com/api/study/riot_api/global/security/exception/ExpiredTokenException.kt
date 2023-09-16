package com.api.study.riot_api.global.security.exception

import com.api.study.riot_api.global.error.GlobalErrorCode

class ExpiredTokenException: BusinessException(GlobalErrorCode.EXPIRED_TOKEN)