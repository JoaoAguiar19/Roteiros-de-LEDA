package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (!(array == null || leftIndex < 0 || rightIndex >= array.length || leftIndex >= rightIndex)) {

			if (leftIndex < rightIndex) {
				int meio = (leftIndex + rightIndex)/2;
				sort(array, leftIndex, meio);
				sort(array, meio+1, rightIndex);
				merge(array, leftIndex, rightIndex);
			} 
		}
	}

	private void merge(T[] array, int leftIndex, int rightIndex) {
		int meioAux = (leftIndex + rightIndex)/2;
		T[] aux = (T[]) new Comparable[rightIndex - leftIndex + 1];

		int i = leftIndex;
		int j = meioAux+1;
		int k = 0;

		while (i <= meioAux && j <= rightIndex) {
			if (array[i].compareTo(array[j]) <= 0) {
				aux[k++] = array[i++];
			} else {
				aux[k++] = array[j++];
			}
		}

		while (i <= meioAux) {
			aux[k++] = array[i++];
		}

		while (j <= rightIndex) {
			aux[k++] = array[j++];
		}

		for (int n = 0; n < aux.length; n++) {
			array[leftIndex + n] = aux[n];
		}
	}
}
