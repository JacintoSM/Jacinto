

import java.util.List;

public class EmpleadoService {
	
	public Empleado obtenerInfoEmpleado (int id)
	{
		Empleado empleado = null;
		EmpleadoBD empleadoBD = null;
			
			empleadoBD = new EmpleadoBD();
			empleado = empleadoBD.obtenerEmpleadoBD(id);
		
		return empleado;
		
	}
	
	public List<Empleado> getEmpleados ()
	{
		List<Empleado> l_empleados = null;
		
			EmpleadoBD empleadoBD = new EmpleadoBD();
			l_empleados = empleadoBD.getEmpleadosBD();
		
		return l_empleados;
	}
	
	public List<Empleado> getEmpleadosByDpto (int ndpto)
	{
		List<Empleado> l_empleados = null;
		
			EmpleadoBD empleadoBD = new EmpleadoBD();
			l_empleados = empleadoBD.getEmpleadosBDByDpto(ndpto);
		
		return l_empleados;
	}
	
	public boolean validarUsuario 
	(String nombre, String pwd)
	{
		boolean valido = false;
		EmpleadoBD empleadoBD = null;
		
			empleadoBD = new EmpleadoBD();
			valido = empleadoBD.existeEmpleadoEnBD(nombre, pwd);
			
		return valido;
	}

}
