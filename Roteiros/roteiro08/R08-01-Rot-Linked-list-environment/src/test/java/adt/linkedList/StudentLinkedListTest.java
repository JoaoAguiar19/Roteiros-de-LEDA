package adt.linkedList;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class StudentLinkedListTest {

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
		lista1 = new SingleLinkedListImpl<>();
		lista2 = new SingleLinkedListImpl<>();
	}

	@Test
	public void testIsEmpty() {
		Assert.assertFalse(lista1.isEmpty());
		Assert.assertTrue(lista2.isEmpty());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(3, lista1.size());
		Assert.assertEquals(0, lista2.size());
	}

	@Test
	public void testSearch() {
		Assert.assertTrue(2 == lista1.search(2));
		Assert.assertNull(lista1.search(4));
		Assert.assertFalse(3 == lista1.search(2));
	}

	@Test
	public void testInsert() {
		Assert.assertEquals(3, lista1.size());
		lista1.insert(5);
		lista1.insert(7);
		Assert.assertEquals(5, lista1.size());

		Assert.assertEquals(0, lista2.size());
		lista2.insert(4);
		lista2.insert(7);
		Assert.assertEquals(2, lista2.size());
	}

	@Test
	public void testRemove() {
		Assert.assertEquals(3, lista1.size());
		lista1.remove(2);
		lista1.remove(1);
		Assert.assertEquals(1, lista1.size());

		lista1.remove(10);
		Assert.assertEquals(1, lista1.size());

	}

	@Test
	public void testToArray() {
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
		Assert.assertArrayEquals(new Integer[] { 3, 2, 1 }, lista1.toArray());
	}

	@Test
	public void testSearchNull() {
		Assert.assertNull(lista1.search(null));
		Assert.assertNull(lista2.search(null));
	}

	@Test
	public void testInsertNull() {
		assertEquals(3, lista1.size());
		lista1.insert(null);
		assertEquals(3, lista1.size());
		
		assertEquals(0, lista2.size());
		lista2.insert(null);
		assertEquals(0, lista2.size());
	}

	@Test
	public void testRemoveNull() {
		assertEquals(3, lista1.size());
		lista1.remove(3);
		assertEquals(2, lista1.size());

		lista1.remove(null);
		assertEquals(2, lista1.size());
	}

	@Test
	public void testRemoveListaVazia() {
		assertEquals(0, lista2.size());
		lista2.remove(5);
		assertEquals(0, lista2.size());

		assertEquals(0, lista2.size());
		lista2.remove(null);
		assertEquals(0, lista2.size());
	}
	
	@Test
	public void testGetHead() {
		// lista1 foi inicializada com 3 -> 2 -> 1 no setUp()
		SingleLinkedListImpl<Integer> impl = (SingleLinkedListImpl<Integer>) lista1;

		// Verifica se a cabeça tem o valor 3
		assertEquals(Integer.valueOf(3), impl.getHead().getData());

		// Verifica se a cabeça da lista vazia é NIL
		SingleLinkedListImpl<Integer> impl2 = (SingleLinkedListImpl<Integer>) lista2;
		assertTrue(impl2.getHead().isNIL());
	}

	@Test
	public void testSetHead() {
		SingleLinkedListImpl<Integer> impl = (SingleLinkedListImpl<Integer>) lista1;

		// Cria um novo nó para ser a nova cabeça
		SingleLinkedListNode<Integer> newHead = new SingleLinkedListNode<>(99, impl.getHead());

		// Ajusta a cabeça
		impl.setHead(newHead);

		// Verifica se a cabeça foi alterada corretamente
		assertEquals(Integer.valueOf(99), impl.getHead().getData());

		// Verifica se o próximo nó da cabeça é o antigo head
		assertEquals(Integer.valueOf(3), impl.getHead().getNext().getData());
	}

}