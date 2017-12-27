/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;
import java.util.HashMap;
import java.util.Map;
import negocio.Aluno;
import negocio.UtilizadorJaRegistadoException;

public class AlunoDAO {
    
    static Map<String,Aluno>alunos = new HashMap();
    //TODO:Alterar argumento no vpp
    public static Aluno get(String nomeUtilizador) {
        return alunos.get(nomeUtilizador);
    }
//WARNIN:Não faz clone
    public static void put(Aluno aluno) throws UtilizadorJaRegistadoException{
        if(alunos.containsKey(aluno.getNomeUtilizador()))
            throw new UtilizadorJaRegistadoException();
	alunos.put(aluno.getNomeUtilizador(),aluno);
    }

    public static boolean existeAluno(String nomeUtilizador) {
        return alunos.containsKey(nomeUtilizador);
    }

    public static void atualizar(Aluno aluno) {
	alunos.put(aluno.getNomeUtilizador(),aluno);
    }

    public static void atualizarPropostas(Aluno aAluno) {
	throw new UnsupportedOperationException();
    }

    public static void atualizarTurnos(Aluno aAluno) {
	throw new UnsupportedOperationException();
    }
}
