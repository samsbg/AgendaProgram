package vaquitas;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Evento {

	private boolean activo;
	private String fecha;
	private int horaDeInicio;
	private int horaDeFin;
	private String nombre;
	private Materia materia;
	private boolean tieneValor;
	private double calificacion;
	private double porcentaje;
	
	ArrayList<String> datos = new ArrayList<String>();
	ArrayList<Materia> materias = new ArrayList<Materia>();
	String linea;
	
	public Evento (boolean activo, String fecha, int horaDeInicio, int horaDeFin, String nombre, String materia) {
		
		leerArchivoDeMateria();
		
		this.activo = activo;
		this.fecha = fecha;
		this.horaDeInicio = horaDeInicio;
		this.horaDeFin = horaDeFin;
		this.nombre = nombre;
		this.materia = materias.get(buscarMateria(0, materias.size() - 1, materia));
		this.tieneValor = false;
		
	}
	
	public Evento (boolean activo, String fecha, int horaDeInicio, int horaDeFin, String nombre, String materia, boolean valor, double calificacion, double porcentaje) {
		
		leerArchivoDeMateria();
		
		this.activo = activo;
		this.fecha = fecha;
		this.horaDeInicio = horaDeInicio;
		this.horaDeFin = horaDeFin;
		this.nombre = nombre;
		this.materia = materias.get(buscarMateria(0, materias.size() - 1, materia));
		this.tieneValor = valor;
		this.calificacion = calificacion;
		this.porcentaje = porcentaje;
	}

	public boolean isActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getHoraDeInicio() {
		return horaDeInicio;
	}

	public void setHoraDeInicio(int horaDeInicio) {
		this.horaDeInicio = horaDeInicio;
	}

	public int getHoraDeFin() {
		return horaDeFin;
	}

	public void setHoraDeFin(int horaDeFin) {
		this.horaDeFin = horaDeFin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
	public void setMateria(String materia) {
		this.materia = materias.get(buscarMateria(0, materias.size() - 1, materia));
	}

	public boolean isTieneValor() {
		return tieneValor;
	}

	public void setTieneValor(boolean tieneValor) {
		this.tieneValor = tieneValor;
	}

	public double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	public int buscarMateria(int b, int a, String materia) {
			
			if (a >= b) { 
	            int mid = b + (a - b) / 2; 
	  
	            if (materia.compareTo(materias.get(mid).getNombre()) == 0) 
	                return mid; 
	  
	            if (materia.compareTo(materias.get(mid).getNombre()) < 0) 
	                return buscarMateria(b, mid - 1, materia); 
	 
	            return buscarMateria(mid + 1, a, materia); 
	        } 

	        return -1;
			
		}
	
	public void leerArchivoDeMateria() {
		try {
			RandomAccessFile archivo=new RandomAccessFile ("materias.dat","rw");
			
			String linea = archivo.readLine();
			String[] materiaTemporal;
			
			if(linea != null) 
				while(linea != null) {
					materiaTemporal = linea.split("/");
					materias.add(new Materia(materiaTemporal[0], Double.parseDouble(materiaTemporal[1]), Double.parseDouble(materiaTemporal[2])));
					linea = archivo.readLine();
				}	
	 		archivo.close();
	 		
		}
		catch(IOException e) {   
			JOptionPane.showMessageDialog(null, "Error encontrando el archivo en Evento:leerArchivoDeMateria");
		}
	}

	public void imprimirEvento(RandomAccessFile archivo) {
		
		try { 
			
			archivo.writeBytes(String.valueOf(isActivo()));
			archivo.writeBytes("/");
			
			archivo.writeBytes(getFecha());
			archivo.writeBytes("/");
			
			archivo.writeBytes(String.valueOf(getHoraDeInicio()));
			archivo.writeBytes("/");
			
			archivo.writeBytes(String.valueOf(getHoraDeFin()));
			archivo.writeBytes("/");
			
			archivo.writeBytes(getNombre());
			archivo.writeBytes("/");
			
			archivo.writeBytes(getMateria().getNombre());
			archivo.writeBytes("/");
			
			archivo.writeBytes(String.valueOf(isTieneValor()));
			archivo.writeBytes("/");
			
			if(getCalificacion() != 0)
				archivo.writeBytes(Double.toString(getCalificacion()));
			else
				archivo.writeBytes("0");
			archivo.writeBytes("/");
			
			if(getPorcentaje() != 0)
				archivo.writeBytes(Double.toString(getPorcentaje()));
			else
				archivo.writeBytes("0");
			archivo.writeBytes("\n");
			
		}
		catch(IOException f) {
			System.out.println("Archivo no encontrado Evento:imprimirEvento");
		}
		
	}
}