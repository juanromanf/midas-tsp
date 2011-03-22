package src.model.dao.test;

import java.io.FileInputStream;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LocControl;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;
import com.midas.tsp.model.dao.BasicDAO;

/**
 * @author CIDC
 * @date 22/03/2011
 *
 */
@LogTs({@LogT(cycle=2, date="22/03/2011", id="50", time=22, who="CIDC")})
public class BasicDAOTest {

	/**
	 * Constante con el path del properties a Probar
	 */
	private static final String PATH_PROPERTIES = "/Volumes/Archivos/ECOS_NotSync/workspace/midas-tsp/properties/defects.properties";
	/**
	 * Almancena Instancia BasicDAO
	 */
	private BasicDAO basicDAO;

	/**
	 * Inicializa variables
	 * @throws Exception
	 */
	@Before
	@Loc(size=1, type=LocControl.LocType.NUEVA, who="CIDC")
	public void setUp() throws Exception {
		basicDAO = new BasicDAO(PATH_PROPERTIES);
	}

	/**
	 * Prueba método BasicDAO.save
	 * @throws Exception
	 */
	@Test
	@Loc(size=6, type=LocControl.LocType.NUEVA, who="CIDC")
	public void testSave() throws Exception {
		Properties props = new Properties();
		FileInputStream fis = null;
		fis = new FileInputStream(PATH_PROPERTIES);
		props.load(fis);
		fis.close();
		basicDAO.save(props);
	}

	/**
	 * Prueba método BasicDAO.load
	 * @throws Exception
	 */
	@Test
	@Loc(size=2, type=LocControl.LocType.NUEVA, who="CIDC")
	public void testLoad() throws Exception {
		Properties prop = basicDAO.load();
		Assert.assertNotNull(prop);
	}
}
