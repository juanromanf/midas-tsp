package com.midas.tsp.annotations.quality;

/**
 * <code>Enum</code> to identify the diferent phases of development for the
 * TSP projects
 * @author German Dario Camacho Sanchez
 * @date 14/03/2011
 */
@LogDs({@LogD(cycleDetected=1, cycleInjected=1, date="14/03/2011", phaseDetected=ProcessPhase.IMPLEMENTATION, phaseInjected=ProcessPhase.IMPLEMENTATION, id=142, removed=true)}) //WHO = CIDC
public enum ProcessPhase {
	PLANNING, DESIGN, IMPLEMENTATION, INSPECTION, TESTS
}