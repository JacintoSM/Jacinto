

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class FiltroConsultaEmpleados
 */
@WebFilter("/ConsultaEmpleados")
public class FiltroConsultaEmpleados implements Filter {

	
	private final static Logger log = 
			Logger.getLogger("mylog");
    /**
     * Default constructor. 
     */
    public FiltroConsultaEmpleados() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		
		
		// pass the request along the filter chain
		//MENSAJE DE LOG INICIAL
		log.debug("PASA POR EL FILTRO ANTES");
		long tiempo_inicial = System.currentTimeMillis();
		chain.doFilter(request, response);
		log.debug("PASA POR EL FILTRO DEPUÉS");
		long tiempo_final = System.currentTimeMillis();
		long tiempo_total = tiempo_final-tiempo_inicial;
		log.debug("Tiempo_total " + tiempo_total);
		
		ServletContext sc = request.getServletContext();
		Long tms = (Long)sc.getAttribute("tms");
		
		if (tms == null)
		{
			//1ª vez
			sc.setAttribute("tms", tiempo_total);
			sc.setAttribute("peticiones", 1);
			log.debug("TIEMPO MEDIO = " + tiempo_total);
		} else 
		{
			//tms = (tms + tiempo_total) / 2;
			Integer peticiones = (Integer)sc.getAttribute("peticiones");
			peticiones = peticiones +1;
			tms = (tms + tiempo_total)/peticiones;
			
			sc.setAttribute("tms", tms);
			sc.setAttribute("peticiones", peticiones);
			log.debug("TIEMPO MEDIO = " + tms);
		}
		
		//MENSAJE DE LOG FINAL
		//http://192.168.1.40:9090/empleadosweb/ConsultaEmpleados
		/*ServletContext sc = request.getServletContext();
		Integer peticiones = (Integer)sc.getAttribute("peticiones");
		if (peticiones == null)
		{
			log.debug("No existe peticiones");
			sc.setAttribute("peticiones", 0);
		} else 
			{
				peticiones = peticiones + 1;
				sc.setAttribute("peticiones", peticiones);
				log.debug("Peticiones = " + peticiones);
			}*/
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
