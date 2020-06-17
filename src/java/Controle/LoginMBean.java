
package Controle;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import ControleUteis.UteisJsf;
import ModeloEntidade.Usuario;
import ModeloSessionBean.LogarSBean;

/**
 *
 * @author Kelvis
 */
@Named(value = "loginMBean")
@ViewScoped
public class LoginMBean implements Serializable {

    private String userName = "";
    private String senha = "";

    private Usuario usuario = new Usuario();

    @EJB
    private LogarSBean logarSBean;

    public LoginMBean() {

    }

    public String logar() {
        try {
            this.usuario = logarSBean.logar(this.userName, this.senha);
            if (this.usuario != null) {
                UteisJsf.setObjectSession("usuarioLogado", this.usuario);
                return "home";
            }
            UteisJsf.addMensagemInfo("Usuario ou senha invalidos.", "");
        } catch (Exception ex) {
            UteisJsf.addMensagemInfo(ex.getMessage(), "");
        }
        this.usuario = new Usuario();
        this.userName = "";
        this.senha = "";
        return null;
    }

    public String sair() {
        UteisJsf.removeObjectSession("usuarioLogado");
        return "cadUsuario?faces-redirect=true";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
