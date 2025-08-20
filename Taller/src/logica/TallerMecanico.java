package logica;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;


public class TallerMecanico {
	
	//mapas para clientes y autos, clave:id - valor:objeto
	private Map<Integer,Cliente>clientes;
	private Map<Integer,Auto>autos;
	//scanner para leer lo que el usuario ingresa
	private Scanner lector;
	
	//constructor Taller Mecanico
	public TallerMecanico() {
		lector = new Scanner(System.in);//inicializando lector
		
		//inicializando mapas clientes y autos
		clientes = new HashMap<>();
		autos = new HashMap<>();
		
		Cliente cliente1 = new Cliente(1230,"Luis", "Cruz","San fernando 15 tlalpan cdmx");
		Cliente cliente2 = new Cliente(1231,"Maria", "Salas","San fernando 15 tlalpan cdmx");
		Auto auto1 = new Auto("12Q5Z","Vento","Rojo","BBXU29",10001,1230);
		Auto auto2 = new Auto("14f5f","Spark","plata","29FER4",10002,1230);
		Auto auto3 = new Auto("1d34t","focus","negro","APXU24",10003,1231);
		Auto auto4 = new Auto("1vt41","ferrari","azul","G5X29T",10004,1231);
		Auto auto5 = new Auto("1Zf45","tsuru","Rojo","B4U5Y9",10005,1230);
		autos.put(auto1.getIdAuto(), auto1);
		autos.put(auto2.getIdAuto(), auto2);
		autos.put(auto3.getIdAuto(), auto3);
		autos.put(auto4.getIdAuto(), auto4);
		autos.put(auto5.getIdAuto(), auto5);
		clientes.put(cliente1.getIdCliente(), cliente1);
		clientes.put(cliente2.getIdCliente(), cliente2);
		cliente1.addAuto(10001);
		cliente1.addAuto(10002);
		cliente2.addAuto(10003);
		cliente2.addAuto(10004);
		cliente1.addAuto(10005);
	}
	
	//metodo leer texto
	private String leerTexto(String mensaje) {
		System.out.print(mensaje);
		return lector.nextLine();
	}
	
	//metodo leer enteros
	private int leerEntero(String mensaje) {
		//valor que ingresa el usuario
		int valor = 0;
		//verificar que EL NUMERO SEA ENTERO
		boolean valido = false;
		//mientas sea falso
		while (!valido) {
			try {
				System.out.print(mensaje);
				valor = Integer.parseInt(lector.nextLine()); //convertir la cadena en entero
				valido = true;
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Debes ingresar un número entero.");
			}
		}
		return valor;
	}

	//Alta cliente
	private void altaCliente() {
		//pedir el numero de cliente
		int idCliente = leerEntero("ID del cliente: ");
		//si la clave está en el mapa clientes ya no se debe registrar
		if (clientes.containsKey(idCliente)){
			System.out.println("Cliente existente");
			return;
		}
		//si no está se procede al registro
		
		String nombre = leerTexto("Ingrese nombre: ");
		String apellido = leerTexto("Ingrese apellido paterno: ");
		String domicilio = leerTexto("Ingrese domicilio: ");
		//creacion de nuevo objeto cliente
		Cliente nuevo = new Cliente(idCliente,nombre,apellido,domicilio);
		//guardar el objeto cliente en el mapa, como clave id
		clientes.put(idCliente, nuevo);
		System.out.println("Cliente registrado exitosamente");
		System.out.println(nuevo.toString());
		}
	
	//Baja Cliente
	private void eliminarCliente() {
		//pedir idCliente 
		int idCliente = leerEntero("ID del cliente:");
		//metodo remove devuelve el valor de la clave asociada o null si no existe
		if (clientes.get(idCliente)==null) {
			System.out.println("Cliente NO existentente");
			return;
		}
		//verificar el estatuto de sus autos
		boolean tieneAutosEnTaller = false;
		for (int idAutosEliminar : clientes.get(idCliente).getAutos()) {
		    Auto auto = autos.get(idAutosEliminar);
		    if (auto != null && auto.isStatus()) {
		        tieneAutosEnTaller = true;
		        break;
		    }
		}
		if (tieneAutosEnTaller) {
			System.out.println("No se puede eliminar el cliente porque tiene autos en taller");
			return;
		}
		
		Cliente eliminado = clientes.remove(idCliente);
		//si eliminado exixte y es diferente a null
		if (eliminado!= null) {
			//recorrer la lista de autos asociados a este cliente y eliminarlos igual.
			for (Integer idAutoEliminar : eliminado.getAutos()) {
				 autos.remove(idAutoEliminar);
			}
			System.out.println("Cliente eliminado correctamente");
		}else {
			
		}
	}
	//Consulta Cliente
	private void consultaCliente(){
		//ingresar id del cliente
		int id = leerEntero("ID del cliente: ");
		//metodo get para obtener el cliente con 
		Cliente consulta = clientes.get(id);
		//get retorna null si no existe un cliente con esa id
		if (consulta != null) {
			System.out.println(consulta.toString());
		}else {
			System.out.println("Cliente NO existentente");
		}
		
	}
	
	//Modificación Cliente
	private void modificacionCliente() {
		//pedir id cliente
		int id = leerEntero("Id Cliente: ");
		//metodo get para obtener el cliente con 
		Cliente cliente = clientes.get(id);
		//get retorna null si no existe un cliente con esa id
		if (cliente != null) {
			String nuevoNombre = leerTexto("Ingrese nuevo nombre:");
			String nuevoApellido = leerTexto("Ingrese nuevo apellido");
			String nuevoDomicilio = leerTexto("Ingrese nuevo Domicilio");
			cliente.setApellido(nuevoApellido);
			cliente.setDomicilio(nuevoDomicilio);
			cliente.setNombre(nuevoNombre);
			System.out.println("Cliente modificado con exito");
			System.out.println(cliente.toString());
		}else {
			System.out.println("Cliente NO existentente");
		}
				
	}
	
	//Alta Auto
	private void altaAuto() {
		//comprobar primero que el id auto no exista
		int idAuto = leerEntero("Id Auto: ");
		if ( autos.containsKey(idAuto)) {
			System.out.println("Auto ya registrado");
			//imprime la información del auto registrado 
			System.out.println(autos.get(idAuto).toString());
			return ;
		}
		//comprobar que si hay un cliente registrado.
		
		int idCliente = leerEntero("ID del cliente: ");
		
		if (clientes.containsKey(idCliente)){
		//si está la clave:	
			System.out.println("Cliente existente");
			String ns = leerTexto("Ingrese numero de serie del auto: ");
			String modelo = leerTexto("Ingrese el modelo del auto: ");
			String color = leerTexto("Ingrese el color del auto: ");
			String placas = leerTexto("Ingrese las placas del auto: ");
			
			//creacion de nuevo objeto auto
			Auto nuevo = new Auto(ns,modelo,color,placas,idAuto,idCliente);
			//guardar el objeto cliente en el mapa, como clave id
			autos.put(idAuto, nuevo);
			//almacenar elauto nuevo a la lista de autos del cliente que corresponda.
			clientes.get(idCliente).addAuto(idAuto);
			System.out.println("Auto registrado exitosamente");
			System.out.println(nuevo.toString());
						
		}else {
			System.out.println("Cliente NO existentente. Favor de registrar primero al cliente");
		}

	}
	
	//Baja Auto
	private void eliminarAuto() {
		//pedir el id Auto 
		int idAuto = leerEntero("ID del Auto: ");
		//metodo remove devuelve el valor de la clave eliminada
		if(autos.get(idAuto).isStatus()) {
			System.out.println("El auto no se puede eliminar ya que está en taller");
			return ;
		}
		Auto eliminado = autos.remove(idAuto);
		if (eliminado!= null) {
			//obtener el id cliente asociado a ese auto
			 int idCliente = eliminado.getId_cliente();
			
			//eliminar de ese cliente el id_auto de la lista autos
			clientes.get(idCliente).removeAuto(idAuto);
			
			System.out.println("Auto eliminado correctamente");
		}else {
			System.out.println("Auto no existente");
		}
	}
	
	//Consulta Auto
	private void consultaAuto(){
		//pedir id auto
		int idAuto = leerEntero("Ingrese el ID del Auto: ");
		
		//metodo get para obtener el auto con esa id 
		Auto consulta = autos.get(idAuto);
		//get retorna null si no existe un cliente con esa id
		if (consulta != null) {
			System.out.println(consulta.toString());
		}else {
			System.out.println("Auto NO existentente");
		}
	}
	
	//Modificar Auto
	private void modificacionAuto() {
		//ingresar id auto
		int idAuto = leerEntero("Ingrese el ID del auto");
		Auto auto = autos.get(idAuto);
		
		//get retorna null si no existe un cliente con esa id
		if (auto != null) {
			String nuevoNS = leerTexto("Ingrese nuevo numero de serie");
			String nuevoModelo = leerTexto("Ingrese nuevo modelo");
			String nuevoColor = leerTexto("Ingrese nuevo color");
			String nuevaPlaca = leerTexto("Ingrese nueva placa");
			
			auto.setColor(nuevoColor);
			auto.setModelo(nuevoModelo);
			auto.setnSerie(nuevoNS);
			auto.setPlacas(nuevaPlaca);
			
			System.out.println("Auto modificado con exito");
			System.out.println(auto.toString());
		}else {
			System.out.println("Auto NO existentente");
		}
				
	}
	
	//Entregar Auto
	private void entregarAuto() {
		int idAuto = leerEntero("Ingrese ID auto a entregar: ");
		// si está el id en el mapa autos
		if (autos.containsKey(idAuto)) {
			autos.get(idAuto).setStatus(false);
			System.out.println("Estatus en taller modificado con exito");
			System.out.println(autos.get(idAuto).toString());
		}else {
			System.out.println("Auto NO existentente");
		}
		
	}
	
	//Recibir Auto
	private void recibirAuto() {
		int idAuto = leerEntero("Ingrese ID auto a recibir");
		// si está el id en el mapa autos
		if (autos.containsKey(idAuto)) {
			autos.get(idAuto).setStatus(true);
			System.out.println("Estatus en taller modificado con exito");
			System.out.println(autos.get(idAuto).toString());
		}else {
			System.out.println("Auto NO existentente");
		}
	}

	public void mostrarMenu(){
		int opcion;
		do {
			System.out.println("\n------- MENÚ -------");
	        System.out.println("1. Alta cliente");
	        System.out.println("2. Baja cliente");
	        System.out.println("3. Consulta cliente");
	        System.out.println("4. Modificar cliente");
	        System.out.println("5. Alta auto");
	        System.out.println("6. Baja auto");
	    	System.out.println("7. Consulta auto");
	        System.out.println("8. Modificar auto");
	        System.out.println("9. Recibir auto");
	    	System.out.println("10. Entregar auto");
	        System.out.println("0. Salir");

	        opcion = leerEntero("Seleccione una opción: ");
	        
	        switch(opcion) {
	        case 1:
	        	altaCliente();
	        	break;
	        case 2:
	        	eliminarCliente();
	        	break;
	        case 3:
	        	consultaCliente();
	        	break;
	        case 4:
	        	modificacionCliente();
	        	break;
	        case 5:
	        	altaAuto();
	        	break;
	        case 6:
	        	eliminarAuto();
	        	break;
	        case 7:
	        	consultaAuto();
	        	break;
	        case 8:
	        	modificacionAuto();
	        	break;
	        case 9:
	        	recibirAuto();
	        	break;
	        case 10:
	        	entregarAuto();
	        	break;
	        case 0:
	        	System.out.println("Vuelva pronto");
	        	break;
	        default:
	        	System.out.println("Opción inválida.");
	        }

		}while(opcion != 0);
	}

}
