

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class EscuchaInicioYFinContexto
 *
 */
@WebListener
public class EscuchaInicioYFinContexto implements ServletContextListener {

	private final static Logger log = Logger.getLogger("mylog");
    /**
     * Default constructor. 
     */
    public EscuchaInicioYFinContexto() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  {
    	log.debug("Contexto Destruido");
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	log.debug("Contexto Iniciado");
    	
    	
    	ServletContext sc = arg0.getServletContext();//accedo al saquito del contexto
    	int numsesiones = 0;//inicio cuenta de sesiones
    	sc.setAttribute("sesionesactivas", numsesiones);//meto en el saco de contexto las sesiones a cero
    	
    	Map<String, String> mapa_nombre_sesion = 
    			new HashMap<String, String>();
    	sc.setAttribute("mapa_nombre_sesion", mapa_nombre_sesion);
    	log.debug("Mapa sesiones vacío en el contexto");
    	
    	
    	/*List<String> lsesiones = new ArrayList<String>();
    	ServletContext sc = arg0.getServletContext();
    	sc.setAttribute("lsesiones", lsesiones);*/
         // TODO Auto-generated method stub
    }
	
}
