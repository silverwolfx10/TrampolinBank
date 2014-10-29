package br.com.trampolinbank.manegedbean;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class SwitcherManagedBean implements Serializable{
	
	private static final long serialVersionUID = 4420995111496888033L;  
	private Locale currentLocale = new Locale("pt", "BR"); 
	
	public String englishLocale() {    
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();    
        currentLocale = Locale.US;    
        viewRoot.setLocale(currentLocale);  
        return "";
    }    
      
    public String portugueseLocale() {    
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();    
        currentLocale = new Locale("pt", "BR");    
        viewRoot.setLocale(currentLocale); 
        return "";
    }    
      
    public Locale getCurrentLocale() {    
        return currentLocale;    
    }  
    	
}