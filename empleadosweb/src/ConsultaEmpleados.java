

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class ConsultaEmpleados
 */
@WebServlet("/ConsultaEmpleados")
public class ConsultaEmpleados extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger log = 
			Logger.getLogger("mylog");
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaEmpleados() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		log.debug("Entro en DoGet de Consulta empleados");
		EmpleadoService es = new EmpleadoService();
		List<Empleado> le = es.getEmpleados();
	
		log.debug("Lista de empleados recuperada");
		for (Empleado e : le)
		{
			System.out.println(e.getNombre() + " "+ e.getId());
		}
		
		log.debug("redirijo al jsp");
		//TODO falta generera el jsp
		request.setAttribute("lempleados", le);
		request.getRequestDispatcher("listaempleados.jsp")
		.forward(request, response);
		//response.getWriter().append("LLAMO A GET Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
