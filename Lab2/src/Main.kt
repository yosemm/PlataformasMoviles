// Universidad del Valle de Guatemala
// Programación de Plataformas Móviles
// Sección 40 - 22213 Jorge Chupina
// Laboratorio #2

import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.sqrt

class Calculadora {
    private fun simboloOperacion(c: Char): Boolean {
        return c in "+-*/^"
    }

    private fun jerarquia(op: Char): Int {
        return when (op) {
            '+', '-' -> 1
            '*', '/' -> 2
            '^' -> 3
            else -> -1
        }
    }

    private fun utilizarOperador(b: Double, a: Double, op: Char): Double {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> if (b != 0.0) a / b else throw ArithmeticException("División por cero")
            '^' -> a.pow(b)
            else -> throw IllegalArgumentException("Operador no válido: $op")
        }
    }

    fun evaluarTexto(expression: String): Double {
        val tokens = tokenizar(expression)
        val values = ArrayDeque<Double>()
        val ops = ArrayDeque<Char>()

        tokens.forEach { token ->
            when {
                token == "(" -> ops.addLast('(')
                token == ")" -> {
                    while (ops.isNotEmpty() && ops.last() != '(') {
                        values.addLast(utilizarOperador(values.removeLast(), values.removeLast(), ops.removeLast()))
                    }
                    if (ops.isNotEmpty() && ops.last() == '(') {
                        ops.removeLast()
                    } else {
                        throw IllegalArgumentException("Paréntesis no balanceados")
                    }
                }

                token == "e" -> values.addLast(Math.E)
                token.startsWith("exp") -> {
                    val innerExpr = token.substring(4, token.length - 1)
                    values.addLast(exp(evaluarTexto(innerExpr)))
                }

                token.startsWith("sqrt") -> {
                    val innerExpr = token.substring(5, token.length - 1)
                    values.addLast(sqrt(evaluarTexto(innerExpr)))
                }

                token.toDoubleOrNull() != null -> values.addLast(token.toDouble())
                token.length == 1 && simboloOperacion(token[0]) -> {
                    while (ops.isNotEmpty() && jerarquia(ops.last()) >= jerarquia(token[0])) {
                        values.addLast(utilizarOperador(values.removeLast(), values.removeLast(), ops.removeLast()))
                    }
                    ops.addLast(token[0])
                }

                else -> throw IllegalArgumentException("Token no válido: $token")
            }
        }

        while (ops.isNotEmpty()) {
            if (ops.last() == '(') throw IllegalArgumentException("Paréntesis no balanceados")
            values.addLast(utilizarOperador(values.removeLast(), values.removeLast(), ops.removeLast()))
        }

        return values.last()
    }

    private fun tokenizar(expresion: String): List<String> {
        val tokens = mutableListOf<String>()
        var i = 0
        while (i < expresion.length) {
            when {
                expresion[i].isWhitespace() -> i++
                expresion[i].isDigit() || expresion[i] == '.' -> {
                    val sb = StringBuilder()
                    while (i < expresion.length && (expresion[i].isDigit() || expresion[i] == '.')) {
                        sb.append(expresion[i])
                        i++
                    }
                    tokens.add(sb.toString())
                }

                expresion[i] == '(' || expresion[i] == ')' || simboloOperacion(expresion[i]) -> {
                    tokens.add(expresion[i].toString())
                    i++
                }

                expresion.substring(i).startsWith("exp") || expresion.substring(i).startsWith("sqrt") -> {
                    val func = if (expresion.substring(i).startsWith("exp")) "exp" else "sqrt"
                    val start = i + func.length
                    var balance = 1
                    var end = start + 1
                    while (balance > 0 && end < expresion.length) {
                        if (expresion[end] == '(') balance++
                        if (expresion[end] == ')') balance--
                        end++
                    }
                    if (balance != 0) throw IllegalArgumentException("Paréntesis no balanceados en función $func")
                    tokens.add(expresion.substring(i, end))
                    i = end
                }

                expresion[i] == 'e' -> {
                    tokens.add("e")
                    i++
                }

                else -> throw IllegalArgumentException("Carácter no válido: ${expresion[i]}")
            }
        }
        return tokens
    }
}

fun main() {
    val calculadora = Calculadora()

    while (true) {
        print("Ingrese una expresión para calcular (o 'salir' para terminar): ")
        val input = readlnOrNull()

        if (input == null || input.lowercase() == "salir") {
            println("Gracias por usar la calculadora. ¡Hasta luego!")
            break
        }

        try {
            val result = calculadora.evaluarTexto(input)
            println("Resultado: $result")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }

        println()
    }
}