package br.com.trampolinbrank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.trampolinbank.bean.Conta;
import br.com.trampolinbank.bean.Favoritos;
import br.com.trampolinbank.bean.Movimentacao;
import br.com.trampolinbank.bean.TipoConta;
import br.com.trampolinbank.bean.Usuario;
import br.com.trampolinbrank.factory.ConnectionFactory;

public class MovimentacaoDAO {
	
	public void incluir(Movimentacao m) throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "INSERT INTO movimentacao (id, conta_id, tipo_conta, descricao, valor, created_at) "
					+ "VALUES(NULL,?,?,?,?, now())";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, m.getConta().getId());
			stmt.setInt(2, m.getTipoConta().getId());
			stmt.setString(3, m.getDescricao());
			stmt.setFloat(4, m.getValor());
			stmt.executeUpdate();
		
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			conn.close();
		}

	}
	
	public List<Movimentacao> listar()  throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "select * from movimentacao order by created_at desc";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
		
		while(rs.next()){
			Movimentacao m = new Movimentacao();
			m.setId(rs.getInt("id"));
			m.setValor(rs.getFloat("valor"));
			
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
		
		conn.close();
		
		return movimentacoes;
	}
		
}
