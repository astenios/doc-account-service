package org.example.infrastructure.output

import org.example.application.statement.Printer

class ConsolePrinter : Printer {
    override fun writeln(string: String) {
        println(string)
    }
}
