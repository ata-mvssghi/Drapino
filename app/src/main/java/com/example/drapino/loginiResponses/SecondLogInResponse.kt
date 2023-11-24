package com.example.drapino.loginiResponses

data class SecondLogInResponse(
    val data: Boolean,
    val isSuccess: Boolean,
    val message: String,
    val statusCode: String
)