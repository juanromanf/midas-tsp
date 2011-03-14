package model.test;

import java.lang.annotation.Annotation;
import java.util.List;

import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.Plan;
import com.midas.tsp.model.Model;


import junit.framework.TestCase;

/*
 * Clase de pruebas para el modelo
 * 
 */
public class ModelTest extends TestCase {
	
	private Model modelo;
	
	/*
	 * Crea un escenario con valores por default del modelo
	 */
	private void setupEscenario1(){
		modelo=new Model();
		
	}
	
	/*
	 * Crea un escenario con datos en LogT Plan.
	 * Si se desea probar los Loc se debe cambiar el target de la anotación
	 */
	
	private void setupEscenarioLogs(){
	/*	
		 modelo=new Model();
		 
		 @LogT(time=200,id=2,date="20/02/2011") final class l { }
		 LogT logT = l.class.getAnnotation( LogT.class ) ; 
		 modelo.addLogT(logT);
		 modelo.addLogT(logT);
		 modelo.addLogT(logT);
		 
		 
		 @Plan(size=400,time=780) final class p { }
		 Plan plan = p.class.getAnnotation( Plan.class ) ; 
		 modelo.setPlan(plan);
		 /*
		  *  Para setear los Loc se debe modificar el target de la Annotation 
		  *  con el fín de poder cmpilar el código de prueba
		  */
		 /*@Loc(size=50) final class code { }
		 Loc loc = code.class.getAnnotation( Loc.class ) ; 
		 modelo.addLoc(loc);
		 modelo.addLoc(loc);
		 modelo.addLoc(loc);
		 */
		 
		 
	}
	
	/*
	 * Prueba que el modelo se crea correctamente por defecto y que las variables base
	 * no se encuentran en null
	 * Metodos a probar:
	 * getEstimatedSize, getEstimatedTime, getTotalSize, getTotalIntTime, getTotalIntTime, getLogTlist
	 * Valor esperado:
	 * Todas las llamadas a los metodos devuelven una instancia
	 */
	public void testValoresDefault(){
		 setupEscenario1();
		 Integer estimatedSize=modelo.getEstimatedSize();
		 Integer estimatedTime=modelo.getEstimatedTime();
		 Integer totalSize=modelo.getTotalSize();
		 Integer totalTime=modelo.getTotalTime();
		 Integer totalIntTime=modelo.getTotalIntTime();
		 List<LogT> listaLogT=modelo.getLogTlist();
		 
		 assertNotNull( "No se cargó estimatedSize", estimatedSize );
		 assertNotNull( "No se cargó estimatedTime", estimatedTime );
		 assertNotNull( "No se cargó totalSize", totalSize );
		 assertNotNull( "No se cargó totalTime", totalTime );
		 assertNotNull( "No se cargó totalIntTime", totalIntTime );
		 assertNotNull( "No se cargó listaLogT", listaLogT );

	 
	}
	
	/*
	 * Prueba que el modelo adiciona correctamente LogT
	 * Metodos a probar:
	 * getTotalTime,getLogTlist,addLogT
	 * Valor esperado:
	 * La sumatoria de valores de tiempo añadidos en el escenario debe ser igual a 600
	 * Las Anotaciones LogT añadidas al modelo deben ser 3
	 */
	public void testAddLogT(){	
		setupEscenarioLogs();
		assertEquals("No es correcto el total time",new Integer(600),modelo.getTotalTime());
		assertEquals("No es correcta la cantidad de LogT leidos",modelo.getLogTlist().size(),3);
	}
	

	
	/*
	 * Prueba que el modelo mantiene el valor estimado según lo indique la anotacion Plan
	 * Metodos a probar:
	 * getEstimatedSize, getEstimatedTime, setPlan
	 * Valor esperado:
	 * Los valores Devueltos por el plan deben ser los mismos que se indican en el escenario
	 */
	public void testPlan(){	
		setupEscenarioLogs();
		assertEquals("No es correcto el size planeado",new Integer(400),modelo.getEstimatedSize());
		assertEquals("No es correcta el time planeado",new Integer(780),modelo.getEstimatedTime());
	}

}
