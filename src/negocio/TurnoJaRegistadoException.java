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
public class TurnoJaRegistadoException extends Exception {

    /**
     * Creates a new instance of <code>TurnoJaRegistadoException</code> without
     * detail message.
     */
    public TurnoJaRegistadoException() {
    }

    /**
     * Constructs an instance of <code>TurnoJaRegistadoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public TurnoJaRegistadoException(String msg) {
        super(msg);
    }
}
