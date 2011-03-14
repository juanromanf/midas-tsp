package com.midas.tsp.annotations.quality;

@LogDs({@LogD(cycleDetected=1, cycleInyected=1, date="14/03/2011", phaseDetected=ProcessPhase.IMPLEMENTATION, phaseInyected=ProcessPhase.IMPLEMENTATION, id=142, removed=true)}) //WHO = CIDC
public enum ProcessPhase {
	PLANNING, DESIGN, IMPLEMENTATION, INSPECTION, TESTS
}