package br.com.trampolinbrank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.trampolinbank.bean.Agenda;
import br.com.trampolinbank.bean.Conta;
import br.com.trampolinbank.bean.Operacao;
import br.com.trampolinbrank.factory.ConnectionFactory;

public class AgendaDAO {
	
	public void incluir(Agenda a){
		
		Connection conn = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			
			String sql = "INSERT INTO agenda (id, valor, conta_id, descricao, agendado, created_at) "
						+ "VALUES(NULL,?,?,?,?, now())";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setFloat(1, a.getValor());
			stmt.setInt(2, a.getConta().getId());
			stmt.setString(3, a.getDescricao());
			stmt.setDate(4, a.getAgendado());
			stmt.executeUpdate();
			
			conn.close();
			
		} catch (Exception e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				System.out.println(e.getMessage());
			}		
		}

	}
	

	public List<Agenda> listar()  throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "select * from agenda order by agendado desc";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Agenda> agendas = new ArrayList<Agenda>();
		
		while(rs.next()){
			Agenda a = new Agenda();
			a.setValor(rs.getFloat("valor"));
			
			Conta conta = new Conta();
			conta.setId(rs.getInt("prazo"));
			a.setConta(conta);
			
			a.setDescricao(rs.getString("descricao"));
			a.setAgendado(rs.getDate("agendado"));
			
			agendas.add(a);
		}
		
		conn.close();
		
		return agendas;
	}
	
	
}
