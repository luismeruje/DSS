/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 */
public abstract class Utilizador {
	private String nomeUtilizador;
        private String password;
	private String nome;

    public Utilizador(String nome, String nomeUtilizador, String password) {
        this.nome = nome;
        this.nomeUtilizador = nomeUtilizador;
        this.password = password;
        
    }

        
    public String getNomeUtilizador() {
        return nomeUtilizador;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }
	
}
