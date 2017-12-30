/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.ArrayList;
import java.util.List;


public class Aluno extends Utilizador{
    private List<Par<String,Integer>> idTurnos=new ArrayList();
    private List<Integer> propostas=new ArrayList();
    private boolean estatuto;
    private int nrAluno;
    
    public Aluno(String nome, String nomeUtilizador,String password, boolean estatuto){
        super(nome,nomeUtilizador,password);
        this.estatuto = estatuto;
    }
    
    
    public void adicionarTurno(Par<String, Integer> turno) {
        this.idTurnos.add(turno);
    }

    public void adicionarProposta(int idProposta) {
        this.propostas.add(idProposta);
    }
    
    //TODO:Alterar nome do método no vpp
    public boolean getEstatuto() {
	return estatuto;
    }
    
    //Warning:não faz clone
    public List<Par<String,Integer>> getIdTurnos(){
        return idTurnos;
    }
    
    public int getNrAluno() {
        return this.nrAluno;
    }
    
    public void setNrAluno(int nrAluno) {
        this.nrAluno = nrAluno; 
    }
}
