package prueba.prueba.domain;


import java.io.Serializable;
import java.io.StringWriter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Funcionario implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String mes;
	
	@Column
	private String anho;
	
	@Column
	private String nroDocumento;
	
	@Column
	private String nombreCompleto;
	
	@Column
	private String objetoGasto;
	
	@Column
	private String nombreObjetoGasto;
	
	@Column
	private String estado;
	
	@Column
	private String antiguedad;
	
	@Column
	private String concepto;
	
	@Column
	private String dependencia;
	
	@Column
	private String cargo;
	
	@Column
	private String rubro;
	
	@Column
	private Long montoRubro;
	
	@Column
	private Long cantidad;
	
	@Column
	private Long salario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAnho() {
		return anho;
	}

	public void setAnho(String anho) {
		this.anho = anho;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getObjetoGasto() {
		return objetoGasto;
	}

	public void setObjetoGasto(String objetoGasto) {
		this.objetoGasto = objetoGasto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getRubro() {
		return rubro;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	public Long getMontoRubro() {
		return montoRubro;
	}

	public void setMontoRubro(Long montoRubro) {
		this.montoRubro = montoRubro;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Long getSalario() {
		return salario;
	}

	public void setSalario(Long salario) {
		this.salario = salario;
	}

	public String getNombreObjetoGasto() {
		return nombreObjetoGasto;
	}

	public void setNombreObjetoGasto(String nombreObjetoGasto) {
		this.nombreObjetoGasto = nombreObjetoGasto;
	}
	/*public static Funcionario valueOf(String json) {
		ObjectMapper mapper = new ObjectMapper();
		Funcionario atributo = null;

		try {
			atributo = mapper.readValue(json, Funcionario.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return atributo;
	}

	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter json = new StringWriter();

		try {
			mapper.writeValue(json, this);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json.toString();
	}
*/
	
}
