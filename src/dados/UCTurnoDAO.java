/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import negocio.Par;
import negocio.RegistoPresencas;
import negocio.Turno;
import negocio.TurnoJaRegistadoException;
import negocio.UC;
import negocio.UCInexistenteException;
import negocio.UCJaRegistadaException;

public class UCTurnoDAO {
    static Map<String,UC>ucs=new HashMap();
        
    public void atualizarPresencasTurno(Par<String, Integer> aCodT, Turno aTurno) {
	throw new UnsupportedOperationException();
    }
    
    public void atualizarAlunosTurno(Par<String, Integer> aCodT, Turno aTurno) {
	throw new UnsupportedOperationException();
    }
    
    public void atualizarUC(UC aUc) {
	throw new UnsupportedOperationException();
    }
    
    public static boolean existeUC(String aNome) {
	throw new UnsupportedOperationException();
    }
     
    public UC get(String abreviaturaUC) {
	throw new UnsupportedOperationException();
    }
      
    public static Map<String,Par<String,Integer>> getInfoUCs(){
        Map<String,Par<String,Integer>> info = new HashMap(); 
        Iterator it = ucs.values().iterator();
        while(it.hasNext()){
            UC aux = (UC) it.next();
            info.put(aux.getAbreviatura(),new Par(aux.getNome(),aux.getSemestre()));
        }
        return info;
    }
    
    public static List<Turno> getInfoTurnos(String abreviaturaUC){
        List<Turno> turnos = new ArrayList();
        UC uc = ucs.get(abreviaturaUC);
        if(uc !=null)
            return uc.getTurnos();
        return turnos;
    }
    
    public List<RegistoPresencas> getPresencas() {
	throw new UnsupportedOperationException();
    }

    public static Turno getTurno(Par<String, Integer> idTurno) {
	Turno turno = ucs.get(idTurno.getEsquerda()).getTurno(idTurno.getDireita());
        return turno;
    }
    
    public List<Turno> getTurnos(String aNomeUC) {
	throw new UnsupportedOperationException();
    }
    
    public static void inserirAlunoTurno(Par<String,Integer> idTurno,String nomeUtilizadorAluno){
        UC uc = ucs.get(idTurno.getEsquerda());
        uc.adicionarAlunoATurno(idTurno.getDireita(),nomeUtilizadorAluno);
    }
    
    
    public static void inserirTurno(Par<String,Turno> ucTurno)throws UCInexistenteException,TurnoJaRegistadoException{
        UC uc = ucs.get(ucTurno.getEsquerda());
        if(uc == null)
            throw new UCInexistenteException();
        uc.inserirTurno(ucTurno.getDireita());
    } 
        
    public static void put(UC uc) throws UCJaRegistadaException{
	if(ucs.containsKey(uc.getAbreviatura()))
            throw new UCJaRegistadaException();
        else
            ucs.put(uc.getAbreviatura(),uc);
    }
}
