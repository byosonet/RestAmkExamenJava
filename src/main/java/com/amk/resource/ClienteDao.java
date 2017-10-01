package com.amk.resource;
/**
 * @author GTArmenta
 */

import com.amk.entity.Cliente;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/event")
@Produces("application/json")
public class ClienteDao extends ClienteConfig<Cliente> {
	
  private EntityManager em;
  private List<Cliente> listaClientes;
  private Cliente cliente;

  @SuppressWarnings("unchecked")
  @Override
  protected List<Cliente> getListaClienteQuery() throws NamingException {
    em = getEntityManager();
    em.getTransaction().begin();
    listaClientes = em.createQuery("SELECT e FROM  Cliente e").getResultList();
    em.getTransaction().commit();
    em.close();
    return listaClientes;
  }

  @Override
  protected Cliente getClienteQuery(int id) throws NamingException {
    em = getEntityManager();
    em.getTransaction().begin();
    cliente = (Cliente) em.find(Cliente.class, id);
    em.getTransaction().commit();
    em.close();
    return cliente;
  }

  @Override
  protected int createQuery(Cliente t) throws SQLException, NamingException {
    em = getEntityManager();
    em.getTransaction().begin();
    em.persist(t);
    em.getTransaction().commit();
    em.close();
    return t.getId();
  }

  @Override
  protected void deleteQuery(int id) throws SQLException, NamingException {
	Cliente cte = new Cliente();
    em = getEntityManager();
    em.getTransaction().begin();
    cte = em.find(Cliente.class, id);
    em.remove(cte);
    em.getTransaction().commit();
    em.close();
  }

  @Override
  protected void updateQuery(Cliente t, int id) throws SQLException, NamingException {
	Cliente cte = new Cliente();
    em = getEntityManager();
    em.getTransaction().begin();
    cte = em.find(Cliente.class, id);
    
    em.persist(cte);
    em.getTransaction().commit();
    em.close();
  }
}