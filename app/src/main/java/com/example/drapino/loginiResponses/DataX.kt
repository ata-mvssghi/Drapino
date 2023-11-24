package com.example.drapino.loginiResponses

import com.fasterxml.jackson.annotation.JsonIgnore

data class DataX(
    val hasCards: Boolean,
    val claims: Claims,
    val token: Token
)