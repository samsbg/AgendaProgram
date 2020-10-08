package vaquitas;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Materia {
	
	private String nombre;
	private double total;
	private double porcentaje;
	
	public Materia(String nombre, double porcentaje, double total) {
		this.nombre = nombre;
		this.total = total;
		this.porcentaje = porcentaje;		
	}
	
	public Materia(String nombre) {
		this.nombre = nombre;
		this.total = 0.0;
		this.porcentaje = 0.0;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public double getTotal() {
		return total;
	}
	
	public double getPorcentaje() {
		return porcentaje;
	}	
	
	public void aniadirCalificacion(double calificacion, double valor) {
		porcentaje =  porcentaje + valor;
		total = total + calificacion * valor;
	}
	
	public void restarCalificacion(double calificacion, double valor) {
		porcentaje = porcentaje - valor;
		total = total - porcentaje * valor;

	}
	
	public void imprimirMateria(RandomAccessFile archivo) {
		
		try {
			archivo.writeBytes(getNombre());
			archivo.writeBytes("/");
			
			if(getPorcentaje() == 0)
				archivo.writeBytes("0");
			else
				archivo.writeBytes(String.valueOf(getPorcentaje()));
			archivo.writeBytes("/");
			
			if(getTotal() == 0)
				archivo.writeBytes("0");
			else
				archivo.writeBytes(String.valueOf(getTotal()));
			archivo.writeBytes("\n");
				
		}
		catch(IOException f) {
			System.out.println("Archivo no encontrado en imprimirMateria()");
		}
	}

	
}
