/**
 * 
 */
package com.midas.tsp.exceptions;


/**
 * @author German Dario Camacho Sanchez
 * @date 21/02/2011
 */
@SuppressWarnings(value = { "all" })
public class TSPException extends Exception {
	
	public TSPException(String msg){
		super(msg);
	}
	
	public TSPException(Throwable tr){
		super(tr);
	}
}
