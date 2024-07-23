// Universidad del Valle de Guatemala
// Programación de Plataformas Móviles
// Sección 40 - 22213 Jorge Chupina
// Laboratorio #1

fun convertirDecimalABinario() {
    println("Ingresa un número decimal")
    val numero = readLine()?.toIntOrNull() ?: 0
    println("El numero en binario es ${numero.toString(2)}")
}

fun main() {
    convertirDecimalABinario()
}