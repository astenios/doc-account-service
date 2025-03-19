package org.example.infrastructure.output.db

import org.example.domain.Transaction
import org.example.domain.TransactionRepository

class InMemoryTransactionRepository : TransactionRepository {

    private val transactionList = mutableListOf<Transaction>()

    override fun save(transaction: Transaction) {
        transactionList.add(transaction)
    }

    override fun allOrderedByDateDesc(): List<Transaction> {
        return transactionList.reversed()
    }
}
