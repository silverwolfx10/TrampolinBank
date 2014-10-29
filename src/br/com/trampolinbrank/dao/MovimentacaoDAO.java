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

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


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
	
	public List<Movimentacao> listar(int conta_id){
		
		Connection conn = null;
		ArrayList<Movimentacao> movimentacoes = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			
						
			String sql = "select * from movimentacao where conta_id =? order by created_at desc limit 5";
						
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, conta_id);
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
			
			Conta conta = new Conta();
			conta.setId(rs.getInt("conta_id"));
			m.setConta(conta);
			
			TipoConta tipoConta = new TipoConta();
			tipoConta.setId(rs.getInt("tipo_conta"));
			m.setTipoConta(tipoConta);
			
			m.setDescricao(rs.getString("descricao"));
			
			m.setCreatedAt(rs.getDate("created_at"));
			
			movimentacao.add(m);
		}
		
		conn.close();
		
		return movimentacao;
	}
		
}
