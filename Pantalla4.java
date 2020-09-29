package solución;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class Pantalla4 extends JFrame implements ActionListener{
	
	private JLabel frases, calificacion, porcentajeCalificacion;
	
	private JButton regresar, eliminarEvento, guardarEvento, crearMateria, eliminarMateria;
	
	private JTextArea escribirNombre, escribirCalificacion, escribirPorcentaje, crearMateriaNombre;
	
	private JRadioButton si, no;
	
	private ButtonGroup eleccion;
	
	private JDatePickerImpl elegirFecha;
	
	private JComboBox<String> horaInicio, horaFin, materiaElegir, materiaEliminar;
	
	protected String[] horas = {"12:30 am", "1:00 am", "1:30 am", "2:00 am", "2:30 am", "3:00 am", "3:30 am", "4:00 am", "4:30am", "5:00 am", "5:30 am", "6:00 am", "6:30 am", "7:00 am", "7:30 am", "8:00 am", "8:30 am", "9:00 am", "10:00 am", "10:30 am", "11:00 am", "11:30 am", "12:00 am", "12:30 pm", "1:00 pm", "1:30 pm", "2:00 pm", "2:30 pm", "3:00 pm", "3:30 pm", "4:00 pm", "4:30pm", "5:00 pm", "5:30 pm", "6:00 pm", "6:30 pm", "7:00 pm", "7:30 pm", "8:00 pm", "8:30 pm", "9:00 pm", "10:00 pm", "10:30 pm", "11:00 pm", "11:30 pm", "12:00 pm"};
	
	private Date fecha = new Date();
	private Calendar cal = Calendar.getInstance();
	
	private ArrayList<Materia> materias = new ArrayList<Materia>();
	private List<Evento> eventos = new ArrayList<Evento>();
	private int eventoIndex = -1;
	
	int clave = eventos.size();

	ArrayList<String> datos = new ArrayList<String>();
	boolean valor = false;
	String linea;
	
	public Pantalla4(Evento evento, ArrayList<Materia> materias, ArrayList<Evento> eventos) {	
		
		setLayout(null);
		
		setBounds(0, 0, 1250, 700);
	    setLocationRelativeTo(null);
	    setResizable(false);
	    setLayout(null);
	    getContentPane().setBackground(new Color(218, 227, 242));
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    
	    for (int i = 0; i < eventos.size(); i++) {
			if(eventos.get(i).equals(evento))
				this.eventoIndex = i;
		}
	    
	    this.materias = materias;
	    this.eventos = eventos;
	    
	    Locale.setDefault(new Locale("ES"));
		cal.setTime(fecha);
	    
	    crearFrase("Nombre", 100, -200, 200, 500, 30, true);
	    crearFrase("Fecha", 100, -110, 1000, 500, 30, true);
	    crearFrase("Hora de inicio", 100, -20, 1000, 500, 30, true);
	    crearFrase("Hora de cierre", 100, 70, 1000, 500, 30, true);
	    crearFrase("Materia", 100, 160, 1000, 500, 30, true);
	    crearFrase("Tiene valor", 100, 250, 1000, 500, 30, true);

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
		
		eliminarEvento = new JButton("<html> Eliminar<br>" + "evento<html>");
		eliminarEvento.setBounds(700, 550, 200, 70);
		eliminarEvento.setBackground(new Color(253, 127, 124));
		eliminarEvento.setOpaque(true);
		eliminarEvento.setBorderPainted(false);
		eliminarEvento.setFont(new Font("American Typewriter", 0, 24));
		eliminarEvento.setHorizontalAlignment(SwingConstants.CENTER);
		eliminarEvento.setHorizontalTextPosition(SwingConstants.CENTER);
		eliminarEvento.addActionListener(this);
		add(eliminarEvento);
		
		guardarEvento = new JButton("<html> Guardar<br>" + "evento<html>");
		guardarEvento.setBounds(950, 550, 200, 70);
		guardarEvento.setBackground(new Color(253, 127, 124));
		guardarEvento.setOpaque(true);
		guardarEvento.setBorderPainted(false);
		guardarEvento.setFont(new Font("American Typewriter", 0, 24));
		guardarEvento.setHorizontalAlignment(SwingConstants.CENTER);
		guardarEvento.setHorizontalTextPosition(SwingConstants.CENTER);
		guardarEvento.addActionListener(this);
		add(guardarEvento);
		
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
	    
		calificacion = new JLabel("Calificacion");
		calificacion.setBounds(700, -200, 1000, 500);
		calificacion.setFont(new Font("American Typewriter", 0, 30));
		calificacion.setVisible(false);
		add(calificacion);
		 
		porcentajeCalificacion = new JLabel("Porcentaje de calificacion");
		porcentajeCalificacion.setBounds(700, -110, 1000, 500);
		porcentajeCalificacion.setFont(new Font("American Typewriter", 0, 30));
		porcentajeCalificacion.setVisible(false);
		add(porcentajeCalificacion);
	
		Locale.setDefault(new Locale("ES"));
		
		UtilDateModel model = new UtilDateModel();
		model.setDate(Integer.parseInt(eventos.get(eventoIndex).getFecha().substring(6)), Integer.parseInt(eventos.get(eventoIndex).getFecha().substring(3, 5))-1, Integer.parseInt(eventos.get(eventoIndex).getFecha().substring(0, 2)));
		model.setSelected(true);

		Properties p = new Properties();
		p.put("text.today", "Hoy");
		p.put("text.month", "Mes");
		p.put("text.year", "Anio");
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		
		elegirFecha = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		elegirFecha.setBounds(425, 120, 200, 30);
		elegirFecha.setOpaque(false);
		add(elegirFecha);
		
		horaInicio = new JComboBox<String>(horas);
		horaInicio.setBounds(425, 215, 200, 30);
		horaInicio.setSelectedIndex(eventos.get(eventoIndex).getHoraDeInicio());
		add(horaInicio);
		
		horaFin = new JComboBox<String>(horas);
		horaFin.setBounds(425, 305, 200, 30);
		horaFin.setSelectedIndex(eventos.get(eventoIndex).getHoraDeFin());
		add(horaFin);
		
		if(materias.isEmpty())
			materias.add(new Materia("No hay materias"));
		
		String[] arregloDeMaterias = new String[materias.size()];
		
		for (int i = 0; i < arregloDeMaterias.length; i++) {
			arregloDeMaterias[i] = materias.get(i).getNombre();
		}
		
		int a = 0;
		
		for (int i = 0; i < materias.size(); i++) {
			if(materias.get(i).equals(eventos.get(eventoIndex).getMateria()));
				a = i;
		}
		
		materiaElegir = new JComboBox<String>(arregloDeMaterias);
		materiaElegir.setBounds(425, 395, 200, 30);
		materiaElegir.setSelectedIndex(a);
		add(materiaElegir);
		
		materiaEliminar = new JComboBox<String>(arregloDeMaterias);
		materiaEliminar.setBounds(925, 445, 225, 30);
		add(materiaEliminar);

		escribirNombre = new JTextArea(evento.getNombre());
		escribirNombre.setBounds(425, 35, 200, 30);
		escribirNombre.setFont(new Font("American Typewriter", 0, 20));
		escribirNombre.setVisible(true);
		add(escribirNombre);
		
		if(evento.getCalificacion() != 0)
			escribirCalificacion = new JTextArea(Double.toString(eventos.get(eventoIndex).getCalificacion()));
		else
			escribirCalificacion = new JTextArea();
		escribirCalificacion.setBounds(700, 80, 200, 30);
		escribirCalificacion.setFont(new Font("American Typewriter", 0, 20));
		escribirCalificacion.setVisible(false);
		add(escribirCalificacion);
		revalidate();
		repaint();
		 
		if(evento.getPorcentaje() != 0)
			escribirPorcentaje = new JTextArea(Double.toString(eventos.get(eventoIndex).getPorcentaje()));
		else
			escribirPorcentaje = new JTextArea();
		escribirPorcentaje = new JTextArea();
		escribirPorcentaje.setBounds(700, 170, 200, 30);
		escribirPorcentaje.setFont(new Font("American Typewriter", 0, 20));
		escribirPorcentaje.setVisible(false);
		add(escribirPorcentaje);
		revalidate();
		repaint();
		
		crearMateriaNombre = new JTextArea();
		crearMateriaNombre.setBounds(925, 400, 225, 30);
		crearMateriaNombre.setFont(new Font("American Typewriter", 0, 20));
		add(crearMateriaNombre);

	    eleccion = new ButtonGroup();
	    
	    si = new JRadioButton("Si");
	    si.setBounds(425, 450, 100, 100);
	    si.addActionListener(this);
	    si.setFont(new Font("American Typewriter", 0, 24));
		add(si);
		eleccion.add(si);
		
		no = new JRadioButton("No");
		no.setBounds(525, 450, 100, 100);
		no.addActionListener(this);
		no.setSelected(true);
		no.setFont(new Font("American Typewriter", 0, 24));
		add(no);
		eleccion.add(no);
	    
	    setVisible(true);
	}
	
	public void crearFrase(String frase, int posicionx, int posiciony, int tamaniox, int tamanioy, int tamanio, boolean bool) {
		frases = new JLabel(frase);
		frases.setBounds(posicionx, posiciony, tamaniox, tamanioy);
		frases.setFont(new Font("American Typewriter", 0, tamanio));
		frases.setVisible(bool);
		add(frases);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(si.isSelected()) {
			calificacion.setVisible(true);
			porcentajeCalificacion.setVisible(true);
			escribirCalificacion.setVisible(true);
			escribirPorcentaje.setVisible(true);
		}
		else if(no.isSelected()) {	
			calificacion.setVisible(false);
			porcentajeCalificacion.setVisible(false);
			escribirCalificacion.setVisible(false);
			escribirPorcentaje.setVisible(false);
		}
		if(e.getSource() == regresar) {
			Pantalla1 Pantalla = new Pantalla1();
		    Pantalla.setVisible(true);
		    dispose();
		}
		if (e.getSource() == eliminarEvento) {
			
			int confirmacion = JOptionPane.showConfirmDialog(null, "Seguro que quiere eliminar " + eventos.get(eventoIndex).getNombre() + "?", "", JOptionPane.YES_NO_OPTION);
			
			if(confirmacion == JOptionPane.YES_OPTION) {
				
				eventos.remove(eventoIndex);
				imprimirEventos();
				
				Pantalla1 Pantalla = new Pantalla1();
			    Pantalla.setVisible(true);
			    dispose();
			}
		}
		if (e.getSource() == guardarEvento) {
			
			String nombre = escribirNombre.getText();
    		String fecha = elegirFecha.getJFormattedTextField().getText();
    		int horaDeInicio = horaInicio.getSelectedIndex();
    		int horaDeFin = horaFin.getSelectedIndex();
    		String materia = materias.get(materiaElegir.getSelectedIndex()).getNombre();
    		
    		if(calificacion.isVisible() && ((!escribirCalificacion.getText().equals(null) && escribirPorcentaje.getText().equals(null)) || (escribirCalificacion.getText().equals(null) && !escribirPorcentaje.getText().equals(null))))
    			JOptionPane.showMessageDialog(this, "Se tiene que tener porcentaje Y calificación");
    		else if(nombre.equals(null))
    			JOptionPane.showMessageDialog(this, "No puede dejar el nombre en blanco");
    		else if(materias.get(0).getNombre().equals("No hay materias para eliminar"))
    				JOptionPane.showMessageDialog(this, "Tiene que asignar una materia");
    		else {
	    		eventos.get(eventoIndex).setNombre(nombre);
	    		eventos.get(eventoIndex).setFecha(fecha);
	    		eventos.get(eventoIndex).setHoraDeInicio(horaDeInicio);
	    		eventos.get(eventoIndex).setHoraDeFin(horaDeFin);
	    		if(!eventos.get(eventoIndex).getMateria().getNombre().equals(materia)) {
	    			eventos.get(eventoIndex).setMateria(buscarMateria(materia));
    			}
	    		if(calificacion.isVisible() && !escribirCalificacion.getText().equals(null) && !escribirPorcentaje.getText().equals(null)) {
	    			eventos.get(eventoIndex).setCalificacion(Double.parseDouble(escribirCalificacion.getText()));
	    			eventos.get(eventoIndex).setPorcentaje(Double.parseDouble(escribirPorcentaje.getText()));
	    			eventos.get(eventoIndex).setTieneValor(true);
	    			buscarMateria(eventos.get(eventoIndex).getMateria().getNombre()).aniadirCalificacion(Double.parseDouble(escribirCalificacion.getText()), Double.parseDouble(escribirPorcentaje.getText()));
	    		}
	    		
	    		imprimirEventos();
	    		imprimirMaterias();
	   			
	   			JOptionPane.showMessageDialog(this, "El evento " + eventos.get(eventoIndex).getNombre() + " fue guardado");
				
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

	public void leerArchivoDeMateria() {
		try {
			RandomAccessFile archivo=new RandomAccessFile ("materias.dat","rw");
			
			linea = archivo.readLine();
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
			JOptionPane.showMessageDialog(null, "Error encontrando el archivo en leerArchivoDeMateria");
		}
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
			System.out.println("Archivo no encontrado en Pantalla4:imprimirEventos");
		}
	}
}
