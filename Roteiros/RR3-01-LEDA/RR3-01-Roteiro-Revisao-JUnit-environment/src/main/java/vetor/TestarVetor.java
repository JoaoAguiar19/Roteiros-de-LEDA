package vetor;

public class TestarVetor {

	public static void main(String[] args) {
		Vetor<Aluno> vetor = new Vetor<Aluno>(20);
		ComparadorMaximo compMax = new ComparadorMaximo();
		vetor.setComparadorMaximo(compMax);
		ComparadorMinimo compMin = new ComparadorMinimo();
		vetor.setComparadorMinimo(compMin);

		vetor.inserir(new Aluno("Ana", 8.4));
        vetor.inserir(new Aluno("Bruno", 6.8));
        vetor.inserir(new Aluno("Carla", 9.3));
        vetor.inserir(new Aluno("Daniel", 7));

        // Testando o método maximo
        Aluno melhor = vetor.maximo();
        System.out.println("Aluno com maior média: " + melhor.getNome() + " - Média: " + melhor.getMedia());

        // Testando o método minimo
        Aluno pior = vetor.minimo();
        System.out.println("Aluno com menor média: " + pior.getNome() + " - Média: " + pior.getMedia());
	}
}
