package br.com.trampolinbrank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.com.trampolinbank.bean.Conta;
import br.com.trampolinbank.bean.Operacao;
import br.com.trampolinbrank.factory.ConnectionFactory;

public class OperacaoDAO {
	
public List<Operacao> listar()  throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "select * from operacao";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<Operacao> operacoes = new ArrayList<Operacao>();
		
		while(rs.next()){
			Operacao o = new Operacao();
			o.setDescricao(rs.getString("descricao"));
			o.setPrazo(rs.getInt("prazo"));
			o.setTaxa(rs.getFloat("taxa"));
			
			operacoes.add(o);
		}
		
		conn.close();
		
		return operacoes;
	}
	
	
}
