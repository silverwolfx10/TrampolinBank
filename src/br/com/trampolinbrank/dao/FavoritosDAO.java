package br.com.trampolinbrank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.trampolinbank.bean.Agenda;
import br.com.trampolinbank.bean.Conta;
import br.com.trampolinbank.bean.Favoritos;
import br.com.trampolinbank.bean.Operacao;
import br.com.trampolinbank.bean.Usuario;
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
	
	
	public List<Favoritos> listar()  throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "select * from favoritos order by apelido asc";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Favoritos> favoritos = new ArrayList<Favoritos>();
		
		while(rs.next()){
			Favoritos f = new Favoritos();
			f.setId(rs.getInt("id"));
			f.setApelido(rs.getString("apelido"));
			f.setCpf(rs.getString("cpf"));
			
			Conta conta = new Conta();
			conta.setId(rs.getInt("conta_id"));
			f.setConta(conta);
			
			Usuario usuario = new Usuario();
			usuario.setId(rs.getInt("usuario_id"));
			f.setUsuario(usuario);
			
			f.setCreatedAt(rs.getDate("created_at"));
			
			favoritos.add(f);
		}
		
		conn.close();
		
		return favoritos;
	}
	
	
	
}
