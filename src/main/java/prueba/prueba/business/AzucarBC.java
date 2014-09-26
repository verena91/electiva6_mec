package prueba.prueba.business;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.JDBCException;
import org.slf4j.Logger;
import org.ticpy.tekoporu.stereotype.BusinessController;

import py.org.vue.azucar_etl.domain.ObjExcell;
import py.org.vue.azucar_etl.persistence.ExcellDAO;

@BusinessController
public class AzucarBC{
	
private static final long serialVersionUID = 1L;
	
	@Inject
	private ExcellDAO excellDAO;
	
	@Inject
	private ExcellExport excel;
	
	@Inject
	private Logger logger;

	public int[] consultarDatos(String path) throws Exception {		
		excel = new ExcellExport();
		List<ObjExcell> result = excel.leerArchivoExcel(path);
    	int[] insertados = new int[2];		
		insertados[0]=0;
		insertados[1]=0;
		for (int i = 0; i < result.size(); i++) {
			String anhomes = result.get(i).getAnhomes()+"";
			String ruc = result.get(i).getRuc()+"";
			int codProd = result.get(i).getCodProducto();	
			
			Object[] obj = excellDAO.consultar(anhomes,ruc,codProd);
			if(obj == null)
				throw new Exception("Error de materia prima");
			
			int nroSolicitud = Integer.parseInt(obj[0]+"");
			int nroItem = Integer.parseInt(obj[1]+"");
			
			Object res = excellDAO.consultarSubitem(nroSolicitud, nroItem);
			int subitem = res ==null?(0):(Integer.parseInt((res+"")));
			
			try{
				boolean o = excellDAO.consultarDuplicados(nroSolicitud, nroItem, result.get(i).getNroLote());
				if(o){
					insertados[1]++;//existe un registro con el mismo numero de lote
					continue;
				}
				
				insertados[0]+= excellDAO.insert(nroSolicitud,nroItem,subitem,result.get(i));
			}catch(JDBCException e){
				logger.info(">>>>>> Llave duplicada");
			}catch(Exception ex){
				
			}
			
		}
		return insertados;

	}
}
