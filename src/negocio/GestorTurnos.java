/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;
import dados.AdminDAO;
import dados.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GestorTurnos {
    
    public static void alocarTurnos(String path)throws IOException, FicheiroCorrompidoException,ConnectionErrorException,SQLException,ContaInexistenteException{
        Map<String,List<Par<String,Integer>>> todasAlocacoes = Parser.parseFicheiroAlocacoesTurnos(path);
        Iterator it = todasAlocacoes.keySet().iterator();
        while(it.hasNext()){
            String nomeUtilizadorAluno = (String)it.next();
                List<Par<String,Integer>> alocacoesAluno = todasAlocacoes.get(nomeUtilizadorAluno);
                for(Par p: alocacoesAluno){
                    System.out.println(nomeUtilizadorAluno+p.getEsquerda()+p.getDireita());
                    GestorTurnos.alocarTurnoAAluno(p,nomeUtilizadorAluno);
                }
        }
    }
    
    public static void alocarTurnoAAluno(Par<String,Integer> idTurno,String nomeUtilizadorAluno)throws ContaInexistenteException,ConnectionErrorException,SQLException{
        Turno turno = UCTurnoDAO.getTurno(idTurno);
        if(turno != null){
            List<String>alunosDoTurno = turno.getAlunos();
            Aluno aluno = AlunoDAO.get(nomeUtilizadorAluno);
            List<Par<String,Integer>>turnosDoAluno = aluno.getIdTurnos();
            if(!alunosDoTurno.contains(nomeUtilizadorAluno)||!turnosDoAluno.contains(idTurno)){
                UCTurnoDAO.inserirAlunoTurno(idTurno,nomeUtilizadorAluno);
                //AlunoDAO.inserirTurno(idTurno);
            }
            
        }
    }
    
    public static List<String >getIdsDosAlunosDoTurno(Par<String,Integer> idTurno){
        Turno turno = UCTurnoDAO.getTurno(idTurno);
        return turno.getAlunos();
    }
    
    //@return devolve um Map que tem como chave os nomes de utilizador dos alunos e como valores, um par com o nome e estatuto do aluno.
    public static Map<String,Par<String,Boolean>> getInfoAlunos() throws SQLException, ConnectionErrorException{
        return AlunoDAO.getInfoAlunos();
    }
    
    //@return Lista com pares que contêm o nome de utilizador e nome, de cada docente registado no sistema. 
    public static List<Par<String,String>> getInfoDocentes(){
        return DocenteDAO.getInfoDocentes();
    }
    
    //@return Mapa que mapeia as abreviaturas de cada UC para um Par com o seu nome e nr. semestre
    public static Map<String,Par<String,Integer>> getInfoUCs(){
        return UCTurnoDAO.getInfoUCs();
    }
    
    public static List<Turno>getInfoTurnos(String UC){
        return UCTurnoDAO.getInfoTurnos(UC);
    }

    
    public static void inserirAluno(String nome, String nomeUtilizador, String password, Boolean estatuto) throws UtilizadorJaRegistadoException, ConnectionErrorException, SQLException{
        AlunoDAO.put(new Aluno(nome,nomeUtilizador,password,estatuto));
    }
    
    public static List<String> inserirAlunos(String path) throws IOException,FicheiroCorrompidoException, ConnectionErrorException, SQLException{
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
    
    public static void inserirTurno(Par<String,Turno> turno)throws UCInexistenteException,TurnoJaRegistadoException{
        UCTurnoDAO.inserirTurno(turno);
    }
    
    public static List<Par<String,Integer>> inserirTurnos(String path)throws IOException,FicheiroCorrompidoException,UCInexistenteException{
        Map<String,List<Turno>>todosTurnos = Parser.parseFicheiroTurnos(path);
        List<Par<String,Integer>> jaRegistados = null;
        Iterator it = todosTurnos.keySet().iterator();
        while(it.hasNext()){
            String nomeUC = (String)it.next();
            for(Turno t: todosTurnos.get(nomeUC)){
                try{
                    GestorTurnos.inserirTurno(new Par(nomeUC,t));
                }
                catch(TurnoJaRegistadoException e){
                    if(jaRegistados == null){
                        jaRegistados = new ArrayList();
                    }
                    jaRegistados.add(new Par(nomeUC,t.getNumero()));
                }
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
    public static Utilizador login(String nomeUtilizador, String password) throws PasswordIncorretaException,ContaInexistenteException, ConnectionErrorException, SQLException{
        try {
            Aluno aluno = AlunoDAO.get(nomeUtilizador);
            if(aluno!=null){
                if(!verificaPassword(aluno,password))
                    throw new PasswordIncorretaException();
                else
                    return aluno;
            }
        }
        catch (ContaInexistenteException e) {}
        
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
