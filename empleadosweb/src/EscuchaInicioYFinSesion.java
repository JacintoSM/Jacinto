

import java.util.Map;

import javax.servlet.ServletContext;
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
    	//TODO SUMAR UNO A LA VARIABLE sesionesactivas
    	//PRESENTE EN EL SAQUITO DEL CONTEXTO
    	ServletContext sc = sesion.getServletContext();//pillo el saco
    	int sesionesactivas = (int)sc.getAttribute("sesionesactivas");//pillo la var
    	sesionesactivas = sesionesactivas+1;//incremento
    	sc.setAttribute("sesionesactivas", sesionesactivas);//actualizo
    	log.debug("Sesiones activas = " +sesionesactivas);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	HttpSession sesion = arg0.getSession();
    	String id_sesion = sesion.getId();
    	log.debug("Sesión destruida = " + id_sesion);
    	
    	ServletContext sc = sesion.getServletContext();//pillo el saco
    	int sesionesactivas = (int)sc.getAttribute("sesionesactivas");//pillo la var
    	sesionesactivas = sesionesactivas-1;//incremento
    	sc.setAttribute("sesionesactivas", sesionesactivas);//actualizo
    	
    	//TODO ACTUALIZO EL MAPA DE SESIONES Y ELIMINO LA
    	//QUE ACABA DE DESTRUIRSE
    	Map<String, String> mapa_nombre_sesion =
		(Map<String, String>)sc.getAttribute("mapa_nombre_sesion");
    	mapa_nombre_sesion.remove(id_sesion);
    	log.debug("Sesión extraída del mapa = " + id_sesion);
    	
    	
    	log.debug("Sesiones activas = " +sesionesactivas);
    }
	
}
