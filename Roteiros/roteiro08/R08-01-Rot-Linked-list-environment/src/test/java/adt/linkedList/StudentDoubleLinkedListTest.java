package adt.linkedList;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class StudentDoubleLinkedListTest extends StudentLinkedListTest {

	private DoubleLinkedList<Integer> lista3;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

		// Lista com 1 elemento.
		lista3.insert(1);
	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		lista1 = new DoubleLinkedListImpl<>();
		lista2 = new DoubleLinkedListImpl<>(); // Lista vazia
		lista3 = new DoubleLinkedListImpl<>();
	}

	// Métodos de DoubleLinkedList

	// Lista com elementos
	@Test
	public void testInsertFirst() {
		((DoubleLinkedList<Integer>) lista1).insertFirst(4);
		Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista1.toArray());

		// Tentar inserir um elemento null no inicio
		((DoubleLinkedList<Integer>) lista1).insertFirst(null);
		Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista1.toArray());
	}

	@Test
	public void testInsertLast() {
		((DoubleLinkedList<Integer>) lista1).insert(0);
		Assert.assertArrayEquals(new Integer[] { 3, 2, 1, 0 }, lista1.toArray());

		// Tentar inserir um elemento null no final
		((DoubleLinkedList<Integer>) lista1).insert(null);
		Assert.assertArrayEquals(new Integer[] { 3, 2, 1, 0 }, lista1.toArray());
	}

	@Test
	public void testRemoveFirst() {
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] { 2, 1 }, lista1.toArray());
	}

	@Test
	public void testRemoveLast() {
		((DoubleLinkedList<Integer>) lista1).removeLast();
		Assert.assertArrayEquals(new Integer[] { 3, 2 }, lista1.toArray());
	}


	// Lista vazia
	@Test
	public void testInsertFirstIsEmpty() {
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
		((DoubleLinkedList<Integer>) lista2).insertFirst(4);
		Assert.assertArrayEquals(new Integer[] {4}, lista2.toArray());

		// Tentar inserir um elemento null no inicio
		((DoubleLinkedList<Integer>) lista2).insertFirst(null);
		Assert.assertArrayEquals(new Integer[] {4}, lista2.toArray());
	}

	@Test
	public void testInsertLastIsEmpty() {
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
		((DoubleLinkedList<Integer>) lista2).insert(0);
		Assert.assertArrayEquals(new Integer[] {0}, lista2.toArray());

		// Tentar inserir um elemento null no final
		((DoubleLinkedList<Integer>) lista2).insert(null);
		Assert.assertArrayEquals(new Integer[] {0}, lista2.toArray());
	}

	@Test
	public void testRemoveFirstIsEmpty() {
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());

		((DoubleLinkedList<Integer>) lista2).removeFirst();
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
	}

	@Test
	public void testRemoveLastIsEmpty() {
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());

		((DoubleLinkedList<Integer>) lista2).removeLast();
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
	}

	// Remover de uma lista com um elemento
	@Test
	public void testRemoveElementSingleElement() {
		((DoubleLinkedList<Integer>) lista3).remove(1);
		Assert.assertArrayEquals(new Integer[] {}, lista3.toArray());
	}
	@Test
	public void testRemoveFirstSingleElement() {
		((DoubleLinkedList<Integer>) lista3).removeFirst();
		Assert.assertArrayEquals(new Integer[] {}, lista3.toArray());
	}

	@Test
	public void testRemoveLastSingleElement() {
		((DoubleLinkedList<Integer>) lista3).removeLast();
		Assert.assertArrayEquals(new Integer[] {}, lista3.toArray());
	}

	// Pesquisar em uma lista
	@Test
	public void testSearch() {
		//Extremos
		assertEquals(Integer.valueOf(3), lista1.search(3));
		assertEquals(Integer.valueOf(1), lista1.search(1));

		// Meio
		assertEquals(Integer.valueOf(2), lista1.search(2));

		// Elemento inexistente ou null
		assertEquals(null, lista1.search(8));
		assertEquals(null, lista1.search(null));

		// Lista com um único elemento
		assertEquals(Integer.valueOf(1), lista3.search(1));
		assertEquals(null, lista3.search(7));
		assertEquals(null, lista3.search(null));

		// Lista vazia
		assertEquals(null, lista2.search(1));
		assertEquals(null, lista2.search(null));
	}

	@Test
	public void testSeatchTwoElement() {
		lista2.insert(20);
		lista2.insert(21);

		assertEquals(Integer.valueOf(21), lista2.search(21));		
		assertEquals(Integer.valueOf(20), lista2.search(20));		
	}

	@Test
	public void testGetLastAfterInsertions() {
		DoubleLinkedListImpl<Integer> lista = new DoubleLinkedListImpl<>();
		lista.insert(10);
		lista.insert(20);
		lista.insert(30);

		assertEquals(Integer.valueOf(30), lista.getLast().getData());
	}

	@Test
	public void testSetLastManually() {
		DoubleLinkedListImpl<Integer> lista = new DoubleLinkedListImpl<>();
		DoubleLinkedListNode<Integer> node1 = new DoubleLinkedListNode<>(10, new DoubleLinkedListNode<>(), new DoubleLinkedListNode<>());
		DoubleLinkedListNode<Integer> node2 = new DoubleLinkedListNode<>(99, new DoubleLinkedListNode<>(), new DoubleLinkedListNode<>());

		lista.setLast(node1);  // setando manualmente
		assertEquals(Integer.valueOf(10), lista.getLast().getData());

		lista.setLast(node2);  // trocando
		assertEquals(Integer.valueOf(99), lista.getLast().getData());
	}

	@Test
	public void testGetLastOnEmptyList() {
		DoubleLinkedListImpl<Integer> lista = new DoubleLinkedListImpl<>();
		assertTrue(lista.getLast().isNIL());
	}

}
