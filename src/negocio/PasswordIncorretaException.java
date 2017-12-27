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
public class PasswordIncorretaException extends Exception {

    /**
     * Creates a new instance of <code>PasswordIncorretaException</code> without
     * detail message.
     */
    public PasswordIncorretaException() {
    }

    /**
     * Constructs an instance of <code>PasswordIncorretaException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public PasswordIncorretaException(String msg) {
        super(msg);
    }
}
