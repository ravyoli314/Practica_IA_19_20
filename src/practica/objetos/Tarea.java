package practica.objetos;

/**
 * Clase creada como objeto base para la pr�ctica 2019-2020 de Inteligencia Artificial, UC3M, Colmenarejo
 *
 * @author Daniel Amigo Herrero
 * @author David S�nchez Pedroche
 */

public class Tarea {

	// Variables del objeto Tarea
	String tipo;
	String area;
	int unidades;
	// A�ADIR LAS VARIABLES NECESARIAS


	/**
	 * Constructor para el objeto
	 * NO MODIFICAR LA LLAMADA DEL CONSTRUCTOR
	 */
	public Tarea(String tipo, String area, int unidades) {
		this.tipo = tipo;
		this.area = area;
		this.unidades = unidades;
		// A�adir el estado inicial (est�tico) de las variables que se a�adan
		// Si se necesita a�adir valores variables, como un ID, utilizar setters
	}
	
	// M�todos getters y setters
	/**
	 * A�adir (si procede) m�todos auxiliares, como getters o setters
	 */
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public void printTarea() {
		System.out.println("TAREA - tipo: " + this.tipo + ", area: " + this.area + ", unidades: " + this.unidades);
	}
	



}
