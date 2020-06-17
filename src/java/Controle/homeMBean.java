
package Controle;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import ControleUteis.UteisJsf;
import ModeloEntidade.Usuario;

/**
 *
 * @author Kelvis
 */
@Named(value = "homeMBean")
@RequestScoped
public class homeMBean implements Serializable {
 
    private Usuario usuarioLogado;
       

    public homeMBean() {
        
    }
    
    @PostConstruct
    public void init() {
         usuarioLogado = (Usuario)UteisJsf.getObjectSession("usuarioLogado");
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
    
}
