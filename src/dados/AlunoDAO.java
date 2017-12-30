/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import negocio.Aluno;
import negocio.Par;
import negocio.UtilizadorJaRegistadoException;
import negocio.ContaInexistenteException;
import negocio.ConnectionErrorException;

public class AlunoDAO {
    
    public static boolean existeAluno(String nomeUtilizador) throws ConnectionErrorException, SQLException {
        Connection c = Connect.connect();
        if (c != null) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Aluno WHERE Aluno.NomeUtilizador = ?");
            ps.setString(1, nomeUtilizador);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return true;
        }
        else
            throw new ConnectionErrorException();
          
        return false;
    }
 
    public static Aluno get(String nomeUtilizador) throws ContaInexistenteException, ConnectionErrorException, SQLException {
        Connection c = Connect.connect();
        if (c != null) {
            Aluno aluno;

            // Obter informações do aluno
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Aluno where NomeUtilizador = ?");
            ps.setString(1, nomeUtilizador);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("Nome");
                String password = rs.getString("Password");
                boolean estatuto = rs.getBoolean("Estatuto");
                int nrAluno = rs.getInt("NrAluno");
                aluno = new Aluno(nome, nomeUtilizador, password, estatuto);
                aluno.setNrAluno(nrAluno);
            }
            else
                throw new ContaInexistenteException();

            // Obter turnos e respetivas UC's a que o aluno está inscrito
            ps = c.prepareStatement("SELECT Turno.idTurno, Turno.UC_idUC FROM Turno"
                                        +"INNER JOIN Aluno_Turno on Turno.idTurno = Aluno_Turno.Turno_idTurno"
                                        +"INNER JOIN Aluno on Aluno_Turno.Aluno.NrAluno = Aluno.NrAluno"
                                        +"WHERE Aluno.NomeUtilizador = ?");
            ps.setString(1, nomeUtilizador);
            rs = ps.executeQuery();
            while (rs.next()) {
                String esquerda = rs.getString("UC_idUC");
                int direita = rs.getInt("idTurno");
                Par<String, Integer> turno;
                turno = new Par<String, Integer>(esquerda, direita);
                aluno.adicionarTurno(turno);
            }

            // Obter propostas relacionadas com o aluno
            ps = c.prepareStatement("SELECT PropostaTroca.idPropostaTroca FROM PropostaTroca"
                                       + "WHERE Proponente_NrAluno = ? or Oblato_NrAluno = ?");
            ps.setInt(1, aluno.getNrAluno());
            ps.setInt(2, aluno.getNrAluno());
            rs = ps.executeQuery();
            while (rs.next())
                aluno.adicionarProposta(rs.getInt("idPropostaTroca"));

            c.close();
            return aluno;
            }
        else
            throw new ConnectionErrorException();
    }
    
    //@return devolve um Map que tem como chave os nomes de utilizador dos alunos e como valores, um par com o nome e estatuto do aluno.
    public static Map<String, Par<String,Boolean>> getInfoAlunos() throws SQLException, ConnectionErrorException{
        Connection c = Connect.connect();
        if (c != null) {
            Map info = new HashMap<String, Par<String,Boolean>>();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Aluno");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String nomeUtilizador = rs.getString("NomeUtilizador");
                String nome = rs.getString("Nome");
                boolean estatuto = rs.getBoolean("Estatuto");
                info.put(nomeUtilizador, new Par<String, Boolean>(nome, estatuto));
            }
            c.close();
            return info;
        }
        else
            throw new ConnectionErrorException();
    }
    
    public static void put(Aluno aluno) throws UtilizadorJaRegistadoException, ConnectionErrorException, SQLException {
        Connection c = Connect.connect();
        if (c != null) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Aluno WHERE Aluno.NrAluno = ?");
            ps.setInt(1, aluno.getNrAluno());
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                PreparedStatement insertStmt = c.prepareStatement("INSERT INTO Aluno (NrAluno, Estatuto, Nome, NomeUtilizador, Password)"
                                                                    + "VALUES (?, ?, ?, ?, ?)");
                insertStmt.setInt(1, aluno.getNrAluno());
                insertStmt.setBoolean(2, aluno.getEstatuto());
                insertStmt.setString(3, aluno.getNome());
                insertStmt.setString(4, aluno.getNomeUtilizador());
                insertStmt.setString(5, aluno.getPassword());
                int numRows = insertStmt.executeUpdate();
            }
            else
                throw new UtilizadorJaRegistadoException();
              
            c.close();
          }
          else
            throw new ConnectionErrorException();
        }
}