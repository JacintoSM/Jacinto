

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
