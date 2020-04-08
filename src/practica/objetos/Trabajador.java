package practica.objetos;

/**
 * Clase creada como objeto base para la pr�ctica 2019-2020 de Inteligencia Artificial, UC3M, Colmenarejo
 *
 * @author Daniel Amigo Herrero
 * @author David S�nchez Pedroche
 */

public class Trabajador {

	// Variables del objeto Trabajador
	String nombre;
	int habPodar;
	int habLimpiar;
	int habReparar;
	int tiempoOcupado; // tiempo de trabajo restante (en minutos)
	String herramienta; // herramienta que tiene en mano
	String area;
	// A�ADIR LAS VARIABLES NECESARIAS

	/**
	 * Constructor para el objeto
	 * NO MODIFICAR LA LLAMADA DEL CONSTRUCTOR
	 */
	public Trabajador(String nombre, int habPodar, int habLimpiar, int habReparar) {
		this.nombre      = nombre;
		this.habPodar    = habPodar;
		this.habLimpiar  = habLimpiar;
		this.habReparar  = habReparar;
		this.tiempoOcupado = 0;
		this.herramienta = "";
		this.area = "A";
		// A�adir el estado inicial (est�tico) de las variables que se a�adan
		// Si se necesita a�adir valores variables, como un ID, utilizar setters
	}
	
	/**
	 * A�adir (si procede) m�todos auxiliares, como getters o setters
	 */
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getHabPodar() {
		return habPodar;
	}
	public void setHabPodar(int habPodar) {
		this.habPodar = habPodar;
	}
	public int getHabLimpiar() {
		return habLimpiar;
	}
	public void setHabLimpiar(int habLimpiar) {
		this.habLimpiar = habLimpiar;
	}
	public int getHabReparar() {
		return habReparar;
	}
	public void setHabReparar(int habReparar) {
		this.habReparar = habReparar;
	}
	public int getTiempoOcupado() {
		return tiempoOcupado;
	}
	public String getHerramienta () {
		return herramienta;
	}
	public void setHerramienta(String herramientaTarea) {
		this.herramienta = herramientaTarea;
	}
	
	public String getArea() {
		return this.area;
	}
	public void setArea(String areaTarea) {
		this.area = areaTarea;
	}
	
	
	public void aumentarTiempoOcupado(int tiempo) { // igual no hace falta
		this.tiempoOcupado += tiempo;
	}
	
	public void disminuirTiempoOcupado(int tiempo) {
		this.tiempoOcupado -= tiempo;
	}
	
	public void tiempoTarea(String tipoTarea, int unidadesTrabajo) {
		int tiempoOcupado;
		switch(tipoTarea) {
		case "podar":
			tiempoOcupado= (unidadesTrabajo / getHabPodar()) * 60;
			break;
		case "limpiar":
			tiempoOcupado= (unidadesTrabajo / getHabLimpiar()) * 60 ;
			break;
		case "reparar":
			tiempoOcupado= (unidadesTrabajo / getHabReparar()) * 60;
			break;
		default:
			tiempoOcupado = 0;
		}
		
		this.tiempoOcupado += tiempoOcupado;
	}
	
	public void cogerHerramienta(String tarea) { // en principio mi herramienta es limpiar, reparar, podar (no entro en detalle)
		if(!this.area.equals("A")) { // si no está en el almacén le añado el tiempo de trayecto hasta él
			this.tiempoOcupado += calcularTiempoTrayecto(area, "A");
			this.area = "A";
		}
		this.herramienta = tarea;	
		
	}
	
	public int calcularTiempoTrayecto(String origen, String destino) { // HALLAR EL CAMINO MÁS CORTO!! AMPLITUD O PROFUNDIDAD??
		// if adyacentes return 5, else => desplazarme 1 (RECURSIVIDAD??)
		return 5; // por ej hasta que lo complete
	}
	
	
}
