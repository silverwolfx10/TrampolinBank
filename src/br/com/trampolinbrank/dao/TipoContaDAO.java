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
	
public List<TipoConta> listar()  throws SQLException{
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "select * from tipo_conta";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<TipoConta> tipoContas = new ArrayList<TipoConta>();
		
		while(rs.next()){
			TipoConta t = new TipoConta();
			t.setId(rs.getInt("id"));
			t.setDescricao(rs.getString("descricao"));
			
			
			tipoContas.add(t);
		}
		
		conn.close();
		
		return tipoContas;
	}
	
	
}
