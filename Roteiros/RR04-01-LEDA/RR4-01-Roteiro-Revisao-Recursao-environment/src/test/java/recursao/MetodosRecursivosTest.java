package recursao;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class MetodosRecursivosTest {

    MetodosRecursivos metodosRecursivos;

    @Before
    public void setUp() {
        metodosRecursivos = new MetodosRecursivos();
    }

    @Test
    public void testCalcularFatorial() {
        assertEquals(479001600, metodosRecursivos.calcularFatorial(12));
        assertEquals(1, metodosRecursivos.calcularFatorial(0));
    }

    @Test
    public void testCalcularFibonacci() {
        assertEquals(21, metodosRecursivos.calcularFibonacci(8));
        assertEquals(1, metodosRecursivos.calcularFibonacci(1));
        assertEquals(144, metodosRecursivos.calcularFibonacci(12));

    }

    @Test
    public void testCalcularSomaArray() {
        int[] array = {1,2,3,4,5};
        assertEquals(15, metodosRecursivos.calcularSomaArray(array));

        int[] array2 = {5,3,8,32};
        assertEquals(48, metodosRecursivos.calcularSomaArray(array2));
    }

    @Test
    public void testCountNotNull() {
        Object[] arrayObjects = {1, "um", null, null, 'j', metodosRecursivos, false, null};
        assertEquals(5, metodosRecursivos.countNotNull(arrayObjects));

        Object[] array = {1,2,null,null,5};
         assertEquals(3, metodosRecursivos.countNotNull(array));

    }

    @Test
    public void testPotenciaDe2() {
        assertEquals(32, metodosRecursivos.potenciaDe2(5));
        assertEquals(1, metodosRecursivos.potenciaDe2(0));
        assertEquals(1024, metodosRecursivos.potenciaDe2(10));


    }

    @Test
    public void testProgressaoAritmetica() {
        // PA: 0, 2, 4, 6, 8, 10, ...
        assertEquals(0.0, metodosRecursivos.progressaoAritmetica(0, 2, 1), 0.0001);
        assertEquals(2.0, metodosRecursivos.progressaoAritmetica(0, 2, 2), 0.0001);
        assertEquals(4.0, metodosRecursivos.progressaoAritmetica(0, 2, 3), 0.0001);
        assertEquals(8.0, metodosRecursivos.progressaoAritmetica(0, 2, 5), 0.0001);

        // PA: 5, 5.5, 6.0, 6.5, ...
        assertEquals(6.5, metodosRecursivos.progressaoAritmetica(5, 0.5, 4), 0.0001);

    }

    @Test
    public void testProgressaoGeometrica() {
        // PG: 1, 2, 4, 8, 16, ...
        assertEquals(1.0, metodosRecursivos.progressaoGeometrica(1, 2, 1), 0.0001);
        assertEquals(2.0, metodosRecursivos.progressaoGeometrica(1, 2, 2), 0.0001);
        assertEquals(4.0, metodosRecursivos.progressaoGeometrica(1, 2, 3), 0.0001);
        assertEquals(16.0, metodosRecursivos.progressaoGeometrica(1, 2, 5), 0.0001);

        // PG: 3, 6, 12, 24, ...
        assertEquals(24.0, metodosRecursivos.progressaoGeometrica(3, 2, 4), 0.0001);

    }
}
