package recursao;

public class TestarMetodosRecursivos {
	// preencha esse metodo com codigo para testar a classe MetodosRecursivos.
	public static void main(String[] args) {
		MetodosRecursivos mr = new MetodosRecursivos();

		System.out.println("Teste de calcularSomaArray");
		int[] array = {1,2,3,4,5};
		int soma = mr.calcularSomaArray(array);
		System.out.println(soma);
		System.out.println("--------------------");

		System.out.println("Teste de calcularFatorial");
		mr.calcularFatorial(12);
		System.out.println("--------------------");

		System.out.println("Teste de calcularFibonacci");
		System.out.println(mr.calcularFibonacci(5)); // imprime o 5º termo = 5
		System.out.println("--------------------");

		System.out.println("Teste de imprimirSequenciaFibonacci");
		imprimirSequenciaFibonacci(12);
		System.out.println("\n--------------------");

		System.out.println("Teste de countNotNull");
		Object[] arrayObjects = {1, "um", null, null, 'j', mr, false, null}; // Array de tamanho 8 com 3 elementos nulos
		System.out.println(mr.countNotNull(arrayObjects)); // Output = 5
		System.out.println("--------------------");

		System.out.println("Teste de potenciaDe2");
		System.out.println(mr.potenciaDe2(8)); // Output: 256
		System.out.println("--------------------");

		System.out.println("Teste de progressaoAritmetica");
		System.out.println(mr.progressaoAritmetica(0, 2, 5)); // PA = 0,2,4,6,8,10... O 5° termo é 8
		System.out.println("--------------------");

		System.out.println("Teste de progressaoGeometrica");
		System.out.println(mr.progressaoGeometrica(1, 2, 5)); // PG = 1,2,4,8,16,32,... O 5° termo é 16
		System.out.println("--------------------");
	}

	private static void imprimirSequenciaFibonacci(int n) {
		MetodosRecursivos mr = new MetodosRecursivos();

		for (int i = 1; i <= n; i++) {
			System.out.print(mr.calcularFibonacci(i) + " ");
		}
	}
}
