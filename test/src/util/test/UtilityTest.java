/**
 * 
 */
package util.test;


import com.midas.tsp.util.Utility;

import junit.framework.TestCase;

/**
 * @author German Dario Camacho S.
 * @date 21/02/2011
 *
 */
public class UtilityTest extends TestCase{
	
	
	public void testConvertir(){
		Integer minutes=180;
		Double hours=0.0;
		hours=Utility.minutes2hours(minutes);
		assertEquals("Fallo en la conversion de minutos a horas",3.0,hours);
	}
	
	

}
