package problems.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import problems.FloorCeilBinarySearchImpl;

public class TestFloorCeil {

    FloorCeilBinarySearchImpl fc;
    Integer[] arraySemRepeticao;
    Integer[] arrayComRepeticao;
    Integer[] arrayNegativenumber;
    Integer[] arrayVazio;
    Integer[] arrayNulo;

    @Before
    public void setUp() {
        fc = new FloorCeilBinarySearchImpl();
        arraySemRepeticao = new Integer[] {1,3,5,7,9,11,13,15,17,19};
        arrayComRepeticao = new Integer[] {3,6,8,8,8,9,11,14,16};
        arrayNegativenumber = new Integer[] {-10,-8,-5,-5,-4,-4,-4,-1};
        arrayVazio = new Integer[]{};
        arrayNulo = null;
    }

    @Test
    public void testFloor() {
        // Array sem repetição
        assertTrue(fc.floor(arraySemRepeticao, 0) == null);
        assertTrue(fc.floor(arraySemRepeticao, 5) == 5);
        assertTrue(fc.floor(arraySemRepeticao, 8) == 7);
        assertTrue(fc.floor(arraySemRepeticao, 100) == 19);

        // Array com repetição
        assertTrue(fc.floor(arrayComRepeticao, 0) == null);
        assertTrue(fc.floor(arrayComRepeticao, 5) == 3);
        assertTrue(fc.floor(arrayComRepeticao, 8) == 8);
        assertTrue(fc.floor(arrayComRepeticao, 100) == 16);

        // Array com números negativos
        assertTrue(fc.floor(arrayNegativenumber, -11) == null);
        assertTrue(fc.floor(arrayNegativenumber, -5) == -5);
        assertTrue(fc.floor(arrayNegativenumber, -2) == -4);
        assertTrue(fc.floor(arrayNegativenumber, 10) == -1);

        // Array vazio
        assertTrue(fc.floor(arrayVazio, 7) == null);

        // Array nulo
        assertTrue(fc.floor(arrayNulo, 7) == null);
    }

    @Test
    public void testCeil() {
        // Array sem repetição
        assertTrue(fc.ceil(arraySemRepeticao, -10) == 1);
        assertTrue(fc.ceil(arraySemRepeticao, 0) == 1);
        assertTrue(fc.ceil(arraySemRepeticao, 5) == 5);
        assertTrue(fc.ceil(arraySemRepeticao, 8) == 9);
        assertTrue(fc.ceil(arraySemRepeticao, 100) == null);

        // Array com repetição
        assertTrue(fc.ceil(arrayComRepeticao, -10) == 3);
        assertTrue(fc.ceil(arrayComRepeticao, 0) == 3);
        assertTrue(fc.ceil(arrayComRepeticao, 5) == 6);
        assertTrue(fc.ceil(arrayComRepeticao, 8) == 8);
        assertTrue(fc.ceil(arrayComRepeticao, 100) == null);

        // Array com números negativos
        assertTrue(fc.ceil(arrayNegativenumber, -100) == -10);
        assertTrue(fc.ceil(arrayNegativenumber, -11) == -10);
        assertTrue(fc.ceil(arrayNegativenumber, -5) == -5);
        assertTrue(fc.ceil(arrayNegativenumber, -2) == -1);
        assertTrue(fc.ceil(arrayNegativenumber, 10) == null);

        // Array vazio
        assertTrue(fc.ceil(arrayVazio, 7) == null);

        // Array nulo
        assertTrue(fc.ceil(arrayNulo, 7) == null);
    }
}
