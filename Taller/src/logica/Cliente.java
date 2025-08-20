package logica;
import java.util.List;
import java.util.ArrayList;

public class Cliente {
	//atributos
	private int idCliente;
	private String nombre;
	private String apellido;
	private String domicilio;
	
	//almacenar que datos estan regsitrados por un cliente.
	
	private List<Integer> autos;
	
	//constructores
	
	public Cliente() {
	}
	
	
	public Cliente(int idCliente, String nombre, String apellido, String domicilio) {
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		
		//se crea automaticamente una lista para agregar en un futuro autos asociados a este cliente 
		
		this.autos = new ArrayList<>();  
	}

	//getters
	public int getIdCliente() {
		return idCliente;
	}


	public String getNombre() {
		return nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public String getDomicilio() {
		return domicilio;
	}


	public List<Integer> getAutos() {
		return autos;
	}

    //setters
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}


	public void setAutos(List<Integer> autos) {
		this.autos = autos;
	}

	//adicionar los id de autos asociados a este cliente
	
	public void addAuto(Integer idAuto) {
		autos.add(idAuto);
	}
	
	//eliminbar los autos asociados a este cliente
	
	public void removeAuto(Integer idAuto) {
		autos.remove(idAuto);
	}
	
	//ToString para imprimir información
	@Override
	public String toString() {
		return "Cliente" + "\n Id: " + idCliente + "\n Nombre: " + nombre + "\n Apellido: " + apellido + "\n Domicilio: "
				+ domicilio + "\n Autos: " + autos;
	}
	

	
	
	
	
	
	
}
