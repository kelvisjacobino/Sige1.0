/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloSessionBean;

import ModeloEntidade.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Kelvis
 */
@Stateless
public class UsuarioSBean {

    @PersistenceContext(unitName = "SistemaIntegradoPU")
    EntityManager em;

    public void salvar(Usuario usuario) throws Exception {
        try {
            validarDados(usuario);
            em.merge(usuario);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    private void validarDados(Usuario usuario) throws Exception {
        validarUsuarioUnico(usuario);
        if (usuario.getSenha().length() < 8) {
            throw new Exception("Senha não poder ser menor do que 8 caracteres.");
        }
    }

    private void validarUsuarioUnico(Usuario usuarioValidar) throws Exception {
        try {
            Query consulta = em.createNamedQuery("Usuario.findByUserName");
            consulta.setParameter("userName", usuarioValidar.getUserName());
            Usuario usuarioBD = (Usuario) consulta.getSingleResult();
            if (usuarioBD != null) {
                if (!usuarioValidar.equals(usuarioBD)) {
                    throw new Exception("Usuario já existe.");
                }
            }
        } catch (NoResultException rex) {

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void excluir(Usuario usuario) throws Exception {
        try {
            em.remove(em.find(Usuario.class, usuario.getId()));
        } catch (Exception ex) {
            throw new Exception("Não foi possivel excluir o usuario.");
        }
    }

    public Usuario pesquisar(Long id) throws Exception {
        try {
            return em.find(Usuario.class, id);
        } catch (IllegalArgumentException ex) {
            throw new Exception("Valor pesquisado invalido.");
        } catch (Exception ex) {
            throw new Exception("Ocorreu um erro ao pesquisar o id do usuario.");
        }
    }

    public List<Usuario> pesquisar(String nome) {
        List<Usuario> listaUsuarios;
        Query consulta = em.createNamedQuery("Usuario.findByNome");
        consulta.setParameter("nome", nome.toUpperCase() + "%");
        listaUsuarios = consulta.getResultList();
        return listaUsuarios;
    }

    
}
