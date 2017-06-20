

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ConsultarMiInfo
 */
@WebServlet("/ConsultarMiInfo")
public class ConsultarMiInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarMiInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		ServletContext sc = request.getServletContext();
		Map<Integer, Empleado> m_empleados = 
				(Map<Integer, Empleado>)sc.getAttribute("mapae");
		
		HttpSession sesion = request.getSession(false);
		String id = (String)sesion.getAttribute("id");
		EmpleadoService es = new EmpleadoService();
		int id_empleado = Integer.parseInt(id);
		
		Empleado empleado =  m_empleados.get(id_empleado);//es.obtenerInfoEmpleado(id_empleado);
		
		request.setAttribute("empleado", empleado);
		request.getRequestDispatcher("miperfil.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
