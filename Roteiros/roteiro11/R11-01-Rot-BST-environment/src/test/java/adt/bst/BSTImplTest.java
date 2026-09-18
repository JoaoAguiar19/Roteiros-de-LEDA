package adt.bst;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import adt.bt.BTNode;

public class BSTImplTest {

    private BSTImpl<Integer> tree;
    private BTNode<Integer> NIL = new BTNode<Integer>();

    private void fillTree() {
        Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
        for (int i : array) {
            tree.insert(i);
        }
    }

    @Before
    public void setUp() {
        tree = new BSTImpl<>();
    }

    @Test
    public void testIsEmpty() {
        // Árvore vazia
        assertTrue(tree.isEmpty());

        // Árvore com elementos
        fillTree();
        assertFalse(tree.isEmpty());

    }

    @Test
    public void testSize() {
        // Árvore vazia
        assertEquals(0, tree.size());

        // Árvore com elementos 
        // {6,23,-34,5,9,2,0,76,12,67,232,-40} 12 elementos
        fillTree();
        assertEquals(12, tree.size());
    }

    @Test
    public void testHeight() {
        // Árvore vazia
        assertEquals(-1, tree.height());

         // Árvore com elementos
        fillTree();
        assertEquals(4, tree.height());
    }

    @Test
    public void testInsertNull() {
        // Árvore vazia
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertEquals(-1, tree.height());

        // Inserir um elemento null
        tree.insert(null);

        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertEquals(-1, tree.height());

    }

    @Test
    public void testInsert() {
        // Árvore vazia
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());

        // Inserindo um elemento
        tree.insert(6);

        assertFalse(tree.isEmpty());
        assertEquals(1, tree.size());

        // Inserindo mais elementos
        tree.insert(23);
        tree.insert(-34);
        tree.insert(0);

        assertFalse(tree.isEmpty());
        assertEquals(4, tree.size());
    }

    @Test
    public void testSearch() {
        // Árvore vazia
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());

        assertEquals(NIL, tree.search(null));
        assertEquals(NIL, tree.search(6));
        assertEquals(NIL, tree.search(76));
        assertEquals(NIL, tree.search(40));

        // Árvore com elementos
        // {6,23,-34,5,9,2,0,76,12,67,232,-40} 12 elementos
        fillTree();

        assertFalse(tree.isEmpty());
        assertEquals(12, tree.size());

        assertEquals(NIL, tree.search(null));
        assertEquals(Integer.valueOf(6), tree.search(6).getData());
        assertEquals(Integer.valueOf(76), tree.search(76).getData());
        assertEquals(Integer.valueOf(-40), tree.search(-40).getData());
    }

    @Test
    public void testMaxMin() {
        // Árvore vazia
        assertEquals(null, tree.minimum());
        assertEquals(null, tree.maximum());

        // Inserindo um elemento
        tree.insert(6);
        assertEquals(Integer.valueOf(6), tree.minimum().getData());
        assertEquals(Integer.valueOf(6), tree.maximum().getData());

        // Inserindo mais elementos
        tree.insert(23);
        tree.insert(-34);

        assertEquals(Integer.valueOf(-34), tree.minimum().getData());
        assertEquals(Integer.valueOf(23), tree.maximum().getData());

    }

    @Test
    public void testSucessorPredecessor() {
        // Árvore vazia
        assertEquals(null, tree.sucessor(12));
        assertEquals(null, tree.sucessor(-23));
        assertEquals(null, tree.sucessor(0));

        assertEquals(null, tree.predecessor(12));
        assertEquals(null, tree.predecessor(-23));
        assertEquals(null, tree.predecessor(0));

        // Árvore com elementos
        fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

        assertEquals(null, tree.predecessor(-40));
        assertEquals(Integer.valueOf(-34), tree.sucessor(-40).getData());

        assertEquals(Integer.valueOf(-40), tree.predecessor(-34).getData());
        assertEquals(Integer.valueOf(0), tree.sucessor(-34).getData());

        assertEquals(Integer.valueOf(-34), tree.predecessor(0).getData());
        assertEquals(Integer.valueOf(2), tree.sucessor(0).getData());

        assertEquals(Integer.valueOf(0), tree.predecessor(2).getData());
        assertEquals(Integer.valueOf(5), tree.sucessor(2).getData());

        assertEquals(Integer.valueOf(2), tree.predecessor(5).getData());
        assertEquals(Integer.valueOf(6), tree.sucessor(5).getData());

        assertEquals(Integer.valueOf(5), tree.predecessor(6).getData());
        assertEquals(Integer.valueOf(9), tree.sucessor(6).getData());

        assertEquals(Integer.valueOf(6), tree.predecessor(9).getData());
        assertEquals(Integer.valueOf(12), tree.sucessor(9).getData());

        assertEquals(Integer.valueOf(9), tree.predecessor(12).getData());
        assertEquals(Integer.valueOf(23), tree.sucessor(12).getData());

        assertEquals(Integer.valueOf(12), tree.predecessor(23).getData());
        assertEquals(Integer.valueOf(67), tree.sucessor(23).getData());

        assertEquals(Integer.valueOf(23), tree.predecessor(67).getData());
        assertEquals(Integer.valueOf(76), tree.sucessor(67).getData());

        assertEquals(Integer.valueOf(67), tree.predecessor(76).getData());
        assertEquals(Integer.valueOf(232), tree.sucessor(76).getData());

        assertEquals(Integer.valueOf(76), tree.predecessor(232).getData());
        assertEquals(null, tree.sucessor(232));

        // Testando elementos que não existem na árvore
        assertEquals(null, tree.predecessor(1000));
        assertEquals(null, tree.sucessor(-1000));

        assertEquals(null, tree.predecessor(15));
        assertEquals(null, tree.sucessor(15));
        
        // Testando com null
        assertEquals(null, tree.predecessor(null));
        assertEquals(null, tree.sucessor(null));
    }

    @Test
    public void testRemove() {
        // Remover de árvore vazia
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());

        tree.remove(null);
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());

        tree.remove(12);
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());

        // Remover de árvore com elementos
        fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232
        assertFalse(tree.isEmpty());
        assertEquals(12, tree.size());

        // Removendo um elemento inexistente
        tree.remove(null);
        assertFalse(tree.isEmpty());
        assertEquals(12, tree.size());

        tree.remove(1000);
        assertFalse(tree.isEmpty());
        assertEquals(12, tree.size());

        tree.remove(-1000);
        assertFalse(tree.isEmpty());
        assertEquals(12, tree.size());

        tree.remove(15);
        assertFalse(tree.isEmpty());
        assertEquals(12, tree.size());

        // Removendo um nó folha
        tree.remove(0);
        assertFalse(tree.isEmpty());
        assertEquals(11, tree.size());
        assertEquals(NIL, tree.search(0));

        tree.remove(232);
        assertFalse(tree.isEmpty());
        assertEquals(10, tree.size());
        assertEquals(NIL, tree.search(232));

        // Removendo um nó com um filho
        tree.remove(5);
        assertFalse(tree.isEmpty());
        assertEquals(9, tree.size());
        assertEquals(NIL, tree.search(5));

        tree.remove(9);
        assertFalse(tree.isEmpty());
        assertEquals(8, tree.size());
        assertEquals(NIL, tree.search(9));

        // Removendo um nó com dois filhos
        tree.remove(-34);
        assertFalse(tree.isEmpty());
        assertEquals(7, tree.size());
        assertEquals(NIL, tree.search(-34));

        tree.remove(23);
        assertFalse(tree.isEmpty());
        assertEquals(6, tree.size());
        assertEquals(NIL, tree.search(23));

        // Removendo a raiz
        tree.remove(6);
        assertFalse(tree.isEmpty());
        assertEquals(5, tree.size());
        assertEquals(NIL, tree.search(6));

        tree.remove(12);
        assertFalse(tree.isEmpty());
        assertEquals(4, tree.size());
        assertEquals(NIL, tree.search(12));

    }

    @Test
    public void testOrder() {
        // Árvore vazia
        Integer[] order = {};
        assertArrayEquals(order, tree.order());

        // Árvore com elementos
        fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

        order = new Integer[] {-40, -34, 0, 2, 5, 6, 9, 12, 23, 67, 76, 232};
        assertArrayEquals(order, tree.order());
    }

    @Test
    public void testPreOrder() {
        // Árvore vazia
        Integer[] preOrder = {};
        assertArrayEquals(preOrder, tree.preOrder());

        // Árvore com elementos
        fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

        preOrder = new Integer[] {6, -34, -40, 5, 2, 0, 23, 9, 12, 76, 67, 232};
        assertArrayEquals(preOrder, tree.preOrder());
    }

    @Test
    public void testPostOrder() {
        // Árvore vazia
        Integer[] postOrder = {};
        assertArrayEquals(postOrder, tree.postOrder());

        // Árvore com elementos
        fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

        postOrder = new Integer[] {-40, 0, 2, 5, -34, 12, 9, 67, 232, 76, 23, 6};
        assertArrayEquals(postOrder, tree.postOrder());
    }
}