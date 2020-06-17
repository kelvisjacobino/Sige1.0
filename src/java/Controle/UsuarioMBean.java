
package Controle;

import ModeloEntidade.Usuario;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import ControleUteis.UteisJsf;
import ModeloSessionBean.UsuarioSBean;

/**
 *
 * @author Kelvis
 */
@Named(value = "usuarioMBean")
@ViewScoped
public class UsuarioMBean implements Serializable {

    @EJB
    private UsuarioSBean usuarioSBean;

    private Usuario usuario;

    private List<Usuario> listaUsuario;

    private String valorPesquisar;

    public UsuarioMBean() {

    }
    
    

    @PostConstruct
    public void init() {
        this.usuario = new Usuario();
        this.listaUsuario = new ArrayList<>();
    }

    public String botaoNovo() {
        this.usuario = new Usuario();
        return "cadUsuario?faces-redirect=true";
    }

    public void botaoSalvar() {
        try {
            usuarioSBean.salvar(usuario);
            UteisJsf.addMensagemInfo("INFO: - ", "Usuário Salvo com sucesso.");
            usuario = new Usuario();
        } catch (Exception ex) {
            UteisJsf.addMensagemErro("ATENÇÃO: - ", ex.getMessage());           
        }                
    }

    public void botaoPesquisar() {
        listaUsuario = usuarioSBean.pesquisar(valorPesquisar);
    }

    public void botaoExcluir() {
        try {
            usuarioSBean.excluir(usuario);
            this.listaUsuario.remove(usuario);
            UteisJsf.addMensagemInfo("INFO: - ", "Usuário Excluído com sucesso.");
        } catch (Exception ex) {
            UteisJsf.addMensagemErro("ATENÇÃO: - ", ex.getMessage());
        }
    }

    public String botaoEditar() {
        return "cadUsuario?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public String getValorPesquisar() {
        return valorPesquisar;
    }

    public void setValorPesquisar(String valorPesquisar) {
        this.valorPesquisar = valorPesquisar;
    }

}
