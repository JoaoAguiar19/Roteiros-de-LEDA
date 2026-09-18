package adt.avltree;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import adt.bst.BSTNode;

public class StudentAVLTest {

	private AVLTree<Integer> avl;
	private BSTNode<Integer> NIL = new BSTNode<Integer>();
	private AVLTreeVerifier<Integer> verifier;

	@Before
	public void setUp() {
		avl = new AVLTreeImpl<>();
		verifier = new AVLTreeVerifierImpl<>(avl);
	}

	@Test
	public void testInit() {
		assertTrue(avl.isEmpty());
		assertEquals(0, avl.size());
		assertEquals(-1, avl.height());
		assertEquals(NIL, avl.getRoot());
		assertTrue(verifier.isAVLTree());

	}

	@Test
	public void testInsert() {
		assertTrue(verifier.isAVLTree());

		avl.insert(-10);
		assertEquals(1, avl.size());
		assertEquals(0, avl.height());
		assertArrayEquals(new Integer[] { -10 }, avl.preOrder());
		assertTrue(verifier.isAVLTree());

		assertFalse(avl.isEmpty());
		assertEquals(new Integer(-10), avl.getRoot().getData());
		
		avl.insert(-15);
		assertEquals(2, avl.size());
		assertEquals(1, avl.height());
		assertArrayEquals(new Integer[] { -10, -15 }, avl.preOrder());
		assertTrue(verifier.isAVLTree());
		
		avl.insert(20);
		assertEquals(3, avl.size());
		assertEquals(1, avl.height());
		assertArrayEquals(new Integer[] { -10, -15, 20 }, avl.preOrder());
		assertTrue(verifier.isAVLTree());
		
	}

	@Test
	public void testRemove() {
		avl.insert(55);
		avl.insert(9);
		avl.insert(91);
		avl.insert(12);
		assertTrue(verifier.isAVLTree());

		avl.remove(-1);
		assertEquals(4, avl.size());
		assertTrue(verifier.isAVLTree());

		avl.remove(91);
		assertEquals(3, avl.size());
		assertArrayEquals(new Integer[] { 12, 9, 55 }, avl.preOrder());
		assertTrue(verifier.isAVLTree());

		avl.remove(12);
		assertEquals(2, avl.size());
		assertArrayEquals(new Integer[] { 55, 9 }, avl.preOrder());
		assertTrue(verifier.isAVLTree());

		avl.remove(9);
		avl.remove(55);
		assertEquals(NIL, avl.getRoot());
		assertTrue(avl.isEmpty());
		assertTrue(verifier.isAVLTree());

	}

	@Test
	public void testRotacao() {
		avl.insert(10);
		avl.insert(15);
		assertEquals(Integer.valueOf(10), avl.getRoot().getData());
		Integer[] expectedPreOrder = new Integer[]{10, 15};
		assertArrayEquals(expectedPreOrder, avl.preOrder());
		assertTrue(verifier.isAVLTree());

		// Caso RR
		avl.insert(20);
		expectedPreOrder = new Integer[]{15, 10, 20};
		assertArrayEquals(expectedPreOrder, avl.preOrder());
		assertEquals(Integer.valueOf(15), avl.getRoot().getData());
		assertTrue(verifier.isAVLTree());
		
		 // Caso LL
		 avl.insert(5);
		 avl.insert(0);
		 expectedPreOrder = new Integer[]{15, 5, 0, 10, 20};
		 assertArrayEquals(expectedPreOrder, avl.preOrder());
		 assertEquals(Integer.valueOf(15), avl.getRoot().getData());
		 assertTrue(verifier.isAVLTree());

		 // Caso LR
		 avl = new AVLTreeImpl<>();
		 avl.insert(20);
		 avl.insert(10);
		 avl.insert(15);
		 expectedPreOrder = new Integer[]{15, 10, 20};
		 assertArrayEquals(expectedPreOrder, avl.preOrder());
		 assertEquals(Integer.valueOf(15), avl.getRoot().getData());
		 assertTrue(verifier.isAVLTree());

		 // Caso RL
		 avl = new AVLTreeImpl<>();
		 avl.insert(20);
		 avl.insert(25);
		 avl.insert(23);
		 expectedPreOrder = new Integer[]{23, 20, 25};
		 assertArrayEquals(expectedPreOrder, avl.preOrder());
		 assertEquals(Integer.valueOf(23), avl.getRoot().getData());
		 assertTrue(verifier.isAVLTree());

	}

	@Test
	public void testRemove2() {
		// Removendo de uma árvore vazia
		assertTrue(verifier.isAVLTree());
		assertTrue(avl.isEmpty());
		assertTrue(avl.size() == 0);
		assertEquals(null, avl.getRoot().getData());
		avl.remove(1);
		assertTrue(avl.isEmpty());
		assertTrue(avl.size() == 0);
		assertEquals(null, avl.getRoot().getData());

		// Removendo a raiz 
		avl.insert(10);
		assertFalse(avl.isEmpty());
		assertTrue(avl.size() == 1);
		assertEquals(Integer.valueOf(10), avl.getRoot().getData());
		assertTrue(verifier.isAVLTree());

		avl.remove(10);
		assertTrue(avl.isEmpty());
		assertTrue(avl.size() == 0);
		assertEquals(null, avl.getRoot().getData());
		assertTrue(verifier.isAVLTree());

		// Removendo nó folha
		avl.insert(10);
		avl.insert(5);
		avl.insert(15);
		assertFalse(avl.isEmpty());
		assertTrue(avl.size() == 3);

		Integer[] expectedPreOrder = new Integer[]{10, 5, 15};
		assertArrayEquals(expectedPreOrder, avl.preOrder());
		assertTrue(verifier.isAVLTree());

		assertEquals(Integer.valueOf(10), avl.getRoot().getData());
		avl.remove(5);
		assertFalse(avl.isEmpty());
		assertTrue(avl.size() == 2);
		assertEquals(Integer.valueOf(10), avl.getRoot().getData());

		expectedPreOrder = new Integer[]{10, 15};
		assertArrayEquals(expectedPreOrder, avl.preOrder());
		assertTrue(verifier.isAVLTree());

		// Removendo nó com um filho
		avl.insert(5);
		avl.insert(3);
		assertFalse(avl.isEmpty());
		assertTrue(avl.size() == 4);
		assertEquals(Integer.valueOf(10), avl.getRoot().getData());

		expectedPreOrder = new Integer[]{10, 5, 3, 15};
		assertArrayEquals(expectedPreOrder, avl.preOrder());

		avl.remove(5);
		assertFalse(avl.isEmpty());
		assertTrue(avl.size() == 3);
		
		assertEquals(Integer.valueOf(10), avl.getRoot().getData());
		expectedPreOrder = new Integer[]{10, 3, 15};
		assertArrayEquals(expectedPreOrder, avl.preOrder());
		assertTrue(verifier.isAVLTree());

		// Removendo nó com dois filhos
		avl.insert(7);
		avl.insert(0);
		assertFalse(avl.isEmpty());
		assertTrue(avl.size() == 5);
		assertEquals(Integer.valueOf(10), avl.getRoot().getData());
		
		expectedPreOrder = new Integer[]{10, 3, 0, 7, 15};
		assertArrayEquals(expectedPreOrder, avl.preOrder());

		avl.remove(3);
		assertFalse(avl.isEmpty());
		assertTrue(avl.size() == 4);
		assertEquals(Integer.valueOf(10), avl.getRoot().getData());
		
		expectedPreOrder = new Integer[]{10, 7, 0, 15};
		assertArrayEquals(expectedPreOrder, avl.preOrder());
		assertTrue(verifier.isAVLTree());

		// Removendo a raiz 
		avl.remove(10);
		assertFalse(avl.isEmpty());
		assertTrue(avl.size() == 3);
		assertEquals(Integer.valueOf(7), avl.getRoot().getData());

		expectedPreOrder = new Integer[]{7, 0, 15};
		assertArrayEquals(expectedPreOrder, avl.preOrder());
		assertTrue(verifier.isAVLTree());

		// Removendo elemento que não existe na árvore
		avl.remove(100);
		assertFalse(avl.isEmpty());
		assertTrue(avl.size() == 3);
		assertEquals(Integer.valueOf(7), avl.getRoot().getData());

		expectedPreOrder = new Integer[]{7, 0, 15};
		assertArrayEquals(expectedPreOrder, avl.preOrder());
		assertTrue(verifier.isAVLTree());

		// Remover um elemento null
		avl.remove(null);
		assertFalse(avl.isEmpty());
		assertTrue(avl.size() == 3);
		assertEquals(Integer.valueOf(7), avl.getRoot().getData());

		expectedPreOrder = new Integer[]{7, 0, 15};
		assertArrayEquals(expectedPreOrder, avl.preOrder());
		assertTrue(verifier.isAVLTree());
	}

	@Test
	public void testRemoveRotations() {
		// Criando a árovre
		avl.insert(10);
		avl.insert(5);
		avl.insert(20);
		avl.insert(25);

		Integer[] expectedPreOrder = new Integer[]{10, 5, 20, 25};
		assertArrayEquals(expectedPreOrder, avl.preOrder());
		assertEquals(Integer.valueOf(10), avl.getRoot().getData());
		assertTrue(verifier.isAVLTree());
		
		// Caso RR
		avl.remove(5);
		expectedPreOrder = new Integer[]{20, 10, 25};
		assertArrayEquals(expectedPreOrder, avl.preOrder());
		assertEquals(Integer.valueOf(20), avl.getRoot().getData());
		assertTrue(verifier.isAVLTree());

		// Caso LL
		avl.insert(5);
		expectedPreOrder = new Integer[]{20, 10, 5, 25};
		assertArrayEquals(expectedPreOrder, avl.preOrder());
		assertEquals(Integer.valueOf(20), avl.getRoot().getData());
		assertTrue(verifier.isAVLTree());

		avl.remove(25);
		expectedPreOrder = new Integer[]{10, 5, 20};
		assertArrayEquals(expectedPreOrder, avl.preOrder());
		assertEquals(Integer.valueOf(10), avl.getRoot().getData());
		assertTrue(verifier.isAVLTree());

		// Caso RL
		avl.insert(15);
		expectedPreOrder = new Integer[]{10, 5, 20, 15};
		assertArrayEquals(expectedPreOrder, avl.preOrder());
		assertEquals(Integer.valueOf(10), avl.getRoot().getData());
		assertTrue(verifier.isAVLTree());
		
		avl.remove(5);
		expectedPreOrder = new Integer[] {15, 10, 20};
		assertArrayEquals(expectedPreOrder, avl.preOrder());
		assertEquals(Integer.valueOf(15), avl.getRoot().getData());
		assertTrue(verifier.isAVLTree());

		// Caso LR
		avl.insert(12);
		expectedPreOrder = new Integer[] {15, 10, 12, 20};
		assertArrayEquals(expectedPreOrder, avl.preOrder());
		assertEquals(Integer.valueOf(15), avl.getRoot().getData());
		assertTrue(verifier.isAVLTree());

		avl.remove(20);
		expectedPreOrder = new Integer[] {12, 10, 15};
		assertArrayEquals(expectedPreOrder, avl.preOrder());
		assertEquals(Integer.valueOf(12), avl.getRoot().getData());
		assertTrue(verifier.isAVLTree());
		
	}

}