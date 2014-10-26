package br.com.trampolinbrank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.trampolinbank.bean.Agenda;
import br.com.trampolinbank.bean.Conta;
import br.com.trampolinbank.bean.Favoritos;
import br.com.trampolinbrank.factory.ConnectionFactory;

public class FavoritosDAO {
	
	public void incluir(Favoritos f) throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "INSERT INTO favoritos (id, usuario_id,  conta_id, apelido, cpf, created_at) "
					+ "VALUES(NULL,?,?,?,?, now())";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, f.getUsuario().getId());
			stmt.setInt(2, f.getConta().getId());
			stmt.setString(3, f.getApelido());
			stmt.setString(4, f.getCpf());
			stmt.executeUpdate();
		
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			conn.close();
		}

	}
	
	public void excluir(Integer pk) throws SQLException{
			
			Connection conn = ConnectionFactory.getConnection();
			
			String sql = "delete from favoritos where id = ?";
			try{
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, pk);
						
				stmt.executeUpdate();
			}catch(SQLException ex){
				ex.printStackTrace();
			}finally{
				conn.close();
			}
	
		}
	
	
}
