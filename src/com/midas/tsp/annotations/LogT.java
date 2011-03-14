package com.midas.tsp.annotations;

import java.lang.annotation.*;

/**
 * @author German Dario Camacho S.
 * @date 20/02/2011
 * Anotación para tomar la tarea realizada junto con su fecha y tiempo de ejecución
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LogT {
	String date() default "";
	int id() default 0;
	int time() default 0;
	int cycle() ;
	String who();
}
