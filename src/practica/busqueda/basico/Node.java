package practica.busqueda.basico;

import java.util.ArrayList;

import practica.objetos.Herramienta;
import practica.objetos.Tarea;
import practica.objetos.Trabajador;

/**
 * Clase creada como base para la parte 2 de la pr�ctica 2019-2020 de Inteligencia Artificial, UC3M, Colmenarejo
 *
 * @author Daniel Amigo Herrero
 * @author David S�nchez Pedroche
 * 
 */

public class Node {
	private double cost;							// Valor del coste de llegada al nodo
	private double heuristic;						// Valor de la heur�stica del nodo
	private double evaluation;						// Valor de la evaluaci�n
	private Node parent;							// Nodo padre del arbol A*
	private Node nextNodeList = null;				// Para la gesti�n de la lista
	ArrayList<Herramienta> herramientas;
	ArrayList<Trabajador>  trabajadores;
	ArrayList<Tarea>       tareas;
	// A�adir m�s variables si se desea

	/**
	 * MODIFICAR
	 * Constructor para introducir un nuevo nodo en el algoritmo A estrella
	 */
	public Node(Node parentNode, ArrayList<Herramienta> herramientas, ArrayList<Trabajador> trabajadores, ArrayList<Tarea> tareas) {	
		// CREAR ARRAYLISTS NUEVOS PARA TRABAJADORES, HERRAMIENTAS Y TAREAS Y COPIAR EN ELLOS LOS OBJETOS QUE NOS PASAN		
		this.parent       = parentNode;  // padre en el �rbol A*
		this.herramientas = herramientas;
		this.trabajadores = trabajadores;
		this.tareas       = tareas;
		// A�adir m�s variables si se desea
		
		// calcular funcion de evaluacion -> pasamos la heuristica (calcular) y el coste
	}

	/**
	 * MODIFICAR
	 * Constructor auxiliar para la implementaci�n del algoritmo. Genera una copia de un nodo para introducirla en la OpenList
	 */ 
	public Node(Node original) {
		// Incluir todas las variables del nodo
		this.cost        = original.cost;
		this.heuristic   = original.heuristic;
		this.evaluation   = original.evaluation;
		this.parent       = original.parent;
		this.nextNodeList = original.nextNodeList;
		// A�adir m�s variables si se desea

		// Se copian los objetos de los ArrayList a uno nuevo de este Nodo
		// Si se necesita a�adir valores variables, como un ID, utilizar setters
		ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>();
		for (int i = 0; i < original.trabajadores.size(); i++) {
			Trabajador trabajador = new Trabajador(original.trabajadores.get(i).getNombre(), original.trabajadores.get(i).getHabPodar(), original.trabajadores.get(i).getHabLimpiar(), original.trabajadores.get(i).getHabReparar());
			trabajadores.add(trabajador);
		}
		this.trabajadores = trabajadores;
		ArrayList<Herramienta> herramientas = new ArrayList<Herramienta>();
		for (int i = 0; i < original.herramientas.size(); i++) {
			Herramienta herramienta = new Herramienta(original.herramientas.get(i).getNombre(), original.herramientas.get(i).getTrabajo(), original.herramientas.get(i).getPeso(), original.herramientas.get(i).getMejora(), original.herramientas.get(i).getCantidad());
			herramientas.add(herramienta);
		}
		this.herramientas = herramientas;
		ArrayList<Tarea> tareas = new ArrayList<Tarea>();
		for (int i = 0; i < original.tareas.size(); i++) {
			Tarea tarea = new Tarea(original.tareas.get(i).getTipo(), original.tareas.get(i).getArea(), original.tareas.get(i).getUnidades());
			tareas.add(tarea);
		}
		this.tareas = tareas;
	}

	/**
	 * Constructor auxiliar para generar el primer nodo de la lista abierta
	 */ 
	public Node() {	}

	/**
	 *  Calcula el valor de la heuristica del problema para el nodo 
	 *  MODIFICAR
	 * @param finalNode - El nodo sobre el que calcular la heur�stica
	 * this.heuristica  - Resultado
	 */
	public void computeHeuristic(Node finalNode) {
		// MODIFICAR para ajustarse a las necesidades del problema
		this.heuristic = 0;
	}

	/**
	 * Comprobaci�n de que la informaci�n de un nodo es equivalente a la de otro nodo
	 * Solo comparar la informaci�n necesaria para ver si es el mismo estado del problema
	 * 
	 * @param other - el nodo con el que comparar this
	 * @return true: son iguales. false: no lo son
	 */
	public boolean equals(Node other) {
		boolean check = true; 
		
		int i = 0;
		while(check && i < this.tareas.size()) {
			Tarea tarea1 = this.tareas.get(i);
			for (Tarea tarea2: other.getTareas()) {
				if(!check)
					break;
				
				if(tarea1.getTipo().equals(tarea2.getTipo()) && tarea1.getArea().equals(tarea2.getArea())){ // solo comparo las tareas del mismo tipo y area
					if(tarea1.getUnidades() != tarea2.getUnidades()) { // dos tareas (mismo area y tipo) se diferencian por sus unidades
						check = false;
					}
					break;
				}
			}
			i++;
		}
		
		
		int j = 0;
		while((check == true) && j < this.trabajadores.size()) {
			Trabajador trabajador1 = this.trabajadores.get(j);
			for (Trabajador trabajador2: this.trabajadores) {
				if(!check)
					break;
				
				if(trabajador1.getNombre().equals(trabajador2.getNombre())) {
					if((trabajador1.getTiempoTotalTrabajado() != trabajador2.getTiempoTotalTrabajado()) || (trabajador1.getTiempoOcupado() != trabajador2.getTiempoOcupado()) || 
							!(trabajador1.getArea().equals(trabajador2.getArea()))) {
						check = false;
					}
					/*
					else if ((trabajador1.getHerramienta() != null && trabajador2.herramientaCorrecta(trabajador1.getHerramienta().getTrabajo())) || 
							(trabajador2.getHerramienta() != null && trabajador1.herramientaCorrecta(trabajador2.getHerramienta().getTrabajo()))) {
						check = false;
					} */
					break;
				}
			}
			j++;
		}
		/*
		int k = 0;
		while((check == true) && k < this.herramientas.size()) {
			Herramienta herramienta1 = this.herramientas.get(k);
			for (Herramienta herramienta2: this.herramientas) {
				if(!check)
					break;
				
				if(herramienta1.getNombre().equals(herramienta2.getNombre()) && (herramienta1.getTrabajo().equals(herramienta2.getTrabajo()))) {
					if(herramienta1.getCantidad() != herramienta2.getCantidad()) {
						check = false;
					}
					break;
				}
			}
			k ++;
		}
		*/
		
		return check;
	}


	/**
	 * Impresi�n de la informaci�n del nodo
	 * @param printDebug. Permite seleccionar cu�ntos mensajes imprimir
	 */
	public void printNodeData(int printDebug) { // COMPLETAR Y PONER LOS 3 CASOS DE DEBUG !!!!!!!!!!!!!!!!!!!!!
		int tareasPendientes = 0;
		for (Tarea tarea: this.tareas) {
			if(tarea.getUnidades() > 0)
				tareasPendientes++;
		}
		System.out.println("TAREAS PENDIENTES: "+ tareasPendientes);
		
		for (Trabajador trabajador: this.trabajadores) {
			if(trabajador.getNombre().equals("Antonio")) {
				System.out.println("TIEMPO TRABAJADO POR ANTONIO: " + trabajador.getTiempoTotalTrabajado() + " tiempo ocupado: " + trabajador.getTiempoOcupado());
				break; }
		}
		
	}

	/**
	 * Ejecuta la funci�n de evaluacion del problema para el nodo. IMPORTANTE: ejecutar despu�s el c�lculo del coste y heur�stica
	 */
	public void computeEvaluation() {
		this.evaluation = this.cost + this.heuristic; 
	}

	/**** Getters y Setters ****/
	/**
	 * MODIFICAR si se considera necesario. No es imprescindible, solo si consideras que puede ayudar a tu implementaci�n
	 */
	public double getEvaluation() {
		return evaluation;
	}
	public ArrayList<Herramienta> getHerramientas() {
		return herramientas;
	}
	public void setHerramientas(ArrayList<Herramienta> herramientas) {
		this.herramientas = herramientas;
	}
	public ArrayList<Trabajador> getTrabajadores() {
		return trabajadores;
	}
	public void setTrabajadores(ArrayList<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}
	public ArrayList<Tarea> getTareas() {
		return tareas;
	}
	public void setTareas(ArrayList<Tarea> tareas) {
		this.tareas = tareas;
	}
	public void setEvaluation(double evaluacion) {
		this.evaluation = evaluacion;
	}
	public double getCost() {
		return cost;
	}
	public void setCoste(double coste) {
		this.cost = coste;
	}
	public double getHeuristic() {
		return heuristic;
	}
	public void setHeuristic(double heuristica) {
		this.heuristic = heuristica;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public Node getNextNode() {
		return nextNodeList;
	}
	public void setNextNode(Node nextNode) {
		this.nextNodeList = nextNode;
	}
}
