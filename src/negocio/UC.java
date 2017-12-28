/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.ArrayList;
import java.util.List;

public class UC {
    private String nome;
    private int semestre;
    private String idResponsavel;
    private List<Turno> turnos = new ArrayList<Turno>();

    public UC(String nome, int semestre) {
        this.nome = nome;
        this.semestre = semestre;
        this.turnos = null;
        this.idResponsavel=null;
    }   

    public String getNome() {
        return nome;
    }

    public int getSemestre() {
        return semestre;
    }
    
}