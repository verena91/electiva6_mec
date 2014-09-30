package pdfreader;

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

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.FilteredTextRenderListener;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.RegionTextRenderFilter;
import com.itextpdf.text.pdf.parser.RenderFilter;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class ExtractPageContentArea {

	/** The original PDF that will be parsed. */
	public static final String PREFACE = "agosto.pdf";
	/** The resulting text file. */
	public static final String RESULT = "agosto.txt";
	//public static final Rectangle LEGAL_LANDSCAPE;
	public static final String PATH = "/home/natalia/Documentos/opendata/electiva6_mec/doc/";
    /**
     * Parses a specific area of a PDF to a plain text file.
     * @param pdf the original PDF
     * @param txt the resulting text
     * @throws IOException
    */
    /*public void parsePdf(String pdf, String txt) throws IOException {
        PdfReader reader = new PdfReader(pdf);
        PrintWriter out = new PrintWriter(new FileOutputStream(txt));
        TextExtractionStrategy strategy;
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i, new MyStrategy());
            out.println(PdfTextExtractor.getTextFromPage(reader, i, strategy));
        }
        out.flush();
        out.close();
        reader.close();
    } */
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

	/**
	 * Main method.
	 * 
	 * @param args
	 *            no arguments needed
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException,
			DocumentException {
		System.out.println("INICIO.... ");
		new ExtractPageContentArea().parsePdf(PATH + PREFACE, PATH + RESULT);
		System.out.println("PARSEAR...");
		parsear();
		System.out.println("-FIN-");
		//new ExtractPageContentArea().parsePdf(PATH + "julio.pdf", PATH + "julio.txt");
		//parsear();
		//new ExtractPageContentArea().parsePdf(PATH + "agosto.pdf", PATH + "agosto.txt");
		//parsear();
		
	}

	private static void parsear() {
		try {
			List<Persona> funcionarios = new ArrayList<Persona>();
			Persona aux = new Persona();
			InputStreamReader entrada = new InputStreamReader(
					new FileInputStream(PATH + RESULT), "UTF-8");
			BufferedReader buffer = new BufferedReader(entrada);
			String strLinea;
			PrintWriter out = new PrintWriter(new FileOutputStream(PATH
					+ "agosto1.txt"));
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
			int i = 1;
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
					new FileInputStream(PATH + "agosto1.txt"), "UTF-8");
			BufferedReader buffer2 = new BufferedReader(entrada2);
			String[] valores = null;
			strLinea = null;
			String[] objPago = null;
			String cargo="";
			String dependencia = "";
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
				
				if(valores[8].compareTo("")!=0){
					dependencia = valores[8];
				}
				aux.setDependencia(dependencia);
				if(valores[9].compareTo("")!=0){
					cargo = valores[9];
				}
				aux.setCargo(cargo);
				
				aux.setRubro(valores[10]);
				aux.setMontoRubro(valores[11].replaceAll("\\.",""));
				aux.setCantidad(valores[12]);
				aux.setSalario(valores[13].replaceAll("\\.",""));
				funcionarios.add(aux);
			}
			entrada2.close();
			writeToCSV(funcionarios, cabecera);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static final String CSV_SEPARATOR = ",";
	private static final String CSV_DELIMITATOR = "\"";

	private static void writeToCSV(List<Persona> list, String cabecera) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(PATH + "funcionarios3.csv"), "UTF-8"));
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
		} catch (UnsupportedEncodingException e) {
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}
}
