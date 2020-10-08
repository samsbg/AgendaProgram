package vaquitas;

import javax.swing.*;
import java.awt.*;

class Cuadricula extends JComponent{
	
	int ancho = 100;
	int altura = 100;
    int x = 0;
    int y = 0;

    public Cuadricula(int x, int y, int ancho, int altura){             
    	setBounds(x, y, ancho + 1, altura + 1);  
    	this.ancho = ancho;
    	this.altura = altura;
    }
    
    @Override
    public void paintComponent (Graphics a){       
        super.paintComponent(a);           
        a.drawRect(0, 0, ancho, altura); 
        a.setColor(new Color(247, 203, 175));
        a.fillRect(0,0,ancho,altura);

    }
}
