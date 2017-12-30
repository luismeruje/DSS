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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Parser{
        
    private static String parseAbreviaturaUC(List<String> linhasUC){
        return linhasUC.get(0).replaceAll("[{=]", "");
    } 
    
    
    public static List<Aluno> parseFicheiroAlunos(String path) throws IOException,FicheiroCorrompidoException{
        List<Aluno> alunos = new ArrayList();
        List<String> linhas =Files.readAllLines(Paths.get(path));
        Boolean estatuto;
        
        for(String s:linhas){
            estatuto = false;
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
            ucs.add(new UC( aux[2],aux[1],semestre));
        }
        return ucs;
    }
    //@return Map que mapeia a abreviatura de uma UC para um conjunto de turnos para lhe serem inseridos
    public static Map<String,List<Turno>> parseFicheiroTurnos(String path) throws IOException, FicheiroCorrompidoException{
        Map<String,List<Turno>> ucTurnos = new HashMap<String,List<Turno>>();
        List<String>linhas = Files.readAllLines(Paths.get(path));
        int i = 0, f=0;
        for(String s: linhas){
            if(s.equals("}")){
                f++;
                List<String>aux=linhas.subList(i,f);
                i = f;
                ucTurnos.put(parseAbreviaturaUC(aux),parseTurnosUC(aux));
            }
            else
                f++;
        }
        return ucTurnos;
    }
    
    private static Horario parseHorario(String linhaDiaSemana, String linhaInicio, String linhaDuracao)throws FicheiroCorrompidoException{
        LocalTime inicio=LocalTime.of(Integer.parseInt(linhaInicio), 0);
        LocalTime fim = inicio.plusHours((long)Float.parseFloat(linhaDuracao));
        int diaSemana = 0;
        switch(linhaDiaSemana.toLowerCase()){
            case "segunda":
                diaSemana=1;
                break;
            case "terça":
                diaSemana=2;
                break;
            case "quarta":
                diaSemana=3;
                break;
            case "quinta":
                diaSemana=4;
                break;
            case "sexta":
                diaSemana=5;
                break;
            default:
                throw new FicheiroCorrompidoException();
        }
        return new Horario(inicio,fim,diaSemana);
    }
    
    public static Map<String,List<Par<String,Integer>>> parseFicheiroAlocacoesTurnos(String path)throws IOException,FicheiroCorrompidoException{
        Map<String,List<Par<String,Integer>>> alocacoes = new HashMap();
        List<String>linhas=Files.readAllLines(Paths.get(path));
        linhas.remove(0);
        linhas.remove(linhas.size()-1);
        for(String s: linhas){
            s.trim();
            String[]linhasAluno =s.split(",");
            if(linhasAluno.length<2)
                throw new FicheiroCorrompidoException();
            List<Par<String,Integer>>turnos = new ArrayList();
            for(int i = 0; i < linhasAluno.length;i++){
                linhasAluno[i]=linhasAluno[i].replaceAll("[\'\\(\\)]","");
                if(i!=0){
                    turnos.add(new Par(linhasAluno[i].split("-")[0],parseNrTurno(linhasAluno[i])));
                }
            }
            alocacoes.put(linhasAluno[0],turnos);
        }
        return alocacoes;
    }
    
    private static int parseNrTurno(String s){
        return Integer.parseInt(s.replaceAll("[^0-9]", ""));
    }
    
    private static String parseTipoTurno(String s){
        return s.replaceAll("[0-9:]","");
    }
    
    private static List<Turno> parseTurnosUC(List<String> linhasUC)throws FicheiroCorrompidoException{
        List<Turno>turnos=new ArrayList();
        String s;
        for(int i = 1;i < linhasUC.size()-1;i++){
            
            s=linhasUC.get(i);
            s=s.trim();
            String[] linhasTurno= s.split(",");
            if(linhasTurno.length!=7){
                throw new FicheiroCorrompidoException();
                
            }
            for(int j = 0; j<linhasTurno.length;j++){
                linhasTurno[j]=linhasTurno[j].replaceAll("[\\'\\(\\)]", "");
            }
            
            turnos.add(new Turno(parseNrTurno(linhasTurno[0]),linhasTurno[1],Integer.parseInt(linhasTurno[2]),parseTipoTurno(linhasTurno[0]),parseHorario(linhasTurno[4],linhasTurno[5],linhasTurno[6]),linhasTurno[3]));   
        }
        return turnos;
    }
    
    
}
