package com.amk.resource;
/**
 * @author GTArmenta
 */
 
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.naming.NamingException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXB;

import com.amk.entity.Cliente;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
 
@Path("/lista/clientes")
public class ClienteService {
	private final static String XML = "xml";
	private final static String JSON = "json";
	ClienteDao service = new ClienteDao();
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getListaClientes(@HeaderParam("media-type") String format) 
			throws SQLException, NamingException {
		List<Cliente> lista = new ArrayList<Cliente>();
		lista = service.getListaCliente();
		String entity = "";
		if(XML.equalsIgnoreCase(format)){
			XStream xstream = new XStream();
		    xstream.addImplicitCollection(Cliente.class, "Clientes");
			entity = xstream.toXML(lista);
		}else if(JSON.equalsIgnoreCase(format)){
			Gson gson = new Gson();
			entity = gson.toJson(lista);
		}

		System.out.println("Media-Type: "+format);
	    System.out.println("Entity: "+entity);
		Response response = 
				Response.ok(entity,"xml".equalsIgnoreCase(format)?
						MediaType.APPLICATION_XML:
						MediaType.APPLICATION_JSON).build();
		return response;
	}
 
	@Path("{id}")
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getCliente(@PathParam("id") int idCliente, @HeaderParam("media-type") String format) 
			throws SQLException, NamingException {
			Cliente cte = new Cliente();
			cte = service.getCliente(idCliente);
			
			String entity = "";
			if(XML.equalsIgnoreCase(format)){
				StringWriter sw = new StringWriter();
				JAXB.marshal(cte, sw);
				entity = sw.toString();
			}else if(JSON.equalsIgnoreCase(format)){
				Gson gson = new Gson();
				entity = gson.toJson(cte);
			}

			System.out.println("Media-Type: "+format);
		    System.out.println("Entity: "+entity);
			Response response = 
					Response.ok(entity,"xml".equalsIgnoreCase(format)?
							MediaType.APPLICATION_XML:
							MediaType.APPLICATION_JSON).build();
			return response;
	}
	
	@Path("{id}")
	@DELETE
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response deleteCliente(@PathParam("id") int idCliente, @HeaderParam("media-type") String format) 
			throws SQLException, NamingException {
			
			service.deleteCliente(idCliente);
			
			ClienteRespuesta res = new ClienteRespuesta();
			res.setStatus("200");
			res.setMessage("Registro eliminado correctamente, idCliente "+idCliente);
			
			String entity = "";
			if(XML.equalsIgnoreCase(format)){
				StringWriter sw = new StringWriter();
				JAXB.marshal(res, sw);
				entity = sw.toString();
			}else if(JSON.equalsIgnoreCase(format)){
				Gson gson = new Gson();
				entity = gson.toJson(res);
			}

			System.out.println("Media-Type: "+format);
		    System.out.println("Entity: "+entity);
			Response response = 
					Response.ok(entity,"xml".equalsIgnoreCase(format)?
							MediaType.APPLICATION_XML:
							MediaType.APPLICATION_JSON).build();
			return response;
	}
	
	@Path("add")
	@PUT
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response addCliente(@HeaderParam("media-type") String format) 
			throws SQLException, NamingException {
			Cliente cte = new Cliente();
			cte.setNombre("Nombre_"+UUID.randomUUID().toString());
			cte.setApellido("Apellido_"+UUID.randomUUID().toString());
			cte.setEmail("gtrejo.armenta@gmail.com");
			cte.setFechaNacimiento(new Date());
			cte.setSexo("Masculino");
			cte.setTelefono("5500000045");
			int idCliente = service.insertCliente(cte);;
			
			cte.setId(idCliente);
			String entity = "";
			if(XML.equalsIgnoreCase(format)){
				StringWriter sw = new StringWriter();
				JAXB.marshal(cte, sw);
				entity = sw.toString();
			}else if(JSON.equalsIgnoreCase(format)){
				Gson gson = new Gson();
				entity = gson.toJson(cte);
			}

			System.out.println("Media-Type: "+format);
		    System.out.println("Entity: "+entity);
		    System.out.println("IdCliente Generado: "+idCliente);
		    
			Response response = 
					Response.ok(entity,"xml".equalsIgnoreCase(format)?
							MediaType.APPLICATION_XML:
							MediaType.APPLICATION_JSON).build();
			return response;
	}

}