package com.midas.tsp.annotations;

import java.lang.annotation.*;

import com.midas.tsp.annotations.quality.LogD;
import com.midas.tsp.annotations.quality.LogDs;
import com.midas.tsp.annotations.quality.ProcessPhase;

/**
 * @author German Dario Camacho S.
 * @date 20/02/2011
 * Anotación para tomar la tarea realizada junto con su fecha y tiempo de ejecución
 */
@LogDs({@LogD(cycleDetected=1, cycleInyected=1, date="14/03/2011", phaseDetected=ProcessPhase.IMPLEMENTATION, phaseInyected=ProcessPhase.IMPLEMENTATION, id=141, removed=true)}) //WHO = CIDC
@Retention(RetentionPolicy.RUNTIME)
public @interface LogT {
	String date() default "";
	String id();
	int time() default 0;
	int cycle() ;
	String who();
}
