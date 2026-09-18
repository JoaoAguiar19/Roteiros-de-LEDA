package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (!(array == null || array.length <= 1 || leftIndex >= rightIndex || leftIndex < 0 || rightIndex >= array.length)) {
			// Maior e menor elemento do array
			int max = array[leftIndex];
			int min = array[leftIndex];

			// Loop que pecorrer o array e encontra o maior e o menor elemento de left até right
			for (int i = leftIndex; i <= rightIndex; i++) {
				if (array[i] > max) {
					max = array[i];
				}

				if (array[i] < min) {
					min = array[i];
				}
			}

			// // Array auxiliar: frequência e contagem
			int[] count = new int[max - min + 1];

			// Frequência
			for (int i = leftIndex; i <= rightIndex; i++) {
				count[array[i] - min] += 1;
			}

			// Contagem
			for (int i = 1; i < count.length; i++) {
				count[i] += count[i - 1];
			}

			// Array auxiliar: ordenação
			int[] orderedArray = new int[array.length];
	
			// Ordenando o array auxiliar de ordenação. De right até left
			for (int i = rightIndex; i >= leftIndex; i--) {
				orderedArray[count[array[i] - min] - 1] = array[i];
				count[array[i] - min] -= 1;
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
}
