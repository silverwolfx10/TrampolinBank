package br.com.trampolinbank.manegedbean;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import br.com.trampolinbank.bean.Agenda;
import br.com.trampolinbank.bean.Conta;
import br.com.trampolinbank.bean.Favoritos;
import br.com.trampolinbank.bean.Movimentacao;
import br.com.trampolinbank.bean.TipoConta;
import br.com.trampolinbrank.dao.AgendaDAO;
import br.com.trampolinbrank.dao.ContaDAO;
import br.com.trampolinbrank.dao.FavoritosDAO;
import br.com.trampolinbrank.dao.MovimentacaoDAO;
import br.com.trampolinbrank.dao.TipoContaDAO;

@ManagedBean
@SessionScoped
public class TransferenciaManegedBean {
	
	private Favoritos favorito = new Favoritos();
	private List<Favoritos> listaFavoritos;
	
	private List<TipoConta> tipoContas;
	private Collection teste = new ArrayList<TipoConta>();
	private int idTipoConta;
	
	private Movimentacao movimentacao = new Movimentacao();
	private Agenda agenda = new Agenda();
	private Conta conta = new Conta();
	
	private String dataAgendamento;
	
	public String agendar() throws ParseException{
		
		DateFormat dfmt = new SimpleDateFormat("dd/MM/yyyy");
		Date data = new Date(dfmt.parse(dataAgendamento).getTime());
		
		Conta contaLogada = (Conta) ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute("contaLogada");
		
		agenda.setAgendado(data);
		agenda.setConta(contaLogada);
		agenda.setDescricao("Transfer�ncia para"+favorito.getConta().getAgencia()+" "+favorito.getConta().getConta());
		agenda.setValor(movimentacao.getValor());
		
		new AgendaDAO().incluir(agenda);
		
		return "inicio";
	}
	
	public String transferir(){
		
		Conta contaLogada = (Conta) ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute("contaLogada");
		
		if(new ContaDAO().autenticar(contaLogada.getAgencia(), contaLogada.getConta(), conta.getPassword())!=null){
			if(contaLogada.getTipoConta().getId() == 1)
				contaLogada.setSaldoCorrente(contaLogada.getSaldoCorrente()-movimentacao.getValor());
			else
				contaLogada.setSaldoPoupanca(contaLogada.getSaldoPoupanca()-movimentacao.getValor());
			
			new ContaDAO().editar(contaLogada);
			((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().removeAttribute("contaLogada");
			((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().setAttribute("contaLogada", contaLogada);
			
			Conta contaBenefConta = new ContaDAO().buscar(favorito.getConta().getAgencia(), favorito.getConta().getConta());
			if(contaBenefConta.getTipoConta().getId() == 1)
				contaBenefConta.setSaldoCorrente(contaBenefConta.getSaldoCorrente()+movimentacao.getValor());
			else
				contaBenefConta.setSaldoPoupanca(contaBenefConta.getSaldoPoupanca()+movimentacao.getValor());
			new ContaDAO().editar(contaBenefConta);
			
			movimentacao.setContaOrigem(contaLogada);
			movimentacao.setContaDestino(favorito.getConta());
			movimentacao.setDescricao("Transfer�ncia para"+favorito.getConta().getAgencia()+" "+favorito.getConta().getConta());
			
			new MovimentacaoDAO().incluir(movimentacao);
		}
		
		return "sucesso";
	}
	
	public String cadastrar(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		Conta contaLogada = (Conta) ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute("contaLogada");
		favorito.setUsuario(contaLogada.getUsuario());
		
		Conta contaBeneficiario = new ContaDAO().buscar(favorito.getConta().getAgencia(), favorito.getConta().getConta());
		
		if(contaBeneficiario != null){
			favorito.setConta(contaBeneficiario);
			favorito.setTipoConta(new TipoConta().setId(idTipoConta));
			new FavoritosDAO().incluir(favorito);
		}else{
			context.addMessage(null,new FacesMessage("Conta n�o encontrada!"));
			return null;
		}
		return atualizaLista();
		
	}
	
	public String buscarFavorito(int id){
		
		Conta contaLogada = (Conta) ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute("contaLogada");
		favorito = new FavoritosDAO().buscar(id,contaLogada.getUsuario());
		
		return "transferindo";
	}
	
	public String abrirTelaTransferir(){
		return "";
	}
	
	public String excluirFavorito(int id){
		
		new FavoritosDAO().excluir(id);
		
		return atualizaLista();
	}
	
	
	private String atualizaLista(){
		
		this.listaFavoritos = getListaFavoritos();
		
		return "transferencia";
	}

	public Favoritos getFavorito() {
		return favorito;
	}
	
	public void setFavorito(Favoritos favorito) {
		this.favorito = favorito;
	}
	
	public List<TipoConta> getTipoContas() {
		return this.tipoContas;
	}
	
	public void setTipoContas(List<TipoConta> tipoContas) {
		this.tipoContas = tipoContas;
	}

	public List<Favoritos> getListaFavoritos() {
		Conta contaLogada = (Conta) ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute("contaLogada");
		listaFavoritos = new FavoritosDAO().listar(contaLogada.getUsuario());
		return listaFavoritos;
	}

	public void setListaFavoritos(List<Favoritos> listaFavoritos) {
		this.listaFavoritos = listaFavoritos;
	}
	
	public Collection getTeste() {
		tipoContas = new TipoContaDAO().listar();
		return getValuesComboBox();
	}

	public void setTeste(Collection teste) {
		this.teste = teste;
	} 
	
	public Collection getValuesComboBox(){  
		 
		  Collection toReturn = new ArrayList();  
		  for (TipoConta t : tipoContas) {
			  toReturn.add( new SelectItem( t.getId(), t.getDescricao()));
		  }
		  return toReturn;
		}

	public int getIdTipoConta() {
		return idTipoConta;
	}

	public void setIdTipoConta(int idTipoConta) {
		this.idTipoConta = idTipoConta;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
}
