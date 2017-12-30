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
public class UCInexistenteException extends Exception {

    /**
     * Creates a new instance of <code>UCInexistenteException</code> without
     * detail message.
     */
    public UCInexistenteException() {
    }

    /**
     * Constructs an instance of <code>UCInexistenteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UCInexistenteException(String msg) {
        super(msg);
    }
}
