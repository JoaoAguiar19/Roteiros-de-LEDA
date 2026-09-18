package adt.hashtable.open;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;

public class Linear2 {
    protected AbstractHashtableOpenAddress<HashtableElement> table1;
    protected AbstractHashtableOpenAddress<HashtableElement> table2;

    protected final int PROPOSED_SIZE = 10;

    @Before
    public void setUp() throws Exception {
        table1 = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(
        PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION);
        // o tamanho real utilizado vai ser PROPOSED_SIZE
        table1.insert(new HashtableElement(2)); // coloca no slot indexado com 2
        table1.insert(new HashtableElement(3)); // coloca no slot indexado com 3
        table1.insert(new HashtableElement(4)); // coloca no slot indexado com 4
        table1.insert(new HashtableElement(5)); // coloca no slot indexado com 5

        table2 = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(
        PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION);
    }

 @Test
 public void testInsert() {
 assertEquals(0, table1.getCOLLISIONS());
 table1.insert(new HashtableElement(7)); // nao produz colisao. coloca no
 // slot indexado com 7
 assertEquals(7, table1.indexOf(new HashtableElement(7)));
 assertEquals(0, table1.getCOLLISIONS());

 table1.insert(new HashtableElement(9)); // nao produz colisao. coloca no
 // slot indexado com 9
 assertEquals(9, table1.indexOf(new HashtableElement(9)));
 assertEquals(0, table1.getCOLLISIONS());

 table1.insert(new HashtableElement(12)); // produz colisao com o 2.
 // coloca no slot indexado
 // com 6 (prox disponivel)
 assertEquals(6, table1.indexOf(new HashtableElement(12)));
 assertEquals(4, table1.getCOLLISIONS());

 table2.insert(new HashtableElement(14)); // nao produz colisao. coloca
 // no slot indexado com 4
 assertEquals(4, table2.indexOf(new HashtableElement(14)));
 assertEquals(0, table2.getCOLLISIONS());

 }

 @Test
 public void testRemove() {
 table1.remove(new HashtableElement(12)); // elemento inexistente
 assertEquals(4, table1.size());

 table1.remove(new HashtableElement(5)); // elemento existente
 assertEquals(3, table1.size());
 assertNull(table1.search(new HashtableElement(5)));

 }

 @Test
 public void testSearch() {
 assertEquals(new HashtableElement(5),
 table1.search(new HashtableElement(5))); // elemento que existe
 assertNull(table1.search(new HashtableElement(15))); // elemento que nao
 // existe
 }

 @Test
 public void testIsEmpty() {
 assertFalse(table1.isEmpty());
 table1.remove(new HashtableElement(2)); // esvazia
 table1.remove(new HashtableElement(3));
 table1.remove(new HashtableElement(4));
 table1.remove(new HashtableElement(5));
 assertTrue(table1.isEmpty());

 assertTrue(table2.isEmpty());
 }

 @Test
 public void testIsFull() {
 assertFalse(table1.isFull());
 table1.insert(new HashtableElement(1)); // enche a tabela
 table1.insert(new HashtableElement(6));
 table1.insert(new HashtableElement(7));
 table1.insert(new HashtableElement(8));
 table1.insert(new HashtableElement(9));
 table1.insert(new HashtableElement(10));
 assertTrue(table1.isFull());

 assertFalse(table2.isFull());
 }

 @Test
 public void testSize() {
 assertEquals(4, table1.size());
 }

 @Test
 public void testInsertWithNullElement() {
 assertEquals(4, table1.size());
 table1.insert(null); // nao deve alterar a tabela
 assertEquals(4, table1.size());
 assertFalse(table1.isFull());
 }

 @Test
 public void testInsertWithExistingElement() {
 assertEquals(4, table1.size());
 table1.insert(new HashtableElement(5)); // nao deve alterar a tabela
 assertEquals(4, table1.size());
 assertFalse(table1.isFull());
 }

 @Test
 public void testRemoveWithNullElement() {
 assertEquals(4, table1.size());
 table1.remove(null); // nao deve alterar a tabela
 assertEquals(4, table1.size());
 assertFalse(table1.isFull());
 }

 @Test
 public void testSearchWithNullElement() {
 assertEquals(null,table1.search(null)); // nao deve alterar a tabela
 }

 @Test
 public void testIndexOf() {
 assertEquals(2, table1.indexOf(new HashtableElement(2)));
 assertEquals(-1, table1.indexOf(new HashtableElement(15)));
 assertEquals(-1, table1.indexOf(null));
 }

 @Test
 public void testRemoveUntilEmpty() {
 assertFalse(table1.isEmpty());
 table1.remove(new HashtableElement(2));
 table1.remove(new HashtableElement(3));
 table1.remove(new HashtableElement(4));
 table1.remove(new HashtableElement(5));
 assertTrue(table1.isEmpty());
 }

 @Test
 public void testRemoveNonExistingElement() {
 assertEquals(4, table1.size());
 table1.remove(new HashtableElement(15)); // nao deve alterar a tabela
 assertEquals(4, table1.size());
 assertFalse(table1.isFull());
 }

 @Test
 public void testRemoveDeletedElement() {
 assertEquals(4, table1.size());
 table1.remove(new HashtableElement(2));
 assertEquals(3, table1.size());
 table1.remove(new HashtableElement(2)); // nao deve alterar a tabela
 assertEquals(3, table1.size());
 assertFalse(table1.isFull());
 }

 @Test
 public void testSearchDeletedElement() {
 assertEquals(new HashtableElement(2), table1.search(new HashtableElement(2)));
 table1.remove(new HashtableElement(2));
 assertNull(table1.search(new HashtableElement(2)));
 assertEquals(3, table1.size());
 assertFalse(table1.isFull());
 }

 @Test
 public void testIndexOfDeletedElement() {
 assertEquals(2, table1.indexOf(new HashtableElement(2)));
 table1.remove(new HashtableElement(2));
 assertEquals(-1, table1.indexOf(new HashtableElement(2)));
 assertEquals(3, table1.size());
 assertFalse(table1.isFull());
 }

 @Test
 public void testInsertInListaEmpty() {
 assertTrue(table2.isEmpty());
 table2.insert(new HashtableElement(5));
 assertFalse(table2.isEmpty());
 assertNotNull(table2.search(new HashtableElement(5)));
 assertEquals(1, table2.size());
 assertFalse(table2.isFull());
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
 // alterar a
 // tabela
 assertTrue(table2.isEmpty());
 }

 @Test
 public void testRemoveElementInTable() {
 assertEquals(4, table1.size());
 table1.remove(new HashtableElement(4)); // deve remover
 assertEquals(3, table1.size());
 assertNull(table1.search(new HashtableElement(4)));
 assertFalse(table1.isFull());
 }

 @Test
 public void testRemoveUntilListaEmpty() {
 assertFalse(table1.isEmpty());
 table1.remove(new HashtableElement(2));
 table1.remove(new HashtableElement(3));
 table1.remove(new HashtableElement(4));
 table1.remove(new HashtableElement(5));
 assertTrue(table1.isEmpty());
 }

 @Test
 public void testRemoveInListaEmpty() {
 assertTrue(table2.isEmpty());
 table2.remove(new HashtableElement(5)); // nao deve alterar a tabela
 assertTrue(table2.isEmpty());
 }

 @Test
 public void testIndexOfWithNullElement() {
 assertEquals(-1, table1.indexOf(null)); // nao deve alterar a tabela
 assertFalse(table1.isEmpty());
 }

 @Test
 public void testIndexOfDeletedElementInListaEmpty() {
 assertEquals(-1, table2.indexOf(new HashtableElement(5))); // nao deve
 // alterar a
 // tabela
 assertTrue(table2.isEmpty());
 }

 @Test
 public void testSearchWithNullInListaEmpty() {
 assertEquals(null,table2.search(null)); // nao deve alterar a tabela
 assertTrue(table2.isEmpty());
 }

 @Test
 public void testInsertWithNullInListaEmpty() {
 assertTrue(table2.isEmpty());
 table2.insert(null); // nao deve alterar a tabela
 assertTrue(table2.isEmpty());
 assertEquals(0, table2.size());
 assertFalse(table2.isFull());
 }

 @Test
 public void testRemoveElementNotInTable() {
 assertEquals(0, table2.size());
 table2.remove(new HashtableElement(5)); // nao deve alterar a tabela
 assertEquals(0, table2.size());
 assertFalse(table2.isFull());
 }

 @Test
 public void testRemoveDeletedElementInListaEmpty() {
 assertTrue(table2.isEmpty());
 table2.remove(new HashtableElement(5)); // nao deve alterar a tabela
 assertTrue(table2.isEmpty());
 }

 @Test
 public void testRemoveUntilListaEmptyInListaEmpty() {
 assertTrue(table2.isEmpty());
 table2.remove(new HashtableElement(5)); // nao deve alterar a tabela
 assertTrue(table2.isEmpty());
 }

 @Test
 public void testRemoveDeletedElementInTable() {
 assertEquals(4, table1.size());
 table1.remove(new HashtableElement(4));
 assertEquals(3, table1.size());
 table1.remove(new HashtableElement(4)); // nao deve alterar a tabela
 assertEquals(3, table1.size());
 assertFalse(table1.isFull());
 }

 @Test
 public void testRemoveInListaFull() {
 table1.insert(new HashtableElement(1)); // enche a tabela
 table1.insert(new HashtableElement(6));
 table1.insert(new HashtableElement(7));
 table1.insert(new HashtableElement(8));
 table1.insert(new HashtableElement(9));
 table1.insert(new HashtableElement(10));
 
 assertEquals(10, table1.size());
 assertTrue(table1.isFull());
 table1.remove(new HashtableElement(1)); // deve remover
 assertEquals(9, table1.size());
 assertNull(table1.search(new HashtableElement(1)));
 assertFalse(table1.isFull());
 assertEquals(0, table1.getCOLLISIONS());
 }

 @Test
 public void testSearchInListaFull() {
 table1.insert(new HashtableElement(1)); // enche a tabela
 table1.insert(new HashtableElement(6));
 table1.insert(new HashtableElement(7));
 table1.insert(new HashtableElement(8));
 table1.insert(new HashtableElement(9));
 table1.insert(new HashtableElement(10));
 
 assertEquals(10, table1.size());
 assertTrue(table1.isFull());
 assertEquals(new HashtableElement(1), table1.search(new HashtableElement(1))); // deve encontrar
 assertNull(table1.search(new HashtableElement(11))); // nao deve encontrar
 }

 @Test
 public void testRemoveUntilListaEmptyInListaFull() {
 table1.insert(new HashtableElement(1)); // enche a tabela
 table1.insert(new HashtableElement(6));
 table1.insert(new HashtableElement(7));
 table1.insert(new HashtableElement(8));
 table1.insert(new HashtableElement(9));
 table1.insert(new HashtableElement(10));
 assertEquals(10, table1.size());
 assertTrue(table1.isFull());
 table1.remove(new HashtableElement(1)); // deve remover
 table1.remove(new HashtableElement(2));
 table1.remove(new HashtableElement(3));
 table1.remove(new HashtableElement(4));
 table1.remove(new HashtableElement(5));
 table1.remove(new HashtableElement(6));
 table1.remove(new HashtableElement(7));
 table1.remove(new HashtableElement(8));
 table1.remove(new HashtableElement(9));
 table1.remove(new HashtableElement(10));
 assertTrue(table1.isEmpty());
 table1.remove(new HashtableElement(11)); // nao deve alterar a tabela
 assertFalse(table1.isFull());
 assertEquals(0, table1.getCOLLISIONS());
 }

 @Test
 public void testIndexOfInListaFull() {
 table1.insert(new HashtableElement(1)); // enche a tabela
 table1.insert(new HashtableElement(6));
 table1.insert(new HashtableElement(7));
 table1.insert(new HashtableElement(8));
 table1.insert(new HashtableElement(9));
 table1.insert(new HashtableElement(10));
 assertEquals(10, table1.size());
 assertTrue(table1.isFull());
 assertEquals(1, table1.indexOf(new HashtableElement(1))); // deve encontrar
 assertEquals(-1, table1.indexOf(new HashtableElement(11))); // nao deve encontrar
 assertEquals(0, table1.getCOLLISIONS());
 }

 

 @Test
 public void testInsertWithOverflow() throws HashtableOverflowException {
 table1.insert(new HashtableElement(1)); // enche a tabela
 table1.insert(new HashtableElement(6));
 table1.insert(new HashtableElement(7));
 table1.insert(new HashtableElement(8));
 table1.insert(new HashtableElement(9));
 table1.insert(new HashtableElement(10));

 assertEquals(10, table1.size());
 assertTrue(table1.isFull());

 HashtableOverflowException thrown = assertThrows(
 HashtableOverflowException.class,() -> table1.insert(new HashtableElement(11)) // esta deve lançar
 );

 assertEquals("Hashtable overflow!", thrown.getMessage());

 
 }
}