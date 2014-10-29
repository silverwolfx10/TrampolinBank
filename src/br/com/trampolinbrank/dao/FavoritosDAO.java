package br.com.trampolinbrank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.trampolinbank.bean.Conta;
import br.com.trampolinbank.bean.Favoritos;
import br.com.trampolinbank.bean.TipoConta;
import br.com.trampolinbank.bean.Usuario;
import br.com.trampolinbrank.factory.ConnectionFactory;

public class FavoritosDAO {
	
	public void incluir(Favoritos f){
		
		Connection conn = null;
		try {
			conn = ConnectionFactory.getConnection();
			
			String sql = "INSERT INTO favoritos (id, usuario_id,conta_id,tipo_conta, apelido, cpf, created_at) "
						+ "VALUES(NULL,?,?,?,?,?, now())";
			
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, f.getUsuario().getId());
				stmt.setInt(2, f.getConta().getId());
				stmt.setInt(3, f.getTipoConta().getId());
				stmt.setString(4, f.getApelido());
				stmt.setString(5, f.getCpf());
				stmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void excluir(Integer pk){
		
		Connection conn = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			
			String sql = "delete from favoritos where id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pk);
					
			stmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	public List<Favoritos> listar(Usuario usuario){
		
		Connection conn = null;
		ArrayList<Favoritos> favoritos = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			
			String sql = "select * from favoritos favo inner join conta cont on cont.id = favo.conta_id left join tipo_conta tpco on tpco.id=favo.tipo_conta where usuario_id = ? order by apelido asc";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, usuario.getId());
			
			ResultSet rs = stmt.executeQuery();
			
			favoritos = new ArrayList<Favoritos>();
			
			while(rs.next()){
				Favoritos f = new Favoritos();
				f.setId(rs.getInt("favo.id"));
				f.setApelido(rs.getString("favo.apelido"));
				f.setUsuario(usuario);
				f.setCreatedAt(rs.getDate("favo.created_at"));
				
				TipoConta tpConta = new TipoConta();
				tpConta.setId(rs.getInt("tpco.id"));
				tpConta.setDescricao(rs.getString("tpco.descricao"));
				f.setTipoConta(tpConta);
				
				Conta conta = new Conta();
				conta.setId(rs.getInt("cont.id"));
				conta.setAgencia(rs.getString("cont.agencia"));
				conta.setConta(rs.getString("conta"));
				f.setConta(conta);
				
				favoritos.add(f);
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
		
		return favoritos;
	}

	public Favoritos buscar(int id, Usuario usuario) {
		
		Connection conn = null;
		Favoritos f = new Favoritos();
		
		try {
			conn = ConnectionFactory.getConnection();
			
			String sql = "select * from favoritos favo inner join conta cont on cont.id = favo.conta_id left join tipo_conta tpco on tpco.id=favo.tipo_conta where favo.id = ? order by apelido asc";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				
				f.setId(rs.getInt("favo.id"));
				f.setApelido(rs.getString("favo.apelido"));
				f.setUsuario(usuario);
				f.setCreatedAt(rs.getDate("favo.created_at"));
				
				TipoConta tpConta = new TipoConta();
				tpConta.setId(rs.getInt("tpco.id"));
				tpConta.setDescricao(rs.getString("tpco.descricao"));
				f.setTipoConta(tpConta);
				
				Conta conta = new Conta();
				conta.setId(rs.getInt("cont.id"));
				conta.setAgencia(rs.getString("cont.agencia"));
				conta.setConta(rs.getString("conta"));
				f.setConta(conta);
				
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
		
		return f;
	}
	
}
