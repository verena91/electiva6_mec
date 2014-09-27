package prueba.prueba.util;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/prueba")
public class Rest {
	//private PersonaDAO personaDao;

	@GET
	@Path("/")
	// @Produces(MediaType.APPLICATION_JSON)
	public String /* Funcionario getAllPersonas() */getHola() {
		return "Hola";
		// return personaDao.find();
	}

}
