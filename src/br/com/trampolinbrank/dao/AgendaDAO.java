package br.com.trampolinbrank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.trampolinbank.bean.Agenda;
import br.com.trampolinbank.bean.Conta;
import br.com.trampolinbrank.factory.ConnectionFactory;

public class AgendaDAO {
	
	public void incluir(Agenda a) throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "INSERT INTO agenda (id, valor, conta_id, descricao, agendado, created_at) "
					+ "VALUES(NULL,?,?,?,?, now())";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setFloat(1, a.getValor());
			stmt.setInt(2, a.getConta().getId());
			stmt.setString(3, a.getDescricao());
			stmt.setDate(4, a.getAgendado());
			stmt.executeUpdate();
		
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			conn.close();
		}

	}
	
	
}
