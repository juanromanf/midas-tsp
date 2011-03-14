package model.test;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.processing.AbstractProcessor;
import javax.tools.*;
import javax.tools.JavaCompiler.CompilationTask;

import com.midas.tsp.model.Model;
import com.midas.tsp.model.PspProcessor;

import junit.framework.TestCase;

public class PspProcessorTest extends TestCase {

	
	List<String> archivos = new ArrayList<String>();
	Model modelo = null;
	File archivoAyuda = new File("");

	public void setupEscenarioCorrecto() {

		modelo = new Model();
		archivos.add("test/data/MyPspData.java");

	}
	
	public void setupEscenarioProyecto() {

		modelo = new Model();
		archivos.add("src/MyPsp.java");

	}

	/*
	 * Prueba si se puede obtener la instancia del compilador en Runtime Valor
	 * Prueba el procesador de anotaciones de acuerdo al archivo en la carpeta
	 * test/data/
	 * Prueba la integración del procesador de anotaciones con el modelo
	 * Valores esperados:
	 * La referencia al compilador no debe ser null
	 * El modelo debe devolver los valores correctos como resultado del proceso.
	 */
	public void testProcessorA() {
		setupEscenarioCorrecto();

		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		assertNotNull("No se puede obtener una instancia del compilador",
				compiler);

		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
				null, null, null);

		Iterable<? extends JavaFileObject> compilationUnits1 = fileManager
				.getJavaFileObjectsFromStrings(archivos);

		CompilationTask task = compiler.getTask(null, fileManager, null, null,
				null, compilationUnits1);

		LinkedList<AbstractProcessor> processors = new LinkedList<AbstractProcessor>();

		processors.add(new PspProcessor(modelo));

		task.setProcessors(processors);

		task.call();
		/*
		System.out.println(modelo.getEstimatedSize());
		System.out.println(modelo.getEstimatedTime());
		System.out.println(modelo.getTotalTime());
		System.out.println(modelo.getTotalSize());
		System.out.println(modelo.getTotalIntTime());
		 */
		assertEquals("El tamaño estimado no concuerda.", new Integer(400),
				modelo.getEstimatedSize());
		assertEquals("El tiempo estimado no concuerda.", new Integer(620),
				modelo.getEstimatedTime());

		assertEquals("El tiempo total no concuerda.", new Integer(380),
				modelo.getTotalTime());

		assertEquals("El tamaño real no concuerda.", new Integer(10),
				modelo.getTotalSize());

		assertEquals("El tiempo de interrupciones no concuerda.", new Integer(60),
				modelo.getTotalIntTime());
	}

	/*
	 * Test con los datos del proyecto
	 * Muestra por consola los datos del proyecto
	 * 
	 */
	public void testDatosProyecto() {
		setupEscenarioProyecto();

		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		assertNotNull("No se puede obtener una instancia del compilador",
				compiler);

		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
				null, null, null);

		Iterable<? extends JavaFileObject> compilationUnits1 = fileManager
				.getJavaFileObjectsFromStrings(archivos);

		CompilationTask task = compiler.getTask(null, fileManager, null, null,
				null, compilationUnits1);

		LinkedList<AbstractProcessor> processors = new LinkedList<AbstractProcessor>();

		processors.add(new PspProcessor(modelo));

		task.setProcessors(processors);

		task.call();
		
		System.out.println("Resumen de informacion del proyecto:");
		System.out.println("Tamaño estimado en LOC: "+modelo.getEstimatedSize());
		System.out.println("Tiempo estimado en minutos: "+modelo.getEstimatedTime());
		System.out.println("Tiempo total: "+modelo.getTotalTime());
		System.out.println("Tiempo de interrupciones en minutos: "+modelo.getTotalIntTime());
		System.out.println("Tamaño total en LOC: "+modelo.getTotalSize());
		System.out.println("Tamaño total en LOC según metrics en src: 105");
		 
	}
	

}
