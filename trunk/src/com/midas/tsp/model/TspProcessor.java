package com.midas.tsp.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;

import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LocControl;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;
import com.midas.tsp.annotations.Plan;
import com.midas.tsp.annotations.Plans;
import com.midas.tsp.annotations.quality.LogD;
import com.midas.tsp.annotations.quality.LogDs;
import com.midas.tsp.annotations.quality.PlanQ;
import com.midas.tsp.annotations.quality.ProcessPhase;

/**
 * Implements <code>AbtractProcessor</code> in order to process the annotations
 * defined in the package <code>com.midas.tsp.annotations</code>. The objects of
 * this class must receive an instance of a <code>Model</code> object in wich
 * data will be collected.
 * 
 * @author German Dario Camacho S.
 * @see javax.annotation.processing.AbstractProcessor
 * @date 20/02/2011
 **/

@LogDs({ 
	@LogD( id = 900, date = "13/03/2011", removed=true,
				cycleInjected = 1, phaseInjected = ProcessPhase.DESIGN, 
				cycleDetected = 3, phaseDetected = ProcessPhase.IMPLEMENTATION)
})
@LogTs({ @LogT(cycle = 3, date = "27/03/2011", id = "19", time = 70, who = "GDCS"),
		 @LogT(cycle = 3, date = "27/03/2011", id = "20", time = 55 , who = "GDCS")
	})
@SupportedAnnotationTypes({ "com.midas.tsp.annotations.*",
		"com.midas.tsp.annotations.quality.*" })
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class TspProcessor extends AbstractProcessor {

	/**
	 * Instance for <code>Model</code> to pass encountered data.
	 */
	private Model model;

	/**
	 * List with all the <code>Class</code> types for the annotations the
	 * processor supports. it's defined for convenience about the usage of the
	 * <code>Model</code>.
	 */
	private static ArrayList<Class<? extends Annotation>> supportedAnnotations;

	/**
	 * Initialize list of annotations to process
	 */
	static {
		supportedAnnotations = new ArrayList<Class<? extends Annotation>>();
		supportedAnnotations.add(Loc.class);
		supportedAnnotations.add(LocControl.class);
		supportedAnnotations.add(LogT.class);
		supportedAnnotations.add(LogTs.class);
		supportedAnnotations.add(Plans.class);
		supportedAnnotations.add(LogDs.class);
		supportedAnnotations.add(Plan.class);
		supportedAnnotations.add(LogD.class);
		supportedAnnotations.add(PlanQ.class);
	}

	/**
	 * Creates a <code>TspProcessor</code> object that will populate the
	 * <code>Model</code> object passed to the constructor with all the data
	 * obtained from the source.
	 * 
	 * @param model
	 *            Instance of <code>Model</code> that will be populated by this
	 *            anntotation processor
	 */
	public TspProcessor(Model model) {
		super();
		this.model = model;
	}

	/**
	 * Processes all annotations founded in the source files that the processor
	 * is analizing. After each evaluation the annotation processed will not be
	 * significant in subsequent compiler passes.
	 * 
	 * The method uses Reflection in order to call the correcct method in the
	 * model.
	 * 
	 * @see javax.annotation.processing.AbstractProcessor#process(Set,
	 *      RoundEnvironment)
	 * 
	 */
	@LocControl(value = {
			@Loc(cycle = 3, size = 9, type = LocControl.LocType.NEW, who = "GDCS"),
			@Loc(cycle = 3, size = 14, type = LocControl.LocType.DELETED, who = "GDCS") })
	public boolean process(Set<? extends TypeElement> arg0,
			RoundEnvironment roundEnvironment) {

		try {

			/**
			 * Cycle through supported indicated annotations in
			 * supportedAnnotations
			 */
			for (Class<? extends Annotation> annotation : supportedAnnotations) {

				/**
				 * Identify the Actual annotation class and the add method associated
				 * to that type in the model.
				 */
				Class<? extends Annotation> clase = annotation.asSubclass(annotation);
				Method x = Model.class.getMethod("add", clase);

				/**
				 * Obtain all elements annotated for each Annotation type the
				 * outer cycle indicates
				 */
				for (Element e : roundEnvironment
						.getElementsAnnotatedWith(annotation)) {
					/**
					 * Obtain the annotation from the encountered element.
					 */
					Object tmp = e.getAnnotation(annotation);
					/**
					 * Invoke the method already founded for the actual
					 * annotation in the model instance, passing the casted object to it.
					 */
					x.invoke(model, clase.cast(tmp));
				}
			}

		} catch (Exception e) {
			/**
			 * The process Method is not allowed to throw Exceptions so here are masked all the exceptions
			 * all the method calls and model access are type safe, so no business exceptions are able to rise.
			 * 
			 * If needed a status would be added to the model in order to flag it as invalid o r with errors but
			 * is can add logic that is never going to be used.
			 * 
			 * This method calls methods with reflection but only uses the types indicated in supportedAnnotations list
			 * wich are all supported by the model at the moment of the class implementation
			 */
			e.printStackTrace();
			
		}
		
		/**
		 * Returns true so the annotations processed are not counted twice.
		 */
		return true;
	}

}
