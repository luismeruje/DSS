/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import negocio.Aluno;
import negocio.Par;
import negocio.UtilizadorJaRegistadoException;

public class AlunoDAO {
    
    static Map<String,Aluno>alunos = new HashMap();
    
    
    public static void atualizar(Aluno aluno) {
	alunos.put(aluno.getNomeUtilizador(),aluno);
    }

    public static void atualizarPropostas(Aluno aAluno) {
	throw new UnsupportedOperationException();
    }

    public static void atualizarTurnos(Aluno aAluno) {
	throw new UnsupportedOperationException();
    }
    
    public static boolean existeAluno(String nomeUtilizador) {
        return alunos.containsKey(nomeUtilizador);
    }
 
    public static Aluno get(String nomeUtilizador) {
        return alunos.get(nomeUtilizador);
    }
    
    //@return devolve um Map que tem como chave os nomes de utilizador dos alunos e como valores, um par com o nome e estatuto do aluno.
    public static Map<String,Par<String,Boolean>> getInfoAlunos(){
        Map<String,Par<String,Boolean>> info = new HashMap(); 
        Iterator it = alunos.values().iterator();
        while(it.hasNext()){
            Aluno aux = (Aluno) it.next();
            info.put(aux.getNomeUtilizador(),new Par(aux.getNome(),aux.getEstatuto()));
        }
        return info;
    }
    
     //WARNING:Não faz clone
    public static void put(Aluno aluno) throws UtilizadorJaRegistadoException{
       
        if(alunos.containsKey(aluno.getNomeUtilizador()))
            throw new UtilizadorJaRegistadoException(aluno.getNomeUtilizador());
	alunos.put(aluno.getNomeUtilizador(),aluno);
    }
}
