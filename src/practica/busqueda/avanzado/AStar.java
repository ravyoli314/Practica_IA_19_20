package practica.busqueda.avanzado;

import java.util.ArrayList;
import java.util.List;
import practica.busqueda.avanzado.Node;
import practica.busqueda.avanzado.OpenList;
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

public class AStar {

	int printDebug; // 0: nada, 1: informaci�n b�sica, 2: informaci�n completa

	private OpenList openList = new OpenList();						// Lista de nodos por explorar
	private ArrayList<Node> closedList = new ArrayList<Node>();		// Lista de nodos explorados
	private Node initialNode;										// Nodo inicial del problema
	private Node goalNode;											// Nodo meta del problema
	private boolean findGoal;										// Se ha encontrado la meta
	
	private ArrayList<Tarea> tareasPoda = new ArrayList<Tarea>();

	/**
	 * Insertamos en la lista de nodos abiertos los nodos seg�n las acciones que se pueden realizar en este instante
	 * MODIFICAR
	 * @param currentNode - el nodo actual
	 */

	private void addAdjacentNodes(Node currentNode) {
		ArrayList<Trabajador> trabajadores  = currentNode.getTrabajadores();
		ArrayList<Herramienta> herramientas = currentNode.getHerramientas();
		ArrayList<Tarea> tareas             = currentNode.getTareas();

		Double coste = 0.0;
		boolean tareasTerminadas = true;
		
		// solo muevo al/los trabajador que termine antes-------------------------------
		
		int tiempoOcupado = 0; //almaceno el tiempo de ocupación del trabajador que termine antes

		for(int i = 0; i < trabajadores.size(); i++) {
			if (i == 0) tiempoOcupado = trabajadores.get(i).getTiempoOcupado();
			else{
				if(trabajadores.get(i).getTiempoOcupado() < tiempoOcupado) tiempoOcupado = trabajadores.get(i).getTiempoOcupado();
				else continue;
			}
		}
		
		for(Tarea tareaOriginal : tareas) { // Un estado sucesor distinto por cada tarea pendiente
			if(tareaOriginal.getUnidades() > 0) {
				tareasTerminadas = false;

				// ************** Creo los arraylists del nuevo nodo (estado) sucesor *****************************
				
				ArrayList<Herramienta> herramientasNuevas = new ArrayList<Herramienta>();
				for (Herramienta herramienta : herramientas) {
					Herramienta herramientaNueva = new Herramienta(herramienta.getNombre(), herramienta.getTrabajo(), herramienta.getPeso(), herramienta.getMejora(), herramienta.getCantidad());
					herramientaNueva.setCantidad(herramienta.getCantidad());
					herramientaNueva.setMaxCantidad(herramienta.getMaxCantidad());
					herramientasNuevas.add(herramientaNueva);
				}

				boolean tareaAsignada = false;
				ArrayList<Trabajador> trabajadoresNuevos = new ArrayList<Trabajador>();
				for (Trabajador trabajador : trabajadores) {
					Trabajador trabajadorNuevo = new Trabajador(trabajador.getNombre(), trabajador.getHabPodar(), trabajador.getHabLimpiar(), trabajador.getHabReparar());
					trabajadorNuevo.setArea(trabajador.getArea());
					trabajadorNuevo.setTiempoTotalTrabajado(trabajador.getTiempoTotalTrabajado());
					trabajadorNuevo.setTiempoOcupado(trabajador.getTiempoOcupado() - tiempoOcupado); // actualizo el tiempo, dejando libre al/los trabajador que termine primero
					trabajadorNuevo.setHerramienta(trabajador.getHerramienta());

					if (trabajadorNuevo.getTiempoOcupado() == 0 && !tareaAsignada) {
						tareaAsignada = true;
						if(!trabajadorNuevo.herramientaCorrecta(tareaOriginal.getTipo())) { // si no tiene la herramienta correcta, la coge del almacen
							for (Herramienta herramienta : herramientasNuevas) {
								if((trabajadorNuevo.getHerramienta() != null) && (trabajadorNuevo.getHerramienta().getNombre().equals(herramienta.getNombre())))
									herramienta.setCantidad(herramienta.getCantidad() +1); // el trabajador suelta la herramienta
								if(herramienta.getTrabajo().equals(tareaOriginal.getTipo()) && herramienta.getCantidad() > 0) { // && herramienta.getCantidad() > 0
									herramienta.setCantidad(herramienta.getCantidad() - 1);
									trabajadorNuevo.cogerHerramienta(herramienta);
									break;
								}
							}
						}
						trabajadorNuevo.tiempoTarea(tareaOriginal.getTipo(), tareaOriginal.getUnidades(), trabajadorNuevo.getHerramienta().getMejora());
						trabajadorNuevo.setArea(tareaOriginal.getArea(), trabajadorNuevo.getHerramienta().getPeso());
					}
					coste += (double)trabajadorNuevo.getTiempoTotalTrabajado(); // el coste es el tiempo total trabajado por todos
					trabajadoresNuevos.add(trabajadorNuevo);
				}

				ArrayList<Tarea> tareasNuevas = new ArrayList<Tarea>();
				for (Tarea tarea: tareas) {
					Tarea tareaNueva = new Tarea(tarea.getTipo(), tarea.getArea(), tarea.getUnidades()); // Parto de que tareaOriginal tiene 0 uds
					if(tareaOriginal.getTipo().equals("podar") && tareaNueva.getTipo().equals("limpiar") && tareaNueva.getArea().equals(tareaOriginal.getArea())) { // por cada unidad de poda añado una de limpieza
						for(Tarea poda : tareasPoda) {
							if(poda.getArea().equals(tareaOriginal.getArea())) {
								tareaNueva.setUnidades(tareaNueva.getUnidades() + poda.getUnidades());
								break; 
							}
						}
					}
					if(tareaNueva.getTipo().equals(tareaOriginal.getTipo()) && tareaNueva.getArea().equals(tareaOriginal.getArea())) {
						tareaNueva.setUnidades(0); // El estado nuevo (nodo sucesor) parte de la tarea que se acaba de completar
					}
					tareasNuevas.add(tareaNueva);
				}

				// ************** Creo el nodo sucesor con los arraylists que acabo de crear (estos definen el nuevo estado): **************
				
				Node sucesor = new Node(currentNode, herramientasNuevas, trabajadoresNuevos, tareasNuevas);
				sucesor.setCoste(coste);
				sucesor.computeHeuristic(this.goalNode);
				sucesor.computeEvaluation();
				openList.insertAtEvaluation(sucesor); // lo añado a la lista de nodos por explorar (ordenada segun la funcion de evaluacion)
			} // if tarea pendiente
		} // for	
		
		if(tareasTerminadas) { // Si no quedan tareas pendientes, el único nodo sucesor es el nodo meta (que tendrá los valores reseteados)
			Node sucesor = new Node(goalNode);
			sucesor.setParent(currentNode);
			openList.insertAtEvaluation(sucesor);
		}
	}
	
	/**
	 * Implementaci�n de A estrella
	 */
	public double Algorithm() {
		double initialTime = Double.parseDouble(""+System.currentTimeMillis()); // Para contar el tiempo de ejecuci�n

		Node currentNode = null;

		while(!this.openList.isEmpty()) { 				// Recorremos la lista de nodos sin explorar
			currentNode = this.openList.pullFirst(); 	// Extraemos el primero (la lista esta ordenada segun la funcion de evaluaci�n)
			if(checkNode(currentNode)) {				// Si el nodo ya se ha visitado con un coste menor (esta en la lista de explorados) lo ignoramos
				currentNode.printNodeData(printDebug);
				closedList.add(currentNode); 			// A�adimos dicho nodo a la lista de explorados

				if(this.getGoalNode().equals(currentNode)) {	// Si es el nodo meta hemos acabado y no hace falta continuar
					this.setGoalNode(currentNode);
					this.setFindGoal(true);
					break;
				}
				this.addAdjacentNodes(currentNode); 	// Expandimos el nodo segun las acciones posibles    	
			}
		}
		
		// Para contar el tiempo de ejecuci�n
		double fin    = Double.parseDouble(""+System.currentTimeMillis());
		double tiempo = (fin - initialTime) / 1000;
		return tiempo;
	}

	public void printTotalNode() {
		int totalNode = this.closedList.size() + openList.getSize();
		System.out.println("El número total de nodos expandidos es : " + totalNode + 
				", han sido visitados " + this.closedList.size() + " y no han sido visitados " + openList.getSize());
	}
	

	/**
	 * Constructor del algoritmo, obtiene el nodo de inicio y el nodo meta
	 * NO MODIFICAR
	 * @param initialNode
	 * @param goalNode
	 */
	public AStar(int printDebug, Node initialNode, Node goalNode) { 
		this.printDebug = printDebug;
		this.setInitialNode(initialNode);
		this.setGoalNode(goalNode);
		this.setFindGoal(false); 					// No se ha llegado al nodo meta

		// Introducir heur�sticas y costes para el nodo inicial. El nodo meta solo tiene heur�stica
		initialNode.computeHeuristic(goalNode);	// Coste esperado por la heur�stica para llegar al nodo final desde el inicial
		initialNode.setCoste(0);					// el nodo inicial tiene coste cero
		initialNode.computeEvaluation();			// coste + heur�stica
		goalNode.computeHeuristic(goalNode);		// Debe ser 0, ya es el nodo final

		// Genera la lista de nodos explorados y sin explorar
		this.closedList = new ArrayList<Node>();
		this.openList   = new OpenList();
		this.openList.insertAtEvaluation(initialNode); // A�adimos a la lista de nodos sin explorar el nodo inicial
		for (Tarea tarea: this.initialNode.getTareas()) {
			if(tarea.getTipo().equals("podar")) { // Conservo las unidades de poda
				Tarea tareaNueva = new Tarea(tarea.getTipo(), tarea.getArea(), tarea.getUnidades());
				this.tareasPoda.add(tareaNueva);
			}}
	}


	/**
	 * Comprobaci�n de si el nodo ya se ha explorado
	 * NO MODIFICAR
	 * @param currentNode
	 * @return
	 */
	private boolean checkNode(Node currentNode) {
		boolean expandirNodo = true;
		for (Node node : this.closedList) { // Se observa si el nodo est� en la lista de cerrados
			if(currentNode.equals(node)) {	// Comprueba si la informaci�n del nodo es igual
				expandirNodo = false;
				break;
			}
		}
		return expandirNodo;				// false en el caso de que el nodo se haya visitado, indicando que no hay que expandirlo
	}


	/**
	 * M�todo para calcular el camino desde el nodo Inicial hasta el nodo actual
	 * NO MODIFICAR
	 * @param currentNode
	 * @return lista de nodos ordenada, desde el primer nodo al �ltimo
	 */
	public List<Node> getPath(Node currentNode) {
		List<Node> path = new ArrayList<Node>();	
		path.add(currentNode);	
		Node parent;
		while ((parent = currentNode.getParent()) != null) {	// Desde el nodo actual, se busca el nodo padre y se insertan 
			path.add(0, parent);								//  dentro de la lista de manera inversa
			currentNode = parent;
		}
		return path;
	}


	/**** Getters y Setters ****/
	/**
	 * MODIFICAR y/o A�ADIR si se considera necesario. No es imprescindible, solo si se considera que puede ayudar a la implementaci�n
	 */
	public Node getInitialNode() {
		return initialNode;
	}
	public void setInitialNode(Node initialNode) {
		this.initialNode = initialNode;
	}
	public boolean isFindGoal() {
		return findGoal;
	}
	public void setFindGoal(boolean findGoal) {
		this.findGoal = findGoal;
	}
	public Node getGoalNode() {
		return goalNode;
	}
	public void setGoalNode(Node goalNode) {
		this.goalNode = goalNode;
	}

}
