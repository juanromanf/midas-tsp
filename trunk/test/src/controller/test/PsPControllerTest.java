/**
 * 
 */
package controller.test;

import com.midas.tsp.controller.PsPController;
import com.midas.tsp.exceptions.MyPspException;
import com.midas.tsp.model.Model;

import junit.framework.TestCase;

/**
 * @author German Dario Camacho Sanchez
 * @date 21/02/2011
 * 
 * Contiene las pruebas unitarias para el controlador
 */
public class PsPControllerTest extends TestCase {
	
	String[] parametros;
	PsPController controlador;
	public void setupCargarOK(){
		controlador=new PsPController(new Model());
		String[] parametros={"-f","test/data/MyPspData.java","-d","src/","-p","src/"};
		this.parametros=parametros;
	}
	
	public void setupCargarError(){
		controlador=new PsPController(new Model());
		String[] parametros={"f","test/data/MyPspData.java","d","test/data/","-p","src/"};
		this.parametros=parametros;
	}
	
	public void testCargaSoloArchivo(){
		setupCargarOK();
		boolean flag=true;
		try {
			controlador.cargarParametros(parametros);
		} catch (MyPspException e) {
			e.printStackTrace();
			flag=false;
		}
		assertTrue("Ocurrio una exepcion",flag);
	}

	public void testCargaErrorParams(){
		setupCargarError();
		boolean flag=false;
		try {
			controlador.cargarParametros(parametros);
		} catch (MyPspException e) {
			flag=true;
		}
		assertTrue("Ocurrio una exepcion",flag);
	}

}
