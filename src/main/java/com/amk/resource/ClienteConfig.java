package com.amk.resource;
/**
 * @author GTArmenta
 */

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;

import com.amk.entity.Cliente;

public abstract class ClienteConfig<T> {

  protected abstract List<Cliente> getListaClienteQuery() throws SQLException, NamingException;
  protected abstract Cliente getClienteQuery(int id) throws NamingException;
  protected abstract int createQuery(T t) throws SQLException, NamingException;
  protected abstract void deleteQuery(int id) throws SQLException, NamingException;
  protected abstract void updateQuery(T t, int id) throws SQLException, NamingException;

  protected static EntityManager getEntityManager() throws NamingException {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("amk");
    return emf.createEntityManager();
  }

  @GET
  public List<Cliente> getListaCliente() throws SQLException, NamingException {
    List<Cliente> clientes = getListaClienteQuery();
      return clientes;
  }

  @GET
  @Path("{id}")
  public Cliente getCliente(@PathParam("id") int id) throws NamingException {
    Cliente ct = getClienteQuery(id);
      return ct;
  }

  @POST
  public int insertCliente(T t) throws NamingException, SQLException {
    return createQuery(t);
  }

  @DELETE
  @Path("{id}")
  public void deleteCliente(@PathParam("id") int id) throws SQLException, NamingException {
    deleteQuery(id);
  }

  @PUT
  @Path("{id}")
  public void updateCliente(T t, @PathParam("id") int id) throws SQLException, NamingException {
    updateQuery(t,id);
  }
}