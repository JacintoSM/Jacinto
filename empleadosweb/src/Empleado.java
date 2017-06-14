

public class Empleado {
	
	private String nombre;
	private int id;
	private String apellido;
	private String email;
	

	public Empleado(String nombre, int id, String apellido, String email) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.apellido = apellido;
		this.email = email;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public Empleado(String nombre, int id) {
		super();
		this.nombre = nombre;
		this.id = id;
	}
	
	public Empleado(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public Empleado ()
	{
		
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	

}
