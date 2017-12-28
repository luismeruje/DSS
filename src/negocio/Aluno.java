/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.List;


public class Aluno extends Utilizador{
    private List<Par<String,Integer>> idTurnos=null;
    private List<Integer> propostas=null;
    private boolean estatuto;
    
    public Aluno(String nome, String nomeUtilizador,String password, boolean estatuto){
        super(nome,nomeUtilizador,password);
        this.estatuto=estatuto;
    }
    
    //TODO:Alterar nome do método no vpp
    public boolean getEstatuto() {
	return estatuto;
    }
}
