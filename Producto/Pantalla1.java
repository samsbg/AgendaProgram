package vaquitas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.*;

public class Pantalla1 extends JFrame implements ActionListener{

	private JLabel frases, frase1;
	private JLabel[] semana = new JLabel[7];
	private JLabel[] eventosDelMesFrases;
	
	private JButton crearEvento, salir, flechaIzquierda, flechaDerecha;
	private JButton dias[] = new JButton[31];
	
	private SimpleDateFormat anio = new SimpleDateFormat("yyyy"); 
	private SimpleDateFormat mesi = new SimpleDateFormat("MMMM", new Locale("ES"));
	private SimpleDateFormat day = new SimpleDateFormat("d", new Locale("ES")); 
	
	private Date date = new Date();
	private Calendar cal = Calendar.getInstance(); 
	
	private int a;
	private int mesMostrado = Integer.parseInt(new SimpleDateFormat("MM").format(Calendar.getInstance().getTime()))-1;
	
	protected String[] meses = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
	protected String diasDeLaSemana[] = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
	
	private ArrayList<Materia> materias = new ArrayList<Materia>();
	private ArrayList<Evento> eventos = new ArrayList<Evento>();
	private ArrayList<Evento> eventosDelMes = new ArrayList<Evento>();
	
	public Pantalla1() {	
		
		setLayout(null);
		
		setBounds(0, 0, 1250, 700);
	    setLocationRelativeTo(null);
	    setResizable(false);
	    setLayout(null);
	    getContentPane().setBackground(new Color(218, 227, 242));
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    leerArchivoDeEventos();
	    leerArchivoDeMateria();
	    
	    crearFrase("Eventos de hoy:", 10, -220, 1000, 500, 20);
	    crearFrase("<html>" + day.format(cal.getTime( )) + " de " + mesi.format(cal.getTime( )) + "<br> de " + anio.format(cal.getTime( )) + "<html>", 10, -160, 1000, 500, 32);
		
	    crearFrase("Materias", 10, 130, 200, 50, 32);
		for (int i = 0; i < materias.size(); i++) {
			if(materias.get(i).getPorcentaje() != 0)
				crearFrase(materias.get(i).getNombre() + ": " + materias.get(i).getTotal()/materias.get(i).getPorcentaje(), 10, 180 + 50*i, 250, 50, 25);
			else
				crearFrase(materias.get(i).getNombre() + ": -", 10, 180 + 50*i, 200, 50, 25);
		}
	
		crearEvento = new JButton("Crear evento");
		crearEvento.setBounds(20, 550, 170, 30);
		crearEvento.setBackground(new Color(253, 127, 124));
		crearEvento.setOpaque(true);
		crearEvento.setBorderPainted(false);
		crearEvento.setFont(new Font("American Typewriter", 0, 18));
		crearEvento.setHorizontalAlignment(SwingConstants.CENTER);
		crearEvento.setHorizontalTextPosition(SwingConstants.CENTER);
		crearEvento.addActionListener(this);
		crearEvento.setToolTipText("Presionar para crear un nuevo evento en el calendario");
		add(crearEvento);
		
		salir = new JButton("Salir");
		salir.setBounds(20, 600, 170, 30);
		salir.setBackground(new Color(253, 127, 124));
		salir.setOpaque(true);
		salir.setBorderPainted(false);
		salir.setFont(new Font("American Typewriter", 0, 18));
		salir.setHorizontalAlignment(SwingConstants.CENTER);
		salir.setHorizontalTextPosition(SwingConstants.CENTER);
		salir.addActionListener(this);
		salir.setToolTipText("Presionar para salir de la aplicación");
		add(salir);
		
		flechaIzquierda = new JButton("\u25C4");
		flechaIzquierda.setBounds(275, -8, 100, 100);
		flechaIzquierda.setBackground(new Color(218, 227, 242));
		flechaIzquierda.setOpaque(true);
		flechaIzquierda.setBorderPainted(false);
		flechaIzquierda.setFont(new Font("American Typewriter", 0, 50));
		flechaIzquierda.addActionListener(this);
		flechaIzquierda.setToolTipText("Ir al mes pasado");
		add(flechaIzquierda);
		
		flechaDerecha = new JButton("\u25BA");
		flechaDerecha.setBounds(770, -8, 100, 100);
		flechaDerecha.setBackground(new Color(218, 227, 242));
		flechaDerecha.setOpaque(true);
		flechaDerecha.setBorderPainted(false);
		flechaDerecha.setFont(new Font("American Typewriter", 0, 50));
		flechaDerecha.addActionListener(this);
		flechaDerecha.setToolTipText("Ir al mes siguiente");
		add(flechaDerecha);
		
	    Locale.setDefault(new Locale("ES"));
		cal.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
		cal.setTime(date);

		botonesDeDias();
		
		for (int i = 0; i < semana.length; i++) {
			semana[i] = new JLabel(diasDeLaSemana[i]);
			semana[i].setBounds(i*131+320, 90, 130, 20);
			semana[i].setFont(new Font("American Typewriter", 0, 18));
			add(semana[i]);
		}

	    setVisible(true);
	}
	
	public void crearFrase(String frase, int posicionx, int posiciony, int tamaniox, int tamanioy, int tamanio) {
		frases = new JLabel(frase);
		frases.setBounds(posicionx, posiciony, tamaniox, tamanioy);
		frases.setFont(new Font("American Typewriter", 0, tamanio));
		add(frases);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == crearEvento) {
			Pantalla3 Pantalla = new Pantalla3(eventos, materias);
		    Pantalla.setVisible(true);
		    dispose();
		}
		
		if(e.getSource() == salir) {
		    System.exit(0);
		}
		
		for (int i = 0; i < dias.length; i++) {
			if(e.getSource() == dias[i]) {
				cal.add(Calendar.MONTH, -1);
				System.out.println(i);
				Pantalla2 pantalla = new Pantalla2(i+1, Integer.parseInt(new SimpleDateFormat("MM").format(cal.getTime())), Integer.parseInt(anio.format(cal.getTime( ))), eventos, materias);
				pantalla.setVisible(true);
			    dispose();
			}
		}
		
		if(e.getSource() == flechaIzquierda) {
			for (int i = 0; i < a; i++) {
				dias[i].setVisible(false);
			}
			
			cal.add(Calendar.MONTH, -2);
			cal.set(Calendar.DATE,1);

			mesMostrado = mesMostrado - 1;
			if(mesMostrado == -1)
				mesMostrado = 11;
			frase1.setVisible(false);
			botonesDeDias();
		}
		
		if(e.getSource() == flechaDerecha) {
			for (int i = 0; i < a; i++) {
				dias[i].setVisible(false);
			}

			mesMostrado = mesMostrado + 1;
			if(mesMostrado == 12)
				mesMostrado = 0;
			frase1.setVisible(false);
			botonesDeDias();
		}
	}
	
	public void botonesDeDias() {
		a = 0;
		cal.set(Calendar.DATE,1); 
		
		frase1 = new JLabel(mesi.format(cal.getTime( )).substring(0, 1).toUpperCase() + mesi.format(cal.getTime( )).substring(1) + " " + anio.format(cal.getTime( )));
		frase1.setBounds(375, -210, 1000, 500);
		frase1.setFont(new Font("American Typewriter", 0, 48));
		add(frase1);
	
		if(cal.get(Calendar.DAY_OF_WEEK) == 1)
			cal.add(Calendar.DATE,-6);
		else
			cal.add(Calendar.DATE,-cal.get(Calendar.DAY_OF_WEEK)+2);
		
		for (int i = 0; i < eventos.size(); i++) {
			if(Integer.parseInt(eventos.get(i).getFecha().substring(3, 5)) == mesMostrado+1)
				eventosDelMes.add(eventos.get(i));
		}
		
		eventosDelMesFrases = new JLabel[eventosDelMes.size()];
		
		for(int week = 0; week < 6; week++) {
			for(int d = 0; d < 7; d++) {
				
				if(mesi.format(cal.getTime( )).equals(meses[mesMostrado])) {
					
					String frase = "<body><br>";
					
					for (int i = 0; i < eventosDelMes.size(); i++) 
						if(eventosDelMes.get(i).getFecha().substring(0, 2).equals(day.format(cal.getTime( ))) && 
							(Integer.parseInt(eventosDelMes.get(i).getFecha().substring(3, 5)) == (mesMostrado + 1)))
							frase = frase + "-" + eventosDelMes.get(i).getNombre() + "\n";
					
					dias[a] = new JButton();
					dias[a].setText("<html>" + day.format(cal.getTime( )) + frase + "<html>");
					dias[a].setHorizontalAlignment(SwingConstants.LEFT);
					dias[a].setVerticalAlignment(SwingConstants.TOP);
					dias[a].setBounds(d*131+290, week*88+131, 130, 87);
					dias[a].setBackground(new Color(253, 127, 124));
					dias[a].setOpaque(true);
					dias[a].setFont(new Font("American Typewriter", 0, 18));
					dias[a].addActionListener(this);
					if((day.format(cal.getTime( )).equals(day.format(Calendar.getInstance().getTime()))) && 
							(mesi.format(cal.getTime( )).equals(mesi.format(Calendar.getInstance().getTime()))))
							dias[a].setForeground(new Color(253, 127, 124));
					add(dias[a], -1);
					dias[a].revalidate();
					dias[a].repaint();
					a++;
				}
				cal.add(Calendar.DATE,+1);
			} 
		}
	}
	
	public void leerArchivoDeEventos() {
		try {
			
			RandomAccessFile archivo = new RandomAccessFile ("eventos.dat","rw");
			
			String linea = archivo.readLine();
			String[] eventoMomentario;
			
			if(linea != null)
				while(linea != null) {
					eventoMomentario = linea.split("/");
					
					Evento eventoTemporal = new Evento(Boolean.parseBoolean(eventoMomentario[0]), eventoMomentario[1], 
							Integer.parseInt(eventoMomentario[2]),Integer.parseInt(eventoMomentario[3]), eventoMomentario[4], 
							eventoMomentario[5], false, Double.parseDouble(eventoMomentario[7]), Double.parseDouble(eventoMomentario[8]));
					eventos.add(eventoTemporal);
					linea = archivo.readLine();
				}
				
	 		archivo.close();
	 		
		}
		catch(IOException e) {   
			JOptionPane.showMessageDialog(null, "Error encontrando el archivo Pantalla1:leerArchivo");
		}
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
			JOptionPane.showMessageDialog(null, "Error encontrando el archivo en leerArchivoDeMateria");
		}
	}


}
