/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author utilizador
 */
public class NotificacaoPresencas {
    
    private String aluno;
    private int nrAula;
    private Par<String,Integer> turno; 
    
    public NotificacaoPresencas(String aluno, int nrAula, Par<String,Integer> turno) {
        this.aluno = aluno;
        this.nrAula = nrAula;
        this.turno = turno;
    }
}
