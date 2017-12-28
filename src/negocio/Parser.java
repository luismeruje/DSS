/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Parser{
    public static List<Aluno> parseFicheiroAlunos(String path) throws IOException,FicheiroCorrompidoException{
        List<Aluno> alunos = new ArrayList();
        List<String> linhas =Files.readAllLines(Paths.get(path));
        Boolean estatuto = false;
        
        for(String s:linhas){
            String[] aux = s.split(":");
            if(aux.length < 3 || aux.length > 4)
                throw new FicheiroCorrompidoException();
            if(aux.length == 4){
                estatuto = true;
            }
            alunos.add(new Aluno(aux[0],aux[1],aux[2],estatuto));
        }
        return alunos;
    }
}
