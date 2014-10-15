package br.com.trampolinbrank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.trampolinbank.bean.Conta;
import br.com.trampolinbank.bean.Usuario;
import br.com.trampolinbrank.factory.ConnectionFactory;

public class ContaDAO {
	
	public void incluir(Conta c) throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "INSERT INTO conta (id, id_usuario, agencia, conta, tipo_conta, password, saldo_poupanca, saldo_corrente, status, created_at) "
					+ "VALUES(NULL,?,?,?,?,?,?,?,?, now())";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, c.getUsuario().getId());
			stmt.setString(2, c.getAgencia());
			stmt.setString(3, c.getConta());
			stmt.setInt(4, c.getTipoConta());
			stmt.setString(5, c.getPassword());
			stmt.setFloat(6, c.getSaldoPoupanca());
			stmt.setFloat(7, c.getSaldoCorrente());
			stmt.setInt(8, c.getStatus());
			stmt.executeUpdate();
		
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			conn.close();
		}

	}
		
	public void editar(Conta c) throws SQLException{
			
			Connection conn = ConnectionFactory.getConnection();
			
			String sql = "UPDATE conta SET  tipo_conta = ?, password = ?, saldo_poupanca = ?, saldo_corrente = ?, updated_at = now() WHERE id = ?";
			try{
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				stmt.setInt(1, c.getTipoConta());
				stmt.setString(2, c.getPassword());
				stmt.setFloat(3, c.getSaldoPoupanca());
				stmt.setFloat(4, c.getSaldoCorrente());
				stmt.executeUpdate();
			
			}catch(SQLException ex){
				ex.printStackTrace();
			}finally{
				conn.close();
			}
	
		}
	
	
	
}
