package org.example.domain

import java.time.Instant

data class Transaction(
    val date: Instant,
    val amount: Int,
    val balance: Int
)
