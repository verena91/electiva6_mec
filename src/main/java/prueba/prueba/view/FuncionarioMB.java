package prueba.prueba.view;

import java.util.List;

import javax.inject.Inject;

import org.ticpy.tekoporu.stereotype.ViewController;

import prueba.prueba.business.FuncionarioBC;
import prueba.prueba.domain.Funcionario;

@ViewController
public class FuncionarioMB {

	private List<Funcionario> resultados;

	@Inject
	private FuncionarioBC funcionarioBC;

	private String nombre;

	private String ci;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public List<Funcionario> getResultados() {
		return resultados;
	}

	public void setResultados(List<Funcionario> resultados) {
		this.resultados = resultados;
	}

}
