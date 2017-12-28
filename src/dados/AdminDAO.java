/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.HashMap;
import java.util.Map;
import negocio.Admin;
import negocio.UtilizadorJaRegistadoException;

public class AdminDAO {
    static Map<String,Admin>administradores=new HashMap();
    
    //TODO: AlterarArgumento no vpp
    public static Admin get(String nomeUtilizador) {
        return administradores.get(nomeUtilizador);
    }

    public static void put(Admin administrador)throws UtilizadorJaRegistadoException {
        if(administradores.containsKey(administrador.getNomeUtilizador()))
            throw new UtilizadorJaRegistadoException(administrador.getNomeUtilizador());
	administradores.put(administrador.getNomeUtilizador(),administrador);
    }

    public static boolean existeAdmin(String nomeUtilizador) {
	return administradores.containsKey(nomeUtilizador);
    }

    public static void atualizarAdmin(Admin administrador) {
        administradores.put(administrador.getNomeUtilizador(),administrador);
    }
}