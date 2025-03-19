package org.example.application.account

import org.example.application.statement.Printer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

class AccountServiceImplTest {

    private val output = TestPrinter()
    private val clock = TestClock()
    private val accountService = AccountServiceImpl(clock, output)

    @Test
    fun `should print  bank statement`() {
        clock.setInstantValue(Instant.parse("2012-01-10T00:00:00Z"))
        accountService.deposit(1000)
        clock.setInstantValue(Instant.parse("2012-01-13T00:00:00Z"))
        accountService.deposit(2000)
        clock.setInstantValue(Instant.parse("2012-01-14T00:00:00Z"))
        accountService.withdraw(500)

        accountService.printStatement()

        val expected = listOf(
            "Date || Amount || Balance",
            "2012-01-14T00:00:00Z || -500 || 2500",
            "2012-01-13T00:00:00Z || 2000 || 3000",
            "2012-01-10T00:00:00Z || 1000 || 1000",
        )
        val result = output.print()

        assertEquals(expected, result)
    }

}

class TestClock : Clock() {

    private var instantValue = Instant.now()

    override fun getZone(): ZoneId? {
        TODO("Not yet implemented")
    }

    override fun withZone(zone: ZoneId?): Clock? {
        TODO("Not yet implemented")
    }

    override fun instant(): Instant? {
        return instantValue;
    }

    fun setInstantValue(value: Instant) {
        instantValue = value
    }
}

class TestPrinter : Printer {
    private val buffer = mutableListOf<String>()
    override fun writeln(string: String) {
        buffer.add(string)
    }

    fun print(): List<String> {
        buffer.forEach {
            println(it)
        }
        return buffer
    }
}
