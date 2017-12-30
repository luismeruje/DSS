/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.ArrayList;
import java.util.List;

public class UC {
    private final String abreviatura;
    private final String nome;
    private final int semestre;
    private String idResponsavel;
    private List<Turno> turnos;

    public UC(String abreviatura, String nome, int semestre) {
        this.abreviatura = abreviatura;
        this.nome = nome;
        this.semestre = semestre;
        this.turnos = new ArrayList<Turno>();
        this.idResponsavel=null;
    }   
    public void adicionarAlunoATurno(int numero,String nomeUtilizadorAluno){
        Turno turno = turnos.get(numero);
        turno.adicionarAluno(nomeUtilizadorAluno);
    }
    
    
    public String getAbreviatura(){
        return abreviatura;
    }
    
    public String getNome() {
        return nome;
    }

    public int getSemestre() {
        return semestre;
    }
    //WARTNING:não faz clone().
    public Turno getTurno(int numero){
        Turno turno = null;
        for(int i=0;i<turnos.size();i++){
            Turno auxTurno = turnos.get(i);
            if(auxTurno.getNumero()==numero){
                turno = turnos.get(i);
                i = turnos.size();
            }
        }
        return turno;
    }
    
    //WARNING:Não faz clone
    public List<Turno> getTurnos(){
        return turnos;
    }
    
    public void inserirTurno(Turno turno) throws TurnoJaRegistadoException{
        if(turnos.contains(turno))
            throw new TurnoJaRegistadoException();
        turnos.add(turno);
    }
    
}
