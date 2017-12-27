/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.ArrayList;
import java.util.Iterator;


//Mudar para idTurnos vpp.
public class Docente extends Utilizador{
    private ArrayList<Par<String,Integer>> idTurnos;
    //private ArrayList<NotificacaoPresenca> notificacoesPresenca;
    //private ArrayList notificacoes = new ArrayList<NotificacaoPresenca>();
    public Docente(String nome,String nomeUtilizador,String password){
        super(nome,nomeUtilizador,password);
    }
    
}
