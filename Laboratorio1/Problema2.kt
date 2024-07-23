// Universidad del Valle de Guatemala
// Programación de Plataformas Móviles
// Sección 40 - 22213 Jorge Chupina
// Laboratorio #1

fun main() {

    print("Ingrese una palabra por linea, para terminar ingrese '0': ")

    var palabra: String = readln()
    var palindromas: MutableList<String> = mutableListOf()
    var dosVocales: MutableList<String> = mutableListOf()
    var empiezConConsonante: MutableList<String> = mutableListOf()

    while (palabra != "0") {
        if (isPalindromo(palabra)) {
            palindromas.add(palabra)
        }
        if (contieneDosVocalesDistintas(palabra)) {
            dosVocales.add(palabra)
        }
        if (empiezaConConsonante(palabra)) {
            empiezConConsonante.add(palabra)
        }
        palabra = readln()
    }
    print("Los resultados son:\n")
    print("Palabras palindromas: ${palindromas.size}\n")
    print("Palabras con al menos 2 vocales distintas: ${dosVocales.size}\n")
    print("Palabras que inician con una consonante: ${empiezConConsonante.size}\n")

}

fun isPalindromo(palabra: String): Boolean {
    return palabra == palabra.reversed()
}

fun contieneDosVocalesDistintas(palabra: String): Boolean {
    val vocales = "aeiou"
    val vocalesEncontradas = mutableSetOf<Char>()
    for (letra in palabra) {
        if (vocales.contains(letra)) {
            vocalesEncontradas.add(letra)
        }
    }
    return vocalesEncontradas.size >= 2
}

fun empiezaConConsonante(palabra: String): Boolean {
    val consonantes = "bcdfghjklmnpqrstvwxyz"
    val primeraLetra = palabra[0]
    return consonantes.contains(primeraLetra)
}