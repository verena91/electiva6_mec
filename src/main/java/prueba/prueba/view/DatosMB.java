package prueba.prueba.view;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.ticpy.tekoporu.stereotype.ViewController;

import prueba.prueba.business.FuncionarioBC;
import prueba.prueba.util.Concepto;
@ViewController
public class DatosMB {
	
	private List<Concepto> conceptos = new ArrayList<Concepto>();
	
	@Inject
	private FuncionarioBC funcionarioBC;

	/*public List<Object[]> getConceptos() {
		return conceptos;
	}

	public void setConceptos(List<Object[]> conceptos) {
		this.conceptos = conceptos;
	}*/
	
	

	@PostConstruct
	public void postconst(){
		List<Object[]> conceptos = this.funcionarioBC.getMaxConceptos();
		for (Object[] objects : conceptos) {
			this.conceptos.add(new Concepto((String)objects[1],(BigInteger) objects[0]));
		}
	}
	
	public List<Concepto> getConceptos() {
		return conceptos;
	}

	public void setConceptos(List<Concepto> conceptos) {
		this.conceptos = conceptos;
	}
		
}
