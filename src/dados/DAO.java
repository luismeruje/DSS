/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import negocio.ConnectionErrorException;

/**
 *
 * @author Grupo34
 */
public class DAO {
  
  public static void remove(String tabela, String coluna, String chave) throws ConnectionErrorException, SQLException {
    Connection c = Connect.connect();
    if (c != null) {
      PreparedStatement ps = c.prepareStatement("DELETE FROM "+tabela+" WHERE "+coluna+" = ?");
      ps.setString(1, chave);
      int nrows = ps.executeUpdate();
    }
    else {
      throw new ConnectionErrorException();
    }
  }

  public static void remove(String tabela, String coluna, int chave) throws ConnectionErrorException, SQLException {
    Connection c = Connect.connect();
    if (c != null) {
      PreparedStatement ps = c.prepareStatement("DELETE FROM "+tabela+" WHERE "+coluna+" = ?");
      ps.setInt(1, chave);
      int nrows = ps.executeUpdate();
    }
    else {
      throw new ConnectionErrorException();
    }
  }

  public static void size(String tabela) throws ConnectionErrorException, SQLException {
    Connection c = Connect.connect();
    if (c != null) {
      PreparedStatement ps = c.prepareStatement("SELECT Count(*) FROM "+tabela);
      
    }
    else {
      throw new ConnectionErrorException();
    }
  }
}
