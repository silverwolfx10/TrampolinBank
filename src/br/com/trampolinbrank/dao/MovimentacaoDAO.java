package br.com.trampolinbrank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.trampolinbank.bean.Conta;
import br.com.trampolinbank.bean.Movimentacao;
import br.com.trampolinbrank.factory.ConnectionFactory;

public class MovimentacaoDAO {
	
	public void incluir(Movimentacao m){
		Connection conn = null;
		try {
			conn = ConnectionFactory.getConnection();
			
			String sql = "INSERT INTO movimentacao (id, conta_origem_id,conta_destino_id, descricao, valor, created_at,saldo) "
						+ "VALUES(NULL,?,?,?,?, now(),?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, m.getContaOrigem().getId());
			if(m.getContaDestino() != null)
				stmt.setInt(2, m.getContaDestino().getId());
			else
				stmt.setInt(2,0);
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
	
	public List<Movimentacao> listar(int conta_id){
		
		Connection conn = null;
		ArrayList<Movimentacao> movimentacoes = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			
			String sql = "select * from movimentacao where conta_origem_id = ? order by created_at desc limit 5";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, conta_id);
			ResultSet rs = stmt.executeQuery();
			
			movimentacoes = new ArrayList<Movimentacao>();
			
			while(rs.next()){
				Movimentacao m = new Movimentacao();
				m.setId(rs.getInt("id"));
				m.setValor(rs.getFloat("valor"));
				m.setSaldo(rs.getFloat("saldo"));
				
				Conta contaOrigem = new Conta();
				contaOrigem.setId(rs.getInt("conta_origem_id"));
				m.setContaOrigem(contaOrigem);
				
				Conta contaDestino = new Conta();
				contaDestino.setId(rs.getInt("conta_destino_id"));
				m.setContaOrigem(contaDestino);
				
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
	
	public Movimentacao buscar(int id){
		
		Connection conn = null;
		Movimentacao m = null;
		try {
			conn = ConnectionFactory.getConnection();
			
			String sql = "select * from movimentacao movi inner join conta cont1 on cont1.id = movi.conta_origem_id left join conta cont2 on cont2.id = movi.conta_destino_id where movi.id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				m = new Movimentacao();
				m.setId(rs.getInt("id"));
				m.setValor(rs.getFloat("valor"));
				m.setSaldo(rs.getFloat("saldo"));
				
				Conta contaOrigem = new Conta();
				contaOrigem.setId(rs.getInt("conta_origem_id"));
				contaOrigem.setAgencia(rs.getString("cont1.agencia"));
				contaOrigem.setConta(rs.getString("cont1.conta"));
				m.setContaOrigem(contaOrigem);
				
				Conta contaDestino = new Conta();
				contaDestino.setId(rs.getInt("conta_destino_id"));
				contaDestino.setAgencia(rs.getString("cont2.agencia"));
				contaDestino.setConta(rs.getString("cont2.conta"));
				m.setContaDestino(contaDestino);
				
				m.setDescricao(rs.getString("descricao"));
				
				m.setCreatedAt(rs.getDate("created_at"));
				
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
		
		return m;
	}
	
public List<Movimentacao> listar(Integer conta_id, String dataDe, String dataAte)  throws SQLException, ParseException{
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "select * from movimentacao where conta_id =? ";

		if(dataDe != null && !dataDe.equals("") && dataAte != null && !dataAte.equals(""))
			sql += " and created_at > ? and created_at < ? ";
		
		sql += "order by created_at";
		
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, conta_id);
		
		if(dataDe != null && !dataDe.equals("") && dataAte != null && !dataAte.equals("")){
			
			DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");  
			java.sql.Date de = new java.sql.Date(fmt.parse(dataDe).getTime());
			java.sql.Date ate = new java.sql.Date(fmt.parse(dataAte).getTime());
			
			stmt.setDate(2, new java.sql.Date(de.getTime()));
			
			stmt.setDate(3, new java.sql.Date(ate.getTime()));
		}
		
		ResultSet rs = stmt.executeQuery();
		ArrayList<Movimentacao> movimentacao = new ArrayList<Movimentacao>();
		
		while(rs.next()){
			Movimentacao m = new Movimentacao();
			m.setId(rs.getInt("id"));
			m.setValor(rs.getFloat("valor"));
			m.setSaldo(rs.getFloat("saldo"));
			
			Conta contaOrigem = new Conta();
			contaOrigem.setId(rs.getInt("conta_origem_id"));
			contaOrigem.setAgencia(rs.getString("cont1.agencia"));
			contaOrigem.setConta(rs.getString("cont1.conta"));
			m.setContaOrigem(contaOrigem);
			
			Conta contaDestino = new Conta();
			contaDestino.setId(rs.getInt("conta_destino_id"));
			contaDestino.setAgencia(rs.getString("cont2.agencia"));
			contaDestino.setConta(rs.getString("cont2.conta"));
			m.setContaDestino(contaDestino);
			
			m.setDescricao(rs.getString("descricao"));
			
			m.setCreatedAt(rs.getDate("created_at"));
			movimentacao.add(m);
		}
		
		conn.close();
		
		return movimentacao;
	}
		
}
