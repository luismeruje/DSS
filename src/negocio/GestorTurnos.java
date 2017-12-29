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
import java.util.Map;

public class GestorTurnos {
    //@return devolve um Map que tem como chave os nomes de utilizador dos alunos e como valores, um par com o nome e estatuto do aluno.
    public static Map<String,Par<String,Boolean>> getInfoAlunos(){
        return AlunoDAO.getInfoAlunos();
    }
    
    //TODO: inserir método no vpp
    
    public static void inserirAdministrador(String nomeUtilizador, String password)throws UtilizadorJaRegistadoException{
        Admin administrador = new Admin(nomeUtilizador,password);
        AdminDAO.put(administrador);
    }
    
    public static void inserirAluno(String nome, String nomeUtilizador, String password, Boolean estatuto) throws UtilizadorJaRegistadoException{
        AlunoDAO.put(new Aluno(nome,nomeUtilizador,password,estatuto));
    }
    
    public static List<String> inserirAlunos(String path) throws IOException,FicheiroCorrompidoException{
        List<Aluno> alunos = Parser.parseFicheiroAlunos(path);
        List<String> jaRegistados = null;
        for(Aluno a: alunos){
            try{
                GestorTurnos.inserirAluno(a.getNome(),a.getNomeUtilizador(),a.getPassword(),a.getEstatuto());
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
    public static void inserirDocente(String nome, String nomeUtilizador, String password) throws UtilizadorJaRegistadoException{
        DocenteDAO.put(new Docente(nome,nomeUtilizador,password));
    }
    public static List<String> inserirDocentes(String path) throws IOException, FicheiroCorrompidoException{
        List<Docente> docentes = Parser.parseFicheiroDocentes(path);
        List<String> jaRegistados = null;
        for(Docente d: docentes){
            try{
                GestorTurnos.inserirDocente(d.getNome(),d.getNomeUtilizador(),d.getPassword());
            }
            catch(UtilizadorJaRegistadoException e){
                if(jaRegistados == null){
                    jaRegistados = new ArrayList();
                }
                jaRegistados.add(d.getNomeUtilizador());
            }
        }
        return jaRegistados;
    }
    
    public static void inserirUC(String abreviatura, String nome, int semestre) throws UCJaRegistadaException{
        UCTurnoDAO.put(new UC(abreviatura, nome,semestre));
    }
    
    
    public static List<String> inserirUCs(String path) throws IOException,FicheiroCorrompidoException{
        List<UC> ucs = Parser.parseFicheiroUCs(path);
        List<String> jaRegistadas = null;
        for(UC u: ucs){
            try{
                GestorTurnos.inserirUC(u.getAbreviatura(),u.getNome(),u.getSemestre());
            }
            catch(UCJaRegistadaException e){
                if(jaRegistadas == null){
                    jaRegistadas = new ArrayList();
                }
                jaRegistadas.add(u.getNome());
            }
        }
        return jaRegistadas;
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
