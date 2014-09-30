package prueba.prueba.business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.ticpy.tekoporu.stereotype.BusinessController;

import pdfreader.MyStrategy;
import pdfreader.Persona;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.FilteredTextRenderListener;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.RegionTextRenderFilter;
import com.itextpdf.text.pdf.parser.RenderFilter;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

@BusinessController
public class PdfBC{
	
private static final long serialVersionUID = 1L;
	
	@Inject
	private Logger logger;
	/** The original PDF that will be parsed. */
	public static String RESULT = "aux.txt";
	public static String PATH;
	private static final String CSV_SEPARATOR = ",";
	private static final String CSV_DELIMITATOR = "\"";
	
	public int consultarDatos(String path,String pdf) throws IOException {
		PATH = path;
		parsePdf(PATH+pdf, PATH+RESULT);
		return parsear();
	}
    /**
     * Parses a specific area of a PDF to a plain text file.
     * @param pdf the original PDF
     * @param txt the resulting text
     * @throws IOException
    */
   
	public void parsePdf(String pdf, String txt) throws IOException {
		PdfReader reader = new PdfReader(pdf);
		PrintWriter out = new PrintWriter(new FileOutputStream(txt));
		Rectangle rect = new Rectangle(PageSize.LEDGER);
		RenderFilter filter = new RegionTextRenderFilter(rect);
		TextExtractionStrategy strategy;
		for (int i = 1; i <= reader.getNumberOfPages(); i++) {
			strategy = new FilteredTextRenderListener(new MyStrategy(), filter);
			out.println(PdfTextExtractor.getTextFromPage(reader, i, strategy));
		}
		out.flush();
		out.close();
		reader.close();
	}

	private static int parsear() {
		try {
			List<Persona> funcionarios = new ArrayList<Persona>();
			Persona aux = new Persona();
			InputStreamReader entrada = new InputStreamReader(
					new FileInputStream(PATH + RESULT), "UTF-8");
			BufferedReader buffer = new BufferedReader(entrada);
			String strLinea;
			PrintWriter out = new PrintWriter(new FileOutputStream(PATH
					+ "aux1.txt"));
			String anterior = buffer.readLine();
			String cabecera = anterior;
			strLinea = buffer.readLine();
			String mes = strLinea.substring(0, 5);
			if (strLinea.length() != 0) {
				if (strLinea.length() > 4) {
					if (!(strLinea.substring(0, 5).equals(mes))) {
						anterior = anterior + strLinea.replaceAll(" ;", " ");
					} else {
						if (!anterior
								.contains("Mes;Año;N° Documento;Nombre completo;Objeto de Gasto;Estado;Antiguedad;Concepto;Dependencia;Cargo;Rubro;Monto Rubro;Cantidad;Asignación;")) {
							String cadenaFinal = (anterior
									.replaceAll(" ;", " ")).replaceAll("\"",
									"'");
							out.println(cadenaFinal.substring(0,
									cadenaFinal.length() - 1));
						}
						anterior = strLinea;
					}
				} else {
					anterior = anterior + strLinea.replaceAll(" ;", " ");
				}
			}
			while ((strLinea = buffer.readLine()) != null) {
				if (strLinea.length() != 0) {
					if (strLinea.length() > 4) {
						if (!(strLinea.substring(0, 5).equals(mes)) && !(strLinea.substring(0, 3).equals("Mes"))) {
							anterior = anterior
									+ strLinea.replaceAll(" ;", " ");
						} else {
							if (!anterior
									.contains("Mes;Año;N° Documento;Nombre completo;Objeto de Gasto;Estado;Antiguedad;Concepto;Dependencia;Cargo;Rubro;Monto Rubro;Cantidad;Asignación;")) {
								String cadenaFinal = (anterior.replaceAll(" ;",
										" ")).replaceAll("\"", "'");
								if(cadenaFinal.contains(";;("))
									cadenaFinal = cadenaFinal.replaceAll(";;\\(","(");
								if(cadenaFinal.contains(";("))
									cadenaFinal = cadenaFinal.replaceAll(";\\(","(");
								if(cadenaFinal.contains("-;"))
									cadenaFinal = cadenaFinal.replaceAll("-;","-");
								if(cadenaFinal.contains("/;"))
									cadenaFinal = cadenaFinal.replaceAll("/;","/");
								out.println(cadenaFinal.substring(0,
										cadenaFinal.length() - 1));
								
							}
							anterior = strLinea;
						}
					} else {
						anterior = anterior + strLinea.replaceAll(" ;", " ");
					}
				}
			}
			String cadenaFinal = (anterior.replaceAll(" ;",
					" ")).replaceAll("\"", "'");
			if(cadenaFinal.contains(";("))
				cadenaFinal = cadenaFinal.replaceAll(";\\(","(");
			if(cadenaFinal.contains("-;"))
				cadenaFinal = cadenaFinal.replaceAll("-;","-");
			if(cadenaFinal.contains("/;"))
				cadenaFinal = cadenaFinal.replaceAll("/;","/");
			out.println(cadenaFinal.substring(0,
					cadenaFinal.length() - 1));
			out.flush();
			out.close();
			entrada.close();
			InputStreamReader entrada2 = new InputStreamReader(
					new FileInputStream(PATH + "aux1.txt"), "UTF-8");
			BufferedReader buffer2 = new BufferedReader(entrada2);
			String[] valores = null;
			strLinea = null;
			String[] objPago = null;
			String cargo = "";
			String dependencia="";
			while ((strLinea = buffer2.readLine()) != null) {
				aux = new Persona();
				valores = strLinea.split(";");
				aux.setMes(valores[0]);
				aux.setAnho(valores[1]);
				aux.setCedula(valores[2]);
				aux.setNombre(valores[3]);
				objPago = valores[4].split("\\(");
				aux.setCategoriaPago(objPago[0].substring(0,
						objPago[0].length() - 1));
				aux.setObjetoPago(objPago[1].substring(0,
						objPago[1].length() - 1));
				aux.setTipoFuncionario(valores[5]);
				aux.setAntiguedad(valores[6]);
				aux.setConcepto(valores[7]);
				aux.setDependencia(valores[8]);
				aux.setCargo(valores[9]);
				aux.setRubro(valores[10]);
				aux.setMontoRubro(valores[11].replaceAll("\\.",""));
				aux.setCantidad(valores[12]);
				aux.setSalario(valores[13].replaceAll("\\.",""));
				funcionarios.add(aux);
			}
			entrada2.close();
			return writeToCSV(funcionarios, cabecera);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	private static int writeToCSV(List<Persona> list, String cabecera) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(PATH + "funcionarios.csv"), "UTF-8"));
			StringBuffer oneLine = new StringBuffer();
			String[] cabeceras = cabecera.split(";");
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(cabeceras[0]);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(cabeceras[1]);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(cabeceras[2]);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(cabeceras[3]);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(cabeceras[4]);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append("Nombre " + cabeceras[4]);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(cabeceras[5]);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(cabeceras[6]);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(cabeceras[7]);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(cabeceras[8]);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(cabeceras[9]);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(cabeceras[10]);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(cabeceras[11]);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(cabeceras[12]);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(CSV_DELIMITATOR);
			oneLine.append(cabeceras[13]);
			oneLine.append(CSV_DELIMITATOR);
			bw.write(oneLine.toString());
			bw.newLine();
			for (Persona persona : list) {
				oneLine = new StringBuffer();
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(persona.getMes());
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(persona.getAnho());
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(persona.getCedula());
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(persona.getNombre());
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(persona.getObjetoPago());
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(persona.getCategoriaPago());
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(persona.getTipoFuncionario());
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(persona.getAntiguedad());
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(persona.getConcepto());
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(persona.getDependencia());
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(persona.getCargo());
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(persona.getRubro());
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(persona.getMontoRubro());
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(persona.getCantidad());
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(CSV_DELIMITATOR);
				oneLine.append(persona.getSalario());
				oneLine.append(CSV_DELIMITATOR);
				bw.write(oneLine.toString());
				bw.newLine();
			}
			bw.flush();
			bw.close();
			return 1;
		} catch (UnsupportedEncodingException e) {
			return 0;
		} catch (FileNotFoundException e) {
			return 0;
		} catch (IOException e) {
			return 0;
		}
	}
}
