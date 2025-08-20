package logica;

public class Auto {
	//atributos
	private String nSerie;
	private String modelo;
	private String color;
	private String placas;
	private int idAuto;
	private boolean Status;
	
	//ver que cliente esta registrado con este auto.
	private int id_cliente;

	//constructores
	public Auto() {
	}
	
	public Auto(String nSerie, String modelo, String color, String placas, int idAuto, int id_cliente) {
		this.nSerie = nSerie;
		this.modelo = modelo;
		this.color = color;
		this.placas = placas;
		this.idAuto = idAuto;
		this.id_cliente = id_cliente;
	}

	//getters
	public String getnSerie() {
		return nSerie;
	}

	public String getModelo() {
		return modelo;
	}

	public String getColor() {
		return color;
	}

	public String getPlacas() {
		return placas;
	}

	public int getIdAuto() {
		return idAuto;
	}

	public int getId_cliente() {
		return id_cliente;
	}
	
	public boolean isStatus() {
		return Status;
	}

	//setters
	
	public void setnSerie(String nSerie) {
		this.nSerie = nSerie;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setPlacas(String placas) {
		this.placas = placas;
	}

	public void setIdAuto(int idAuto) {
		this.idAuto = idAuto;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	public void setStatus(boolean status) {
		Status = status;
	}

	
	//metodo ToString para consultar la información de auto

	@Override
	public String toString() {
		return "Auto"+ "\n SN: " + nSerie + "\n Modelo: " + modelo + "\n Color: " + color + "\n Placas: " + placas + "\n ID: "
				+ idAuto + "\n Id Cliente: " + id_cliente + "\n En taller: " + Status;
	}
	
	
}
