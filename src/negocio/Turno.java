
package negocio;

import java.util.ArrayList;
import java.util.List;

public class Turno {
        private final Integer numero;
	private String sala;
	private int capacidadeSala;
	private final String tipo;
	private List<String> alunos;
	private List<RegistoPresencas> registos = new ArrayList<RegistoPresencas>();
	private Horario horario;
        private String idDocente;

    public Turno(int numero, String sala, int capacidadeSala, String tipo, Horario horario, String idDocente) {
        this.numero = numero;
        this.sala = sala;
        this.capacidadeSala = capacidadeSala;
        this.tipo = tipo;
        this.horario = horario;
        this.idDocente = idDocente;
    }

        
        
    public void adicionarAluno(String nomeUtilizador) {
        if(alunos == null)
            alunos = new ArrayList();
        alunos.add(nomeUtilizador);
    }

    
    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Turno turno = (Turno) o;
        return this.numero.equals(turno.getNumero());
    }
    //WARNING:não faz clone
    public List<String> getAlunos(){
        return alunos;
    }
    
    public int getNumero() {
        return numero;
    }

    public String getSala() {
        return sala;
    }

    public int getCapacidadeSala() {
        return capacidadeSala;
    }

    public String getTipo() {
        return tipo;
    }

    public Horario getHorario() {
        return horario;
    }

    public String getIdDocente() {
        return idDocente;
    }
        
        

	public void removerAluno(int codA) {
		throw new UnsupportedOperationException();
	}
}
