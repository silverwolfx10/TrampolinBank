package br.com.trampolinbrank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.com.trampolinbank.bean.Movimentacao;
import br.com.trampolinbrank.factory.ConnectionFactory;

public class MovimentacaoDAO {
	
	public void incluir(Movimentacao m) throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "INSERT INTO movimentacao (id, conta_id, tipo_conta, descricao, valor, created_at) "
					+ "VALUES(NULL,?,?,?,?, now())";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, m.getContaId().getId());
			stmt.setInt(2, m.getTipoConta());
			stmt.setString(3, m.getDescricao());
			stmt.setFloat(4, m.getValor());
			stmt.executeUpdate();
		
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			conn.close();
		}

	}
		
}
