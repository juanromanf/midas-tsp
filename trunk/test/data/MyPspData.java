import java.util.LinkedList;

import javax.annotation.processing.AbstractProcessor;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import model.PspProcessor;
import com.midas.tsp.anotaciones.Loc;
import annotations.LogInt;
import annotations.LogInts;
import com.midas.tsp.anotaciones.LogT;
import com.midas.tsp.anotaciones.LogTs;
import com.midas.tsp.anotaciones.Plan;

/**
 * @author German
 * @date 20/02/2011
 * Clase ejecutable desde la que se gestiona el ingreso de parametros y 
 * la instanciación del modelo y controlador de la aplicación
 */

@Plan(size=400,time=620)
@LogTs({
@LogT(date="20/02/2011",id=1,time=180),
@LogT(date="20/02/2011",id=2,time=30),
@LogT(date="20/02/2011",id=3,time=50),
@LogT(date="20/02/2011",id=4,time=50),
@LogT(date="20/02/2011",id=5,time=70)

})
@LogInts({
	@LogInt(date="20/02/2011",time=10,inte=1),
	@LogInt(date="20/02/2011",time=10,inte=6),
	@LogInt(date="20/02/2011",time=40,inte=3)

})
public class MyPspData {



	
	/**
	 * @param args parametros indicados desde la línea de comandos
	 */
	@Loc(size=10)
public static void main(String [] args){
		

		
	}
	
	
}
