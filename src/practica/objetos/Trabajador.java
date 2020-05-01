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
		int tiempoDesplazamiento = calcularTiempoTrayecto(this.area, areaTarea); 
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

	public void disminuirTiempoOcupado(int tiempo) {
		if(tiempo > this.tiempoOcupado) {
			this.tiempoOcupado = 0;
		}
		else this.tiempoOcupado -= tiempo;
	}

	public void tiempoTarea(String tipoTarea, int unidadesTrabajo) { // aqui no tengo en cuenta la mejora de la herramienta
		int tiempoOcupado;
		switch(tipoTarea) {
		case "podar":
			tiempoOcupado= (unidadesTrabajo * 60) / getHabPodar();
			break;
		case "limpiar":
			tiempoOcupado= (unidadesTrabajo * 60 ) / getHabLimpiar();
			break;
		case "reparar":
			tiempoOcupado= (unidadesTrabajo * 60 ) / getHabReparar();
			break;
		default:
			tiempoOcupado = 0;
		}
		// el tiempo que tarde en desplazarse lo calcula el setArea
		this.tiempoOcupado += tiempoOcupado;
		this.tiempoTotalTrabajado += tiempoOcupado;
	}

	public void cogerHerramienta(Herramienta herramientaNueva) {
		setArea("A"); // se encarga de añadirle el tiempo de desplazamiento (0 si ya esta en el almacen)
		this.herramienta = herramientaNueva;
	}

	public int calcularTiempoTrayecto(String origen, String destino) {
		int tiempo = 0;

		if(origen.equals(destino)) return 0;
		if(origen.equals("A")) {
			if(destino.equals("J3") || destino.equals("C2") || destino.equals("J2")) tiempo = 5;
			else if (destino.equals("R") || destino.equals("C1") || destino.equals("J1") || destino.equals("U")) tiempo = 10;
			else if(destino.equals("B")) tiempo = 15;
		} else if(origen.equals("R")) {
			if(destino.equals("J3")) tiempo = 5;
			else if(destino.equals("A")) tiempo = 10;
			else if(destino.equals("C2") || destino.equals("J2")) tiempo = 15;
			else if(destino.equals("C1") || destino.equals("J1") || destino.equals("U")) tiempo = 20;
			else if(destino.equals("B")) tiempo = 25;
		} else if(origen.equals("J3")) {
			if(destino.equals("R") || destino.equals("A")) tiempo = 5; 
			else if(destino.equals("C2") || destino.equals("J2")) tiempo = 10;
			else if(destino.equals("C1") || destino.equals("J1") || destino.equals("U")) tiempo = 15;
			else if(destino.equals("B")) tiempo = 20;
		} else if(origen.equals("C2")) {
			if(destino.equals("A") || destino.equals("J1") || destino.equals("C1"))tiempo = 5;
			else if(destino.equals("J3") || destino.equals("J1") || destino.equals("U")) tiempo = 10;
			else if(destino.equals("R") || destino.equals("B")) tiempo = 15;
		} else if(origen.equals("C1")) {
			if(destino.equals("C2") || destino.equals("J2") || destino.equals("J1") || destino.equals("U")) tiempo = 5;
			else if(destino.equals("A") || destino.equals("U") || destino.equals("B")) tiempo = 10;
			else if(destino.equals("J3"))  tiempo = 15;
			else if(destino.equals("R")) tiempo = 20;
		} else if(origen.equals("J2")) {
			if(destino.equals("A") || destino.equals("C2") || destino.equals("C1") || destino.equals("J1") || destino.equals("U")) tiempo = 5;
			else if(destino.equals("B") || destino.equals("J3")) tiempo = 10;
			else if(destino.equals("R")) tiempo = 15;
		} else if(origen.equals("J1")) {
			if(destino.equals("J2") || destino.equals("C1") || destino.equals("B")) tiempo = 5;
			else if(destino.equals("A") || destino.equals("C2") || destino.equals("U")) tiempo = 10;
			else if(destino.equals("J3")) tiempo = 15;
			else if(destino.equals("R")) tiempo =20;
		} else if(origen.equals("U")) {
			if(destino.equals("J2") || destino.equals("B")) tiempo = 5;
			else if(destino.equals("J1") || destino.equals("C1") || destino.equals("C2") || destino.equals("A")) tiempo = 10;
			else if(destino.equals("J3")) tiempo = 15;
			else if(destino.equals("R")) tiempo = 20;
		}  else if(origen.equals("B")) {
			if(destino.equals("J1") || destino.equals("U")) tiempo = 5;
			else if(destino.equals("C1") || destino.equals("J2")) tiempo = 10;
			else if(destino.equals("C2") || destino.equals("A")) tiempo = 15;
			else if(destino.equals("J3")) tiempo = 20;
			else if (destino.equals("J3")) tiempo = 20;
		}
		return tiempo;
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
		int tiempoDesplazamiento = (int) (calcularTiempoTrayecto(this.area, areaTarea) + (calcularTiempoTrayecto(this.area, areaTarea)/5) * peso);
		this.tiempoOcupado += tiempoDesplazamiento; 
		this.tiempoTotalTrabajado += tiempoDesplazamiento;
		System.out.println(this.nombre + " tiempo trayecto: " + tiempoDesplazamiento);
		this.area = areaTarea;
	}
	
}
