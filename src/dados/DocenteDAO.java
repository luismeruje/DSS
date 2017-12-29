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
import negocio.Aluno;
import negocio.Docente;
import negocio.Par;
import negocio.UtilizadorJaRegistadoException;


public class DocenteDAO {
    
    static Map<String,Docente>docentes = new HashMap();

	public static void atualizarDocente(Docente docente) {
		docentes.put(docente.getNomeUtilizador(),docente);
	}
        
        public static boolean existeDocente(String nomeUtilizador) {
		return docentes.containsKey(nomeUtilizador);
	}
        
         //TODO:atualizar no vpp o argumento
        public static Docente get(String nomeUtilizador) {
		return docentes.get(nomeUtilizador);
	}
        
        //@return Lista com pares que contêm o nome de utilizador e nome, de cada docente registado no sistema. 
        public static List<Par<String,String>> getInfoDocentes(){
            List<Par<String,String>> info = new ArrayList(); 
            Iterator it = docentes.values().iterator();
            while(it.hasNext()){
                Docente aux = (Docente) it.next();
                info.add(new Par(aux.getNomeUtilizador(),aux.getNome()));
            }
            return info;
        }
        
        //WARNING: shallow clone
	public static void put(Docente docente)throws UtilizadorJaRegistadoException {
            if(docentes.containsKey(docente.getNomeUtilizador()))
                throw new UtilizadorJaRegistadoException(docente.getNomeUtilizador());
            docentes.put(docente.getNomeUtilizador(),docente);
	}
}
