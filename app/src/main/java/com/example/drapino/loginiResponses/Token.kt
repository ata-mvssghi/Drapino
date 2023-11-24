package com.example.drapino.loginiResponses

data class Token(
    val access_token: String,
    val refresh_token: String?, // Nullable if it can be null in the response
    val token_type: String,
    val expires_in: Long
)