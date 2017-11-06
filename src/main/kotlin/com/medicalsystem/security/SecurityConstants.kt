package com.medicalsystem.security

import io.jsonwebtoken.SignatureAlgorithm

object SecurityConstants {
    val SECRET = "SuperSecretString"
    val EXPIRATION_TIME = 10 * 24 * 60 * 60 * 1000
    val SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256
    val TOKEN_PREFIX = "Bearer "
    val HEADER_STRING = "Authorization"
    val LOGIN_URL = "api/auth/login"
    val REGISTER_URL = "api/auth/register"
}