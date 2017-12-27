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

    public Utilizador(String nomeUtilizador, String password, String nome) {
        this.nomeUtilizador = nomeUtilizador;
        this.password = password;
        this.nome = nome;
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
