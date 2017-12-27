/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.awt.List;


public class Aluno extends Utilizador{
    private List codTurnos=null;
    private List propostas=null;
    private boolean estatuto;
    
    public Aluno(String nome, String nomeUtilizador,String password, boolean estatuto){
        super(nome,nomeUtilizador,password);
        this.estatuto=estatuto;
    }
    
    //TODO:Alterar nome do método para está no vpp
    public boolean getEstatuto() {
	return estatuto;
    }
}
