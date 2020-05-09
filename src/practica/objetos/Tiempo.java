package practica.objetos;

public class Tiempo {
	
	public int tiempoTarea(String tipoTarea, int unidadesTrabajo, int habPodar, int habLimpiar, int habReparar) { // aqui no tengo en cuenta la mejora de la herramienta
		int tiempoOcupado;
		switch(tipoTarea) {
		case "podar":
			tiempoOcupado= (unidadesTrabajo * 60) / habPodar;
			break;
		case "limpiar":
			tiempoOcupado= (unidadesTrabajo * 60 ) / habLimpiar;
			break;
		case "reparar":
			tiempoOcupado= (unidadesTrabajo * 60 ) / habReparar;
			break;
		default:
			tiempoOcupado = 0;
		}
		
		return tiempoOcupado;
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
}
