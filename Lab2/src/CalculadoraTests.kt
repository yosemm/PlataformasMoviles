import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class CalculadoraTest {

    private val calculadora = Calculadora()

    @Test
    fun testSimboloOperacion() {
        assertEquals(true, calculadora.simboloOperacion('+'))
        assertEquals(true, calculadora.simboloOperacion('-'))
        assertEquals(true, calculadora.simboloOperacion('*'))
        assertEquals(true, calculadora.simboloOperacion('/'))
        assertEquals(true, calculadora.simboloOperacion('^'))

        // Operadores no validos
        assertEquals(false, calculadora.simboloOperacion('a'))
        assertEquals(false, calculadora.simboloOperacion('1'))
        assertEquals(false, calculadora.simboloOperacion(' '))
    }

    @Test
    fun testJerarquia() {
        assertEquals(1, calculadora.jerarquia('+'))
        assertEquals(1, calculadora.jerarquia('-'))
        assertEquals(2, calculadora.jerarquia('*'))
        assertEquals(2, calculadora.jerarquia('/'))
        assertEquals(3, calculadora.jerarquia('^'))

        // Operadores no validos
        assertEquals(-1, calculadora.jerarquia('a'))
    }

    @Test
    fun testUtilizarOperador() {
        assertEquals(5.0, calculadora.utilizarOperador(2.0, 3.0, '+'))
        assertEquals(1.0, calculadora.utilizarOperador(2.0, 3.0, '-'))
        assertEquals(6.0, calculadora.utilizarOperador(2.0, 3.0, '*'))
        assertEquals(1.5, calculadora.utilizarOperador(2.0, 3.0, '/'))
        assertEquals(9.0, calculadora.utilizarOperador(2.0, 3.0, '^'))
    }
}