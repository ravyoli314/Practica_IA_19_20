package practica.objetos;

/**
 * Clase creada como objeto base para la pr�ctica 2019-2020 de Inteligencia Artificial, UC3M, Colmenarejo
 *
 * @author Daniel Amigo Herrero
 * @author David S�nchez Pedroche
 */

public class Herramienta {

	// Variables del objeto Herramienta
	String nombre;
	String trabajo;
	double peso;
	int mejora;
	int cantidad;
	final int MaxCantidad;
	// A�ADIR LAS VARIABLES NECESARIAS

	/**
	 * Constructor para el objeto
	 * NO MODIFICAR LA LLAMADA DEL CONSTRUCTOR
	 */
	public Herramienta(String nombre, String trabajo, double peso, int mejora, int cantidad) {
		this.nombre = nombre;
		this.trabajo = trabajo;
		this.peso = peso;
		this.mejora = mejora;
		this.cantidad = cantidad;
		this.MaxCantidad = cantidad;

		// A�adir el estado inicial (est�tico) de las variables que se a�adan
		// Si se necesita a�adir valores variables, como un ID, utilizar setters
	}

	/**
	 * M�todos getters y setters
	 */
	// A�adir (si procede) m�todos auxiliares, como getters o setters

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTrabajo() {
		return trabajo;
	}
	public void setTrabajo(String trabajo) {
		this.trabajo = trabajo;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public int getMejora() {
		return mejora;
	}
	public void setMejora(int mejora) {
		this.mejora = mejora;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}	
	public int getMaxCantidad() {
		return MaxCantidad;
	}
}
