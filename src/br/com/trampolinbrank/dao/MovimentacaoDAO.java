package br.com.trampolinbrank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.trampolinbank.bean.Conta;
import br.com.trampolinbank.bean.Movimentacao;
import br.com.trampolinbank.bean.TipoConta;
import br.com.trampolinbrank.factory.ConnectionFactory;

public class MovimentacaoDAO {
	
	public void incluir(Movimentacao m){
		Connection conn = null;
		try {
			conn = ConnectionFactory.getConnection();
			
			String sql = "INSERT INTO movimentacao (id, conta_id, tipo_conta, descricao, valor, created_at,saldo) "
						+ "VALUES(NULL,?,?,?,?, now(),?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, m.getConta().getId());
			stmt.setInt(2, m.getTipoConta().getId());
			stmt.setString(3, m.getDescricao());
			stmt.setFloat(4, m.getValor());
			stmt.setFloat(5, m.getSaldo());
			stmt.executeUpdate();
			
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
	
	public List<Movimentacao> listar(){
		
		Connection conn = null;
		ArrayList<Movimentacao> movimentacoes = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			
			String sql = "select * from movimentacao order by created_at desc";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			movimentacoes = new ArrayList<Movimentacao>();
			
			while(rs.next()){
				Movimentacao m = new Movimentacao();
				m.setId(rs.getInt("id"));
				m.setValor(rs.getFloat("valor"));
				m.setSaldo(rs.getFloat("saldo"));
				
				Conta conta = new Conta();
				conta.setId(rs.getInt("conta_id"));
				m.setConta(conta);
				
				TipoConta tipoConta = new TipoConta();
				tipoConta.setId(rs.getInt("tipo_conta"));
				m.setTipoConta(tipoConta);
				
				m.setDescricao(rs.getString("descricao"));
				
				m.setCreatedAt(rs.getDate("created_at"));
				
				movimentacoes.add(m);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return movimentacoes;
	}
		
}
