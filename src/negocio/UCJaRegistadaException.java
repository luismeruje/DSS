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
public class UCJaRegistadaException extends Exception {

    /**
     * Creates a new instance of <code>UCJaRegistadaException</code> without
     * detail message.
     */
    public UCJaRegistadaException() {
    }

    /**
     * Constructs an instance of <code>UCJaRegistadaException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UCJaRegistadaException(String msg) {
        super(msg);
    }
}
