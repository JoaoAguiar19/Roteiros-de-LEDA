package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala.
 *
 * Procure evitar desperdício de memória: AO INVÉS de alocar o array de contadores
 * com um tamanho arbitrariamente grande (por exemplo, com o maior valor de entrada possível),
 * aloque este array com o tamanho sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Seu algoritmo deve assumir que o array de entrada nao possui numeros negativos,
 * ou seja, possui apenas numeros inteiros positivos e o zero.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (!(array == null || array.length <= 1 || leftIndex >= rightIndex || leftIndex < 0 || rightIndex >= array.length)) {

			// Maior elemento do array
			int max = maxElementArray(array, leftIndex, rightIndex);
	
			// Array auxiliar: frequência e contagem
			int[] count = new int[max + 1];
	
			// Frequência
			for (int i = leftIndex; i <= rightIndex; i++) {
				count[array[i]] += 1;
			}
	
			// Soma acumulativa
			for (int i = 1; i < count.length; i++) {
				count[i] += count[i - 1];
			}
	
			// Array auxiliar: ordenação
			int[] orderedArray = new int[array.length];
	
			// Ordenando o array auxiliar de ordenação. De right até left
			for (int i = rightIndex; i >= leftIndex; i--) {
				orderedArray[count[array[i]] - 1] = array[i];
				count[array[i]] -= 1;
			}
	
			// Cópia o array ordenado para o array original
			copyArray(array, orderedArray, leftIndex, rightIndex);
		}

	}
	
    private void copyArray(Integer[] array, int[] orderedArray, int left, int right) {
		for (int i = left; i <= right; i++) {
			array[i] = orderedArray[i - left];
		}
	}

	private int maxElementArray(Integer[] array, int left, int right) {
		int max = array[left];

		// Encontrando o maior elemento do array
		for (int i = left + 1; i <= right; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
    }

}