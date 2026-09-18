package recursao;

public class MetodosRecursivos {
	
	// TODO ESCREVA AQUI O CÓDIGO (USANDO RECURSAO) PARA CALCULAR A SOMA
	// DOS EMENTOS DE UM ARRAY
	public int calcularSomaArray(int[] array){
		return calcularSomaArray(array, 0);
	}

	private int calcularSomaArray(int[] array, int index) {
		int soma = array[index];

		if (index < array.length-1) {
			soma += calcularSomaArray(array, index+1);
		}

		return soma;
	}

	// TODO ESCREVA AQUI O CÓDIGO (USANDO RECURSAO) PARA CALCULAR E IMPRIMIR
	// O FATORIAL DO PARAMETRO
	// DE ACORDO COM O QUE FOI MOSTRADO NO EXERCCICIO. OBSERVE QUE SENDO O
	// METODO
	// RECURSIVO, O FATORIAL DOS NUMEROS ANTERIORES TAMBEM VAO SER IMPRESSOS
	public long calcularFatorial(int n) {
		long result = 1;

		if (n == 0) {
			// Caso base: retorna 1
		} else {
			result = n * calcularFatorial(n - 1);
		}
		System.out.println(n + "! = " + result);

		return result;
	}
	
	// TODO ESCREVA AQUI O CÓDIGO (USANDO RECURSAO) PARA CALCULAR E IMPRIMIR
	// O N-ESIMO TERMO
	// DA SEQUENCIA DE FIBONACCI, QUE TEM A SEGUINTE LEI DE FORMACAO: O
	// PRIMEIRO E SEGUNDO NUMEROS
	// DA SEQUENCIA SÃO 1. A PARTIR DO TERCEIRO TERMO, CADA TERMO DA
	// SEQUENCIA É DADO
	// PELA SOMA DOS OUTROS DOIS ANTERIORES. OBSERVE QUE SENDO O METODO
	// RECURSIVO, O FIBONACCI DOS NUMEROS ANTERIORES TAMBEM VAO SER
	// IMPRESSOS
	public int calcularFibonacci(int n) {
		int result = 1;

		if (n == 1 || n == 2) {
			// Caso base: return 1
		} else {
			result = calcularFibonacci(n-1) + calcularFibonacci(n-2);
		}

		return result;
	}
	
	// TODO IMPLEMENTE AQUI O CODIGO QUE CONTA (USANDO RECURSAO) A
	// QUANTIDADE DE ELEMENTOS NAO NULOS
	// DE UM ARRAY DE OBJETOS RECEBIDO COMO PARAMETRO
	public int countNotNull(Object[] array) {
		return countNotNull(array, 0);
	}

	private int countNotNull(Object[] array, int index) {
		int notNull = 0;

		if (array[index] != null) {
			notNull++;
		}

		if (index < array.length-1) {
			notNull += countNotNull(array, index+1);
		}

		return notNull;
	}

	// TODO IMPLEMENTE (USANDO RECURSAO) O CODIGO PARA PRODUZIR A N-ESIMA
	// POTENCIA
	// DE 2
	public long potenciaDe2(int expoente) {
		long result = 1;

		if (expoente == 0) {
			// Caso base: 2^0 = 1
		} else {
			result = 2 * potenciaDe2(expoente-1);
		}

		return result;
	}

	// TODO IMPLEMENTE SEU CODIGO (USANDO RECURSAO) DE ENCONTRAR O n-ESIMO
	// TERMO
	// DA PROGRESSAO ARITMETICA, DADO O TERMO INICIAL E A RAZAO
	// VOCE NAO PODE USAR A FORMULA QUE CALCULA O N-ESIMO TERMO. DEVE USAR RECURSAO
	public double progressaoAritmetica(double termoInicial, double razao, int n) {
		double result = termoInicial;

		if (n == 1) {
			// Caso base: considera o termo inicial
		} else {
			result = progressaoAritmetica(termoInicial, razao, n-1) + razao;
		}

		return result;
	}

	// TODO IMPLEMENTE SEU CODIGO (USANDO RECURSAO) DE ENCONTRAR O n-ESIMO
	// TERMO
	// DA PROGRESSAO GEOMETRICA, DADO O TERMO INICIAL E A RAZAO
	// VOCE NAO PODE USAR A FORMULA QUE CALCULA O N-ESIMO TERMO. DEVE USAR RECURSAO
	public double progressaoGeometrica(double termoInicial, double razao, int n) {
		double result = termoInicial;

		if (n == 1) {
			// Caso base: termo inicial
		} else {
			result = progressaoGeometrica(termoInicial, razao, n-1) * razao;
		}

		return result;
	}
	
	
}
