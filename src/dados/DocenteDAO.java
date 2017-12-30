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
import java.util.ArrayList;
import java.util.List;
import negocio.Docente;
import negocio.Par;
import negocio.ConnectionErrorException;
import negocio.UtilizadorJaRegistadoException;
import negocio.ContaInexistenteException;
import negocio.NotificacaoPresencas;


public class DocenteDAO {
        
  public static boolean existeDocente(String nomeUtilizador) throws ConnectionErrorException, SQLException {
    Connection c = Connect.connect();
    if (c != null) {
      PreparedStatement ps = c.prepareStatement("SELECT * FROM Docente WHERE Docente.NomeUtilizador = ?");
      ps.setString(1, nomeUtilizador);
      ResultSet rs = ps.executeQuery();
      if (rs.next())
        return true;
    }
    else
      throw new ConnectionErrorException();
    
    return false;
	}
        
  public static Docente get(String nomeUtilizador) throws SQLException, ContaInexistenteException, ConnectionErrorException {
    Connection c = Connect.connect();
    if (c != null) {
        Docente docente;

        PreparedStatement ps = c.prepareStatement("SELECT * FROM Docente where NomeUtilizador = ?");
        ps.setString(1, nomeUtilizador);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String nome = rs.getString("Nome");
            String password = rs.getString("Password");
            docente = new Docente(nome, nomeUtilizador, password);
        }
        else
          throw new ContaInexistenteException();

        ps = c.prepareStatement("SELECT UCAbreviatura, Numero FROM Turno"
                                    + "WHERE Docente_NomeUtilizador = ?");
        ps.setString(1, nomeUtilizador);
        rs = ps.executeQuery();
        while (rs.next()) {
          Par turno;
          String ucAbreviatura = rs.getString("UCAbreviatura");
          int numero = rs.getInt("Numero");
          turno = new Par<String, Integer>(ucAbreviatura, numero);
          docente.adicionarTurno(turno);
        }

        ps = c.prepareStatement("SELECT NotificacaoPresenca.Aluno_NomeUtilizador, NotificacaoPresenca.NrAula,"
                                    + "Turno.UCAbreviatura, Turno.Numero"
                                    + "FROM NotificacaoPresenca"
                                    + "INNER JOIN Turno ON NotificacaoPresenca.Turno_idTurno = Turno.idTurno"
                                    + "WHERE Docente_NomeUtilizador = ?");
        ps.setString(1, nomeUtilizador);
        rs = ps.executeQuery();
        while (rs.next()) {
          String alunoNomeUtilizador = rs.getString("Aluno_NomeUtilizador");
          int nrAula = rs.getInt("NrAula");
          String uc = rs.getString("UCAbreviatura");
          int turnoNumero = rs.getInt("Numero");
          Par turno = new Par<String, Integer>(uc, turnoNumero);
          NotificacaoPresencas notificacao = new NotificacaoPresencas(alunoNomeUtilizador, nrAula, turno);
          docente.adicionarNotificacaoPresenca(notificacao);
        }

        c.close();
        return docente;
    }
    else
      throw new ConnectionErrorException();
	}
        
  //@return Lista com pares que contêm o nome de utilizador e nome, de cada docente registado no sistema. 
  public static List<Par<String,String>> getInfoDocentes() throws ConnectionErrorException, SQLException {
    Connection c = Connect.connect();
    if (c != null) {
      List info = new ArrayList<Par<String,String>>();
      PreparedStatement ps = c.prepareStatement("SELECT NomeUtilizador, Nome FROM Docente");
      ResultSet rs = ps.executeQuery();
      
      while (rs.next()) {
        String nomeUtilizador = rs.getString("NomeUtilizador");
        String nome = rs.getString("Nome");
        info.add(new Par<String,String>(nomeUtilizador, nome));
      }

      c.close();
      return info;
    }
    else
      throw new ConnectionErrorException();
  }
        
  //WARNING: shallow clone
  public static void put(Docente docente) throws UtilizadorJaRegistadoException, ConnectionErrorException, SQLException {
    Connection c = Connect.connect();
    if (c != null) {
      PreparedStatement ps = c.prepareStatement("SELECT * FROM Docente WHERE NomeUtilizador = ?");
      ps.setString(1, docente.getNomeUtilizador());
      ResultSet rs = ps.executeQuery();
      
      if (!rs.next()) {
        PreparedStatement insertStmt = c.prepareStatement("INSERT INTO Docente (NomeUtilizador, Nome, Password)"
                                                            + "VALUES (?, ?, ?)");
        insertStmt.setString(1, docente.getNome());
        insertStmt.setString(2, docente.getNomeUtilizador());
        insertStmt.setString(3, docente.getPassword());
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
