/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.time.LocalTime;

public class Horario {
    private LocalTime inicio;
	private LocalTime fim;
	private int diaSemana;

    public Horario(LocalTime inicio, LocalTime fim, int diaSemana) {
        this.inicio = inicio;
        this.fim = fim;
        this.diaSemana = diaSemana;
    }
        
        
        
    public boolean horariosSobrepostos(Horario aHorario) {
        throw new UnsupportedOperationException();
    }

	public int getDiaSemana() {
		return this.diaSemana;
	}

	public LocalTime getInicio() {
		return this.inicio;
	}

	public LocalTime getFim() {
		return this.fim;
	}
}
