package prueba.prueba.business;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;

import prueba.prueba.domain.Funcionario;
import prueba.prueba.persistence.FuncionarioDAO;

public class FuncionarioBC {

	@Inject
	private FuncionarioDAO funcionarioDAO;
	
		
	public List<Funcionario> getResultByDependencia(String dependencia){
		return this.funcionarioDAO.getResultByDependencia(dependencia);
	}
	
	public List<Object[]> getMaxConceptos(){
		return this.funcionarioDAO.getMaxConceptos();
	}
	
	public List<Object> getGastosPorMes(){
		return this.funcionarioDAO.getGastosPorMes();
	}
	
	public List<Object> getGastosPorConcepto(){
		return this.funcionarioDAO.getGastosPorConcepto();
	}
	
	public List<Object> getPorConceptoMes(String mes){
		return this.funcionarioDAO.getPorConceptoMes(mes);
	}
	public Object getTotal(String mes){
		return this.funcionarioDAO.getTotal(mes);

	}
	
	public Object getTotalFuncionarios(String mes){
		return this.funcionarioDAO.getTotalFuncionarios(mes);

	}
	
	public List<Object> getMejoresPagados(){
		return this.funcionarioDAO.getMejoresPagados();

	}
}
