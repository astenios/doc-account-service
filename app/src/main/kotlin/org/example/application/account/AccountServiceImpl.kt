package org.example.application.account

import org.example.application.statement.Printer
import org.example.domain.Transaction
import org.example.domain.TransactionRepository
import org.example.infrastructure.output.ConsolePrinter
import org.example.infrastructure.output.db.InMemoryTransactionRepository
import java.time.Clock
import java.time.temporal.ChronoUnit


class AccountServiceImpl(
    private val clock: Clock,
    private val output: Printer = ConsolePrinter(),
    private val transactionRepository: TransactionRepository = InMemoryTransactionRepository(),
) : AccountService {

    private var currentBalance = 0

    override fun deposit(amount: Int) {
        transactionRepository.save(Transaction(clock.instant(), amount, currentBalance + amount))
        currentBalance += amount
    }

    override fun withdraw(amount: Int) {
        transactionRepository.save(Transaction(clock.instant(), -amount, currentBalance - amount))
        currentBalance -= amount
    }

    override fun printStatement() {
        output.writeln("Date || Amount || Balance")
        transactionRepository.allOrderedByDateDesc().forEach {
            output.writeln("${it.date.truncatedTo(ChronoUnit.DAYS)} || ${it.amount} || ${it.balance}")
        }
    }
}
