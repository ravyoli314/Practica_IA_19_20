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
	Herramienta herramienta; // herramienta que tiene en mano
	String area;
	int tiempoTotalTrabajado;
	Tiempo tiempo;
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
		this.herramienta = null; // en la primera iteración, el trabajador NO tiene u objeto herramienta (==null)
		this.area = "A";
		this.tiempoTotalTrabajado = 0;
		this.tiempo = new Tiempo();
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
	public void setTiempoOcupado(int t) {
		this.tiempoOcupado = t;
	}

	public Herramienta getHerramienta () {
		return herramienta;
	}
	public void setHerramienta(Herramienta herramientaTarea) {
		this.herramienta = herramientaTarea;
	}

	public String getArea() {
		return this.area;
	}
	public void setArea(String areaTarea) {
		int tiempoDesplazamiento = tiempo.calcularTiempoTrayecto(this.area, areaTarea); 
		this.tiempoOcupado += tiempoDesplazamiento;
		this.tiempoTotalTrabajado += tiempoDesplazamiento;
		this.area = areaTarea;
	}

	public int getTiempoOcupado() {
		return tiempoOcupado;
	}
	public int getTiempoTotalTrabajado() {
		return tiempoTotalTrabajado;
	}
	public void setTiempoTotalTrabajado(int t) {
		this.tiempoTotalTrabajado = t;
	}

	public void disminuirTiempoOcupado(int tiempo) {
		if(tiempo > this.tiempoOcupado) {
			this.tiempoOcupado = 0;
		}
		else this.tiempoOcupado -= tiempo;
	}

	public void tiempoTarea(String tipoTarea, int unidadesTrabajo) { // aqui no tengo en cuenta la mejora de la herramienta
		int tiempoOcupado;
		tiempoOcupado = tiempo.tiempoTarea(tipoTarea, unidadesTrabajo, getHabPodar(), getHabLimpiar(), getHabReparar());
		// el tiempo que tarde en desplazarse lo calcula el setArea
		this.tiempoOcupado += tiempoOcupado;
		this.tiempoTotalTrabajado += tiempoOcupado;
	}
	
	public void cogerHerramienta(Herramienta herramientaNueva) {
		setArea("A"); // se encarga de añadirle el tiempo de desplazamiento (0 si ya esta en el almacen)
		this.herramienta = herramientaNueva;
	}


	public boolean herramientaCorrecta(String tarea) { // comparo que la herramienta del trabajador no sea nula y sea la de la tarea a realizar
		if(this.herramienta == null)
			return false;
		else if(this.herramienta.getTrabajo().equals(tarea))
			return true;
		else 
			return false;
	}

	public void terminarDia() {
		setArea("A");
		setHerramienta(null);
		setTiempoOcupado(0);
	}
	
	public void printTrabajador() {
		String herrActual;
		if(this.herramienta == null) herrActual = "null";
		else herrActual = this.herramienta.getNombre();
	
		System.out.println("TRABAJADOR - Nombre: " + this.nombre + ", tiempo ocupado (mins): " + this.tiempoOcupado + 
				", tiempo total trabajado (horas): " + this.tiempoTotalTrabajado/60 + ":" + this.tiempoTotalTrabajado%60 +
				", herramienta: " + herrActual + ", area: " + this.area);
	}


	/**************** PARTE 2. INFERENCIA AVANZADO ******************************/

	public void tiempoTarea(String tipoTarea, int unidadesTrabajo, int mejora) { // Tengo en cuenta la MEJORA de la herramienta
		int tiempoTrabajando; //tiempo en realizar la tarea.
		switch(tipoTarea) {
		case "podar":
			tiempoTrabajando= (unidadesTrabajo * 60) / (getHabPodar() + mejora);
			break;
		case "limpiar":
			tiempoTrabajando= (unidadesTrabajo * 60 ) / (getHabLimpiar() + mejora);
			break;
		case "reparar":
			tiempoTrabajando= (unidadesTrabajo * 60 ) / (getHabReparar() + mejora);
			break;
		default:
			tiempoTrabajando = 0;
		}

		this.tiempoOcupado += tiempoTrabajando;
		this.tiempoTotalTrabajado += tiempoTrabajando;
	}
	
	public void setArea(String areaTarea, double peso) { // Tengo en cuenta el PESO de la herramienta
		int tiempoDesplazamiento = (int) (tiempo.calcularTiempoTrayecto(this.area, areaTarea) + (tiempo.calcularTiempoTrayecto(this.area, areaTarea)/5) * peso);
		this.tiempoOcupado += tiempoDesplazamiento; 
		this.tiempoTotalTrabajado += tiempoDesplazamiento;
		System.out.println(this.nombre + " tiempo trayecto: " + tiempoDesplazamiento);
		this.area = areaTarea;
	}
	
}
