package GUI;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class ThreadMT extends Thread {
	
	JTextField visual_cinta[];
	ArrayList<String> cinta = new ArrayList<String>();
	JTextArea jta = new JTextArea();
	GUI_MAIN gui;
	
	public ThreadMT(JTextField  [] visual_cinta, ArrayList<String> cinta, JTextArea jta, GUI_MAIN gui) {
		this.visual_cinta = visual_cinta;
		this.cinta = cinta;
		this.jta=jta;
		this.gui=gui;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		int p_actual = 0; //inicio desde la posicion vacia de la cinta
		jta.append("Iniciando hilo...\n\n");
		visual_cinta[p_actual].setBorder(new MatteBorder(1, 1, 1, 1, new Color(193,88,220)));
		try {Thread.sleep(1000);}catch(Exception e) {}
		
		estado_q0(p_actual);
		
	}//fin hilo
	
	public void cambia_status(int anterior, int actual) {
	    System.out.println("\nAnterior: "+anterior);
	    System.out.println("Actual: "+actual);
	    jta.append("\t\t\nMovimiento\n");
		visual_cinta[anterior].setBorder(UIManager.getBorder("TextField.border"));
		visual_cinta[actual].setBorder(new MatteBorder(1, 1, 1, 1, new Color(193,88,220)));
		try {Thread.sleep(1000);}catch(Exception e) {}
		
	}
	
	
	
	public void estado_q0(int p_anterior) {
		int p_actual = p_anterior + 1; 
		
		if (visual_cinta[p_actual].getText().equals("a") && cinta.get(p_actual).equals("a")) {
			
			cambia_status(p_actual-1, p_actual);
			
			
			// elimina la antigua
				cinta.remove(p_actual);
				visual_cinta[p_actual].setBackground(new Color(144, 238, 144));
			// Anade el valor analizado
				visual_cinta[p_actual].setText("A");
				cinta.add(p_actual, "A");
				jta.append("\n[a:"+cinta.get(p_actual)+":R]->Q1");
		    System.out.println("\n[a:"+cinta.get(p_actual)+":R]->Q1");
//		    p_actual+=1;
		    estado_q1(p_actual);	
			
		}

		while(visual_cinta[p_actual].getText().equals("B") && cinta.get(p_actual).equals("B")) {
			 jta.append("\n["+cinta.get(p_actual)+":"+cinta.get(p_actual)+":R]->Q0");
			 System.out.print("\n["+cinta.get(p_actual)+":"+cinta.get(p_actual)+":R]->Q0");
		     p_actual++; 
		}//analiza la cinta en busca de alguna a o b
		
		if(visual_cinta[p_actual].getText().equals(" ") && cinta.get(p_actual).equals(" ")) {
			jta.append("\n["+cinta.get(p_actual)+":"+cinta.get(p_actual)+":R]->Q2");
			System.out.print("\n["+cinta.get(p_actual)+":"+cinta.get(p_actual)+":R]->Q2");
			estado_q2();
		}
	}
	
	public void estado_q1(int p_anterior) {
		int p_actual = p_anterior + 1; //analizo la posicion seguiente

	    System.out.println(p_actual);
	    
		while(visual_cinta[p_actual].getText().equals("a") && cinta.get(p_actual).equals("a")) {			
			cambia_status(p_actual-1, p_actual);	
			jta.append("\n[a:"+cinta.get(p_actual)+":R]->Q1");
			System.out.print("\n[a:"+cinta.get(p_actual)+":R]->Q1");
	        p_actual++;
		}// bucle Q1
		
	    
		if(visual_cinta[p_actual].getText().equals("b") && cinta.get(p_actual).equals("b")) {
			cambia_status(p_actual-1, p_actual);
			// elimina la antigua
				cinta.remove(p_actual);
				visual_cinta[p_actual].setBackground(new Color(144, 238, 144));
			// Anade el valor analizado
				visual_cinta[p_actual].setText("B");
				cinta.add(p_actual,"B");
			jta.append("\n[b:"+cinta.get(p_actual)+":L]<-Q3");
	        System.out.println("\n[b:"+cinta.get(p_actual)+":L]<-Q3");			        

	        estado_q3(p_actual);
	        
        } else if(visual_cinta[p_actual].getText().equals("B") && cinta.get(p_actual).equals("B")){
        	jta.append("\n["+cinta.get(p_actual)+":"+cinta.get(p_actual)+":R]->Q4");
	        System.out.print("\n["+cinta.get(p_actual)+":"+cinta.get(p_actual)+":R]->Q4 --> hm");
			cambia_status(p_actual-1, p_actual);
//        	Debo ir al estado q4 simulacion	        	

	        estado_q4(p_actual);
        
        }
	}//Q1
	
	public void estado_q2() {
        System.out.println();
        System.out.println();

		jta.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(50, 205, 50)));
		jta.append("\n\nFin del recorrido!\n\n");
		
		
        System.out.println();
        System.out.println("Fin del recorrido!");
        		
	}//Q2

	
	public void estado_q3(int p_anterior) {
		int p_actual = p_anterior - 1; //analizo la posicion posterior
        while((visual_cinta[p_actual].getText().equals("a") && cinta.get(p_actual).equals("a")) || 
        		( visual_cinta[p_actual].getText().equals("B") && cinta.get(p_actual).equals("B"))) {
        	cambia_status(p_actual+1, p_actual);
        	jta.append("\n["+cinta.get(p_actual)+":"+cinta.get(p_actual)+":L]<-Q3");
	        System.out.print("\n["+cinta.get(p_actual)+":"+cinta.get(p_actual)+":L]<-Q3*");
	        p_actual--;
        }//bucle q3
        if(visual_cinta[p_actual].getText().equals("A") && cinta.get(p_actual).equals("A")) {
        	cambia_status(p_actual+1, p_actual);
        	jta.append("\n["+cinta.get(p_actual)+":"+cinta.get(p_actual)+":R]->Q0");
	        System.out.print("\n["+cinta.get(p_actual)+":"+cinta.get(p_actual)+":R]->Q0 -->");
	        estado_q0(p_actual);
        }
        /*
         * Este metodo retrocedera hasta encontrar la primera A y apartir de alli ara nuevamente el analicis de par en par
         * 
         * */
		
	}//Q3

	public void estado_q4(int p_anterior) {
		int p_actual = p_anterior + 1;
		while(visual_cinta[p_actual].getText().equals("B") && cinta.get(p_actual).equals("B")){
			jta.append("\n["+cinta.get(p_actual)+":"+cinta.get(p_actual)+":R]->Q4");
			 System.out.print("\n["+cinta.get(p_actual)+":"+cinta.get(p_actual)+":R]->Q4#");
			 cambia_status(p_actual-1, p_actual);
		     p_actual++;
		}
		
		if(visual_cinta[p_actual].getText().equals("b") && cinta.get(p_actual).equals("b")) {

			// elimina la antigua
				cinta.remove(p_actual);
				visual_cinta[p_actual].setBackground(new Color(144, 238, 144));
			// Anade el valor analizado
				visual_cinta[p_actual].setText("B");
				cinta.add(p_actual,"B");
			jta.append("\n[b:"+cinta.get(p_actual)+":L]<-Q3");
	        System.out.println("\n[b:"+cinta.get(p_actual)+":L]<-Q3");
	        cambia_status(p_actual-1, p_actual);
        	estado_q3( p_actual);
		}
		
		
	}//Q4

}
