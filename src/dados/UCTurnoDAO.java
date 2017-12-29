/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import negocio.Par;
import negocio.RegistoPresencas;
import negocio.Turno;
import negocio.UC;
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
    
    public List<RegistoPresencas> getPresencas() {
	throw new UnsupportedOperationException();
    }

    public Turno getTurno(Par<String, Integer> aTurno) {
	throw new UnsupportedOperationException();
    }
    
    public List<Turno> getTurnos(String aNomeUC) {
	throw new UnsupportedOperationException();
    }
        
    public static void put(UC uc) throws UCJaRegistadaException{
	if(ucs.containsKey(uc.getAbreviatura()))
            throw new UCJaRegistadaException();
        else
            ucs.put(uc.getAbreviatura(),uc);
    }
}
