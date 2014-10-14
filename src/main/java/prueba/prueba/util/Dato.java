package prueba.prueba.util;

public class Dato {
	private String campo;
	private String descripcion;
	
	public Dato(String campo, String descripcion){
		this.campo = campo;
		this.descripcion = descripcion;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
