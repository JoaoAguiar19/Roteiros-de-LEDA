package vetor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class VetorTest {

    Vetor<Aluno> vetor;
    Aluno aluno1;
    Aluno aluno2;
    Aluno aluno3;
    Aluno aluno4;

    @Before
    public void setUp() {
        vetor = new Vetor<>(5);
        aluno1 = new Aluno("Ana", 8.4);
        aluno2 = new Aluno("Bruno", 6.8);
        aluno3 = new Aluno("Carla", 9.3);
        aluno4 = new Aluno("Daniel", 7.0);
    }

    @Test
    public void testInserir() {
        assertTrue(vetor.isVazio());
        vetor.inserir(aluno1);
        vetor.inserir(aluno2);
        vetor.inserir(aluno3);
        vetor.inserir(aluno4);
        assertFalse(vetor.isVazio());
    }

    @Test
    public void testIsCheio() {
        vetor.inserir(aluno1);
        vetor.inserir(aluno2);
        vetor.inserir(aluno3);
        vetor.inserir(aluno4);
        assertFalse(vetor.isCheio());
        vetor.inserir(new Aluno("Eve", 5.0));
        assertTrue(vetor.isCheio());
    }

    @Test
    public void testIsVazio() {
        assertTrue(vetor.isVazio());
        vetor.inserir(aluno1);
        assertFalse(vetor.isVazio());
        vetor.remover(aluno1);
        assertTrue(vetor.isVazio());

    }

    @Test
    public void testProcurar() {
        vetor.inserir(aluno1);
        vetor.inserir(aluno2);
        vetor.inserir(aluno3);
        vetor.inserir(aluno4);
        
        Aluno encontrado = vetor.procurar(aluno2);
        assertTrue(encontrado.equals(aluno2));
        
        Aluno naoEncontrado = vetor.procurar(new Aluno("Eve", 5.0));
        assertTrue(naoEncontrado == null);

    }

    @Test
    public void testRemover() {
        vetor.inserir(aluno1);
        vetor.inserir(aluno2);
        vetor.inserir(aluno3);
        vetor.inserir(aluno4);
        
        assertTrue(vetor.procurar(aluno2).equals(aluno2));
        Aluno removido = aluno2;
        vetor.remover(removido);
        assertTrue(vetor.procurar(removido) != null);

    }

    @Test
    public void testSetComparadorMaximo() {
        ComparadorMaximo compMax = new ComparadorMaximo();
        vetor.setComparadorMaximo(compMax);
        
        vetor.inserir(aluno1);
        vetor.inserir(aluno2);
        vetor.inserir(aluno3);
        vetor.inserir(aluno4);
        
        Aluno maximo = vetor.maximo();
        assertTrue(maximo.equals(aluno3)); // Carla com média 9.3 deve ser o máximo

    }

    @Test
    public void testSetComparadorMinimo() {
        ComparadorMinimo compMin = new ComparadorMinimo();
        vetor.setComparadorMinimo(compMin);
        
        vetor.inserir(aluno1);
        vetor.inserir(aluno2);
        vetor.inserir(aluno3);
        vetor.inserir(aluno4);
        
        Aluno minimo = vetor.minimo();
        assertTrue(minimo.equals(aluno2)); // Bruno com média 6.8 deve ser o mínimo

    }
}
