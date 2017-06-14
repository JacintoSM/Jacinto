

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class EmpleadoBD {
	
	/*
	static {
		// registro el driver
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	public Empleado obtenerEmpleadoBD (int id)
	{
		Empleado empleado = null;
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		
			try{
				conexion = Pool.getConnection();
				st = conexion.createStatement();
				rs = st.executeQuery("SELECT EMPLOYEE_ID"
								+", FIRST_NAME"+
								", LAST_NAME " 
								+ " , EMAIL FROM "
								+ "EMPLOYEES WHERE "
								+ "EMPLOYEE_ID = "
								+ id);
				rs.next();//me sitúo en el primer registro
				int nempleado =rs.getInt("EMPLOYEE_ID");
				String fname = rs.getString("FIRST_NAME");
				String lname = rs.getString("LAST_NAME");
				String email = rs.getString("EMAIL");
				empleado = new Empleado
						(fname, nempleado, lname, email);
				
			}catch (Exception e) {
				// TODO: handle exception
			}finally {
				Pool.liberarRecursos(conexion, st, rs);
			}
		
		
		return empleado;
	}
	
	public boolean existeEmpleadoEnBD 
	(String nombre, String pwd)
	{
		boolean existe = false;
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		
			try{
				
				int ipwd = Integer.parseInt(pwd);
				conexion = Pool.getConnection();
				st = conexion.createStatement();
				rs = st.executeQuery("SELECT"
						+ " EMPLOYEE_ID FROM"
						+ " EMPLOYEES WHERE"
						+ " FIRST_NAME = '"
						+ nombre+"' AND "
						+ " EMPLOYEE_ID = "
						+ipwd
						);
				existe = rs.next();
			}catch (Exception e) {
				//TODO usar el log
				e.printStackTrace();
			} finally {
				Pool.liberarRecursos(conexion, st, rs);
			}
		
		return existe;
	}
	
	public List<Empleado> getEmpleadosBD ()
	{
		List<Empleado> l_empleados = null;
		


		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;

		try {
			
			// obtengo la conexión                                     //192.168.1.40:9090/empleadosweb/consulta.html
			connection = Pool.getConnection(); //DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "adalid");
			// creo el statement
			statement = connection.createStatement();
			// ejecuto la consulta
			resultset = statement.executeQuery("SELECT FIRST_NAME, EMPLOYEE_ID FROM EMPLOYEES ORDER BY EMPLOYEE_ID ASC ");

			l_empleados = new ArrayList<Empleado>();
			Empleado e_auxiliar = null;
			String nombre = null;
			int id = -1;
			
			while (resultset.next()) {
				nombre = resultset.getString("FIRST_NAME");
				id = resultset.getInt("EMPLOYEE_ID");
				e_auxiliar = new Empleado(nombre, id);
				l_empleados.add(e_auxiliar);
				
				System.out.println(nombre);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				
				Pool.liberarRecursos
				(connection, statement, resultset);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return l_empleados;
	}
	
	
	public List<Empleado> getEmpleadosBDByDpto (int dpto)
	{
		List<Empleado> l_empleados = null;
		


		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;

		try {
			
			// obtengo la conexión
			connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "adalid");
			// creo el statement
			statement = connection.createStatement();
			// ejecuto la consulta
			resultset = statement.executeQuery("SELECT FIRST_NAME FROM EMPLOYEES WHERE DEPARTMENT_ID = " +dpto);

			l_empleados = new ArrayList<Empleado>();
			Empleado e_auxiliar = null;
			
			while (resultset.next()) {
				String nombre = resultset.getString("FIRST_NAME");
				e_auxiliar = new Empleado(nombre);
				l_empleados.add(e_auxiliar);
				
				System.out.println(nombre);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				resultset.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return l_empleados;
	}
	

}
