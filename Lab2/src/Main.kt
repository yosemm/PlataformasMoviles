// Universidad del Valle de Guatemala
// Programación de Plataformas Móviles
// Sección 40 - 22213 Jorge Chupina
// Laboratorio #2

class Calculadora {
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