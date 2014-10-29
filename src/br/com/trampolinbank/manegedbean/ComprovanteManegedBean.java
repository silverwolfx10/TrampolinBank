package br.com.trampolinbank.manegedbean;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.trampolinbank.bean.Conta;
import br.com.trampolinbank.bean.Movimentacao;
import br.com.trampolinbrank.dao.MovimentacaoDAO;

public class ComprovanteManegedBean {
	
	private String dataInicio="";
	private String dataFim="";
	private List<Movimentacao> listaMovimentacoes;
	private Movimentacao movimentacao;
	
	public String filtrar(){
		 Conta contaLogada = (Conta) ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute("contaLogada");
		try {
			this.listaMovimentacoes = new MovimentacaoDAO().listarByDateInterval(contaLogada.getId(),dataInicio,dataFim);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "comprovantes";
	}
	
	public String abrirComprovante(int id){
		
		movimentacao = new MovimentacaoDAO().buscar(id);
		
		return "comprovando";
		
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public List<Movimentacao> getListaMovimentacoes() throws SQLException, ParseException {
		if(listaMovimentacoes ==null){
			Conta contaLogada = (Conta) ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute("contaLogada");
			listaMovimentacoes = new MovimentacaoDAO().listar(contaLogada.getId());
		}
		
		return listaMovimentacoes;
	}

	public void setListaMovimentacoes(List<Movimentacao> listaMovimentacoes) {
		this.listaMovimentacoes = listaMovimentacoes;
	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

}
