package adt.queue;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CircularQueueTest {

    CircularQueue<Integer> queue1;
    CircularQueue<Integer> queue2;
    CircularQueue<Integer> queue3;
    CircularQueue<Integer> queue4;
    
    @Before
    public void setUp() throws QueueOverflowException {
        queue1 = new CircularQueue<>(4);
        queue2 = new CircularQueue<>(2);
        queue3 = new CircularQueue<>(0);
        queue4 = new CircularQueue<>(3);
        
        // Fila com 3 elementos não cheia.
		queue1.enqueue(1);
		queue1.enqueue(2);
		queue1.enqueue(3);
        
		// Fila com 2 elementos de tamanho 2. Fila cheia.
		queue2.enqueue(1);
		queue2.enqueue(2);
    }

    // Deve retornar o elemento removido
    @Test
    public void testDequeue() throws QueueUnderflowException {
        assertEquals(new Integer(1), queue1.dequeue());
        assertEquals(new Integer(2), queue1.dequeue());
    }

    // Deve lançar a exceção QueueUnderflowException se não tiver elemento a ser removido (lista vazia)
    @Test
    public void testDequeueisEmpty() throws QueueUnderflowException {
        assertEquals(new Integer(1), queue2.dequeue());
        assertEquals(new Integer(2), queue2.dequeue());
        assertThrows(QueueUnderflowException.class, () -> {
            queue2.dequeue();
        });
    }

    // Deve lança a execeção QueueOverflowException se a lista já estiver cheia
    @Test
    public void testEnqueueFull() {
        assertThrows(QueueOverflowException.class, () -> {
            queue2.enqueue(4);
        });
    }

    // Returna true se estiver vazio e false caso o contrario
    @Test
    public void testIsEmpty() {
        assertTrue(queue3.isEmpty());
        assertFalse(queue1.isEmpty());
    }
    
    // Returna true se estiver cheio e false caso o contrario
    @Test
    public void testIsFull() {
        assertTrue(queue2.isFull());
        assertFalse(queue1.isFull());
    }

    // Não deve adicionar elementos vazio (a lista permanece a mesma)
    @Test
    public void testEnqueueNull() throws QueueOverflowException {
        assertTrue(queue4.isEmpty());
        queue4.enqueue(null);;
        assertTrue(queue4.isEmpty());
        
    }

    // Retorna o proximo elemento a ser removido
    @Test
    public void testHead() {
        assertEquals(new Integer(1), queue1.head());
    }

    // Retorna o proximo elemento a ser removido ou null se estiver vazio
    @Test
    public void testHeadNull() {
        assertEquals(null, queue3.head());
    }

    @Test
    public void testZeroSizeQueue() {
        assertTrue(queue3.isEmpty());
        assertTrue(queue3.isFull()); 
        assertThrows(QueueOverflowException.class, () -> queue3.enqueue(1));
    }

   @Test
    public void testCircular() throws QueueOverflowException, QueueUnderflowException {
        queue4.enqueue(5);
        queue4.enqueue(3);
        // fila: [5, 3, null], head aponta para 5
        
        assertEquals(new Integer(5), queue4.head());
        assertEquals(new Integer(5), queue4.dequeue());
        // fila: [null, 3, null], head aponta para 3
        
        assertEquals(new Integer(3), queue4.head());
        queue4.enqueue(7);
        // fila: [null, 3, 7], head aponta para 3
        
        assertEquals(new Integer(3), queue4.dequeue());
        // fila: [null, null, 7], head aponta para 7
        
        queue4.enqueue(5);
        queue4.enqueue(9);
        // fila: [5, 9, 7], a fila deve estar cheia agora
        assertTrue(queue4.isFull());
        
        // Ordem dos elementos desenfileirando todos
        assertEquals(new Integer(7), queue4.dequeue());
        assertEquals(new Integer(5), queue4.dequeue());
        assertEquals(new Integer(9), queue4.dequeue());
        
        // Depois de remover todos, a fila deve estar vazia
        assertTrue(queue4.isEmpty());
    }

}
