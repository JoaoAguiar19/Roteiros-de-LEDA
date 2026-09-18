package vetor;

import java.util.Comparator;

/**
 * Implementação de um vetor de objetos simples para exercitar os conceitos de
 * Generics.
 * 
 * @author Adalberto
 *
 */
public class Vetor<V extends Comparable<V>> {

	// O array interno onde os objetos manipulados são guardados
	private V[] arrayInterno;

	// O tamanho que o array interno terá
	private int tamanho;

	// Indice que guarda a proxima posição vazia do array interno
	private int indice;

	// O Comparators a serem utilizados
	private Comparator<V> comparadorMaximo;
	private Comparator<V> comparadorMinimo;

	public Vetor(int tamanho) {
		super();
		this.tamanho = tamanho;
		this.indice = -1;
		this.arrayInterno = (V[]) new Comparable[tamanho];
	}

	public void setComparadorMaximo(Comparator<V> comparadorMaximo) {
		this.comparadorMaximo = comparadorMaximo;
	}

	public void setComparadorMinimo(Comparator<V> comparadorMinimo) {
		this.comparadorMinimo = comparadorMinimo;
	}

	// Insere um objeto no vetor
	public void inserir(V o) {
		if (!isCheio()) {
			arrayInterno[++indice] = o;
		}
	}

	// Remove um objeto do vetor
	public V remover(V o) {
		V result = procurar(o);

		for (int i = 0; i <= arrayInterno.length; i++) {

			if (result == null) {
				break;
			}

			if (arrayInterno[i] != null && arrayInterno[i].equals(o)) {
				arrayInterno[i] = arrayInterno[indice];
				arrayInterno[indice] = null;
				indice--;
				break;
			}
		}

		return result;
	}

	// Procura um elemento no vetor
	public V procurar(V o) {
		V result = null;

		for (int i = 0; i <= indice; i++) {
			if (arrayInterno[i].equals(o)) {
				result = arrayInterno[i];
			}
		}

		return result;
	}

	// Diz se o vetor está vazio
	public boolean isVazio() {
		return indice == -1;
	}

	// Diz se o vetor está cheio
	public boolean isCheio() {
		return indice == tamanho - 1;
	}

	public V maximo() {
		V max = null;
		if (!isVazio()) {
			max = arrayInterno[0];
			for (int i = 0; i <= indice; i++) {
				if (comparadorMaximo.compare(arrayInterno[i], max) > 0) {
					max = arrayInterno[i];
				}
			}
		}

		return max;
	}

	public V minimo() {
		V min = null;
		if (!isVazio()) {
			min = arrayInterno[0];
			for (int i = 0; i <= indice; i++) {
				if (comparadorMinimo.compare(arrayInterno[i], min) < 0) {
					min = arrayInterno[i];
				}
			}
		}

		return min;
	}

}


class ComparadorMaximo implements Comparator<Aluno> {
	@Override
	public int compare(Aluno o1, Aluno o2) {
		return Double.compare(o1.getMedia(), o2.getMedia());
	}
}

class ComparadorMinimo implements Comparator<Aluno> {
	@Override
	public int compare(Aluno o1, Aluno o2) {
		return Double.compare(o1.getMedia(), o2.getMedia());
	}
}
