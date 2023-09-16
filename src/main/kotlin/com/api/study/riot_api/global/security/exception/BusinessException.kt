package com.api.study.riot_api.global.security.exception

import com.api.study.riot_api.global.error.GlobalErrorCode


open class BusinessException(val globalErrorCode: GlobalErrorCode) : RuntimeException()