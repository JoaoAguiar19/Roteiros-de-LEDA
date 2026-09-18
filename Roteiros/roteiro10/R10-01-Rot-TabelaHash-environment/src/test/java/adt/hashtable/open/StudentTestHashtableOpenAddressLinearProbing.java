package adt.hashtable.open;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.open.AbstractHashtableOpenAddress;
import adt.hashtable.open.HashtableElement;
import adt.hashtable.open.HashtableOpenAddressLinearProbingImpl;

public class StudentTestHashtableOpenAddressLinearProbing {

	protected AbstractHashtableOpenAddress<HashtableElement> table1;
	protected AbstractHashtableOpenAddress<HashtableElement> table2;
	protected AbstractHashtableOpenAddress<HashtableElement> table3;

	protected final int PROPOSED_SIZE = 10;

	@Before
	public void setUp() throws Exception {
		table1 = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION);
		// o tamanho real utilizado vai ser PROPOSED_SIZE
		table1.insert(new HashtableElement(2)); // coloca no slot indexado com 2
		table1.insert(new HashtableElement(3)); // coloca no slot indexado com 3
		table1.insert(new HashtableElement(4)); // coloca no slot indexado com 4
		table1.insert(new HashtableElement(5)); // coloca no slot indexado com 5

		table2 = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION);
		table3 = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(3, HashFunctionClosedAddressMethod.DIVISION);
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
	public void testInsertNull() {
		assertEquals(0, table3.size());
		table3.insert(null);
		assertEquals(0, table3.size());
	}

	@Test
	public void testInsertRepeat() {
		assertEquals(0, table3.size());
		table3.insert(new HashtableElement(1));
		table3.insert(new HashtableElement(2));
		assertEquals(2, table3.size());

		table3.insert(new HashtableElement(2));
		assertEquals(2, table3.size());

	}

	@Test
	public void testInsertUmElementoRemovido() {
		assertEquals(0, table3.size());
		table3.insert(new HashtableElement(1));
		table3.insert(new HashtableElement(2));
		assertEquals(2, table3.size());

		// Remove o elemento 2
		table3.remove(new HashtableElement(2));
		assertEquals(1, table3.size());

		// Inseriri elemento 2 novamente
		table3.insert(new HashtableElement(2));
		assertEquals(2, table3.size());

	}

	@Test
	public void testInsertException() throws HashtableOverflowException {
		table3.insert(new HashtableElement(1));
		table3.insert(new HashtableElement(2));
		table3.insert(new HashtableElement(3));
		assertTrue(table3.isFull());
		assertThrows(HashtableOverflowException.class, () -> {
			table3.insert(new HashtableElement(4)); // deve gerar excecao
		});
	}

	@Test
	public void testRemoveNull() {
		// Com a lista vazia
		assertEquals(0, table3.size());
		table3.remove(null);
		
		// Com elementos
		assertEquals(0, table3.size());
		table3.insert(new HashtableElement(1));
		table3.insert(new HashtableElement(2));
		assertEquals(2, table3.size());

		table3.remove(null);
		assertEquals(2, table3.size());
		
	}

	@Test
	public void testRemoveElementoNaoInserido() {
		//Com a lista vazia
		assertEquals(0, table3.size());
		table3.remove(new HashtableElement(9));

		// Com elementos
		assertEquals(0, table3.size());
		table3.insert(new HashtableElement(1));
		table3.insert(new HashtableElement(2));
		table3.insert(new HashtableElement(3));
		assertEquals(3, table3.size());

		table3.remove(new HashtableElement(9));
		assertEquals(3, table3.size());
	}

	@Test
	public void testSearchNull() {
		//Com a lista vazia
		assertEquals(0, table3.size());
		assertNull(table3.search(null));

		// Com elementos
		assertEquals(0, table3.size());
		table3.insert(new HashtableElement(1));
		table3.insert(new HashtableElement(2));
		table3.insert(new HashtableElement(3));
		assertEquals(3, table3.size());

		assertNull(table3.search(null));

	}

	@Test
	public void testSearchElementoNaoInserido() {
		//Com a lista vazia
		assertEquals(0, table3.size());
		assertNull(table3.search(new HashtableElement(15)));

		// Com elementos
		assertEquals(0, table3.size());
		table3.insert(new HashtableElement(1));
		table3.insert(new HashtableElement(2));
		table3.insert(new HashtableElement(3));
		assertEquals(3, table3.size());

		assertNull(table3.search(new HashtableElement(15)));
	}

	@Test
	public void testIndexOf() {
		//Com a lista vazia
		assertEquals(0, table3.size());
		assertEquals(-1, table3.indexOf(new HashtableElement(9)));
		assertEquals(-1, table3.indexOf(null));

		// Com elementos
		assertEquals(0, table3.size());
		table3.insert(new HashtableElement(1));
		table3.insert(new HashtableElement(2));
		assertEquals(1, table3.indexOf(new HashtableElement(1)));
		assertEquals(2, table3.indexOf(new HashtableElement(2)));
		assertEquals(-1, table3.indexOf(new HashtableElement(9)));
		assertEquals(-1, table3.indexOf(null));

		// Com a tabela cheia
		table3.insert(new HashtableElement(3));
		assertTrue(table3.isFull());
		assertEquals(1, table3.indexOf(new HashtableElement(1)));
		assertEquals(2, table3.indexOf(new HashtableElement(2)));
		assertEquals(0, table3.indexOf(new HashtableElement(3)));
		assertEquals(-1, table3.indexOf(new HashtableElement(9)));
		assertEquals(-1, table3.indexOf(null));
	}

}
