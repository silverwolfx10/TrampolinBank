package br.com.trampolinbrank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.trampolinbank.bean.Conta;
import br.com.trampolinbank.bean.TipoConta;
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
			stmt.setInt(4, c.getTipoConta().getId());
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
		
	public void editar(Conta c){
			
		Connection conn = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			
			String sql = "UPDATE conta SET  tipo_conta = ?, saldo_poupanca = ?, saldo_corrente = ?, updated_at = now() WHERE id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
				
			stmt.setInt(1, c.getTipoConta().getId());
			stmt.setFloat(2, c.getSaldoPoupanca());
			stmt.setFloat(3, c.getSaldoCorrente());
			stmt.setInt(4, c.getId());
			stmt.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	
	}

	public Usuario logar(String agencia, String conta) {
		
		Usuario usuario = null;
		Connection conn = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			
			String sql = "select * from conta cont inner join usuario user on cont.id_usuario = user.id where agencia = ? and conta = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, agencia);
			stmt.setString(2, conta);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				usuario = new Usuario();
				usuario.setNomeCompleto(rs.getString("nome_completo"));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally{
			try {
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return usuario;
		
	}

	public Conta autenticar(String agencia, String numConta, String password) {
		
		Conta conta = null;
		Connection conn = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			
			String sql = "select * from conta cont inner join usuario user on cont.id_usuario = user.id inner join tipo_conta tpco on tpco.id = cont.tipo_conta where agencia = ? and conta = ? and password = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, agencia);
			stmt.setString(2, numConta);
			stmt.setString(3, password);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				conta = new Conta();
				conta.setAgencia(rs.getString("agencia"));
				conta.setConta(rs.getString("conta"));
				conta.setId(rs.getInt("cont.id"));
				conta.setSaldoCorrente(rs.getFloat("saldo_corrente"));
				conta.setSaldoPoupanca(rs.getFloat("saldo_poupanca"));
				conta.setStatus(rs.getInt("cont.status"));
				conta.setTipoConta(new TipoConta().setId(rs.getInt("tipo_conta")).setDescricao(rs.getString("descricao")));
				
				//buidando usuario
				Usuario usuario = new Usuario().setNomeCompleto(rs.getString("nome_completo"))
											   .setId(rs.getInt("id_usuario"))
											   .setEmail(rs.getString("email"))
											   .setIdade(rs.getInt("idade"))
											   .setStatus(rs.getInt("user.status"));
				
				conta.setUsuario(usuario);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally{
			try {
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return conta;
	}

	public Conta buscar(String agencia, String numConta) {
		
		Conta conta = null;
		Connection conn = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			
			String sql = "select * from conta cont inner join usuario user on cont.id_usuario = user.id inner join tipo_conta tpco on tpco.id = cont.tipo_conta where agencia = ? and conta = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, agencia);
			stmt.setString(2, numConta);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				conta = new Conta();
				conta.setAgencia(rs.getString("agencia"));
				conta.setConta(rs.getString("conta"));
				conta.setId(rs.getInt("cont.id"));
				conta.setSaldoCorrente(rs.getFloat("saldo_corrente"));
				conta.setSaldoPoupanca(rs.getFloat("saldo_poupanca"));
				conta.setStatus(rs.getInt("cont.status"));
				conta.setTipoConta(new TipoConta().setId(rs.getInt("tipo_conta")).setDescricao(rs.getString("descricao")));
				
				//buidando usuario
				Usuario usuario = new Usuario().setNomeCompleto(rs.getString("nome_completo"))
											   .setId(rs.getInt("id_usuario"))
											   .setEmail(rs.getString("email"))
											   .setIdade(rs.getInt("idade"))
											   .setStatus(rs.getInt("user.status"));
				
				conta.setUsuario(usuario);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally{
			try {
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return conta;
	}

	public Conta get(Integer id) {

		Conta conta = null;
		Connection conn = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			
			String sql = "select * from conta cont inner join usuario user on cont.id_usuario = user.id inner join tipo_conta tpco on tpco.id = cont.tipo_conta where cont.id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				conta = new Conta();
				conta.setAgencia(rs.getString("agencia"));
				conta.setConta(rs.getString("conta"));
				conta.setId(rs.getInt("cont.id"));
				conta.setSaldoCorrente(rs.getFloat("saldo_corrente"));
				conta.setSaldoPoupanca(rs.getFloat("saldo_poupanca"));
				conta.setStatus(rs.getInt("cont.status"));
				conta.setTipoConta(new TipoConta().setId(rs.getInt("tipo_conta")).setDescricao(rs.getString("descricao")));
				
				//buidando usuario
				Usuario usuario = new Usuario().setNomeCompleto(rs.getString("nome_completo"))
											   .setId(rs.getInt("id_usuario"))
											   .setEmail(rs.getString("email"))
											   .setIdade(rs.getInt("idade"))
											   .setStatus(rs.getInt("user.status"));
				
				conta.setUsuario(usuario);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally{
			try {
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return conta;
	}

	
	
}
