/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloSessionBean;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ModeloEntidade.Cidade;
import ModeloEntidade.Cliente;

/**
 *
 * @author Kelvis
 */
@Stateless
public class ClienteSBean {

    @PersistenceContext(unitName = "SistemaIntegradoPU")
    private EntityManager em;

    public void salvar(Cliente cliente) throws Exception {
        try {
            em.merge(cliente);
        } catch (Exception ex) {
            throw new Exception("Erro ao Salvar o cliente. " + ex.getMessage());
        }
    }

    public void excluir(Cliente cliente) throws Exception {
        try {
            em.remove(em.find(Cliente.class, cliente.getId()));
        } catch (Exception ex) {
            throw new Exception("Erro ao Excluir o cliente. " + ex.getMessage());
        }
    }

    public Cliente pesquisar(Long id) throws Exception {
        try {
            return em.find(Cliente.class, id);
        } catch (Exception ex) {
            throw new Exception("Erro ao pesquisar o cliente por Id. " + ex.getMessage());
        }
    }

    public List<Cliente> pesquisar(String nome) throws Exception {
        try {
            Query consulta = em.createNamedQuery("Cliente.findByNome");
            consulta.setParameter("nome", nome.toUpperCase() + "%");
            return consulta.getResultList();
        } catch (Exception ex) {
            throw new Exception("Erro ao pesquisar o cliente por nome. " + ex.getMessage());
        }
    }

    public List<Cliente> pesquisar(Cidade cidade, String nome) throws Exception {
        try {
            Query consulta = em.createNamedQuery("Cliente.findByCidadeNome");
            consulta.setParameter("cidade", cidade);
            consulta.setParameter("nome", nome.toUpperCase() + "%");
            return consulta.getResultList();
        } catch (Exception ex) {
            throw new Exception("Erro ao pesquisar o cliente por Cidade e Nome. " + ex.getMessage());
        }
    }
    
    public List<Cliente> pesquisarPorCidade(Cidade cidade) throws Exception {
        try {
            Query consulta = em.createNamedQuery("Cliente.pesquisarPorCidade");
            consulta.setParameter("cidade", cidade);            
            return consulta.getResultList();
        } catch (Exception ex) {
            throw new Exception("Erro ao pesquisar o cliente por Cidade e Nome. " + ex.getMessage());
        }
    }

    public Cliente pesquisarPorCpf(String cpf) throws Exception {
        try {
            Query consulta = em.createNamedQuery("Cliente.findByCpfCnpj");
            consulta.setParameter("nome", cpf);
            return (Cliente) consulta.getSingleResult();
        } catch (Exception ex) {
            throw new Exception("Erro ao pesquisar o cliente por CPF/Cnpj. " + ex.getMessage());
        }
    }
     
}
