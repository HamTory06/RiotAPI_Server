package com.api.study.riot_api.global.security.exception

import com.api.study.riot_api.global.error.GlobalErrorCode

class NotExistUserException: BusinessException(GlobalErrorCode.NOT_EXIST_USER)