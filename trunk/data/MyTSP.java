package com.midas.tsp;
import java.io.IOException;

import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LocControl;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;
import com.midas.tsp.annotations.Plan;
import com.midas.tsp.annotations.Plans;
import com.midas.tsp.annotations.quality.DetPlanQ;
import com.midas.tsp.annotations.quality.LogD;
import com.midas.tsp.annotations.quality.LogDs;
import com.midas.tsp.annotations.quality.PlanQ;
import com.midas.tsp.annotations.quality.ProcessPhase;
import com.midas.tsp.controller.TSPController;
import com.midas.tsp.model.Model;

/**
 * @author German Dario Camacho S.
 * @date 20/02/2011 Clase ejecutable desde la que se gestiona el ingreso de
 *       parametros y la instanciación del modelo y controlador de la aplicación
 */

@Plans({ 
	@Plan(cycle = 1, size = 10, time = 30),
	@Plan(cycle = 2, size = 20, time = 60) ,
	@Plan(cycle = 3, size = 30, time = 100) 
})

@LogTs({ 
	@LogT(cycle = 1, date = "13/03/2011", id = "45", time = 30, who = "GDCS"),
	@LogT(cycle = 2, date = "14/03/2011", id = "999", time = 40, who = "GDCS"), 
	@LogT(cycle = 3, date = "13/03/2011", id = "999", time = 50, who = "CIDC"),
	@LogT(cycle = 3, date = "14/03/2011", id = "999", time = 60, who = "CIDC")	
})

@PlanQ(cycle=3,
		   details={
			@DetPlanQ(processPhase=ProcessPhase.DESIGN,inyect=0,remove=0),
			@DetPlanQ(processPhase=ProcessPhase.PLANNING,inyect=0,remove=0),
			@DetPlanQ(processPhase=ProcessPhase.IMPLEMENTATION,inyect=0,remove=0),
			@DetPlanQ(processPhase=ProcessPhase.INSPECTION,inyect=4,remove=3),
			@DetPlanQ(processPhase=ProcessPhase.TESTS,inyect=2,remove=2)
	})
	
@LogDs({ 
	@LogD( id = 1, date = "13/03/2011", removed=true,
				cycleInjected = 1, phaseInjected = ProcessPhase.DESIGN, 
				cycleDetected = 3, phaseDetected = ProcessPhase.IMPLEMENTATION)
})

public class MyTSP {

	/**
	 * @param args
	 *            parametros indicados desde la línea de comandos
	 */
	@LocControl(value = {
			@Loc(cycle = 1, size = 6, type = LocControl.LocType.MODIFIED, who = "GDCS"),
			@Loc(cycle = 2, size = 15, type = LocControl.LocType.NEW, who = "GDCS"),
			@Loc(cycle = 3, size = 20, type = LocControl.LocType.NEW, who = "GDCS") })
	public static void main(String[] args) {

		Model modelo = new Model();
		TSPController controlador = new TSPController(modelo);

		try {

			controlador.cargarParametros(args);

			try {
				controlador.correrProcess();
			} catch (IOException e) {
				e.printStackTrace();
				throw new Exception(
						"No se puede acceder al dispositivo de almacenamiento.");
			}

			controlador.mostrarResumenConsola();

		} catch (Exception e) {
			System.out.println("Ocurrió un error al procesar los parámetros: ");
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.exit(1);
		}

	}
}


