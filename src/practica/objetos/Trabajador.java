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
	int TiempoTrabajado;
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
		this.herramienta = null;
		this.area = "A";
		this.TiempoTrabajado = 0;
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
		this.area = areaTarea;
	}
	
	public int getTiempoOcupado() {
		return tiempoOcupado;
	}
	
	public void disminuirTiempoOcupado(int tiempo) {
		if(tiempo > this.tiempoOcupado) {
			this.tiempoOcupado = 0;
		}
		else this.tiempoOcupado -= tiempo;
	}
	
	public void tiempoTarea(String tipoTarea, int unidadesTrabajo, String origen, String destino) {
		int tiempoOcupado;
		switch(tipoTarea) {
		case "podar":
			tiempoOcupado= (unidadesTrabajo * 60) / getHabPodar();
			break;
		case "limpiar":
			tiempoOcupado= (unidadesTrabajo * 60 )/ getHabLimpiar();
			break;
		case "reparar":
			tiempoOcupado= (unidadesTrabajo * 60 ) / getHabReparar();
			break;
		default:
			tiempoOcupado = 0;
		}
		// AÑADIR LO QUE TARDA EN DESPLAZARSE A ESE AREA DE LA TAREA 
	
		this.tiempoOcupado += calcularTiempoTrayecto(origen, destino);
		this.tiempoOcupado = this.tiempoOcupado + tiempoOcupado;
	}

	public void cogerHerramienta(Herramienta herramientaNueva) { // en principio mi herramienta es limpiar, reparar, podar (no entro en detalle)
		//Si esta en el almacen, tiempo que tarde en ir al lugar.
		if(!this.area.equals("A")) {
			this.tiempoOcupado += calcularTiempoTrayecto(area, "A");
			this.area = "A";
		}
		this.herramienta = herramientaNueva;
	}
	
	public int calcularTiempoTrayecto(String origen, String destino) {
		int tiempo = 0;
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
		return this.tiempoOcupado = this.tiempoOcupado + tiempo;
	}
	
	public boolean herramientaCorrecta(String tarea) { // comparo que la herramienta del trabajador no sea nula y sea la de la tarea a realizar
		if(this.herramienta == null)
			return false;
		else if(this.herramienta.getTrabajo() == tarea)
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
		System.out.println(this.nombre + " " + this.tiempoOcupado + " mins " + 
	this.herramienta.getNombre() + " " + this.herramienta.getTrabajo() + " " + this.area);
	}
	
	
	
	/**************** PARTE 2. INFERENCIA AVANZADO ******************************/
	
	public void tiempoTarea(String tipoTarea, int unidadesTrabajo, int mejora, double peso, String origen, String destino) {
		int tiempoOcupado;
		switch(tipoTarea) {
		case "podar":
			//+1 restos orgánicos
			tiempoOcupado= (unidadesTrabajo * 60) / (getHabPodar() + mejora);
			break;
		case "limpiar":
			tiempoOcupado= (unidadesTrabajo * 60 ) / (getHabLimpiar() + mejora);
			break;
		case "reparar":
			tiempoOcupado= (unidadesTrabajo * 60 ) / (getHabReparar() + mejora);
			break;
		default:
			tiempoOcupado = 0;
		}
		// AÑADIR LO QUE TARDA EN DESPLAZARSE A ESE AREA DE LA TAREA 
	
		this.tiempoOcupado += calcularTiempoTrayecto(origen, destino);
		this.tiempoOcupado = this.tiempoOcupado + tiempoOcupado + (int)peso;
	}
	
	
	public void cogerHerramienta2(Herramienta herramientaNueva) { // en principio mi herramienta es limpiar, reparar, podar (no entro en detalle)
		//Si esta en el almacen, tiempo que tarde en ir al lugar.
		if(!this.area.equals("A")) {
			this.tiempoOcupado += calcularTiempoTrayecto(area, "A");
			this.area = "A";
		} 
		this.herramienta = herramientaNueva;
		System.out.println("num herramienta actual " + herramientaNueva.getCantidad() + "max" +   herramientaNueva.getMaxCantidad());
		
	}
	
	public void printTrabajador2() {
		System.out.println(this.nombre + " " + this.tiempoOcupado + " mins " + this.herramienta.getNombre() + " " + this.herramienta.getTrabajo() + 
				" " + this.area + " cantidad restante: " + this.herramienta.getCantidad() + " cantidad maxima: " + this.herramienta.getMaxCantidad());
	}
}
