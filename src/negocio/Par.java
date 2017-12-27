/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;


public class Par <E,D>{
    public final E esquerda;
    public final D direita;

    public Par(E esquerda, D direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }
    public E getEsquerda() {
        return esquerda;
    }

    public D getDireita() {
        return direita;
    }
    
    
   
}
