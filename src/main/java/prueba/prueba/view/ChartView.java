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
	private LineChartModel animatedModel1;
    private BarChartModel totalPorMes;
    private DonutChartModel donutModel2;
    private PieChartModel pieModel2;
    private PieChartModel pieModel3;
    private PieChartModel pieModel4;

    
    @Inject
    private FuncionarioBC funcionarioBC;
    
    private List<Object> lista;
    private List<Object> listaPorConcepto;
    private List<Object> listaPorConceptoMes;
    private BarChartModel barModel;

    
    public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	@PostConstruct
    public void init() {
        createAnimatedModels();
    }
 
    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }
 
    public BarChartModel getTotalPorMes() {
        return totalPorMes;
    }
 
    private void createAnimatedModels() {
    	
        lista = funcionarioBC.getGastosPorMes();
        listaPorConcepto = funcionarioBC.getGastosPorConcepto();
    	listaPorConceptoMes = funcionarioBC.getPorConceptoMes("julio");
        Axis yAxis;
        Axis xAxis;
         
        //Gastos Total Por Mes
        float min = 99999999999999999f;
        float max = 0f;
        for(Object ob: lista){
        	Object[] objeto = (Object[])ob;
        	if(Float.valueOf(objeto[2]+"").floatValue() > max)
        		max = Float.valueOf(objeto[2]+"").floatValue();
        	if(Float.valueOf(objeto[2]+"").floatValue() < min)
        		min = Float.valueOf(objeto[2]+"").floatValue();
        }
        
        totalPorMes = initBarModel();
        totalPorMes.setTitle("Gasto Total Por Mes En los ultimos 2 años (En concepto de Sueldos)");
        totalPorMes.setAnimate(true);
        totalPorMes.setLegendPosition("ne");
        yAxis = totalPorMes.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(100);
        yAxis.setLabel("Sueldo");
        xAxis = totalPorMes.getAxis(AxisType.X);
        xAxis.setLabel("Mes");
        
        //Gastos Por Concepto

        min = 99999999999999999f;
        max = 0f;
        for(Object ob: listaPorConcepto){
        	Object[] objeto = (Object[])ob;
        	if(Float.valueOf(objeto[2]+"").floatValue() > max)
        		max = Float.valueOf(objeto[2]+"").floatValue();
        	if(Float.valueOf(objeto[2]+"").floatValue() < min)
        		min = Float.valueOf(objeto[2]+"").floatValue();
        }
        animatedModel1 = initLinearModel();
        animatedModel1.setTitle("Gastos Por Concepto");
        animatedModel1.setAnimate(true);
        animatedModel1.setZoom(true);
        
        animatedModel1.setLegendPosition("se");
        yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
        yAxis.setLabel("Monto");
        xAxis = animatedModel1.getAxis(AxisType.X);
        xAxis.setLabel("Bonificacion");
        
        //
        
        donutModel2 = initDonutModel();
        donutModel2.setTitle("Custom Options");
        donutModel2.setLegendPosition("e");
        donutModel2.setSliceMargin(5);
        donutModel2.setShowDataLabels(true);
        donutModel2.setShadow(false);
        
        //
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
         
        pieModel2.setTitle("Total por Concepto en el mes de Julio");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(true);
        pieModel2.setShadow(true);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(500);
        pieModel2.setLegendCols(2);
        
        //
        
        barModel = initModel();
        
        barModel.setTitle("Total por Concepto mes de Agosto");
        barModel.setLegendPosition("nw");
        barModel.setLegendCols(3);
        xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Mes");
         
        yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("%Monto");
        yAxis.setMin(0);
        yAxis.setMax(70);
    }
    
    private BarChartModel initModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("Julio", 52);
        girls.set("Junio", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);
 
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

	public DonutChartModel getDonutModel2() {
		return donutModel2;
	}

	public void setDonutModel2(DonutChartModel donutModel2) {
		this.donutModel2 = donutModel2;
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
    
}