package adt.hashtable.open;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import adt.hashtable.Hashtable;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;

public class Quadratico2 {
    protected AbstractHashtableOpenAddress<HashtableElement> table1;
    protected AbstractHashtableOpenAddress<HashtableElement> table2;

    protected final int PROPOSED_SIZE = 10;

    @Before
    public void setUp() throws Exception {
        table1 = new HashtableOpenAddressQuadraticProbingImpl<HashtableElement>(
                PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION, 3, 5);
        // o tamanho real utilizado vai ser PROPOSED_SIZE
        table1.insert(new HashtableElement(10)); // coloca no slot indexado com
                                                    // 0
        table1.insert(new HashtableElement(15)); // coloca no slot indexado com
                                                    // 5
        table1.insert(new HashtableElement(2)); // coloca no slot indexado com 2
        table1.insert(new HashtableElement(12)); // coloca no slot indexado com
                                                    // 8, teve 2 colisoes
        table1.insert(new HashtableElement(4)); // coloca no slot indexado com 4
        table1.insert(new HashtableElement(8)); // coloca no slot indexado com
                                                // 6, teve 1 colisao

        table2 = new HashtableOpenAddressQuadraticProbingImpl<HashtableElement>(
                PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION, 3, 5);
    }

    @Test
    public void testInsert() {
        assertEquals(3, table1.getCOLLISIONS());
        table1.insert(new HashtableElement(11)); // nao tem colisao. coloca no
                                                    // slot indexado com 1
        assertEquals(3, table1.getCOLLISIONS());
        assertEquals(1, table1.indexOf(new HashtableElement(11)));

        table1.insert(new HashtableElement(21)); // tem 1 colisao. coloca no
                                                    // slot indexado com 9
        assertEquals(4, table1.getCOLLISIONS());
        assertEquals(9, table1.indexOf(new HashtableElement(21)));

        table1.insert(null);

    }

    @Test
    public void testRemove() {
        table1.remove(new HashtableElement(17)); // elemento inexistente
        assertEquals(6, table1.size());

        table1.remove(new HashtableElement(12)); // elemento existente
        assertEquals(5, table1.size());
        assertNull(table1.search(new HashtableElement(12)));
        table1.remove(null);
        assertEquals(5, table1.size());
    }

    @Test
    public void testSearch() {
        assertEquals(new HashtableElement(4),
                table1.search(new HashtableElement(4))); // elemento que existe
        assertNull(table1.search(new HashtableElement(14))); // elemento que nao
                                                                // existe

    }

    @Test
    public void testIsEmpty() {
        assertFalse(table1.isEmpty());
        table1.remove(new HashtableElement(15)); // esvazia
        table1.remove(new HashtableElement(8));
        table1.remove(new HashtableElement(12));
        table1.remove(new HashtableElement(2));
        table1.remove(new HashtableElement(10));
        table1.remove(new HashtableElement(4));
        assertTrue(table1.isEmpty());

        assertTrue(table2.isEmpty());
    }

    @Test
    public void testIsFull() {
        assertFalse(table1.isFull());
        table1.insert(new HashtableElement(1)); // enche a tabela
        table1.insert(new HashtableElement(23));
        table1.insert(new HashtableElement(37));
        table1.insert(new HashtableElement(49));
        assertTrue(table1.isFull());

        assertFalse(table2.isFull());
    }

    @Test
    public void testSize() {
        assertEquals(6, table1.size());
        table1.insert(new HashtableElement(23));
        assertEquals(7, table1.size());
    }

    @Test
    public void testInsertNull() {
        assertTrue(table2.isEmpty());
        table2.insert(null); // nao deve alterar a tabela
        assertTrue(table2.isEmpty());
    }

    @Test
    public void testInsertRepeat() {
        assertTrue(table2.isEmpty());
        table2.insert(new HashtableElement(5));
        table2.insert(new HashtableElement(5)); // nao deve alterar a tabela
        assertEquals(1, table2.size());
    }

    @Test
    public void testInsertWithExistingElement() {
        assertEquals(6, table1.size());
        table1.insert(new HashtableElement(4)); // nao deve alterar a tabela
        assertEquals(6, table1.size());
        assertFalse(table1.isFull());
    }

    @Test
    public void testRemoveWithNullElement() {
        assertEquals(6, table1.size());
        table1.remove(null); // nao deve alterar a tabela
        assertEquals(6, table1.size());
        assertFalse(table1.isFull());
    }

    @Test
    public void testSearchWithNullElement() {
        assertEquals(null, table1.search(null)); // nao deve alterar a tabela
        assertEquals(6, table1.size());
        assertFalse(table1.isFull());
        assertFalse(table1.isEmpty());
    }

    @Test
    public void testIndexOf() {
        assertEquals(4, table1.indexOf(new HashtableElement(4)));
        assertEquals(-1, table1.indexOf(new HashtableElement(14))); // elemento
                                                                    // inexistente
        assertEquals(-1, table1.indexOf(null));
    }

    @Test
    public void testRemoveUntilEmpty() {
        assertFalse(table1.isEmpty());
        table1.remove(new HashtableElement(10));
        table1.remove(new HashtableElement(15));
        table1.remove(new HashtableElement(2));
        table1.remove(new HashtableElement(12));
        table1.remove(new HashtableElement(8));
        table1.remove(new HashtableElement(4));
        assertTrue(table1.isEmpty());
    }

    @Test
    public void testRemoveElementNotInTable() {
        assertEquals(6, table1.size());
        table1.remove(new HashtableElement(99)); // nao deve alterar a tabela
        assertEquals(6, table1.size());
        assertFalse(table1.isFull());
    }

    @Test
    public void testRemoveInListaEmpty() {
        assertTrue(table2.isEmpty());
        table2.remove(new HashtableElement(99)); // nao deve alterar a tabela
        assertTrue(table2.isEmpty());
    }

    @Test
    public void testRemoveDeletedElement() {
        assertEquals(6, table1.size());
        table1.remove(new HashtableElement(4));
        assertEquals(5, table1.size());
        table1.remove(new HashtableElement(4)); // nao deve alterar a tabela
        assertEquals(5, table1.size());
        assertFalse(table1.isFull());
    }

    @Test
    public void testSearchDeletedElement() {
        assertEquals(new HashtableElement(4),
                table1.search(new HashtableElement(4)));
        table1.remove(new HashtableElement(4));
        assertNull(table1.search(new HashtableElement(4))); 
        assertEquals(5, table1.size());
        assertFalse(table1.isFull());
    }

    @Test
    public void testIndexOfDeletedElement() {
        assertEquals(4, table1.indexOf(new HashtableElement(4)));
        table1.remove(new HashtableElement(4));
        assertEquals(-1, table1.indexOf(new HashtableElement(4)));
        assertEquals(5, table1.size());
        assertFalse(table1.isFull());
    }

    @Test
    public void testSearchInListaEmpty() {
        assertNull(table2.search(new HashtableElement(5))); // nao deve alterar
                                                            // a tabela
        assertTrue(table2.isEmpty());
    }

    @Test
    public void testIndexOfInListaEmpty() {
        assertEquals(-1, table2.indexOf(new HashtableElement(5))); // nao deve
                                                                    // alterar
                                                                    // a tabela
        assertTrue(table2.isEmpty());
    }

    @Test
    public void testIndexOfWithNullElement() {
        assertEquals(-1, table1.indexOf(null)); // nao deve alterar a tabela
        assertEquals(6, table1.size());
        assertFalse(table1.isFull());
        assertFalse(table1.isEmpty());
    }

    @Test
    public void testInsertOverflow() throws HashtableOverflowException {
        Hashtable<HashtableElement> table = new HashtableOpenAddressQuadraticProbingImpl<>(
            1, HashFunctionClosedAddressMethod.DIVISION, 3, 5);

        table.insert(new HashtableElement(1)); // enche a tabela

        HashtableOverflowException thrown = assertThrows(
            HashtableOverflowException.class,
            () -> table.insert(new HashtableElement(11)) // esta deve lançar
        );

        assertEquals("Hashtable overflow!", thrown.getMessage());
    }
}