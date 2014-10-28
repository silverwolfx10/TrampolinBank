package br.com.trampolinbank.manegedbean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import br.com.trampolinbank.bean.Conta;
import br.com.trampolinbank.bean.Favoritos;
import br.com.trampolinbank.bean.TipoConta;
import br.com.trampolinbank.bean.Usuario;
import br.com.trampolinbrank.dao.ContaDAO;
import br.com.trampolinbrank.dao.FavoritosDAO;
import br.com.trampolinbrank.dao.TipoContaDAO;

public class TransferenciaManegedBean {
	
	private Favoritos favorito = new Favoritos();
	private List<Favoritos> listaFavoritos;
	private List<TipoConta> tipoContas;
	private Collection teste = new ArrayList<TipoConta>();
	private int idTipoConta;
	
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
			context.addMessage(null,new FacesMessage("Conta não encontrada!"));
			return null;
		}
		return atualizaLista();
		
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
}
