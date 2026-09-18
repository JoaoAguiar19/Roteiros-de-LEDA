package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import static util.Util.swap;

/**
 * This bubble sort variation has two internal iterations. In the first, it
 * pushes big elements to the right, like the normal bubble sort does. Then in
 * the second, iterates the array backwards, that is, from right to left, while
 * pushing small elements to the left. This process is repeated until the array
 * is sorted.
 */
public class BidirectionalBubbleSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int leftLimit = leftIndex; // Limite a esquerda
		int rightLimit = rightIndex; // Limite a direita
		boolean swapped = true;

		if (!(array == null || leftIndex < 0 || rightIndex >= array.length || leftIndex >= rightIndex)) {
			while (swapped) {
				swapped = false;

				// Guarda a última troca de (left -> right)
				int lastSwapRight = leftLimit;

				for (int i = leftLimit; i < rightLimit; i++) {
					if (array[i].compareTo(array[i+1]) > 0) {
						swap(array, i, i+1);
						lastSwapRight = i;
						swapped = true;
					}
				}

				// Atualiza o limite para a última posição que houve troca
				rightLimit = lastSwapRight;

				// Guarda a última troca de (right -> left)
				int lastSwapLeft = rightLimit;

				for (int j = rightLimit; j > leftLimit; j--) {
					if (array[j].compareTo(array[j-1]) < 0) {
						swap(array, j, j-1);
						lastSwapLeft = j;
						swapped = true;
					}
				}
				
				// Atualiza o limite para a última posição que houve troca
				leftLimit = lastSwapLeft;
			}
		}
	}
}