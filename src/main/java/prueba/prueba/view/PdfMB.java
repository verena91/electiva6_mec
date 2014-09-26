/*
 * TICPY Framework
 * Copyright (C) 2012 Plan Director TICs
 *
----------------------------------------------------------------------------
 * Originally developed by SERPRO
 * Demoiselle Framework
 * Copyright (C) 2010 SERPRO
 *
----------------------------------------------------------------------------
 * This file is part of TICPY Framework.
 *
 * TICPY Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 *
----------------------------------------------------------------------------
 * Este archivo es parte del Framework TICPY.
 *
 * El TICPY Framework es software libre; Usted puede redistribuirlo y/o
 * modificarlo bajo los términos de la GNU Lesser General Public Licence versión 3
 * de la Free Software Foundation.
 *
 * Este programa es distribuido con la esperanza que sea de utilidad,
 * pero sin NINGUNA GARANTÍA; sin una garantía implícita de ADECUACION a cualquier
 * MERCADO o APLICACION EN PARTICULAR. vea la GNU General Public Licence
 * más detalles.
 *
 * Usted deberá haber recibido una copia de la GNU Lesser General Public Licence versión 3
 * "LICENCA.txt", junto con este programa; en caso que no, acceda a <http://www.gnu.org/licenses/>
 * o escriba a la Free Software Foundation (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
package prueba.prueba.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.UploadedFile;
import org.ticpy.tekoporu.stereotype.ViewController;

import prueba.prueba.business.PdfBC;

@ViewController
public class PdfMB {

	@Inject
	private PdfBC pdfBC;

	private static final long serialVersionUID = 1L;

	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void upload() throws FileNotFoundException, IOException {
		if (file != null && file.getFileName() != null
				&& !file.getFileName().equals("")) {
			String extension = file.getFileName().substring(
					file.getFileName().length() - 4);
			if (file.getContents().length > 5000000) {
				FacesMessage msg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Error de tamaño:",
						" el archivo " + file.getFileName()
								+ " puede tener como máximo 5MB.");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
			if (extension != null && !extension.equals(".pdf")) {
				FacesMessage msg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Error de formato:",
						" el archivo " + file.getFileName()
								+ " debe tener extensión '.pdf'.");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
			String camino = "/opt/pdf/";
			String archivoPdf = file.getFileName().substring(0,
					file.getFileName().length() - 4)
					+ " " + (new Date()).toString() + ".pdf";
			FileOutputStream fos = new FileOutputStream(camino + archivoPdf);
			fos.write(file.getContents());
			fos.close();
			try {
				int resultado = this.pdfBC.consultarDatos(camino, archivoPdf);
				if (resultado == 1) {
					FacesMessage msg = new FacesMessage("Exito:",
							" El archivo " + file.getFileName()
									+ " ha sido procesado");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			} catch (Exception ex) {
				File file = new File(camino + archivoPdf);
				if (file.exists()) {
					file.delete();
				} else {
					System.err.println("No se pudo encontrar '" + file + "' ('"
							+ file.getAbsolutePath() + "')");
				}
			}
		}
	}

}
