package com.example.drapino.loginiResponses

data class LastApiCallResponse(
    val data: DataX,
    val isSuccess: Boolean,
    val statusCode: String,
    val message: String
)