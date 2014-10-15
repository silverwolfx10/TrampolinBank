package br.com.trampolinbrank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.trampolinbank.bean.Usuario;
import br.com.trampolinbrank.factory.ConnectionFactory;

public class UsuarioDAO {
	
	public void incluir(Usuario u) throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "INSERT INTO usuario (id, nome_completo, idade, created_at) VALUES(NULL,?,?,?, now())";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, u.getNomeCompleto());
			stmt.setInt(2, u.getIdade());
			stmt.executeUpdate();
		
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			conn.close();
		}

	}
	
	public void editar(Usuario u) throws SQLException{
			
			Connection conn = ConnectionFactory.getConnection();
			
			String sql = "UPDATE  usuario SET  nome_completo = ?, idade = ?, updated_at = now() WHERE id = ?";
			try{
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, u.getNomeCompleto());
				stmt.setInt(2, u.getIdade());
				stmt.setInt(3, u.getId());
				stmt.executeUpdate();
			
			}catch(SQLException ex){
				ex.printStackTrace();
			}finally{
				conn.close();
			}
	
		}
	
	
	
}
