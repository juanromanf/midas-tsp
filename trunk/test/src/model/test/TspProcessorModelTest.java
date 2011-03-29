package src.model.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.processing.AbstractProcessor;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LocControl;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;
import com.midas.tsp.annotations.quality.*;
import com.midas.tsp.model.Model;
import com.midas.tsp.model.TspProcessor;

import junit.framework.TestCase;

@LogDs({ 
	@LogD( id = 902, date = "28/03/2011", removed=true,
				cycleInjected = 3, phaseInjected = ProcessPhase.TESTS, 
				cycleDetected = 3, phaseDetected = ProcessPhase.TESTS),
	@LogD( id = 901, date = "28/03/2011", removed=true,
						cycleInjected = 3, phaseInjected = ProcessPhase.TESTS, 
						cycleDetected = 3, phaseDetected = ProcessPhase.TESTS)
})
@LogTs({ 

	@LogT(cycle = 3, date = "28/03/2011", id = "999", time = 60, who = "GDCS")	
})
public class TspProcessorModelTest extends TestCase {

	List<String> files = null;
	Model model = null;

	/**
	 * Creates a model instance and adds a valid file sintactically speaking to
	 * the list of files that is going to be passed to the compiler
	 */
	@LocControl(value = {
			@Loc(cycle = 3, size = 3, type = LocControl.LocType.NEW, who = "GDCS")
			})
	public void setupStage1() {

		model = new Model();
		files = new ArrayList<String>();
		files.add("data/MyTsp.java");
	}


	/**
	 * Test the model and the annotation processor. The model must not be null -
	 * If the project or application is not well configured it can not find the
	 * compiler
	 */

	@LocControl(value = {
			@Loc(cycle = 3, size = 34, type = LocControl.LocType.NEW, who = "GDCS")
			})
	public void testProcessorA() {
		setupStage1();

		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		assertNotNull("Cannot get a compiler instance", compiler);

		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
				null, null, null);

		/**
		 * Load the compilations units epecified as a list of String with file
		 * to compile-analize
		 * 
		 */
		Iterable<? extends JavaFileObject> compilationUnits1 = fileManager
				.getJavaFileObjectsFromStrings(files);

		/**
		 * Get a CompilationTask for the specified compilation units
		 */
		CompilationTask task = compiler.getTask(null, fileManager, null, null,
				null, compilationUnits1);

		LinkedList<AbstractProcessor> processors = new LinkedList<AbstractProcessor>();
		/**
		 * Creates an instance of the TspProcessor and adds it to the list of
		 * Processor wich the compiler will use
		 */
		processors.add(new TspProcessor(model));

		task.setProcessors(processors);

		/**
		 * Execute the compilation task
		 */
		task.call();

		assertEquals("Estimated size is not the expected.", new Integer(60),
				model.getEstimatedSize());
		assertEquals("Estimated time is not the expected.", new Integer(190),
				model.getEstimatedTime());

		assertEquals("Real time is not the expected.", new Integer(180),
				model.getTotalTime());

		assertEquals("Real size is not the expected", new Integer(41),
				model.getTotalSize());

		assertEquals("Total size in new Locs is not the expected.",
				new Integer(35), model.getNewLocs());

		Integer noLogds = model.getLogDsPerCycle().get(new Integer(3)).size();
		assertEquals("# of defects detected in cycle 3 not the expected.",
				new Integer(1), noLogds);

		Integer noDetPlanQ = model.getPlanQPerCycle().get(new Integer(3))
				.details().length;
		assertEquals(
				"# of PlanQ details processed for cycle 3 not the expected.",
				new Integer(5), noDetPlanQ);

	}
}
