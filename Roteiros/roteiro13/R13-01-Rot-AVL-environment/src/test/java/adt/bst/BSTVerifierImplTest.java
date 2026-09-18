package adt.bst;

import static org.junit.Assert.assertFalse; // Importa os métodos de asserção corretos
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class BSTVerifierImplTest {

    private BSTImpl<Integer> bst;
    private BSTVerifierImpl<Integer> verifier;

    // Sugestão: Usar @Before para inicializar os objetos antes de cada teste
    @Before
    public void setUp() {
        bst = new BSTImpl<>();
        verifier = new BSTVerifierImpl<>(bst);
    }

    @Test
    public void testIsBST() {
        // arvore vazia
        assertTrue(verifier.isBST());

        // arvore com um elemento
        bst.insert(10);
        assertTrue(verifier.isBST());

        // arvore com dois elementos
        bst.insert(5);
        assertTrue(verifier.isBST());
        bst.insert(15);
        assertTrue(verifier.isBST());

        // arvore com tres elementos
        bst.insert(3);
        assertTrue(verifier.isBST());
        bst.insert(7);
        assertTrue(verifier.isBST());
        bst.insert(12);
        assertTrue(verifier.isBST());
        bst.insert(17);
        assertTrue(verifier.isBST());

        // arvore com varios elementos
        bst.insert(1);
        bst.insert(4);
        bst.insert(6);
        bst.insert(8);
        bst.insert(11);
        bst.insert(13);
        bst.insert(16);
        bst.insert(18);
        assertTrue(verifier.isBST());

        // inserindo um elemento que viola a propriedade de BST
        BSTNode<Integer> node = bst.search(7);
        node.setData(20); // viola a propriedade de BST
        assertFalse(verifier.isBST()); // CORRIGIDO: Usa assertFalse para verificar a condição falsa
    }

    @Test
    public void testBSTNull() {
        // O setUp() já cria bst e verifier.
        bst.root = null; // forcando a raiz para null
        assertTrue(verifier.isBST()); // CORRIGIDO: Usa assertTrue
    }
}