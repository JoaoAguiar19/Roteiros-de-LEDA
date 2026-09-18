package adt.linkedList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class RecursiveDoubleLinkedListTest {

    private DoubleLinkedList<Integer> lista1;
    private DoubleLinkedList<Integer> lista2;
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
		lista1 = new RecursiveDoubleLinkedListImpl<>();
		lista2 = new RecursiveDoubleLinkedListImpl<>(); // Lista vazia
		lista3 = new RecursiveDoubleLinkedListImpl<>();
	}

    @Test
    public void testInsert() {
        assertEquals(3, lista1.size());
        lista1.insert(5);
        lista1.insert(7);
        assertEquals(5, lista1.size());
        
        // Inserir elemento null
        lista1.insert(null);
        assertEquals(5, lista1.size());

        lista2.insert(null);
        assertEquals(0, lista2.size());

    }

	@Test
	public void testInsertFirst() {
		Integer[] arrayInicial = {1};
		assertArrayEquals(arrayInicial, lista3.toArray());

		Integer[] expected = {7, 1};
		lista3.insertFirst(7);
		assertArrayEquals(expected, lista3.toArray());

		lista3.insertFirst(11);
		lista3.insertFirst(null);

		Integer[] expected2 = {11, 7, 1};
		assertArrayEquals(expected2, lista3.toArray());

		// Lista vazia
		Integer[] arrayVazio = {};
		assertArrayEquals(arrayVazio, lista2.toArray());

		lista2.insertFirst(null);
		assertArrayEquals(arrayVazio, lista2.toArray());

		Integer[] expected3 = {20};
		lista2.insertFirst(20);
		assertArrayEquals(expected3, lista2.toArray());
	}

	

	@Test
	public void testRemove() {
		Integer[] arrayInicial = {3, 2, 1};
		assertArrayEquals(arrayInicial, lista1.toArray());
		
		lista1.remove(1);
		Integer[] expected = {3, 2};
		assertArrayEquals(expected, lista1.toArray());
		
		lista1.remove(7);
		assertArrayEquals(expected, lista1.toArray());
		
		lista1.remove(3);
		lista1.remove(2);
		
		Integer[] expected2 = {};
		assertArrayEquals(expected2, lista1.toArray());
		
		lista1.remove(null);
		assertArrayEquals(expected2, lista1.toArray());
		
		lista3.remove(null);
		Integer[] expected3 = {1};
		assertArrayEquals(expected3, lista3.toArray());
		
		lista3.insert(7);
		lista3.insert(5);
		lista3.insert(21);
		Integer[] expected4 = {1, 7, 5, 21};
		assertArrayEquals(expected4, lista3.toArray());
		lista3.remove(21);
		lista3.remove(5);
		Integer[] expected5 = {1, 7};
		assertArrayEquals(expected5, lista3.toArray());


	}

	@Test
	public void testRemoveFirst() {
		Integer[] arrayInicial = {3, 2, 1};
		assertArrayEquals(arrayInicial, lista1.toArray());
		
		lista1.removeFirst();
		Integer[] expected = {2, 1};
		assertArrayEquals(expected, lista1.toArray());
		
		lista1.removeFirst();
		lista1.removeFirst();
		Integer[] expected2 = {};
		assertArrayEquals(expected2, lista1.toArray());

		lista1.removeFirst();
		assertArrayEquals(expected2, lista1.toArray());
	}

	@Test
	public void testRemoveLast() {
		Integer[] arrayInicial = {3, 2, 1};
		assertArrayEquals(arrayInicial, lista1.toArray());
		
		lista1.removeLast();
		Integer[] expected = {3, 2};
		assertArrayEquals(expected, lista1.toArray());

		lista1.removeLast();
		lista1.removeLast();
		Integer[] expected2 = {};
		assertArrayEquals(expected2, lista1.toArray());

		lista1.removeLast();
		assertArrayEquals(expected2, lista1.toArray());
	}

	@Test
    public void testGetPreviousInitiallyNull() {
        RecursiveDoubleLinkedListImpl<String> node = new RecursiveDoubleLinkedListImpl<>();
        assertNull(node.getPrevious());
    }

    @Test
    public void testSetAndGetPrevious() {
        RecursiveDoubleLinkedListImpl<String> node1 = new RecursiveDoubleLinkedListImpl<>();
        RecursiveDoubleLinkedListImpl<String> node2 = new RecursiveDoubleLinkedListImpl<>();

        node1.setPrevious(node2);
        assertEquals(node2, node1.getPrevious());
    }

    @Test
    public void testSetPreviousNull() {
        RecursiveDoubleLinkedListImpl<String> node = new RecursiveDoubleLinkedListImpl<>();
        node.setPrevious(null);
        assertNull(node.getPrevious());
    }

    @Test
    public void testInsertUpdatesPrevious() {
        RecursiveDoubleLinkedListImpl<String> list = new RecursiveDoubleLinkedListImpl<>();

        list.insert("A");
        assertNotNull(list.getPrevious());
        assertTrue(list.getPrevious().isEmpty());

        RecursiveDoubleLinkedListImpl<String> secondNode = (RecursiveDoubleLinkedListImpl<String>) list.next;
        assertEquals(list, secondNode.getPrevious());
    }
}
