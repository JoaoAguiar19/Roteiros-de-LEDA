package adt.bst;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class SimpleBSTManipulationImplTest {

    BSTImpl<Integer> tree1;
    BSTImpl<Integer> tree2;
    SimpleBSTManipulationImpl<Integer> manipulator;

    private void fillTree(BST<Integer> tree) {
        Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
        for (int i : array) {
            tree.insert(i);
        }
    }

    @Before
    public void setUp() {
        tree1 = new BSTImpl<>();
        tree2 = new BSTImpl<>();
        manipulator = new SimpleBSTManipulationImpl<>();
    }

    @Test
    public void testEquals() {
        // Árvores vazias
        assertEquals(true, manipulator.equals(tree1, tree2));

        // Árvores com os mesmos elementos
        fillTree(tree1);
        fillTree(tree2);
        assertEquals(true, manipulator.equals(tree1, tree2));

        tree2.remove(6);
        assertEquals(false, manipulator.equals(tree1, tree2));

        tree1.remove(6);
        assertEquals(true, manipulator.equals(tree1, tree2));
    }

    @Test
    public void testIsSimilar() {
        // Árvores vazias
        assertEquals(true, manipulator.isSimilar(tree1, tree2));

        // Árvores com os mesmos elementos
        fillTree(tree1);
        fillTree(tree2);
        assertEquals(true, manipulator.isSimilar(tree1, tree2));

        tree2.remove(6);
        assertEquals(false, manipulator.isSimilar(tree1, tree2));

        tree1.remove(6);
        assertEquals(true, manipulator.isSimilar(tree1, tree2));
    }

    @Test
    public void testOrderStatistic() {
        // Árvore vazia
        assertEquals(null, manipulator.orderStatistic(tree1, 1));
        assertEquals(null, manipulator.orderStatistic(tree1, -2));

        // Árvore com elementos
        fillTree(tree1);
        assertEquals(Integer.valueOf(-40), manipulator.orderStatistic(tree1, 1));
        assertEquals(Integer.valueOf(232), manipulator.orderStatistic(tree1, 12));
        assertEquals(Integer.valueOf(9), manipulator.orderStatistic(tree1, 7));
        assertEquals(null, manipulator.orderStatistic(tree1, 13));
        assertEquals(null, manipulator.orderStatistic(tree1, 0));

        tree1.remove(-40);
        assertEquals(Integer.valueOf(-34), manipulator.orderStatistic(tree1, 1));
        assertEquals(null, manipulator.orderStatistic(tree1, 12));

    }
}
