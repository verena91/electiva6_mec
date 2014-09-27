package prueba.prueba.business;

import java.util.List;

import javax.inject.Inject;

import prueba.prueba.domain.Funcionario;
import prueba.prueba.persistence.FuncionarioDAO;

public class FuncionarioBC {

	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	
	public List<Funcionario> getResultByNombre(String nombre){
		return this.funcionarioDAO.getResultByNombre(nombre);
	}
	
	public List<Funcionario> getResultByCI(String ci){
		return this.funcionarioDAO.getResultByCI(ci);
	}
	
	public List<Funcionario> getResultByDependencia(String dependencia){
		return this.funcionarioDAO.getResultByDependencia(dependencia);
	}
	
}
