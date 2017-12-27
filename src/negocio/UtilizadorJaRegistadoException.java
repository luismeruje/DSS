/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author LuisFerreira
 */
public class UtilizadorJaRegistadoException extends Exception {

    /**
     * Creates a new instance of <code>UtilizadorJaRegistadoException</code>
     * without detail message.
     */
    public UtilizadorJaRegistadoException() {
    }

    /**
     * Constructs an instance of <code>UtilizadorJaRegistadoException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public UtilizadorJaRegistadoException(String msg) {
        super(msg);
    }
}
