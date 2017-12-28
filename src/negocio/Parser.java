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
    
    public static List<Docente> parseFicheiroDocentes(String path) throws IOException, FicheiroCorrompidoException{
        List<Docente> docentes = new ArrayList();
        List<String> linhas =Files.readAllLines(Paths.get(path));
        
        for(String s:linhas){
            String[] aux = s.split(":");
            if(aux.length!=3)
                throw new FicheiroCorrompidoException();
            docentes.add(new Docente(aux[0],aux[1],aux[2]));
        }
        return docentes;
    }
    
    public static List<UC> parseFicheiroUCs(String path)throws IOException, FicheiroCorrompidoException{
        List<UC> ucs = new ArrayList();
        List<String> linhas = Files.readAllLines(Paths.get(path));
        linhas.remove(0);
        linhas.remove(linhas.size()-1);
        linhas.remove(linhas.size()-1);
        for(String s: linhas){
            String[]aux = s.split(",");     
            if (aux.length != 3)
                throw new FicheiroCorrompidoException();
            for(int i = 0;i<aux.length;i++){
                aux[i]=aux[i].trim();
                aux[i]=aux[i].replaceAll("[\'\\(\\)]","");
         
            }
            int semestre=Integer.parseInt(aux[0].substring(0,aux[0].indexOf('N')).replaceAll("[^0-9]",""))%100;
            ucs.add(new UC( aux[1],semestre));
            System.out.println(aux[1]+semestre);
        }
        return ucs;
    }
}
