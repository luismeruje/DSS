/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;
import dados.*;

public class GestorTurnos {

    
    
    //TODO: inserir método no vpp
    
    public static void inserirAdministrador(String nomeUtilizador, String password)throws UtilizadorJaRegistadoException{
        Admin administrador = new Admin(nomeUtilizador,password);
        AdminDAO.put(administrador);
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
            System.out.println("b");
            if(!verificaPassword(docente,password))
                throw new PasswordIncorretaException();
            else
                return docente;
        }
        
        Admin admin = AdminDAO.get(nomeUtilizador);
        if(admin!=null){
            System.out.println("c");
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
