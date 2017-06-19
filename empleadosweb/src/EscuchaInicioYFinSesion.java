

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class EscuchaInicioYFinSesion
 *
 */
@WebListener
public class EscuchaInicioYFinSesion implements HttpSessionListener {

    /**
     * Default constructor. 
     */
	
	private final static Logger log = Logger.getLogger("mylog");
    
	public EscuchaInicioYFinSesion() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	HttpSession sesion = arg0.getSession();
    	String id_sesion = sesion.getId();
    	log.debug("Sesión creada = " + id_sesion);
    	
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	HttpSession sesion = arg0.getSession();
    	String id_sesion = sesion.getId();
    	log.debug("Sesión destruida = " + id_sesion);
    }
	
}
