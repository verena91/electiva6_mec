package prueba.prueba.util;

import java.io.Serializable;
import java.math.BigInteger;

public class Concepto implements Serializable{

	private String concepto;
	private BigInteger monto;
	
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	
	public BigInteger getMonto() {
		return monto;
	}
	public void setMonto(BigInteger monto) {
		this.monto = monto;
	}
	public Concepto(String c, BigInteger m){
		this.concepto=c;
		this.monto=m;
	}
}
