package br.com.trampolinbank.manegedbean;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import br.com.trampolinbank.bean.Conta;
import br.com.trampolinbank.bean.Movimentacao;
import br.com.trampolinbrank.dao.ContaDAO;
import br.com.trampolinbrank.dao.MovimentacaoDAO;

@ManagedBean
@SessionScoped
public class ContaManegedBean extends Conta{
	
	private String erro = "";
	private Movimentacao movimentacao = null;
	private List<Movimentacao> listaMovimentacoes;
	
	
	public void initialize() throws SQLException, ParseException{
		Conta contaLogada = (Conta) ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute("contaLogada");
	
		if(contaLogada != null){
			Conta conta = new ContaDAO().get(contaLogada.getId());
			this.populaObj(conta);
			
			listaMovimentacoes = new MovimentacaoDAO().listar(contaLogada.getId());
		}
		
	}
	
	public String logar(){
		
		String 	pagRet = "login";
		
		usuario  = new ContaDAO().logar(agencia,conta);

		if(usuario != null)pagRet = "autenticacao";
		else erro = "Conta não encontrada, por gentileza tente novamente.";
		
		return pagRet;
	}
	
	public String autenticar() throws SQLException, ParseException{
		
		String 	pagRet = "login";
		
		Conta contaLogada  = null;
		
		ArrayList<Conta> constas = new ContaDAO().buscarContas(agencia,conta,password);
		
		if(constas.size() > 0){
			contaLogada = constas.get(0);
			
			((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().setAttribute("contaLogada",contaLogada);
			this.populaObj(constas.get(0));
			
			if(this.id != 0) listaMovimentacoes = new MovimentacaoDAO().listar(contaLogada.getId());
		}
		
		if(usuario != null){
			pagRet = "inicio";
		}else erro = "Senha inválida!";
		
		return pagRet;
	}
	
	public String logout(){
		((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().setAttribute("contaLogada",null);
		return "login";
	}
	
	public String alterarTipoConta() throws SQLException, ParseException{
		Conta contaLogada = (Conta) ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute("contaLogada");
		contaLogada = new ContaDAO().inverteConta(contaLogada.getAgencia(),contaLogada.getConta(),contaLogada.getTipoConta().getId());
		
		if(contaLogada != null){
			((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().setAttribute("contaLogada",contaLogada);
			this.populaObj(contaLogada);
			listaMovimentacoes = new MovimentacaoDAO().listar(contaLogada.getId());
		}else{
			erro = "Usuario não possui outra conta disponível!";
		}
		
		return "inicio";
		
	}
	
	private void populaObj(Conta contaLogada) {
		this.agencia = contaLogada.getAgencia();
		this.conta = contaLogada.getConta();
		this.id = contaLogada.getId();
		this.saldo_corrente = contaLogada.getSaldoCorrente();
		this.saldo_poupanca = contaLogada.getSaldoPoupanca();
		this.status = contaLogada.getStatus();
		this.tipo_conta = contaLogada.getTipoConta();
		this.usuario = contaLogada.getUsuario();
	}

	//getters and setters
	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public List<Movimentacao> getListaMovimentacoes() {
		return listaMovimentacoes;
	}

	public void setListaMovimentacoes(List<Movimentacao> listaMovimentacoes) {
		this.listaMovimentacoes = listaMovimentacoes;
	}

//	public String atualiza() {
//		Conta contaLogada = (Conta) ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute("contaLogada");
//		refresh();
//		populaObj(contaLogada);
//		this.saldo_corrente = 1.1f;
//		if(this.id != 0) listaMovimentacoes = new MovimentacaoDAO().listar();
//		return "inicio";
//	}
	
	public void refresh() {  
        FacesContext context = FacesContext.getCurrentInstance();  
        Application application = context.getApplication();  
        ViewHandler viewHandler = application.getViewHandler();  
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());  
        context.setViewRoot(viewRoot);  
        context.renderResponse();  
    } 
}
