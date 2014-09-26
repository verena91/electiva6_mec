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

import py.org.vue.azucar_etl.business.AzucarBC;

@ViewController
public class AzucarMB {
	
	@Inject
	private AzucarBC azucarBC;


	private static final long serialVersionUID = 1L;

	private UploadedFile file;  
	  
    public UploadedFile getFile() {  
        return file;  
    }  
  
    public void setFile(UploadedFile file) {  
        this.file = file;  
    }  
  
    public void upload() throws FileNotFoundException, IOException {    
        if(file != null && file.getFileName() != null && !file.getFileName().equals("")) {
        	String extension = file.getFileName().substring(file.getFileName().length()-4);
        	if(file.getContents().length > 30000000){
        		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error de tamaño:" , " el archivo "+file.getFileName() + " puede tener como máximo 30MB.");  
                FacesContext.getCurrentInstance().addMessage(null, msg); 
                return;
        	}
        	if(extension != null && !extension.equals(".xls")){
        		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error de formato:" , " el archivo "+file.getFileName() + " debe tener extensión '.xls'.");  
                FacesContext.getCurrentInstance().addMessage(null, msg); 
                return;
        	}
            String camino = "/home/cbaez/Escritorio/"+file.getFileName().substring(0,file.getFileName().length()-4)+" "+(new Date()).toString()+".xls";
            //String camino = "/home/jruax/Escritorio/"+file.getFileName().substring(0,file.getFileName().length()-4)+" "+(new Date()).toString()+".xls";
            //String camino = "/opt/archivos/azucar_excel/"+file.getFileName().substring(0,file.getFileName().length()-4)+" "+(new Date()).toString()+".xls";
        	FileOutputStream fos = new FileOutputStream(camino);  
            fos.write(file.getContents());  
            fos.close(); 
            try{
            	int[] insertados = new int[2];
            	
            	insertados = this.azucarBC.consultarDatos(camino);
            	FacesMessage msg = new FacesMessage("Exito:", " Del archivo "+file.getFileName() + " han sido insertados "+ insertados[0] +" registros, "
            			+ "se encontraron "+insertados[1]+" duplicados.");  
                FacesContext.getCurrentInstance().addMessage(null, msg);  
            }catch (Exception ex){
            	File file = new File(camino);
            	if (file.exists()) {
            	    file.delete();
            	} else {
            	    System.err.println(
            	        "No se pudo encontrar '" + file + "' ('" + file.getAbsolutePath() + "')");
            	}
            	
            	if(ex.getMessage().equals("Error en nombre de columnas")){
            		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error de columnas:" , " las columnas no contienen el formato establecido");  
                    FacesContext.getCurrentInstance().addMessage(null, msg); 
                    return;
            	}
            	if(ex.getMessage().equals("Error de contenido")){
            		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error de contenido:" , " las celdas no contienen los valores correctos");  
                    FacesContext.getCurrentInstance().addMessage(null, msg); 
                    return;
            	}
            	if(ex.getMessage().equals("Error de contenido 1")){
            		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error de contenido:" , " las celdas contienen valores vacios");  
                    FacesContext.getCurrentInstance().addMessage(null, msg); 
                    return;
            	}
            	if(ex.getMessage().equals("Error de contenido 2")){
            		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error de contenido:" , " verificar columna anhomes debe ser numérico y de longitud 6");  
                    FacesContext.getCurrentInstance().addMessage(null, msg); 
                    return;
            	}
            	if(ex.getMessage().equals("Error de contenido 3")){
            		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error de contenido:" , " verificar columna ruc debe ser numérico");  
                    FacesContext.getCurrentInstance().addMessage(null, msg); 
                    return;
            	}
            	if(ex.getMessage().equals("Error de contenido 4")){
            		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error de contenido:" , " verificar columna cod_producto debe ser numérico entre 1 y 17");  
                    FacesContext.getCurrentInstance().addMessage(null, msg); 
                    return;
            	}
            	if(ex.getMessage().equals("Error de contenido 5")){
            		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error de contenido:" , " verificar columna cantidad bolsas debe ser numérico");  
                    FacesContext.getCurrentInstance().addMessage(null, msg); 
                    return;
            	}
            	if(ex.getMessage().equals("Error de contenido 6")){
            		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error de contenido:" , " verificar columna tipo de bolsa debe ser 25 KG, 50 KG o 1000 KG");  
                    FacesContext.getCurrentInstance().addMessage(null, msg); 
                    return;
            	}
            	if(ex.getMessage().equals("Error de contenido 7")){
            		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error de contenido:" , " verificar tipo de datos convertidos");  
                    FacesContext.getCurrentInstance().addMessage(null, msg); 
                    return;
            	}
            	if(ex.getMessage().equals("Error de materia prima")){
            		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error de materia prima:" , " favor cargar los datos de la materia prima antes de cargar sus lotes");  
                    FacesContext.getCurrentInstance().addMessage(null, msg); 
                    return;
            	}
            	
            }
        }  
    }  

	
}
