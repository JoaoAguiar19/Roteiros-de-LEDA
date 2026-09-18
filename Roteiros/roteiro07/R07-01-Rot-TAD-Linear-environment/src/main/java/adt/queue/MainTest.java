package adt.queue;

public class MainTest {

    public static final String RESET = "\u001B[0m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";

    public static void main(String[] args) throws QueueOverflowException, QueueUnderflowException {
        QueueImpl<Integer> lista = new QueueImpl<>(3);

        // Lista vazia
        System.out.println("Lista vazia: " + VERMELHO + lista.head() + RESET); // Deve retornar null
        System.out.println("A lista está vazia: " + VERDE + lista.isEmpty() + RESET); // true
        System.out.println("A lista está cheia: " + VERMELHO + lista.isFull() + RESET); // false

        // Adicionando elementos na lista
        lista.enqueue(1);
        lista.enqueue(2);
        lista.enqueue(3);

        System.out.println("--------------------------------------------------");
        
        // Lista cheia
        System.out.println("Lista cheia: " + lista.head()); // Deve retornar 1
        System.out.println("A lista está vazia: " + VERMELHO + lista.isEmpty() + RESET); // false
        System.out.println("A lista está cheia: " + VERDE + lista.isFull() + RESET); // true
        
        System.out.println("--------------------------------------------------");
        
        // Remover elemento
        System.out.println("Valor removido: " + lista.dequeue()); // retorn 1
        System.out.println("Proximo a ser removido: " + lista.head());
        System.out.println("A lista está vazia: " + VERMELHO + lista.isEmpty() + RESET); // false
        System.out.println("A lista está cheia: " + VERMELHO + lista.isFull() + RESET); // false
        
        System.out.println("Valor removido: " + lista.dequeue()); // retorn 2
        System.out.println("Valor removido: " + lista.dequeue()); // retorn 3
        
        
        System.out.println("--------------------------------------------------");
        
        // UnderFlow
        try {
            System.out.println("Valor removido: " + lista.dequeue()); // falha
            
        } catch (QueueUnderflowException e) {
            System.out.println(VERMELHO + "Caiu na exceção. Você tentou remover em uma lista vazia" + RESET);
        }
        
        System.out.println("--------------------------------------------------");
        
        // OverFlow
        try {
            lista.enqueue(7);
            lista.enqueue(2);
            lista.enqueue(5);
            lista.enqueue(9);

        } catch (QueueOverflowException e) {
            System.out.println(VERMELHO + "Caiu na exceção. Você tentou adicionar em uma lista cheia" + RESET);   
        }

    }
}
