package prueba.prueba.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import prueba.prueba.business.FuncionarioBC;

@ManagedBean
public class ChartView implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LineChartModel animatedModel1;
    private BarChartModel totalPorMes;
    @Inject
    private FuncionarioBC funcionarioBC;
    
    private List<Object> lista;
    private List<Object> listaPorConcepto;
   
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
        yAxis.setMin(min - ((max-min)/2));
        yAxis.setMax(max);
        yAxis.setLabel("Sueldos");
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
    
}