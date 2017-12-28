
package negocio;

import java.util.ArrayList;
import java.util.List;

public class Turno {
    private int numero;
	private String sala;
	private int capacidadeSala;
	private String tipo;
	private List<Integer> alunos;
	private List<RegistoPresencas> registos = new ArrayList<RegistoPresencas>();
	private Horario horario;

	public void adicionarAluno(int codA) {
		throw new UnsupportedOperationException();
	}

	public void removerAluno(int codA) {
		throw new UnsupportedOperationException();
	}
}
