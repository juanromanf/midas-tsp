/**
 * 
 */
package com.midas.tsp.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author German Dario Camacho Sanchez
 * @date 07/03/2011
 * Anotacion que permite agrupar diferentes anotaciones Loc
 * para habilitar el control de las diferentes modificaciones a un método
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LocControl {
	enum LocType{
		NUEVA,REUTILIZADA,MODIFICADA,ELIMINADA
	}
	int cycle();
	Loc[] value();

}
