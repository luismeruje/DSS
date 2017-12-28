/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;
import dados.AdminDAO;
import dados.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorTurnos {

    
    
    //TODO: inserir método no vpp
    
    public static void inserirAdministrador(String nomeUtilizador, String password)throws UtilizadorJaRegistadoException{
        Admin administrador = new Admin(nomeUtilizador,password);
        AdminDAO.put(administrador);
    }
    
    public static List<String> inserirAlunos(String path) throws IOException,FicheiroCorrompidoException{
        List<Aluno> alunos = Parser.parseFicheiroAlunos(path);
        List<String> jaRegistados = null;
        for(Aluno a: alunos){
            try{
                AlunoDAO.put(a);
            }
            catch(UtilizadorJaRegistadoException e){
                if(jaRegistados == null){
                    jaRegistados = new ArrayList();
                }
                jaRegistados.add(a.getNomeUtilizador());
            }
        }
        return jaRegistados;
    }
    
    //TODO: meter exceptions no vpp
    public static Utilizador login(String nomeUtilizador, String password) throws PasswordIncorretaException,ContaInexistenteException{
        Aluno aluno = AlunoDAO.get(nomeUtilizador);
        if(aluno!=null){
            if(!verificaPassword(aluno,password))
                throw new PasswordIncorretaException();
            else
                return aluno;
        }
        
        Docente docente = DocenteDAO.get(nomeUtilizador);
        if(docente != null){
            if(!verificaPassword(docente,password))
                throw new PasswordIncorretaException();
            else
                return docente;
        }
        
        Admin admin = AdminDAO.get(nomeUtilizador);
        if(admin!=null){
            if(!verificaPassword(admin,password))
                throw new PasswordIncorretaException();
            else
                return admin;
        }
        throw new ContaInexistenteException();
    }
    
    private static boolean verificaPassword(Utilizador utilizador, String password){
        return utilizador.getPassword().equals(password);
    }
}
