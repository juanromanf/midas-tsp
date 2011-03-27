package com.midas.tsp.model;

import java.util.Arrays;
import java.util.Set;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import com.midas.tsp.annotations.LocControl;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;
import com.midas.tsp.annotations.Plan;
import com.midas.tsp.annotations.quality.LogD;
import com.midas.tsp.annotations.quality.LogDs;
import com.midas.tsp.annotations.quality.PlanQ;
import com.midas.tsp.exceptions.TSPException;

/**
 * @author German Dario Camacho S.
 * @date 20/02/2011 Implementacion del procesador de anotaciones definidas en la
 *       aplicación, extiende la clase AbstractProcessor definida en el API de
 *       compilación de Java 6
 **/

@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("annotations.*")
public class PspProcessor extends AbstractProcessor {

	private Model modelo;

	/**
	 * Construye un objeto PspProcessor con un modelo asociado
	 * 
	 * @param modelo
	 *            Instancia model.Modelo en la que se procesará y mantendra la
	 *            informacion de las anotaciones procesadas
	 */
	public PspProcessor(Model modelo) {
		super();
		this.modelo = modelo;

	}

	@Override
	/**
	 * Se encarga de procesar las diferentes anotaciones que soporta la aplicacion
	 * y solicitar el procesamiento de las mismas por parte del modelo
	 */
	public boolean process(Set<? extends TypeElement> arg0,
			RoundEnvironment roundEnvironment) {
		
		
		try {
			for (Element e : roundEnvironment
					.getElementsAnnotatedWith(Plan.class)) {

				modelo.add(e.getAnnotation(Plan.class));

			}

			for (Element e : roundEnvironment
					.getElementsAnnotatedWith(PlanQ.class)) {
				modelo.add(e.getAnnotation(PlanQ.class));
			}

			for (Element e : roundEnvironment
					.getElementsAnnotatedWith(LocControl.class)) {
				modelo.add(e.getAnnotation(LocControl.class));
			}

			for (Element e : roundEnvironment
					.getElementsAnnotatedWith(LogDs.class)) {
				LogDs ints = e.getAnnotation(LogDs.class);
				for (LogD l : Arrays.asList(ints.value())) {
					modelo.add(l);
				}
			}

			for (Element e : roundEnvironment
					.getElementsAnnotatedWith(LogTs.class)) {
				LogTs logts = e.getAnnotation(LogTs.class);
				for (LogT l : Arrays.asList(logts.value())) {
					modelo.add(l);
				}
			}
		} catch (TSPException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}

}
