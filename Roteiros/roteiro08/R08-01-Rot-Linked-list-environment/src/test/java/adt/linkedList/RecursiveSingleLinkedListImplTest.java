package adt.linkedList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class RecursiveSingleLinkedListImplTest {
    
    protected LinkedList<Integer> lista1;
    protected LinkedList<Integer> lista2;

    @Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		lista1 = new RecursiveSingleLinkedListImpl<>();
		lista2 = new RecursiveSingleLinkedListImpl<>();
	}

    @Test
    public void testSize() {
        assertEquals(3, lista1.size());
        assertEquals(0, lista2.size());
        
    }

    @Test
    public void testInsert() {
        assertEquals(0, lista2.size());
        lista2.insert(20);
        lista2.insert(8);
        assertEquals(2, lista2.size());
    
    }
    @Test
    public void testInsertNull() {
        assertEquals(3, lista1.size());
        lista2.insert(null);
        assertEquals(3, lista1.size());
    }

    @Test
    public void testSearch() {
        // Lista com elemento
        assertEquals(Integer.valueOf(2), lista1.search(2)); // Elemento do meio
        assertEquals(Integer.valueOf(1), lista1.search(1)); // Último elemento
        assertEquals(Integer.valueOf(3), lista1.search(3)); // Primeiro elemento
        assertEquals(null, lista1.search(20)); // Elemento inexistente

        // Lista vazia
        assertEquals(null, lista2.search(2));
        assertEquals(null, lista1.search(null));
    }

    @Test
    public void testRemove() {
        assertEquals(3, lista1.size());
        lista1.remove(1);
        lista1.remove(3);
        assertEquals(1, lista1.size());

        // Elemento null
        lista1.remove(null);
        assertEquals(1, lista1.size());

        // Elemento que não está na lista
        lista1.remove(10);
        assertEquals(1, lista1.size());

        // Lista vazia
        assertEquals(0, lista2.size());
        lista2.remove(5);
        lista2.remove(null);
        assertEquals(0, lista2.size());
    }

    @Test
    public void testToArray() {
        // Lista1 = {3, 2, 1}
        Integer[] expected1 = {3, 2, 1};
        assertArrayEquals(expected1, lista1.toArray());

        // Lista2 = {}
        Integer[] expected2 = {};
        assertArrayEquals(expected2, lista2.toArray());

        // Lista2 = {20, 8, 2025}
        lista2.insert(20);
        lista2.insert(8);
        lista2.insert(2025);

        Integer[] expected3 = {20, 8, 2025};
        assertArrayEquals(expected3, lista2.toArray());
    }

    @Test
	public void testGetSetData() {
		RecursiveSingleLinkedListImpl<Integer> node = new RecursiveSingleLinkedListImpl<>();
		node.setData(42);
		assertEquals(Integer.valueOf(42), node.getData());
	}

	@Test
	public void testGetSetNext() {
		RecursiveSingleLinkedListImpl<Integer> node1 = new RecursiveSingleLinkedListImpl<>();
		RecursiveSingleLinkedListImpl<Integer> node2 = new RecursiveSingleLinkedListImpl<>();
		
		node1.setNext(node2);
		assertEquals(node2, node1.getNext());
	}
    
}
