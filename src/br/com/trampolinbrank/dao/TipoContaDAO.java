package br.com.trampolinbrank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.trampolinbank.bean.Conta;
import br.com.trampolinbank.bean.Operacao;
import br.com.trampolinbank.bean.TipoConta;
import br.com.trampolinbrank.factory.ConnectionFactory;

public class TipoContaDAO {
	
public List<TipoConta> listar(){
		
	Connection conn = null;
	ArrayList<TipoConta> tipoContas = null;
	
	try {
		conn = ConnectionFactory.getConnection();
		
		String sql = "select * from tipo_conta";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		tipoContas = new ArrayList<TipoConta>();
		
		while(rs.next()){
			TipoConta t = new TipoConta();
			t.setId(rs.getInt("id"));
			t.setDescricao(rs.getString("descricao"));
			
			
			tipoContas.add(t);
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
	
	
		return tipoContas;
	}
	
	
}
