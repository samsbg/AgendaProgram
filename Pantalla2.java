package solución;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.*;

public class Pantalla2 extends JFrame implements ActionListener{
	
	private JLabel frases;
	private JLabel[] horas = new JLabel[48];
	
	private JButton crearEvento, regresar;
	private JButton[] eventosBotones = new JButton[240];
	
	private boolean[][] disponible = new boolean[48][5];
	
	private Cuadricula casillas[] = new Cuadricula[48];
	private Cuadricula hora[] = new Cuadricula[48];
	
	private String[] horasNum = {"12:30 am", "1:00 am", "1:30 am", "2:00 am", "2:30 am", "3:00 am", "3:30 am", "4:00 am", "4:30 am", "5:00 am", "5:30 am", "6:00 am", "6:30 am", "7:00 am", "7:30 am", "8:00 am", "8:30 am", "9:00 am", "9:30 am", "10:00 am", "10:30 am", "11:00 am", "11:30 am", "12:00 am", "12:30 pm", "1:00 pm", "1:30 pm", "2:00 pm", "2:30 pm", "3:00 pm", "3:30 pm", "4:00 pm", "4:30 pm", "5:00 pm", "5:30 pm", "6:00 pm", "6:30 pm", "7:00 pm", "7:30 pm", "8:00 pm", "8:30 pm", "9:00 pm", "9:30 pm", "10:00 pm", "10:30 pm", "11:00 pm", "11:30 pm", "12:00 pm"};
	protected String[] meses = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
	
	private ArrayList<Materia> materias = new ArrayList<Materia>();
	private ArrayList<Evento> eventos = new ArrayList<Evento>();
	private ArrayList<Evento> eventosDelDia = new ArrayList<Evento>();
	
	private int dia;
	private int mes;
	private int anio;
	
	public Pantalla2(int diaSeleccionado, int mesSeleccionado, int anioSeleccionado, ArrayList<Evento> eventos, ArrayList<Materia> materias) {	
		
		setLayout(null);
		
		setBounds(0, 0, 1250, 700);
	    setLocationRelativeTo(null);
	    setResizable(false);
	    setLayout(null);
	    getContentPane().setBackground(new Color(218, 227, 242));
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.materias = materias;
	    this.eventos = eventos;
	    
	    this.dia = diaSeleccionado;
	    this.mes = mesSeleccionado;
	    this.anio = anioSeleccionado;
	    
	    crearFrase("Eventos de hoy:", 10, -220, 1000, 500, 20);
		crearFrase("<html>" + dia + " de " + meses[mes-1] + "<br> de " + anio + "<html>", 10, -160, 1000, 500, 32);
		
		crearEvento = new JButton("Crear evento");
		crearEvento.setBounds(40, 150, 200, 30);
		crearEvento.setBackground(new Color(253, 127, 124));
		crearEvento.setOpaque(true);
		crearEvento.setBorderPainted(false);
		crearEvento.setFont(new Font("American Typewriter", 0, 18));
		crearEvento.setHorizontalAlignment(SwingConstants.CENTER);
		crearEvento.setHorizontalTextPosition(SwingConstants.CENTER);
		crearEvento.addActionListener(this);
		crearEvento.setToolTipText("Presionar para crear un nuevo evento en el calendario");
		add(crearEvento);
		
		regresar = new JButton("Regresar");
		regresar.setBounds(40, 200, 200, 30);
		regresar.setBackground(new Color(253, 127, 124));
		regresar.setOpaque(true);
		regresar.setBorderPainted(false);
		regresar.setFont(new Font("American Typewriter", 0, 18));
		regresar.setHorizontalAlignment(SwingConstants.CENTER);
		regresar.setHorizontalTextPosition(SwingConstants.CENTER);
		regresar.addActionListener(this);
		add(regresar);

	    for (int i = 0; i < disponible.length; i++) 
			for (int j = 0; j < disponible[i].length; j++) 
				disponible[i][j] = false;
	    
	    buscarEventosDelDia();
	    
	    for (int i = 0; i < eventosDelDia.size(); i++) 
	    	imprimirEventoYChecarDisponibilidad(eventosDelDia.get(i), 0, i);
	        
	    for (int i = 0, x, y = 50; i < horasNum.length/2; i++) {
	    	
	    	x = 375;
			casillas[i] = new Cuadricula(x, y, 375, 25);
			add(casillas[i]);
			
			x = 300;
			horas[i] = new JLabel(horasNum[i]);
			horas[i].setBounds(x+10, y, 74, 25);
			add(horas[i]);
			
			hora[i] = new Cuadricula(x, y, 74, 25);
			add(hora[i]);
			
			y = y + 25;
		}
	    
	    for (int i = horasNum.length/2, x, y = 50; i < horasNum.length; i++) {
	    	
	    	x = 850;
			casillas[i] = new Cuadricula(x, y, 375, 25);
			add(casillas[i]);
			
			x = 775;
			horas[i] = new JLabel(horasNum[i]);
			horas[i].setBounds(x+10, y, 74, 25);
			add(horas[i]);
			
			hora[i] = new Cuadricula(x, y, 74, 25);
			add(hora[i]);
			
			y = y + 25;
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
		if(e.getSource() == regresar) {
			Pantalla1 Pantalla = new Pantalla1();
		    Pantalla.setVisible(true);
		    dispose();
		}
		for (int i = 0; i < eventosDelDia.size(); i++) {
			if(e.getSource() == eventosBotones[i]) {
				Pantalla4 Pantalla = new Pantalla4(eventosDelDia.get(i), materias, eventos);
			    Pantalla.setVisible(true);
			    dispose();
			}
		}
	}

	public void imprimirEventoYChecarDisponibilidad(Evento evento, int inicio, int indice) {
		if(inicio <= 5) {
			boolean a = false;
			for (int i = evento.getHoraDeInicio(); i < evento.getHoraDeFin(); i++) 
				if(disponible[i][inicio] == true)
					a = true;
			if(a)
				imprimirEventoYChecarDisponibilidad(evento, inicio+1, indice);
			else {
				for (int i = evento.getHoraDeInicio(); i < evento.getHoraDeFin(); i++)
					disponible[i][inicio] = true;
				
				String texto = evento.getNombre().substring(0, 3) + "<html> <br><html>" + evento.getMateria().getNombre().substring(0, 3);
				
				eventosBotones[indice] = new JButton(texto);
				eventosBotones[indice].setBackground(new Color(145, 190, 220));
				eventosBotones[indice].setOpaque(true);
				eventosBotones[indice].setBorderPainted(false);
				eventosBotones[indice].setFont(new Font("American Typewriter", 0, 10));
				eventosBotones[indice].setHorizontalTextPosition(SwingConstants.LEFT);
				eventosBotones[indice].addActionListener(this);
				
				if(evento.getHoraDeFin() < horasNum.length/2 && evento.getHoraDeInicio() < horasNum.length/2)	
					eventosBotones[indice].setBounds(75*inicio + 375, 25*evento.getHoraDeInicio() + 50, 75, (evento.getHoraDeFin()-evento.getHoraDeInicio())*25);
				if(evento.getHoraDeFin() > horasNum.length/2 && evento.getHoraDeInicio() > horasNum.length/2)
					eventosBotones[indice].setBounds(75*inicio + 850, 25 * (evento.getHoraDeInicio()-horasNum.length/2) + 50, 75, (evento.getHoraDeFin()-evento.getHoraDeInicio())*25);
				
				add(eventosBotones[indice]);
				eventosBotones[indice].revalidate();
				eventosBotones[indice].repaint();
			}
		}
	}

	
	public void buscarEventosDelDia() {	
		for (int i = 0; i < eventos.size(); i++) 
			if(Integer.parseInt(eventos.get(i).getFecha().substring(0, 2)) == dia)
				eventosDelDia.add(eventos.get(i));
		
		for (int i = 0; i < eventosDelDia.size(); i++) 
			if(Integer.parseInt(eventosDelDia.get(i).getFecha().substring(3, 5)) != mes)
				eventosDelDia.remove(eventosDelDia.get(i));
			
		for (int i = 0; i < eventosDelDia.size(); i++) 
			if(Integer.parseInt(eventosDelDia.get(i).getFecha().substring(6)) != anio)
				eventosDelDia.remove(eventosDelDia.get(i));	
		
	}
}
