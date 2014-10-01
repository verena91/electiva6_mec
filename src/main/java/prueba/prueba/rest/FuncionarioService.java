package prueba.prueba.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import prueba.prueba.domain.Funcionario;
import prueba.prueba.persistence.FuncionarioDAO;

@Path("/funcionario")
public class FuncionarioService {

	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	@GET
	@Path("/nombre/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Funcionario> getFuncionario(@QueryParam("nombre")String nombre, @QueryParam("mes")String mes){
		List<Funcionario> lista = funcionarioDAO.getResultByNombre(nombre.toUpperCase(), mes.toLowerCase());
		return lista;
	}
	
	@GET
	@Path("/nombre/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Funcionario> getFuncionario(@QueryParam("nombre")String nombre){
		List<Funcionario> lista = funcionarioDAO.getResultByNombre(nombre.toUpperCase(), null);
		return lista;
	}
	
	@GET
	@Path("/cedula/{ci}/mes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Funcionario> getFuncionarioByCI(@PathParam("ci")String ci, @QueryParam("mes")String mes){
		List<Funcionario> lista = funcionarioDAO.getResultByCI(ci, mes.toLowerCase());
		return lista;
	}
	
	@GET
	@Path("/cedula/{ci}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Funcionario> getFuncionarioByCI(@PathParam("ci")String ci){
		List<Funcionario> lista = funcionarioDAO.getResultByCI(ci, null);
		return lista;
	}
	
	@GET
	@Path("/mes/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Funcionario> getFuncionariosPorMes(@QueryParam("mes")String mes){
		List<Funcionario> lista = funcionarioDAO.getResultByMes(mes.toLowerCase());
		return lista;
	}
	
	@GET
	@Path("/salario/mes/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]> getFuncionariosSalariosPorMes(@QueryParam("mes")String mes){
		List<Object[]> lista = funcionarioDAO.getResultByMesTotal(mes.toLowerCase());
		return lista;
	}
	
	/*@GET
	@Path("/dependencia/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Funcionario> getFuncionariosByDependencia(@QueryParam("dependencia")String dependencia){
		List<Funcionario> lista = funcionarioDAO.getResultByDependencia(dependencia.toUpperCase());
		return lista;
	}*/
	
	
	
}
