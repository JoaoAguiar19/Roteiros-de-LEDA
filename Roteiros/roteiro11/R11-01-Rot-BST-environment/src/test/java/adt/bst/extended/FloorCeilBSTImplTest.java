package adt.bst.extended;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class FloorCeilBSTImplTest {

    Integer[] array;
    FloorCeilBST tree;

    @Before
    public void setUp() {
        array = new Integer[] {6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
        tree = new FloorCeilBSTImpl();
    }

    @Test
    public void testCeil() {
        assertEquals(Integer.valueOf(6), tree.ceil(array, 6));
        assertEquals(Integer.valueOf(76), tree.ceil(array, 76));
        assertEquals(Integer.valueOf(23), tree.ceil(array, 20));
        assertEquals(Integer.valueOf(-34), tree.ceil(array, -35));
        assertEquals(Integer.valueOf(-40), tree.ceil(array, -50));
        assertEquals(Integer.valueOf(0), tree.ceil(array, -10));
        assertEquals(null, tree.ceil(array, 250));
    }

    @Test
    public void testFloor() {
        assertEquals(Integer.valueOf(6), tree.floor(array, 6));
        assertEquals(Integer.valueOf(67), tree.floor(array, 67));
        assertEquals(Integer.valueOf(67), tree.floor(array, 70));
        assertEquals(Integer.valueOf(232), tree.floor(array, 470));
        assertEquals(Integer.valueOf(-34), tree.floor(array, -1));
        assertEquals(Integer.valueOf(-40), tree.floor(array, -35));
        assertEquals(null, tree.floor(array, -50));
    }
}
