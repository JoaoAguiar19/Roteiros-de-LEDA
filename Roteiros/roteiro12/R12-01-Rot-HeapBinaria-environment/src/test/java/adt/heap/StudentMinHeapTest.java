package adt.heap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

public class StudentMinHeapTest {

	Heap<Integer> heap;

	@Before
	public void setUp() {
		// TODO Instancie seu comparator para fazer sua estrutura funcionar como
		// uma min heap aqui. Use instanciacao anonima da interface
		// Comparator!!!!
		Comparator<Integer> comparator = new ComparatorMinHeap<>();
		heap = new HeapImpl<Integer>(comparator);
	}

	@Test
	public void testBuild() {
		heap.buildHeap(new Integer[] { 82, 6, 99, 12, 34, 64, 58, 1 });

		assertEquals(8, heap.size());
		assertFalse(heap.isEmpty());
		//verifyHeap(new Integer[] { 1, 6, 58, 12, 34, 64, 99, 82 });
		verifyHeap(new Integer[] { 1, 6, 58, 12, 34, 99, 64, 82 });
	}

	@Test
	public void testInsert() {
		heap.insert(8);
		heap.insert(12);
		heap.insert(-2);
		heap.insert(7);
		heap.insert(8);
		heap.insert(-5);
		heap.insert(14);
		heap.insert(3);
		heap.insert(-10);
		heap.insert(0);

		assertEquals(10, heap.size());
		assertFalse(heap.isEmpty());

		verifyHeap(new Integer[] { -10, -5, -2, 3, 0, 8, 14, 12, 7, 8 });
	}

	@Test
	public void testRemove() {
		heap.insert(22);
		heap.insert(45);
		heap.insert(38);
		heap.insert(17);
		heap.insert(40);
		heap.insert(15);
		heap.insert(26);
		heap.insert(79);
		heap.insert(53);
		heap.insert(30);

		assertEquals(new Integer(15), heap.extractRootElement());
		assertEquals(new Integer(17), heap.extractRootElement());
		assertEquals(new Integer(22), heap.extractRootElement());
		assertEquals(new Integer(26), heap.extractRootElement());
		assertEquals(new Integer(30), heap.extractRootElement());

		assertEquals(5, heap.size());
		assertFalse(heap.isEmpty());

		verifyHeap(new Integer[] { 38, 40, 79, 45, 53 });
	}

	@Test
	public void testSort() {
		assertArrayEquals(new Integer[] { 5, 6, 12, 20, 34, 43, 49, 92 },
				heap.heapsort(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 }));

		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());

		assertArrayEquals(new Integer[] {}, heap.toArray());
	}

	private void verifyHeap(Integer[] expected) {
		boolean isHeap = true;

		Comparable<Integer>[] original = heap.toArray();

		Arrays.sort(expected);
		Arrays.sort(original);

		if (Arrays.equals(expected, original) == false)
			isHeap = false;

		original = heap.toArray();

		for (int i = 0; i < original.length; i++) {
			if (2 * i + 1 < original.length && original[i].compareTo((Integer) original[2 * i + 1]) > 0)
				isHeap = false;
			if (2 * i + 2 < original.length && original[i].compareTo((Integer) original[2 * i + 2]) > 0)
				isHeap = false;
		}

		assertTrue(isHeap);
	}

	@Test
	public void testInsertNull() {
		// Heap vazia
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());

		// Inserindo um elemento null
		heap.insert(null);
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());

		// Heap com elementos
		heap.insert(10);
		heap.insert(8);
		heap.insert(5);
		heap.insert(14);

		assertEquals(4, heap.size());
		assertFalse(heap.isEmpty());
		verifyHeap(new Integer[] {5, 8, 10, 14});

		// Inserindo um elemento null
		heap.insert(null);

		assertEquals(4, heap.size());
		assertFalse(heap.isEmpty());
		verifyHeap(new Integer[] {5, 8, 10, 14});
	}

	@Test
	public void testCrescimentoHeap() {
		// Heap vazia
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());

		// Inserindo 20 elementos para forcar o crescimento da heap
		heap.insert(10);
		heap.insert(8);
		heap.insert(5);
		heap.insert(14);
		heap.insert(18);
		heap.insert(19);
		heap.insert(22);
		heap.insert(3);
		heap.insert(27);
		heap.insert(1);
		heap.insert(2);
		heap.insert(7);
		heap.insert(9);
		heap.insert(6);
		heap.insert(4);
		heap.insert(11);
		heap.insert(13);
		heap.insert(12);
		heap.insert(15);
		heap.insert(17);

		assertEquals(20, heap.size());
		assertFalse(heap.isEmpty());

		verifyHeap(new Integer[] { 27, 22, 19, 14, 18, 10, 17, 3, 8, 1, 2, 5, 9,
				6, 4, 11, 13, 12, 15, 7 });

		// Inserindo mais elementos
		heap.insert(21);
		heap.insert(23);

		assertEquals(22, heap.size());
		assertFalse(heap.isEmpty());
		verifyHeap(new Integer[] { 27, 23, 21, 14, 22, 19, 17, 3, 8, 1, 2, 5,
				9, 6, 4, 11, 13, 12, 15, 7, 10, 18 });
	}

	@Test
	public void testBuildNull() {
		// Heap vazia
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());

		// Passando null
		heap.buildHeap(null);

		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
		verifyHeap(new Integer[] {});

		// Passando array vazio
		heap.buildHeap(new Integer[] {});
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
		verifyHeap(new Integer[] {});

		// // Passando array com elementos null
		// heap.buildHeap(new Integer[] { null, null, null });
		// assertEquals(0, heap.size());
		// assertTrue(heap.isEmpty());
		// verifyHeap(new Integer[] {});

		// // Passando array com elementos null
		// heap.buildHeap(new Integer[] { null, 10, null, 3, null, 8, 5, null});
		// assertEquals(4, heap.size());
		// assertFalse(heap.isEmpty());
		// verifyHeap(new Integer[] {10, 5, 8 ,3});

	}

	@Test
	public void testRemoveHeapVazia() {
		// Heap vazia
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());

		assertNull(heap.extractRootElement());
	}

	@Test
	public void testRootElement() {
		// Heap vazia
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());

		assertNull(heap.rootElement());

		// Heap com elemento
		heap.insert(10);
		heap.insert(8);
		heap.insert(5);
		heap.insert(14);

		assertEquals(4, heap.size());
		assertFalse(heap.isEmpty());
		verifyHeap(new Integer[] {5, 8, 10, 14});

		assertEquals(Integer.valueOf(5), heap.rootElement());

		// Mudando o root
		heap.extractRootElement();
		assertEquals(Integer.valueOf(8), heap.rootElement());
		verifyHeap(new Integer[] {8, 10, 14 });
	}

}
