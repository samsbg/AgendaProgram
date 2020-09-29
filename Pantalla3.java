package solución;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.swing.*;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class Pantalla3 extends JFrame implements ActionListener{

	private JLabel frases;
	
	private JButton regresar, crearEvento, crearMateria, eliminarMateria;
	
	private JTextArea nombreEvento, crearMateriaNombre;
	
	private JComboBox<String> horaInicio, horaFinal, materiaElegir, materiaEliminar;
	
	private JDatePickerImpl elegirFecha; 
	
	protected String[] horas = {"12:30 am", "1:00 am", "1:30 am", "2:00 am", "2:30 am", "3:00 am", "3:30 am", "4:00 am", "4:30am", "5:00 am", "5:30 am", "6:00 am", "6:30 am", "7:00 am", "7:30 am", "8:00 am", "8:30 am", "9:00 am", "9:30 am", "10:00 am", "10:30 am", "11:00 am", "11:30 am", "12:00 pm", "12:30 pm", "1:00 pm", "1:30 pm", "2:00 pm", "2:30 pm", "3:00 pm", "3:30 pm", "4:00 pm", "4:30pm", "5:00 pm", "5:30 pm", "6:00 pm", "6:30 pm", "7:00 pm", "7:30 pm", "8:00 pm", "8:30 pm", "9:00 pm", "9:30 pm", "10:00 pm", "10:30 pm", "11:00 pm", "11:30 pm", "12:00 am"};
	
	private ArrayList<Materia> materias = new ArrayList<Materia>();
	
	private List<Evento> eventos = new ArrayList<Evento>();
	
	ArrayList<String> datos = new ArrayList<String>();
	boolean valor = false;
	String linea;
	
	public Pantalla3(List<Evento> eventos, ArrayList<Materia> materias) {	
		
		setLayout(null);
		
		setBounds(0, 0, 1250, 700);
	    setLocationRelativeTo(null);
	    setResizable(false);
	    setLayout(null);
	    getContentPane().setBackground(new Color(218, 227, 242));
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.eventos = eventos;
	    this.materias = materias;
	    
	    crearFrase("Crear Evento", 100, -300, 800, 700, 70);
	    crearFrase("Nombre", 100, -110, 200, 500, 30);
	    crearFrase("Fecha", 100, -20, 1000, 500, 30);
	    crearFrase("Hora de inicio", 100, 70, 1000, 500, 30);
	    crearFrase("Hora de cierre", 100, 160, 1000, 500, 30);
	    crearFrase("Materia", 100, 250, 1000, 500, 30);	    
	    
	    regresar = new JButton("Regresar");
	    regresar.setBounds(120, 575, 200, 30);
	    regresar.setBackground(new Color(253, 127, 124));
	    regresar.setOpaque(true);
	    regresar.setBorderPainted(false);
	    regresar.setFont(new Font("American Typewriter", 0, 24));
	    regresar.setHorizontalAlignment(SwingConstants.CENTER);
	    regresar.setHorizontalTextPosition(SwingConstants.CENTER);
	    regresar.addActionListener(this);
		add(regresar);
		
		crearEvento = new JButton("<html> Guardar<br>" + "evento<html>");
		crearEvento.setBounds(950, 550, 200, 70);
		crearEvento.setBackground(new Color(253, 127, 124));
		crearEvento.setOpaque(true);
		crearEvento.setBorderPainted(false);
		crearEvento.setFont(new Font("American Typewriter", 0, 24));
		crearEvento.setHorizontalAlignment(SwingConstants.CENTER);
		crearEvento.setHorizontalTextPosition(SwingConstants.CENTER);
		crearEvento.addActionListener(this);
		add(crearEvento);
		
		crearMateria = new JButton("Crear materia");
		crearMateria.setBounds(675, 400, 225, 30);
		crearMateria.setBackground(new Color(253, 127, 124));
		crearMateria.setOpaque(true);
		crearMateria.setBorderPainted(false);
		crearMateria.setFont(new Font("American Typewriter", 0, 20));
		crearMateria.setHorizontalAlignment(SwingConstants.CENTER);
		crearMateria.setHorizontalTextPosition(SwingConstants.CENTER);
		crearMateria.addActionListener(this);
		add(crearMateria);
		
		eliminarMateria = new JButton("Eliminar materia");
		eliminarMateria.setBounds(675, 445, 225, 30);
		eliminarMateria.setBackground(new Color(253, 127, 124));
		eliminarMateria.setOpaque(true);
		eliminarMateria.setBorderPainted(false);
		eliminarMateria.setFont(new Font("American Typewriter", 0, 20));
		eliminarMateria.setHorizontalAlignment(SwingConstants.CENTER);
		eliminarMateria.setHorizontalTextPosition(SwingConstants.CENTER);
		eliminarMateria.addActionListener(this);
		add(eliminarMateria);

		nombreEvento = new JTextArea();
		nombreEvento.setBounds(425, 120, 200, 30);
		nombreEvento.setFont(new Font("American Typewriter", 0, 20));
		nombreEvento.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		add(nombreEvento);
		
		crearMateriaNombre = new JTextArea();
		crearMateriaNombre.setBounds(925, 400, 225, 30);
		crearMateriaNombre.setFont(new Font("American Typewriter", 0, 20));
		crearMateriaNombre.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		add(crearMateriaNombre);
	    
	    Locale.setDefault(new Locale("ES"));
		
		UtilDateModel model = new UtilDateModel();
		model.setSelected(true);

		Properties p = new Properties();
		p.put("text.today", "Hoy");
		p.put("text.month", "Mes");
		p.put("text.year", "Anio");
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		
		elegirFecha = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		elegirFecha.setBounds(425, 210, 200, 30);
		elegirFecha.setOpaque(false);
		add(elegirFecha);
		
		horaInicio = new JComboBox<String>(horas);
		horaInicio.setBounds(425, 305, 200, 30);
		horaInicio.setSelectedIndex(22);
		add(horaInicio);
		
		horaFinal = new JComboBox<String>(horas);
		horaFinal.setBounds(425, 395, 200, 30);
		horaFinal.setSelectedIndex(23);
		add(horaFinal);
		
		if(materias.isEmpty())
			materias.add(new Materia("No hay materias"));
		
		String[] arregloDeMaterias = new String[materias.size()];
		
		for (int i = 0; i < arregloDeMaterias.length; i++) {
			arregloDeMaterias[i] = materias.get(i).getNombre();
		}
		
		materiaElegir = new JComboBox<String>(arregloDeMaterias);
		materiaElegir.setBounds(425, 480, 200, 30);
		materiaElegir.setSelectedIndex(0);
		add(materiaElegir);
		
		if(materias.get(0).getNombre().equals("No hay materias")) {
			arregloDeMaterias[0] = "No hay materias para eliminar";
			materias.set(0, new Materia("No hay materias para eliminar"));
		}
		
		materiaEliminar = new JComboBox<String>(arregloDeMaterias);
		materiaEliminar.setBounds(925, 450, 250, 30);
		materiaEliminar.setSelectedIndex(0);
		add(materiaEliminar);
	    
	    setVisible(true);
	}
	
	public void crearFrase(String frase, int posicionx, int posiciony, int tamaniox, int tamanioy, int tamanio) {
		frases = new JLabel(frase);
		frases.setBounds(posicionx, posiciony, tamaniox, tamanioy);
		frases.setFont(new Font("American Typewriter", 0, tamanio));
		add(frases);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == regresar) {
			Pantalla1 Pantalla = new Pantalla1();
		    Pantalla.setVisible(true);
		    dispose();
		}
		if (e.getSource() == crearEvento) {
			
			String nombre = nombreEvento.getText();
    		String fecha = elegirFecha.getJFormattedTextField().getText();
    		int horaDeInicio = horaInicio.getSelectedIndex();
    		int horaDeFin = horaFinal.getSelectedIndex();
    		String materia = materias.get(materiaElegir.getSelectedIndex()).getNombre();
    		
    		if(nombreEvento.getText().isEmpty()) 
    			JOptionPane.showMessageDialog(this, "El evento requiere un nombre para ser creado");
    		
    		else if (materias.get(0).getNombre().equals("No hay materias para eliminar"))
    			JOptionPane.showMessageDialog(this, "El evento requiere una nueva materia");
    		
    		else if (horaDeFin <= horaDeInicio)
    			JOptionPane.showMessageDialog(this, "El evento requiere que la hora de inicio sea antes de cuando termina");
    			
    		else {
    			
    			Evento eventoTemporal = new Evento(true, fecha, horaDeInicio, horaDeFin, nombre, materia);
    			eventos.add(eventoTemporal);
    			
    			imprimirEventos();
    			
    			JOptionPane.showMessageDialog(this, "El evento " + nombre + " fue creado");
    			
    			Pantalla1 Pantalla = new Pantalla1();
    		    Pantalla.setVisible(true);
    		    dispose();
    		}
		}
		if (e.getSource() == crearMateria) {
			
			String nuevaMateria = crearMateriaNombre.getText();
			crearMateriaNombre.setText(null);
			
			boolean unica = true;
			
			for (int i = 0; i < materias.size(); i++) {
				if(materias.get(i).getNombre().equals(nuevaMateria))
					unica = false;
			}
			
			if(unica) {
			
				Materia materiaTemporal = new Materia(nuevaMateria);
				
				materias.add(materiaTemporal);
				
				if(materias.get(0).getNombre().equals("No hay materias para eliminar"))
					materias.remove(0);
				
				ordenarLista(materias);
					
				materiaElegir.removeAllItems();
				materiaEliminar.removeAllItems();
				
				for (int i = 0; i < materias.size(); i++) {
					materiaElegir.addItem(materias.get(i).getNombre());
					materiaEliminar.addItem(materias.get(i).getNombre());
				}
				
				imprimirMaterias();
				
				JOptionPane.showMessageDialog(this, "La materia " + nuevaMateria + " fue creada");
			}
			else 
				JOptionPane.showMessageDialog(this, "Ya existe una materia con este nombre, por favor escoja otro nombre");
		}
		if (e.getSource() == eliminarMateria) {
			
			if(materias.get(0).getNombre().equals("No hay materias para eliminar"))
				JOptionPane.showMessageDialog(this, "No hay materias para eliminar");
			
			else {
				
				String materia = materiaEliminar.getSelectedItem().toString();
				
				int confirmacion = JOptionPane.showConfirmDialog(null, "Seguro que quiere eliminar " + materia + "?", "", JOptionPane.YES_NO_OPTION);
				
				if(confirmacion == JOptionPane.YES_OPTION) {
		
					materias.remove(buscarMateria(materia));
					
					ordenarLista(materias);
					
					materiaElegir.removeAllItems();
					materiaEliminar.removeAllItems();
					
					for (int i = 0; i < materias.size(); i++) {
						materiaElegir.addItem(materias.get(i).getNombre());
						materiaEliminar.addItem(materias.get(i).getNombre());
					}
					
					if(materias.isEmpty()) {
						materias.add(new Materia("No hay materias para eliminar"));
						materiaElegir.addItem("No hay materias");
						materiaEliminar.addItem("No hay materias para eliminar");
					}
					
					imprimirMaterias();
				}
			}
			
		}
	}
	
	public void ordenarLista(ArrayList<Materia> lista) {
		
		Materia temporal;
	
		for (int i = 0; i < lista.size(); i++)  
            for (int j = i + 1; j < lista.size(); j++) 
                if (lista.get(i).getNombre().compareTo(lista.get(j).getNombre()) > 0) {
                    temporal = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, temporal);
                }
	}

	public Materia buscarMateria(String materia) {
		for (int i = 0; i < materias.size(); i++) {
			if(materias.get(i).getNombre().equals(materia))
				return materias.get(i);
		}
		return null;
	}

	public void imprimirMaterias() {
 
		try {
			RandomAccessFile archivo = new RandomAccessFile ("materias.dat", "rw");
			for (int i = 0; i < materias.size(); i++) 
				materias.get(i).imprimirMateria(archivo);
			archivo.close();
		}
		catch(IOException f) {
			System.out.println("Archivo no encontrado en imprimirMaterias");
		}
	}
	
	public void imprimirEventos() {
		
		try {
			RandomAccessFile archivo = new RandomAccessFile ("eventos.dat", "rw");
			for (int i = 0; i < eventos.size(); i++) 
				eventos.get(i).imprimirEvento(archivo);
			archivo.close();
		}
		catch(IOException f) {
			System.out.println("Archivo no encontrado en Pantalla3:imprimirEventos");
		}
		
	}
}