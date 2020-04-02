package com.example.aasmm.data.model

import java.util.*

data class UserPIIModel (
    val firstName: String,
    val lastName: String,
    val DOB: Date,
    val email: String,
    val password: String
)