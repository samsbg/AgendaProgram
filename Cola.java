package solución;

public class Cola {
	
	private Nodo fin;
	private int tamanio;

	public Cola() {
		fin = null;
		tamanio = 0;
	}
	
	public class Nodo {
		Evento elemento;
		Nodo siguiente;

		public Nodo(Evento n) {
			elemento = n;
			siguiente = null;
		}
	}

	public void empujar(Evento n) {
		Nodo nuevoNodo = new Nodo(n);
		if (fin == null)
			fin = nuevoNodo;
		else {
		  nuevoNodo.siguiente = fin;
		  fin = nuevoNodo;
		}
		tamanio++;
	};

	public Evento sacar() {
		if (fin == null)
			return null;
		Evento n = fin.elemento;
		fin = fin.siguiente;
		tamanio--;
		return n;
	}

	public boolean estaVacio() { 
		return (tamanio == 0);
	}

	public int tamanio() {
		return tamanio;
	}

	public Evento fin() {
		if (fin == null)
			return null;
		else
			return fin.elemento;
	}
	
	public void sortQueue() { 
		
		if (estaVacio()) 
	        return; 

	    Evento Evento = fin(); 
	  
	    sacar(); 
	    sortQueue(); 
	    
	    pushInQueue(Evento, tamanio()); 
	} 
	
	public void pushInQueue(Evento evento, int tamanio) { 
	    if (estaVacio()) { 
	        empujar(evento); 
	        return; 
	    } 
	  
	    else if (Integer.parseInt(evento.getFecha().substring(0, 2)) < Integer.parseInt(fin().getFecha().substring(0, 2))) { 
	        empujar(evento); 
	        FrontToLast(tamanio);
	    } 
	    else { 
	        empujar(fin()); 
	        sacar(); 
	        
	        pushInQueue(evento, tamanio - 1); 
	    } 
	}
	
	public void FrontToLast(int tamanio) { 
	    if (tamanio <= 0) 
	        return; 

	    empujar(fin()); 
	    sacar(); 
	   
	    FrontToLast(tamanio - 1); 
	} 

}
