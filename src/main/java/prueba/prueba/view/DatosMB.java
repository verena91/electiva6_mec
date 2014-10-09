package prueba.prueba.view;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
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
	
	private StreamedContent fileObjetoGasto;
	private StreamedContent fileConcepto;
	private StreamedContent fileConceptoEstadisticas;
	private StreamedContent fileCargo;
	private StreamedContent fileDependencia;
	private StreamedContent fileRubro;
    
    public DatosMB() {       
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/csv/objeto_gasto.csv");
        fileObjetoGasto = new DefaultStreamedContent(stream, "text/csv", "mec_objeto_gasto.csv");
        InputStream stream1 = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/csv/conceptos.csv");
        fileConcepto = new DefaultStreamedContent(stream1, "text/csv", "mec_conceptos.csv");
        InputStream stream2 = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/csv/concepto_estadisticas.csv");
        fileConceptoEstadisticas = new DefaultStreamedContent(stream2, "text/csv", "mec_conceptos_estadisticas.csv");
        InputStream stream3 = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/csv/cargo.csv");
        fileCargo = new DefaultStreamedContent(stream3, "text/csv", "mec_cargos.csv");
        InputStream stream4 = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/csv/dependencia.csv");
        fileDependencia = new DefaultStreamedContent(stream4, "text/csv", "mec_dependencias.csv");
        InputStream stream5 = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/csv/rubro.csv");
        fileRubro = new DefaultStreamedContent(stream5, "text/csv", "mec_rubros.csv");
    }
 
    
    
    public StreamedContent getFileObjetoGasto() {
		return fileObjetoGasto;
	}

	public void setFileObjetoGasto(StreamedContent fileObjetoGasto) {
		this.fileObjetoGasto = fileObjetoGasto;
	}

	public StreamedContent getFileConcepto() {
		return fileConcepto;
	}

	public void setFileConcepto(StreamedContent fileConcepto) {
		this.fileConcepto = fileConcepto;
	}

	public StreamedContent getFileConceptoEstadisticas() {
		return fileConceptoEstadisticas;
	}

	public void setFileConceptoEstadisticas(StreamedContent fileConceptoEstadisticas) {
		this.fileConceptoEstadisticas = fileConceptoEstadisticas;
	}

	public StreamedContent getFileCargo() {
		return fileCargo;
	}

	public void setFileCargo(StreamedContent fileCargo) {
		this.fileCargo = fileCargo;
	}

	public StreamedContent getFileDependencia() {
		return fileDependencia;
	}

	public void setFileDependencia(StreamedContent fileDependencia) {
		this.fileDependencia = fileDependencia;
	}

	public StreamedContent getFileRubro() {
		return fileRubro;
	}

	public void setFileRubro(StreamedContent fileRubro) {
		this.fileRubro = fileRubro;
	}

	public StreamedContent getFile() {
        return fileObjetoGasto;
    }
		
}
