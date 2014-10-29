package br.com.trampolinbank.manegedbean;


import java.util.List;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
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
	
	public String logar(){
		
		String 	pagRet = "login";
		
		usuario  = new ContaDAO().logar(agencia,conta);

		if(usuario != null)pagRet = "autenticacao";
		else erro = "Conta não encontrada, por gentileza tente novamente.";
		
		return pagRet;
	}
	
	public String autenticar(){
		
		String 	pagRet = "login";
		
		Conta contaLogada  = new ContaDAO().autenticar(agencia,conta,password);
		((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().setAttribute("contaLogada",contaLogada);
		this.populaObj(contaLogada);
		
		if(this.id != 0) listaMovimentacoes = new MovimentacaoDAO().listar(contaLogada.getId());
		
		if(usuario != null)pagRet = "inicio";
		else erro = "Senha inválida!";
		return pagRet;
		
	}
	
	public String logout(){
		((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().setAttribute("contaLogada",null);
		return "login";
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

	public String atualiza() {
		Conta contaLogada = (Conta) ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute("contaLogada");
		refresh();
		populaObj(contaLogada);
		this.saldo_corrente = 1.1f;
		//if(this.id != 0) listaMovimentacoes = new MovimentacaoDAO().listar();
		return "inicio";
	}
	
	public void refresh() {  
        FacesContext context = FacesContext.getCurrentInstance();  
        Application application = context.getApplication();  
        ViewHandler viewHandler = application.getViewHandler();  
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());  
        context.setViewRoot(viewRoot);  
        context.renderResponse();  
    } 
}
