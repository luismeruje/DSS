/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.List;
import java.util.Iterator;


//Mudar para idTurnos vpp.
public class Docente extends Utilizador{
    //TODO: ter a certeza que em baixo é criado um objeto, null exception
    private List<Par<String,Integer>> idTurnos = null;
    private List<NotificacaoPresencas> notificacoesPresenca = null;
    //private List<NotificacaoPresenca> notificacoes = new ArrayList<NotificacaoPresenca>();
    public Docente(String nome,String nomeUtilizador,String password){
        super(nome,nomeUtilizador,password);
    }

    public void adicionarTurno(Par turno) {
        this.idTurnos.add(turno);
    }

    public void adicionarNotificacaoPresenca(NotificacaoPresencas notificacao) {
        this.notificacoesPresenca.add(notificacao);
    }
}
