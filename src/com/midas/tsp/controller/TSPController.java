/**
 * 
 */
package com.midas.tsp.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.AbstractProcessor;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import com.midas.tsp.annotations.quality.DetPlanQ;
import com.midas.tsp.annotations.quality.PlanQ;
import com.midas.tsp.annotations.quality.ProcessPhase;
import com.midas.tsp.exceptions.TSPException;
import com.midas.tsp.model.Model;
import com.midas.tsp.model.PspProcessor;
import com.midas.tsp.util.JavaSourceOrDirectoryFilter;





/**
 * @author German Dario Camacho Sanchez
 * @date 21/02/2011
 * 
 * Clase controladora la cual gestiona la ejecución del análisis de código y 
 * el procesamiento de los parametros de entrada
 */
public class TSPController {
	
	private Model model;
	
	private String archivo;
	private String directorio;
	private String dirPropiedades;
	private List<String> listaArchivos;
	static private JavaSourceOrDirectoryFilter filtro=new JavaSourceOrDirectoryFilter();
	
	

	public TSPController(Model model){
		this.model=model;
		this.listaArchivos=new ArrayList<String>();
	}
	
	/**
	 * Realizar validación de los parametros y la carga de la lista de archivos si se indica un directorio
	 * @author German Dario Camacho
	 * @date 22/02/2011
	 */
	public void cargarParametros(String[] parametros) throws TSPException{
		
		validar(parametros);
		Map<String,String> parejas=new HashMap<String,String>();
		
		for(int i=0;i<parametros.length;i=i+2){
			if(!parametros[i].startsWith("-")){
				throw new com.midas.tsp.exceptions.TSPException("No se reconoce la opción: "+parametros[i]);
			}
			parejas.put(parametros[i], parametros[i+1]);
		}
		
		if(parejas.get("-f")!=null){
				File tmp=new File(parejas.get("-f"));
				if(tmp.isFile()){
					archivo=parejas.get("-f");
					listaArchivos.add(archivo);
				}else {
					throw new com.midas.tsp.exceptions.TSPException("No se puede leer el archivo: "+parejas.get("-f"));
				}

		}
		
		if(parejas.get("-d")!=null){
	
				File tmp=new File(parejas.get("-d"));
				if(tmp.isDirectory()){
					setDirectorio(parejas.get("-d"));
					listaArchivos=cargarListaArchivos(tmp);
					if(listaArchivos.size()==0){
						throw new com.midas.tsp.exceptions.TSPException("No se encontraron archivos para procesar.");

					}
				}else {
					throw new com.midas.tsp.exceptions.TSPException("No se puede leer el directorio: "+parejas.get("-d"));

				}
			
		}	
		
		if(parejas.get("-p")!=null){

				File tmp=new File(parejas.get("-p"));
				if(tmp.isDirectory()){
					setDirPropiedades(parejas.get("-p"));
				}else {
					throw new com.midas.tsp.exceptions.TSPException("No se puede leer el directorio: "+parejas.get("-p"));

				}

		}	
		
	}

	/**
	 * Realizar la validación de la lista de parametros suministrada frente
	 * a que vengan el número correcto de parametros y los parametros obligatorios
	 * @author German Dario Camacho
	 * @date 22/02/2011
	 */
	private void validar(String[] parametros) throws TSPException {
			
		if(parametros.length%2!=0){
			throw new com.midas.tsp.exceptions.TSPException("El número de parametros indicado no es correcto.");
		}
		
		List<String> params=Arrays.asList(parametros);
		if(!params.contains("-f")&&!params.contains("-d")){
			throw new com.midas.tsp.exceptions.TSPException("Debe indicar un archivo o ruta a analizar.");
		}

	}

	/**
	 * Carga la lista de archivos .java que se encuentren en el directorio indicado.
	 * El llamado utiliza recursividad
	 * @author German Dario Camacho
	 * @date 06/03/2011
	 */
	public List<String> cargarListaArchivos(File directorioRecorrer) throws TSPException{
		List<String> archivosTmp=new ArrayList<String>();
		
		for(File tmp:directorioRecorrer.listFiles(filtro)){
			if(tmp.isDirectory()){
				archivosTmp.addAll(cargarListaArchivos(tmp));
			}else{
				archivosTmp.add(tmp.getAbsolutePath());
			}
		}
		
		return archivosTmp;
	}
	
	/**
	 * Se encarga de ejecutar el compilador junto con el procesador de anotaciones
	 * @author German Dario Camacho
	 * @date 22/02/2011
	 */
	public void correrProcess() throws IOException, TSPException{
		
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
				null, null, null);
		Iterable<? extends JavaFileObject> compilationUnits1 = fileManager
				.getJavaFileObjectsFromStrings(listaArchivos);
		File tempDir=new File("tempClasses");
		if(!tempDir.exists()){
			if(!tempDir.mkdir()){
				throw new TSPException("No se pudo crear el directorio " +
													"temporal para la compilación.");
			}
		}
		tempDir.deleteOnExit();
		fileManager.setLocation(StandardLocation.CLASS_OUTPUT, 
                 Arrays.asList(tempDir));
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null,
				null, compilationUnits1);
		LinkedList<AbstractProcessor> processors = new LinkedList<AbstractProcessor>();

		processors.add(new PspProcessor(model));

		task.setProcessors(processors);

		if(!task.call()){
			//throw new exceptions.MyPspException("Error al momento de la compilación.");
		}
		
		for(Diagnostic<? extends JavaFileObject> tmpD:diagnostics.getDiagnostics()){
			System.out.println(tmpD.getMessage(null));
			
		}
		
		fileManager.close();
	}
	
	/**
	 * Se encarga de mostrar el resumen de los resultados imprimiendo en la consola
	 * @author German Dario Camacho
	 * @date 22/02/2011
	 */

	public void mostrarResumenConsola(){
		
		System.out.println("Resultados:");
		System.out.println("Plan de Calidad(Errores a inyectar, Errores a detectar): ");
		if(model.getPlanQList().size()>0){
			System.out.println("----------->");
			for(PlanQ planes:model.getPlanQList()){
				System.out.println("Plan: "+planes.cycle());
				for(DetPlanQ detalle: planes.details()){
					System.out.println("Etapa: "+detalle.processPhase()+" Inyectar: " + detalle.inyect()+
											" Detectar: "+detalle.remove());
				}
			}
		}
		System.out.println("Tamaño estimado en LOC: "+model.getEstimatedSize());
		System.out.println("Tiempo estimado en minutos: "+model.getEstimatedTime());
		System.out.println("Tiempo total: "+model.getTotalTime());
		System.out.println("Tiempo de interrupciones en minutos: "+model.getTotalIntTime());
		System.out.println("Tamaño total en LOC: "+model.getTotalSize());
		if(!model.getErrorsStage().isEmpty()){
			System.out.println("----------->");
			System.out.println("Defectos Corregidos: ");
			for(ProcessPhase defectos:model.getErrorsStage().keySet()){
				System.out.println("Etapa: "+defectos+" Cantidad: "+
											model.getErrorsStage().get(defectos));
			}
		}
		System.out.println("Productividad(Loc/hora): "+com.midas.tsp.util.Utility.numberFormatter.format(model.getProductivity()));
		
	}
	
	public void setListaArchivos(List<String> listaArchivos) {
		this.listaArchivos = listaArchivos;
	}

	public List<String> getListaArchivos() {
		return listaArchivos;
	}

	public void setDirPropiedades(String dirPropiedades) {
		this.dirPropiedades = dirPropiedades;
	}

	public String getDirPropiedades() {
		return dirPropiedades;
	}

	public void setDirectorio(String directorio) {
		this.directorio = directorio;
	}

	public String getDirectorio() {
		return directorio;
	}
	
}

/*
 @Plan(size=118,time=296)
	@LogTs({
	@LogT(date="06/03/2011",id=1,time=90),
	@LogT(date="06/03/2011",id=2,time=23),
	@LogT(date="06/03/2011",id=3,time=36),
	@LogT(date="06/03/2011",id=4,time=11),
	@LogT(date="06/03/2011",id=999,time=3),
	@LogT(date="06/03/2011",id=5,time=7),
	@LogT(date="07/03/2011",id=6,time=12),
	@LogT(date="07/03/2011",id=7,time=6),
	@LogT(date="07/03/2011",id=8,time=6),
	@LogT(date="07/03/2011",id=9,time=25),
	@LogT(date="07/03/2011",id=10,time=11),
	@LogT(date="07/03/2011",id=999,time=9),
	@LogT(date="07/03/2011",id=11,time=12),
	@LogT(date="07/03/2011",id=12,time=33),
	@LogT(date="07/03/2011",id=999,time=18)
	})
	@LogInts({
		@LogInt(date="06/03/2011",time=6,inte=10),
		@LogInt(date="06/03/2011",time=18,inte=1),
		@LogInt(date="06/03/2011",time=10,inte=11)
	})
	
	@PlanQ(date="06/03/2011",
		   details={
			@DetPlanQ(stage=Stage.DISENO,inyect=0,remove=0),
			@DetPlanQ(stage=Stage.PLANEACION,inyect=0,remove=0),
			@DetPlanQ(stage=Stage.CODIFICACION,inyect=0,remove=0),
			@DetPlanQ(stage=Stage.INSPECCION,inyect=4,remove=3),
			@DetPlanQ(stage=Stage.PRUEBAS,inyect=2,remove=2)
	})
	
	@LogDs({
		@LogD(date="06/03/2011",stage=PlanQ.Stage.PRUEBAS,id=137),
		@LogD(date="06/03/2011",stage=PlanQ.Stage.PRUEBAS,id=138),
		@LogD(date="06/03/2011",stage=PlanQ.Stage.INSPECCION,id=106),
		@LogD(date="06/03/2011",stage=PlanQ.Stage.INSPECCION,id=106),
		@LogD(date="06/03/2011",stage=PlanQ.Stage.INSPECCION,id=106),
		@LogD(date="08/03/2011",stage=PlanQ.Stage.INSPECCION,id=106),
		@LogD(date="08/03/2011",stage=PlanQ.Stage.INSPECCION,id=62),
		@LogD(date="08/03/2011",stage=PlanQ.Stage.PRUEBAS,id=140),
		@LogD(date="08/03/2011",stage=PlanQ.Stage.PRUEBAS,id=139)
	})
	*/
