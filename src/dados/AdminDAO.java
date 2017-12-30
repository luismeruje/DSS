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
import negocio.Admin;
import negocio.ConnectionErrorException;
import negocio.ContaInexistenteException;
import negocio.UtilizadorJaRegistadoException;

public class AdminDAO {
    
    public static Admin get(String nomeUtilizador) throws ContaInexistenteException, ConnectionErrorException, SQLException{
      Connection c = Connect.connect();
      if (c != null) {
          Admin admin;

          // Obter informações do admin
          PreparedStatement ps = c.prepareStatement("SELECT * FROM Admin where NomeUtilizador = ?");
          ps.setString(1, nomeUtilizador);
          ResultSet rs = ps.executeQuery();
          if (rs.next()) {
              String password = rs.getString("Password");
              admin = new Admin(nomeUtilizador, password);

              c.close();
              return admin;
          }
          else
            throw new ContaInexistenteException();
      }
      else {
        throw new ConnectionErrorException();
      }
    }

    public static void put(Admin administrador) throws UtilizadorJaRegistadoException, SQLException, ConnectionErrorException {
      Connection c = Connect.connect();
      if (c != null) {
        PreparedStatement ps = c.prepareStatement("SELECT * FROM Admin WHERE NomeUtilizador = ?");
        ps.setString(1, administrador.getNomeUtilizador());
        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
          PreparedStatement insertStmt = c.prepareStatement("INSERT INTO Admin (idAdmin, Nome, NomeUtilizador, Password) VALUES (1, 'Administrador', ?, ?)");
          insertStmt.setString(1, administrador.getNomeUtilizador());
          insertStmt.setString(2, administrador.getPassword());
          int numRows = insertStmt.executeUpdate();
        }
        else 
          throw new UtilizadorJaRegistadoException();
          
        c.close();
      }
      else
        throw new ConnectionErrorException();
    }

    public static boolean existeAdmin(String nomeUtilizador) throws SQLException, ConnectionErrorException {
      Connection c = Connect.connect();
      if (c != null) {
        PreparedStatement ps = c.prepareStatement("SELECT * FROM Admin WHERE Admin.NomeUtilizador = ?");
        ps.setString(1, nomeUtilizador);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
          return true;
      }
      else
        throw new ConnectionErrorException();
      return false;
    }
}
