package org.example.domain

interface TransactionRepository {
    fun save(transaction: Transaction)
    fun allOrderedByDateDesc() : List<Transaction>
}
