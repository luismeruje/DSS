/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;


public class Admin extends Utilizador{
    public Admin(String nomeUtilizador,String password){
        super("admin",nomeUtilizador,password);
    }
}
