package adt.hashtable.closed;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.hashtable.closed.AbstractHashtableClosedAddress;
import adt.hashtable.closed.HashtableClosedAddressImpl;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;

public class StudentTestHashtableClosedAddressMultiplicationMethod {

	protected AbstractHashtableClosedAddress<Integer> table1;
	protected AbstractHashtableClosedAddress<Integer> table2;
	protected AbstractHashtableClosedAddress<Integer> table3;

	protected final int PROPOSED_SIZE = 100;

	@Before
	public void setUp() throws Exception {
		table1 = new HashtableClosedAddressImpl<Integer>(PROPOSED_SIZE, HashFunctionClosedAddressMethod.MULTIPLICATION);

		Integer initialValue = 200;
		int increment = 5;
		while (initialValue < 600) {
			table1.insert(initialValue);
			initialValue = initialValue + increment;
		}

		table2 = new HashtableClosedAddressImpl<Integer>(PROPOSED_SIZE, HashFunctionClosedAddressMethod.MULTIPLICATION);
		table3 = new HashtableClosedAddressImpl<Integer>(PROPOSED_SIZE, HashFunctionClosedAddressMethod.MULTIPLICATION);
	}

	@Test
	public void testInsert() {
		assertEquals(13, table1.getCOLLISIONS());
		table1.insert(105); // nao produz colisao
		assertEquals(13, table1.getCOLLISIONS());
		assertEquals(89, table1.indexOf(105));
		table1.insert(110); // nao produz colisao
		assertEquals(13, table1.getCOLLISIONS());
		assertEquals(98, table1.indexOf(110));
		table1.insert(101); //
		assertEquals(13, table1.getCOLLISIONS());
		assertEquals(42, table1.indexOf(101));
		table1.insert(102); //
		assertEquals(14, table1.getCOLLISIONS());
		assertEquals(3, table1.indexOf(102));

		table2.insert(103); // nao produz colisao inserindo 1 elemento na talbe
							// vazia
		assertEquals(0, table2.getCOLLISIONS());
		assertEquals(65, table2.indexOf(103));
	}

	@Test
	public void testRemove() {
		int currentSize = table1.size();
		table1.remove(200); // elemento existente
		assertEquals(currentSize - 1, table1.size());
		assertEquals(-1, table1.indexOf(200));
	}

	@Test
	public void testSearch() {
		// busca um elemento inexistente. compara a posicao
		assertNull(table1.search(100));
		assertEquals(-1, table1.indexOf(100));

		// busca um elemento existente. compara a posicao
		assertEquals(new Integer(305), table1.search(305));
		assertEquals(50, table1.indexOf(305));

	}

	@Test
	public void testIsEmpty() {
		assertFalse(table1.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(table1.isFull());
	}

	@Test
	public void testSize() {
		assertEquals(80, table1.size());
	}

	@Test
	public void testInsertNull() {
		assertTrue(table3.isEmpty());
		table3.insert(null); 
		assertTrue(table3.isEmpty());
	}

	@Test
	public void testInsertRepeat(){
		assertTrue(table3.isEmpty());
		table3.insert(5); 
		table3.insert(5);
		assertEquals(1, table3.size());
	}
	
	@Test
	public void testRemoveNull() {
		table3.insert(5);
		table3.insert(8);
		table3.remove(5);
		assertEquals(-1, table3.indexOf(5));
		
		table3.remove(null);
		assertEquals(1, table3.size());
		
		HashtableClosedAddressImpl<Integer> table4 = new HashtableClosedAddressImpl<Integer>(3, HashFunctionClosedAddressMethod.MULTIPLICATION);
		table4.remove(9);
		table4.insert(9);
		assertEquals(1, table4.size());
		table4.remove(null);
		assertEquals(1, table4.size());
	}

	@Test
	public void testInsertSeach(){
		assertTrue(table3.isEmpty());
		table3.insert(5); 
		table3.insert(8);
		assertEquals(Integer.valueOf(5), table3.search(5));
		assertEquals(null, table3.search(null));

		table3.remove(5);
		assertEquals(null, table3.search(5));

	}

}
