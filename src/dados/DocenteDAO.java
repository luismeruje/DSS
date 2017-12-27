/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.HashMap;
import java.util.Map;
import negocio.Docente;


public class DocenteDAO {
    
    static Map<String,Docente>docentes = new HashMap();
    
    //TODO:atualizar no vpp o argumento
    public static Docente get(String nomeUtilizador) {
		return docentes.get(nomeUtilizador);
	}
    //WARNING: n faz clone
	public static void put(Docente docente) {
		docentes.put(docente.getNomeUtilizador(),docente);
	}

	public static boolean existeDocente(String nomeUtilizador) {
		return docentes.containsKey(nomeUtilizador);
	}

	public static void atualizarDocente(Docente docente) {
		put(docente);
	}
}
