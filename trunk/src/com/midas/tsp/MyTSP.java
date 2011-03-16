package com.midas.tsp;
import java.io.IOException;

import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;
import com.midas.tsp.annotations.Plan;
import com.midas.tsp.annotations.Plans;
import com.midas.tsp.annotations.quality.LogD;
import com.midas.tsp.annotations.quality.LogDs;
import com.midas.tsp.annotations.quality.ProcessPhase;
import com.midas.tsp.controller.TSPController;
import com.midas.tsp.model.Model;

/**
 * @author German Dario Camacho S.
 * @date 20/02/2011 Clase ejecutable desde la que se gestiona el ingreso de
 *       parametros y la instanciación del modelo y controlador de la aplicación
 */

@Plans({ 
	@Plan(cycle = 1, size = 190, time = 516) 
})

@LogTs({ 
	@LogT(cycle = 1, date = "13/03/2011", id = "1_45", time = 130, who = "GDCS"),
	@LogT(cycle = 1, date = "14/03/2011", id = "1_999", time = 25, who = "GDCS"), 
	@LogT(cycle = 1, date = "13/03/2011", id = "1_999", time = 130, who = "CIDC"),
	@LogT(cycle = 1, date = "14/03/2011", id = "1_999", time = 240, who = "CIDC"),	
})

@LogDs({ 
	@LogD( id = 1, date = "13/03/2011", removed=true,
				cycleInyected = 1, phaseInyected = ProcessPhase.DESIGN, 
				cycleDetected = 1, phaseDetected = ProcessPhase.IMPLEMENTATION)
})

public class MyTSP {

	/**
	 * @param args
	 *            parametros indicados desde la línea de comandos
	 */

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

/*
 * @Plan(size=255,time=620)
 * 
 * @LogTs({
 * 
 * @LogT(date="20/02/2011",id=1,time=180),
 * 
 * @LogT(date="20/02/2011",id=2,time=30),
 * 
 * @LogT(date="20/02/2011",id=3,time=5),
 * 
 * @LogT(date="20/02/2011",id=4,time=55),
 * 
 * @LogT(date="20/02/2011",id=5,time=7),
 * 
 * @LogT(date="20/02/2011",id=6,time=71),
 * 
 * @LogT(date="20/02/2011",id=7,time=16),
 * 
 * @LogT(date="20/02/2011",id=8,time=15),
 * 
 * @LogT(date="20/02/2011",id=9,time=21),
 * 
 * @LogT(date="21/02/2011",id=10,time=0),
 * 
 * @LogT(date="21/02/2011",id=11,time=15),
 * 
 * @LogT(date="21/02/2011",id=12,time=10),
 * 
 * @LogT(date="21/02/2011",id=13,time=73),
 * 
 * @LogT(date="21/02/2011",id=14,time=60),
 * 
 * @LogT(date="21/02/2011",id=15,time=20),
 * 
 * @LogT(date="21/02/2011",id=16,time=13),
 * 
 * @LogT(date="21/02/2011",id=17,time=0),
 * 
 * @LogT(date="21/02/2011",id=18,time=10),
 * 
 * @LogT(date="21/02/2011",id=19,time=72) })
 * 
 * @LogInts({
 * 
 * @LogInt(date="20/02/2011",time=10,inte=1),
 * 
 * @LogInt(date="20/02/2011",time=4,inte=6),
 * 
 * @LogInt(date="20/02/2011",time=8,inte=3),
 * 
 * @LogInt(date="20/02/2011",time=7,inte=7),
 * 
 * @LogInt(date="20/02/2011",time=3,inte=4),
 * 
 * @LogInt(date="20/02/2011",time=7,inte=4),
 * 
 * @LogInt(date="20/02/2011",time=10,inte=3),
 * 
 * @LogInt(date="20/02/2011",time=4,inte=1),
 * 
 * @LogInt(date="21/02/2011",time=5,inte=3),
 * 
 * @LogInt(date="21/02/2011",time=3,inte=8),
 * 
 * @LogInt(date="21/02/2011",time=15,inte=9),
 * 
 * @LogInt(date="21/02/2011",time=5,inte=3),
 * 
 * @LogInt(date="21/02/2011",time=9,inte=2),
 * 
 * @LogInt(date="21/02/2011",time=4,inte=3) })
 */
