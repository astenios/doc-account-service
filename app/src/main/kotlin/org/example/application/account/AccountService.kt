package org.example.application.account

interface AccountService {
    fun deposit(amount: Int)
    fun withdraw(amount: Int)
    fun printStatement()
}
