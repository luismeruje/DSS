/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import negocio.Par;
import negocio.RegistoPresencas;
import negocio.Turno;
import negocio.UC;
import negocio.UCJaRegistadaException;

public class UCTurnoDAO {
    static Map<String,UC>ucs=new HashMap();
    public UC get(String nomeUC) {
		throw new UnsupportedOperationException();
	}

	public static void put(UC uc) throws UCJaRegistadaException{
		if(ucs.containsKey(uc))
                    throw new UCJaRegistadaException();
                else
                    ucs.put(uc.getNome(),uc);
	}

	public static boolean existeUC(String aNome) {
		throw new UnsupportedOperationException();
	}

	public void atualizarPresencasTurno(Par<String, Integer> aCodT, Turno aTurno) {
		throw new UnsupportedOperationException();
	}

	public List<Turno> getTurnos(String aNomeUC) {
		throw new UnsupportedOperationException();
	}

	public List<RegistoPresencas> getPresencas() {
		throw new UnsupportedOperationException();
	}

	public void atualizarUC(UC aUc) {
		throw new UnsupportedOperationException();
	}

	public Turno getTurno(Par<String, Integer> aTurno) {
		throw new UnsupportedOperationException();
	}

	public void atualizarAlunosTurno(Par<String, Integer> aCodT, Turno aTurno) {
		throw new UnsupportedOperationException();
	}
}
