package prueba.prueba.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

import prueba.prueba.business.FuncionarioBC;

@ManagedBean
public class ChartView implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private PieChartModel pieModel2;
    private PieChartModel pieModel3;
    private PieChartModel pieModel4;
    
    private LineChartModel lineModel2;

    Double junio;
    Double julio;
    Double agosto;
    
    @Inject
    private FuncionarioBC funcionarioBC;
    
    private List<Object> lista;
    private List<Object> listaPorConcepto;
    private List<Object> listaPorConceptoMes;
    
	@PostConstruct
    public void init() {
        junio = new Double(funcionarioBC.getTotalFuncionarios("junio")+"");
        julio = new Double(funcionarioBC.getTotalFuncionarios("julio")+"");
        agosto = new Double(funcionarioBC.getTotalFuncionarios("agosto")+"");
        createAnimatedModels();

    }
 
    private void createAnimatedModels() {
    	
        lista = funcionarioBC.getGastosPorMes();
        listaPorConcepto = funcionarioBC.getGastosPorConcepto();
    	listaPorConceptoMes = funcionarioBC.getPorConceptoMes("julio");
        Axis yAxis;
        Axis xAxis;
        
        //Junio Torta
        pieModel2 = new PieChartModel();
        Double aux = new Double(0);
        for(Object ob: funcionarioBC.getPorConceptoMes("junio")){
        	Object[] objeto = (Object[])ob;
        	pieModel2.set(objeto[0]+"",(Number) objeto[1]);
        	aux = aux + new Double(objeto[1]+"");
        }
        Double total = new Double(funcionarioBC.getTotal("junio")+"");
        Double otros = total - aux;
    	pieModel2.set("Otros",otros);
         
        pieModel2.setTitle("Total por Concepto en el mes de Junio");
        pieModel2.setLegendPosition("s");
        pieModel2.setFill(true);
        pieModel2.setShadow(true);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
        pieModel2.setLegendCols(1);
        
        //Julio Torta
        pieModel3 = new PieChartModel();
        aux = new Double(0);
        for(Object ob: funcionarioBC.getPorConceptoMes("julio")){
        	Object[] objeto = (Object[])ob;
        	pieModel3.set(objeto[0]+"",(Number) objeto[1]);
        	aux = aux + new Double(objeto[1]+"");
        }
        total = new Double(funcionarioBC.getTotal("julio")+"");
        otros = total - aux;
        pieModel3.set("Otros",otros);
         
        pieModel3.setTitle("Total por Concepto en el mes de Julio");
        pieModel3.setLegendPosition("s");
        pieModel3.setFill(true);
        pieModel3.setShadow(true);
        pieModel3.setShowDataLabels(true);
        pieModel3.setDiameter(150);
        pieModel3.setLegendCols(1);
        
      //Agosto Torta
        pieModel4 = new PieChartModel();
        aux = new Double(0);
        for(Object ob: funcionarioBC.getPorConceptoMes("agosto")){
        	Object[] objeto = (Object[])ob;
        	pieModel4.set(objeto[0]+"",(Number) objeto[1]);
        	aux = aux + new Double(objeto[1]+"");
        }
        total = new Double(funcionarioBC.getTotal("agosto")+"");
        otros = total - aux;
        pieModel4.set("Otros",otros);
         
        pieModel4.setTitle("Total por Concepto en el mes de Agosto");
        pieModel4.setLegendPosition("s");
        pieModel4.setFill(true);
        pieModel4.setShadow(true);
        pieModel4.setShowDataLabels(true);
        pieModel4.setDiameter(150);
        pieModel4.setLegendCols(1);
        
        //
        lineModel2 = initCategoryModel();
        lineModel2.setTitle("Total Por Mes");
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Mes"));
        yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad");
        yAxis.setMin(0);
        yAxis.setMax(200);
        Double maximo;
        Double minimo;
        
        maximo = junio;
        minimo = junio;
        if(maximo < julio){
        	maximo =  julio;
        }
        if(maximo < agosto){
        	maximo =  agosto;
        }
        if(minimo > julio){
        	minimo =  julio;
        }
        if(minimo > agosto){
        	minimo = agosto;
        }
        
        yAxis.setMin(15000);
        yAxis.setMax(20000);
    }
    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Numero de Funcionarios");
        boys.set("Junio", junio);
        boys.set("Julio", julio);
        boys.set("Agosto", agosto);
 
        model.addSeries(boys);
         
        return model;
    }
    private BarChartModel initModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries concepto = new ChartSeries();
        Double aux = new Double(0);
        for(Object ob: funcionarioBC.getPorConceptoMes("junio")){
        	Object[] objeto = (Object[])ob;
        	aux = aux + Double.valueOf(objeto[1]+"");
        }
        Double total = new Double(funcionarioBC.getTotal("agosto")+"");
        Double otros = total - aux;
        otros = otros / total;
        for(Object ob: funcionarioBC.getPorConceptoMes("agosto")){
        	Object[] objeto = (Object[])ob;
        	concepto = new ChartSeries();
        	concepto.setLabel(objeto[0]+"");
        	concepto.set("Agosto",((Double.valueOf(objeto[1]+""))/total)*100);
        	
        	model.addSeries(concepto);
        }
        /*
        for(Object ob: funcionarioBC.getPorConceptoMes("julio")){
        	Object[] objeto = (Object[])ob;
        	concepto = new ChartSeries();
        	concepto.setLabel(objeto[0]+"");
        	concepto.set("Julio",((Double.valueOf(objeto[1]+""))/aux)*100);
        	model.addSeries(concepto);
        }
        
        */
        
        //model.addSeries(boys);
        //model.addSeries(girls);
         
        return model;
    }
    
    public List<Object[]> getMejoresPagados(){
    	List<Object[]> lista = new ArrayList<Object[]>();
    	for(Object ob: funcionarioBC.getMejoresPagados())
    		lista.add((Object[]) ob);
    	
    	return lista; 
    }
    
    private DonutChartModel initDonutModel() {
        DonutChartModel model = new DonutChartModel();
         
        Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
        circle1.put("Brand 1", 150);
        circle1.put("Brand 2", 400);
        circle1.put("Brand 3", 200);
        circle1.put("Brand 4", 10);
        model.addCircle(circle1);
         
        Map<String, Number> circle2 = new LinkedHashMap<String, Number>();
        circle2.put("Brand 1", 540);
        circle2.put("Brand 2", 125);
        circle2.put("Brand 3", 702);
        circle2.put("Brand 4", 421);
        model.addCircle(circle2);
         
        Map<String, Number> circle3 = new LinkedHashMap<String, Number>();
        circle3.put("Brand 1", 40);
        circle3.put("Brand 2", 325);
        circle3.put("Brand 3", 402);
        circle3.put("Brand 4", 421);
        model.addCircle(circle3);
         
        return model;
    } 
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        String mesActual = "";
        ChartSeries mes = new ChartSeries();
        List<ChartSeries> listaMes = new ArrayList<ChartSeries>();
        for(Object ob: lista){
        	Object[] objeto = (Object[])ob;
        	if(!mesActual.equals(objeto[0])){
        		if(mes.getLabel() != null && !mes.getLabel().equals(""))
        			listaMes.add(mes);
        		
        		mesActual = objeto[0]+"";
        		mes = new ChartSeries();
            	mes.setLabel(objeto[0]+"");

        	}
        	mes.set(objeto[1], (Number) objeto[2]);
        }
        if(mes.getLabel() != null && !mes.getLabel().equals(""))
			listaMes.add(mes);
        
        for(ChartSeries chart: listaMes)
        	model.addSeries(chart);
         
        return model;
    }
     
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
        /*
        String anhoActual = "";
        LineChartSeries anho = new LineChartSeries();
        List<LineChartSeries> listaAnho = new ArrayList<LineChartSeries>();
        for(Object ob: listaPorConcepto){
        	Object[] objeto = (Object[])ob;
        	if(!anhoActual.equals(objeto[0])){
        		if(anho.getLabel() != null && !anho.getLabel().equals(""))
        			listaAnho.add(anho);
        		
        		anhoActual = objeto[0]+"";
        		anho = new LineChartSeries();
            	anho.setLabel(objeto[0]+"");

        	}
        	anho.set((objeto[1]+ ""), (Number) objeto[2]);
        }
        if(anho.getLabel() != null && !anho.getLabel().equals(""))
        	listaAnho.add(anho);
        
        for(ChartSeries chart: listaAnho)
        	model.addSeries(chart);
         */
        /*ChartSeries anho2014 = new ChartSeries();
    	anho2014.setLabel("2014");
        for(Object ob: listaPorConcepto){
        	Object[] objeto = (Object[])ob;
        	anho2014.set(objeto[1], (Number)objeto[2]);
        }
        model.addSeries(anho2014);
       */
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("2014");
 
        series1.set(1, 1);
        series1.set(2, 6);
        
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("2013");
 
        series2.set(1, 8);
        series2.set(2, 2);
        model.addSeries(series1);

        model.addSeries(series2);
        
        return model;
    }

	public PieChartModel getPieModel2() {
		return pieModel2;
	}

	public void setPieModel2(PieChartModel pieModel2) {
		this.pieModel2 = pieModel2;
	}

	public PieChartModel getPieModel3() {
		return pieModel3;
	}

	public void setPieModel3(PieChartModel pieModel3) {
		this.pieModel3 = pieModel3;
	}

	public PieChartModel getPieModel4() {
		return pieModel4;
	}

	public void setPieModel4(PieChartModel pieModel4) {
		this.pieModel4 = pieModel4;
	}

	public LineChartModel getLineModel2() {
		return lineModel2;
	}

	public void setLineModel2(LineChartModel lineModel2) {
		this.lineModel2 = lineModel2;
	}
    
}