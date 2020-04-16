package com.example.aasmm.data.model

import java.util.*

data class UserPIIDataModel (
    val firstName: String,
    val lastName: String,
    val DOB: Date,
    val email: String,
    val password: String
)