// Universidad del Valle de Guatemala
// Programación de Plataformas Móviles
// Sección 40 - 22213 Jorge Chupina
// Laboratorio #2

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
        println()
    }
}