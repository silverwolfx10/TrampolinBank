package br.com.trampolinbank.manegedbean;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.trampolinbank.bean.Agenda;
import br.com.trampolinbank.bean.Conta;
import br.com.trampolinbank.bean.Movimentacao;
import br.com.trampolinbank.bean.TipoConta;
import br.com.trampolinbrank.dao.AgendaDAO;
import br.com.trampolinbrank.dao.ContaDAO;
import br.com.trampolinbrank.dao.MovimentacaoDAO;

public class PagamentoManagerBean {
	
	private String codigoBarras;
	private float valor;
	private String dataAgendamento;
	private String senha;
	
	public String pagarConta(){
		
		Conta contaLogada = (Conta) ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute("contaLogada");
		
		if(new ContaDAO().autenticar(contaLogada.getAgencia(), contaLogada.getConta(), senha)!=null){
			
			Movimentacao movimentacao = new Movimentacao();

			movimentacao.setContaOrigem(contaLogada);
			movimentacao.setDescricao("Pagamento do Boleto: "+codigoBarras);
			movimentacao.setValor(valor);

			
			if(contaLogada.getTipoConta().getId() == 1){
				contaLogada.setSaldoCorrente(contaLogada.getSaldoCorrente()-valor);
				movimentacao.setSaldo(contaLogada.getSaldoCorrente());
			}else{
				contaLogada.setSaldoPoupanca(contaLogada.getSaldoPoupanca()-valor);
				movimentacao.setSaldo(contaLogada.getSaldoPoupanca());
			}
			
			new ContaDAO().editar(contaLogada);
			
			((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().removeAttribute("contaLogada");
			((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().setAttribute("contaLogada", contaLogada);
			
			new MovimentacaoDAO().incluir(movimentacao);
		}
		

		return "sucesso";

	}
	
	public String agendar() throws ParseException{
		
		DateFormat dfmt = new SimpleDateFormat("dd/MM/yyyy");
		Date data = new Date(dfmt.parse(dataAgendamento).getTime());
		
		Conta contaLogada = (Conta) ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute("contaLogada");
		
		Agenda agenda = new Agenda();
		agenda.setAgendado(data);
		agenda.setConta(contaLogada);
		agenda.setDescricao("Pagamento do Boleto: "+codigoBarras);
		agenda.setValor(valor);
		
		new AgendaDAO().incluir(agenda);
		

		return "sucesso";

	}
	
	
	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
