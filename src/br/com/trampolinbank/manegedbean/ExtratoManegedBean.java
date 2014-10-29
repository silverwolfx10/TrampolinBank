package br.com.trampolinbank.manegedbean;

import br.com.trampolinbank.bean.Conta;
import br.com.trampolinbank.bean.Movimentacao;
import br.com.trampolinbrank.dao.MovimentacaoDAO;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped
public class ExtratoManegedBean extends Movimentacao
{
  private Movimentacao movimentacao = new Movimentacao();
  private List<Movimentacao> listaMovimentacao;
  private String dhCadastroDe = "";
  private String dhCadastroAte = "";
  private String mensagem = "";
  
  
  public String getmensagem()
  {
    return this.mensagem;
  }
  
  public String getDhCadastroDe()
  {
    return this.dhCadastroDe;
  }
  
  public void setDhCadastroDe(String dhCadastroDe)
  {
    this.dhCadastroDe = dhCadastroDe;
  }
  
  public String getDhCadastroAte()
  {
    return this.dhCadastroAte;
  }
  
  public void setDhCadastroAte(String dhCadastroAte)
  {
    this.dhCadastroAte = dhCadastroAte;
  }
  
  public List<Movimentacao> getlistaMovimentacao()
  {
    listar();
    return this.listaMovimentacao;
  }
  
  public Movimentacao getMovimentacao()
  {
    return this.movimentacao;
  }
  
  public void setMovimentacao(Movimentacao Movimentacao)
  {
    this.movimentacao = Movimentacao;
  }
  
  public String listar()
  {
    String resultado = "mostrar";
    
    MovimentacaoDAO dao = new MovimentacaoDAO();
    
    Conta contaLogada = (Conta) ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute("contaLogada");
    
    try
    {
      this.listaMovimentacao = dao.listarByDateInterval(contaLogada.getId(),dhCadastroDe,dhCadastroAte);
    }
    catch (Exception e)
    {
      resultado = "erro";
    }
    return resultado;
  }
  
	public String atualizar(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		if((!dhCadastroDe.equals("") && dhCadastroAte.equals("")) || (!dhCadastroAte.equals("") && dhCadastroDe.equals("")) ){
//			context.addMessage(null,new FacesMessage("Para realizar uma busca por periodo, Eh necessario preencher as duas datas!"));
			return null;
		}else if(!dhCadastroDe.equals("") && !dhCadastroAte.equals("")){
			Date de = new Date(dhCadastroDe);
			Date ate = new Date(dhCadastroAte);
			
			if(de.getTime() <= ate.getTime())
				this.dhCadastroDe = dhCadastroDe;
			else{
//				context.addMessage(null,new FacesMessage("A data do campo 'Ate:' nao pode ser menor que a data do campo 'De:'"));
				return null;
			}
		}
		
		return "entrada";
	}
}


