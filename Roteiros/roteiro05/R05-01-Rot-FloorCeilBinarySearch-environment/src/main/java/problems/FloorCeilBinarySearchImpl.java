package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: 
 * - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais) 
 * - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearchImpl implements FloorCeil {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		Integer result = null;

		if (!(array == null || array.length == 0)) {
			result = floor(array, x, 0, array.length - 1);
		}

		return result;
	}

	private Integer floor(Integer[] array, Integer x, int left, int right) {
		Integer result = null;

		if (left <= right) {
			int mid = (right + left) / 2;

			if (x == array[mid]) {
				result = array[mid];

			} else if (x < array[mid]) {
				result = floor(array, x, left, mid -1);

			} else {
				result = floor(array, x, mid + 1, right);

				if (result == null || array[mid] > result) {
					result = array[mid];
				}
			}
		}

		return result;
	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		Integer result = null;

		if (!(array == null || array.length == 0)) {
			result = ceil(array, x, 0, array.length -1);
		}

		return result;
	}

	private Integer ceil(Integer[] array, Integer x, int left, int right) {
		Integer result = null;

		if (left <= right) {
			int mid = (right + left) / 2;

			if (x == array[mid]) {
				result = array[mid];

			} else if (x < array[mid]) {
				result = ceil(array, x, left, mid - 1);

				if (result == null || array[mid] < result) {
					result = array[mid];
				}
			} else {
				result = ceil(array, x, mid + 1, right);
			}

		}

		return  result;
	}

}
