

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger log = 
			Logger.getLogger("mylog");
	  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String usuario = null;
		String pwd = null;
		
			usuario = request.getParameter("usuario");
			pwd = request.getParameter("pwd");
			
			log.debug("USUARIO Y PWD " + usuario + " " +pwd);
		
			EmpleadoService es = new EmpleadoService();
			boolean valido = es.validarUsuario(usuario, pwd);
			
			if (valido)
			{
				log.debug("USUARIO EXISTE");
				//el usuario existe
				//le mando al jsp de bienvenida
				HttpSession sesion = request.getSession();
				sesion.setAttribute("nombre", usuario);
				sesion.setAttribute("id", pwd);
				
				//ACTUALIZAR EL MAPA DEL CONTEXTO
				//INTRODUCIENDO LA NUEVA SESIÓN Y EL NOMBRE
				ServletContext sc = request.getServletContext();
				Map<String, String> mapa_nombre_sesion =
				(Map<String, String>)sc.getAttribute("mapa_nombre_sesion");
				String clave_sesion = sesion.getId();
				mapa_nombre_sesion.put(clave_sesion, usuario);
				log.debug("Mapa sesion actualizado ");
				
				
				//request.getRequestDispatcher("inicio.jsp").forward(request, response);
				request.getRequestDispatcher("inicio.jsp").include(request, response);
				request.getRequestDispatcher("mostrar_sesiones.jsp").include(request, response);
				
				
				
				
				
				/*ServletContext sc = request.getServletContext();
				List<String> lsesiones = (List<String>)sc.getAttribute("lsesiones");
				lsesiones.add(usuario);
				sc.setAttribute("lsesiones", lsesiones);
				request.getRequestDispatcher("inicio.jsp").include(request, response);
				request.getRequestDispatcher("listasesion.jsp").include(request, response);
				*/
			} else {
				log.debug("USUARIO NO EXISTE");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				//response.sendRedirect("error.jsp");
				//el usuario no existe
				//le mando a una página de error
			}
			
		
	}

}
