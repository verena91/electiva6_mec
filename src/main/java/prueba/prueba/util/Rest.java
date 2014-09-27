package prueba.prueba.util;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import prueba.prueba.domain.Funcionario;
import prueba.prueba.persistence.PersonaDAO;

@Path("/prueba")
public class Rest {
	private PersonaDAO personaDao;

	@GET
	@Path("/")
	// @Produces(MediaType.APPLICATION_JSON)
	public String /* Funcionario getAllPersonas() */getHola() {
		return "Hola";
		// return personaDao.find();
	}

}
