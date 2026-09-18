package sorting.test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sorting.AbstractSorting;
import sorting.variationsOfBubblesort.RecursiveBubbleSort;

public class StudentSortingTest {

	private Integer[] vetorTamPar;
	private Integer[] vetorTamImpar;
	private Integer[] vetorVazio = {};
	private Integer[] vetorValoresRepetidos;
	private Integer[] vetorValoresIguais;

	public AbstractSorting<Integer> implementation;

	@Before
	public void setUp() {
		populaVetorTamanhoPar(new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23, 31 });
		populaVetorTamanhoImpar(new Integer[] { 6, 41, 32, 7, 26, 4, 37, 49, 11, 18, 36 });
		populaVetorRepetido(new Integer[] { 4, 9, 3, 4, 0, 5, 1, 4 });
		populaVetorIgual(new Integer[] { 6, 6, 6, 6, 6, 6 });

		getImplementation();
	}

	// // MÉTODOS AUXILIARES DA INICIALIZAÇÃO
	/**
	 * Método que inicializa a implementação a ser testada com a implementação
	 * do aluno
	 */
	private void getImplementation() {
		// TODO O aluno deve instanciar sua implementação abaixo ao invés de
		// null
		this.implementation = new RecursiveBubbleSort<Integer>();
		//Assert.fail("Implementation not provided");
	}

	public void populaVetorTamanhoPar(Integer[] arrayPadrao) {
		this.vetorTamPar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorTamanhoImpar(Integer[] arrayPadrao) {
		this.vetorTamImpar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorRepetido(Integer[] arrayPadrao) {
		this.vetorValoresRepetidos = Arrays.copyOf(arrayPadrao,
				arrayPadrao.length);
	}

	public void populaVetorIgual(Integer[] arrayPadrao) {
		this.vetorValoresIguais = Arrays
				.copyOf(arrayPadrao, arrayPadrao.length);
	}

	// FIM DOS METODOS AUXILIARES DA INICIALIZAÇÃO

	// MÉTODOS DE TESTE

	public void genericTest(Integer[] array) {
		Integer[] copy1 = {};
		if(array.length > 0){
			copy1 = Arrays.copyOf(array, array.length);			
		}
		implementation.sort(array);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1, array);
	}

	@Test
	public void testSort01() {
		genericTest(vetorTamPar);
	}

	@Test
	public void testSort02() {
		genericTest(vetorTamImpar);
	}

	@Test
	public void testSort03() {
		genericTest(vetorVazio);
	}

	@Test
	public void testSort04() {
		genericTest(vetorValoresIguais);
	}

	@Test
	public void testSort05() {
		genericTest(vetorValoresRepetidos);
	}

	// MÉTODOS QUE OS ALUNOS PODEM CRIAR
	/**
	 * O ALUNO PODE IMPLEMENTAR METODOS DE ORDENAÇÃO TESTANDO O SORT COM TRES
	 * ARGUMENTOS PARA TESTAR A ORDENACAO EM UM PEDAÇO DO ARRAY. DICA: PROCUREM
	 * SEGUIR A ESTRUTURA DOS MÉTODOS DE TESTE ACIMA DESCRITOS, ORDENANDO APENAS
	 * UMA PARTE DO ARRAY.
	 */

	     public void testSingleElement() {
        Integer[] array = {42};
        genericTest(array);
    }

    @Test
    public void testAlreadySortedArray() {
        Integer[] array = {1, 2, 3, 4, 5};
        genericTest(array);
    }

    @Test
    public void testReverseSortedArray() {
        Integer[] array = {5, 4, 3, 2, 1};
        genericTest(array);
    }

    @Test
    public void testAllElementsEqual() {
        Integer[] array = {7, 7, 7, 7, 7};
        genericTest(array);
    }

    // Teste de subarray (ordenar só parte do array)
    @Test
    public void testPartialArraySort() {
        Integer[] array = {100, 30, 20, 10, 200};
        Integer[] expected = {100, 10, 20, 30, 200};  // ordena apenas de 1 a 3
        implementation.sort(array, 1, 3);
        Assert.assertArrayEquals(expected, array);
    }

    // Subarray curto de dois elementos
    @Test
    public void testTwoElementSubarray() {
        Integer[] array = {10, 8, 9, 7};
        Integer[] expected = {10, 8, 9, 7};  // ordena apenas de 1 a 2
        implementation.sort(array, 1, 2);
        Assert.assertArrayEquals(expected, array);
    }

    // Índices invertidos (não deve alterar o array)
    @Test
    public void testLeftGreaterThanRight() {
        Integer[] array = {3, 1, 2};
        Integer[] expected = {3, 1, 2};
        implementation.sort(array, 2, 1);
        Assert.assertArrayEquals(expected, array);
    }

    // Índices fora do limite (não deve alterar o array)
    @Test
    public void testOutOfBoundsHigh() {
        Integer[] array = {1, 4, 2};
        Integer[] expected = {1, 4, 2};
        implementation.sort(array, 0, array.length + 1); // rightIndex inválido
        Assert.assertArrayEquals(expected, array);
    }

    @Test
    public void testOutOfBoundsLow() {
        Integer[] array = {1, 4, 2};
        Integer[] expected = {1, 4, 2};
        implementation.sort(array, -1, 2); // leftIndex inválido
        Assert.assertArrayEquals(expected, array);
    }

    // Teste com array nulo (deve ser tratado internamente sem exception)
    @Test
    public void testNullArray() {
        Integer[] array = null;
        implementation.sort(array, 0, 1); // não deve lançar exceção
        Assert.assertNull(array);
    }
}