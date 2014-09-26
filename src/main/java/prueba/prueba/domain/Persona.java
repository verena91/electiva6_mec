package prueba.prueba.domain;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Persona implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String anho;
	@Column
	private String mes;
	@Column
	private String cedula;
	@Column
	private String nombre;
	@Column
	private String categoriaPago;
	@Column
	private String objetoPago;
	@Column
	private String tipoFuncionario;
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
	
	public String getObjetoPago() {
		return objetoPago;
	}
	public void setObjetoPago(String objetoPago) {
		this.objetoPago = objetoPago;
	}
	public String getAnho() {
		return anho;
	}
	public void setAnho(String anho) {
		this.anho = anho;
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

	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCategoriaPago() {
		return categoriaPago;
	}
	public void setCategoriaPago(String categoriaPago) {
		this.categoriaPago = categoriaPago;
	}
	public String getTipoFuncionario() {
		return tipoFuncionario;
	}
	public void setTipoFuncionario(String tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}
	public String getAntiguedad() {
		return antiguedad;
	}
	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getSalario() {
		return salario;
	}
	public void setSalario(Long salario) {
		this.salario = salario;
	}

	
}
