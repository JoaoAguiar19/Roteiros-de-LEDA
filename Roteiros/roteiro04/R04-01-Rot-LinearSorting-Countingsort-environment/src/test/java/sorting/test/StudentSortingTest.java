package sorting.test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sorting.AbstractSorting;
import sorting.linearSorting.CountingSort;
import sorting.linearSorting.ExtendedCountingSort;

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
		this.implementation = new CountingSort();
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

	 // Testes CountingSort

	 // Teste com o array nulo
	@Test
	public void testCountingSort01() {
		this.implementation = new CountingSort();
		Integer[] array = null;
		this.implementation.sort(array, 0, 0);
		Assert.assertNull(array);	
	}

	// Teste com o limite esquerdo menor que 0 (leftIndex < 0)
	@Test
	public void testCountingSort02() {
		this.implementation = new CountingSort();
		Integer[] array = {5, 2, 6, 1, 0, 4, 2};
		Integer[] expected = {5, 2, 6, 1, 0, 4, 2};
		this.implementation.sort(array, -1, 4);
		Assert.assertArrayEquals(expected, array);
	}

	// Teste com o limite direito maior que o tamanho do array (rightIndex >= array.length)
	@Test
	public void testCountingSort03() {
		this.implementation = new CountingSort();
		Integer[] array = {5, 2, 6, 1, 0, 4, 2};
		Integer[] expected = {5, 2, 6, 1, 0, 4, 2};
		this.implementation.sort(array, 0, 10);
		Assert.assertArrayEquals(expected, array);
	}

	// Teste com o array de tamanho 1
	@Test
	public void testCountingSort04() {
		this.implementation = new CountingSort();
		Integer[] array = {1};
		this.implementation.sort(array, 0, 0);
		Assert.assertArrayEquals(new Integer[] {1}, array);
	}

	// Limite esquerdo maior que o direito (leftIndex >= rightIndex)
	@Test
	public void testCountingSort05() {
		this.implementation = new CountingSort();
		Integer[] array = {5, 2, 6, 1, 0, 4, 2};
		Integer[] expected = {5, 2, 6, 1, 0, 4, 2};
		this.implementation.sort(array, 4, 2);
		Assert.assertArrayEquals(expected, array);
	}

	// Ordenar em um intervalo específico do array (intervalo de 2 a 7) par
	@Test
	public void testCountingSort06() {
		this.implementation = new CountingSort();
		Integer[] array = {6, 41, 37, 7, 26, 4, 32, 49, 11, 18, 36};
		Integer[] expected = {6, 41, 4, 7, 26, 32, 37, 49, 11, 18, 36};
		this.implementation.sort(array, 2, 7);
		Assert.assertArrayEquals(expected, array);
		
	}

	// Ordenar em um intervalo específico do array (intervalo de 0 a 4) ímpar
	@Test
	public void testCountingSort07() {
		this.implementation = new CountingSort();
		Integer[] array = {30, 28, 7, 29, 11, 26, 4, 22, 23, 31};
		Integer[] expected = {7, 11, 28, 29, 30, 26, 4, 22, 23, 31};
		this.implementation.sort(array, 0, 4);
		Assert.assertArrayEquals(expected, array);
	}

	// Pior caso: com números positivos e o zero
	@Test
	public void testCountingSortWorstCase() {
		this.implementation = new CountingSort();
		Integer[] array = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
		Integer[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		this.implementation.sort(array, 0, array.length - 1);
		Assert.assertArrayEquals(expected, array);
	}

	// Testes ExtendedCountingSort

	// Teste com o array nulo
	@Test
	public void testExtendCountingSort01() {
		this.implementation = new ExtendedCountingSort();
		Integer[] array = null;
		this.implementation.sort(array, 0, 0);
		Assert.assertNull(array);
	}

	// Teste com o limite esquerdo menor que 0 (leftIndex < 0)
	@Test
	public void testExtendCountingSort02() {
		this.implementation = new ExtendedCountingSort();
		Integer[] array = {5, 2, 6, 1, 0, 4, 2};
		Integer[] expected = {5, 2, 6, 1, 0, 4, 2};
		this.implementation.sort(array, -1, 4);
		Assert.assertArrayEquals(expected, array);
	}
	
	// Teste com o limite direito maior que o tamanho do array (rightIndex >= array.length)
	@Test
	public void testExtendCountingSort03() {
		this.implementation = new ExtendedCountingSort();
		Integer[] array = {5, 2, 6, 1, 0, 4, 2};
		Integer[] expected = {5, 2, 6, 1, 0, 4, 2};
		this.implementation.sort(array, 0, 10);
		Assert.assertArrayEquals(expected, array);
	}

	// Teste com o array de tamanho 1
	@Test
	public void testExtendCountingSort04() {
		this.implementation = new ExtendedCountingSort();
		Integer[] array = {1};
		this.implementation.sort(array, 0, 0);
		Assert.assertArrayEquals(new Integer[] {1}, array);
	}

	// Limite esquerdo maior que o direito (leftIndex >= rightIndex)
	@Test
	public void testExtendCountingSort05() {
		this.implementation = new ExtendedCountingSort();
		Integer[] array = {5, 2, 6, 1, 0, 4, 2};
		Integer[] expected = {5, 2, 6, 1, 0, 4, 2};
		this.implementation.sort(array, 4, 2);
		Assert.assertArrayEquals(expected, array);
	}
	
	// Ordenar em um intervalo específico do array (intervalo de 2 a 7) par
	@Test
	public void testExtendCountingSort06() {
		this.implementation = new ExtendedCountingSort();
		Integer[] array = {6, 41, 37, 7, 26, 4, 32, 49, 11, 18, 36};
		Integer[] expected = {6, 41, 4, 7, 26, 32, 37, 49, 11, 18, 36};
		this.implementation.sort(array, 2, 7);
		Assert.assertArrayEquals(expected, array);
	}

	// Ordenar em um intervalo específico do array (intervalo de 0 a 4) ímpar
	@Test
	public void testExtendCountingSort07() {
		this.implementation = new ExtendedCountingSort();
		Integer[] array = {30, 28, 7, 29, 11, 26, 4, 22, 23, 31};
		Integer[] expected = {7, 11, 28, 29, 30, 26, 4, 22, 23, 31};
		this.implementation.sort(array, 0, 4);
		Assert.assertArrayEquals(expected, array);
	}

	// Teste com array de números negativos
	@Test
	public void testExtendCountingSort8() {
		this.implementation = new ExtendedCountingSort();
		Integer[] array = { -30, -28, -7, -29, -11, -26, -4, -22, -23, -31 };
		Integer[] expected = { -31, -30, -29, -28, -26, -23, -22, -11, -7, -4 };
		this.implementation.sort(array, 0, array.length - 1);
		Assert.assertArrayEquals(expected, array);}

	// Teste com array de números negativos e positivos
	@Test
	public void testExtendCountingSort9() {
		this.implementation = new ExtendedCountingSort();
		Integer[] array = { -30, 28, -7, 29, -11, 26, -4, 22, -23, 31 };
		Integer[] expected = { -30, -23, -11, -7, -4, 22, 26, 28, 29, 31 };
		this.implementation.sort(array, 0, array.length - 1);
		Assert.assertArrayEquals(expected, array);
	}

}