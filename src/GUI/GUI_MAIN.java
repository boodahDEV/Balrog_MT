package GUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.GridLayout;

public class GUI_MAIN extends JFrame implements Runnable{
	
	public		ArrayList<String> 	cinta = new ArrayList<String> ();
	public 		JTextField 			visual_cinta [];
	private 	JPanel 				contentPane, titulo,panel_cinta;
	public 		int 				x, y;
	public 		int 				tamano_entrada = 0,p_actual;
	private 	JTextField 			entrada;
	protected 	MaterialButton 		busca_archivos;
    public 		JFileChooser 		seleccion;
	
	public static void main(String[] args) {
		GUI_MAIN frame = new GUI_MAIN();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}


	public GUI_MAIN() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 457);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(142,36,170)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
			titulo = new JPanel();
			titulo.setBackground(new Color(142,36,170));
				titulo.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseDragged(MouseEvent a) {
						int xx = a.getXOnScreen(), yy = a.getYOnScreen();
						setLocation(xx-x,yy-y);						}
				});
				titulo.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent a) {
						x = a.getX();
						y = a.getY();
					}
				});
			titulo.setBounds(2, 2, 764, 40);
			contentPane.add(titulo);
			titulo.setLayout(null);
			//---> todo lo del panel titulo

				JButton exit = new JButton("");
					exit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							dispose();
							System.exit(0);
						}
					});
				exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				exit.setIcon(new ImageIcon(GUI_MAIN.class.getResource("exit.png")));
				exit.setFocusable(false);
				exit.setContentAreaFilled(false);
				exit.setBorderPainted(false);
				exit.setBounds(728, 5, 30, 30);
				titulo.add(exit);
				
				JLabel titulo_version = new JLabel("Balrog_MT alpha-1.3");
				titulo_version.setForeground(Color.WHITE);
				titulo_version.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
				titulo_version.setBounds(25, 8, 256, 22);
				titulo.add(titulo_version);
			
			JPanel estado_cadena = new JPanel();
			estado_cadena.setBackground(Color.WHITE);
			estado_cadena.setBounds(480, 54, 276, 353);
			contentPane.add(estado_cadena);
			estado_cadena.setLayout(null);
			
			//---> todo lo del panel estado_cadena
			
			
			
			JPanel configuracion_entrada = new JPanel();
			configuracion_entrada.setBorder(new MatteBorder(0, 0, 0, 1, new Color(193,88,220)));
			configuracion_entrada.setBackground(Color.WHITE);
			configuracion_entrada.setForeground(Color.GRAY);
			configuracion_entrada.setBounds(12, 54, 456, 176);
			contentPane.add(configuracion_entrada);
			configuracion_entrada.setLayout(null);
			
			//---> todo lo del panel configuracion entrada
				
				MaterialButton cargar = new MaterialButton();
				cargar.setBounds(53, 77, 160, 35);
				configuracion_entrada.add(cargar);
				cargar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						new Gestor_color("193,88,220","193,88,220","193,88,220","none",cargar);
						crea_cinta();
						entrada.setEnabled(false);
						busca_archivos.setEnabled(false);cargar.setEnabled(false);
					}
				});
				cargar.setColorNormal(new Color(142,36,170));
				cargar.setColorHover(new Color(193,88,220));
				cargar.setColorPressed(new Color(193,88,220));
				cargar.setColorTextNormal(new Color(255,255,255));
				cargar.setFocusable(false);
				cargar.setText("Cargar a la cinta");
				
				JLabel jltituloentrada = new JLabel("Inserte la cadena a evaluar:");
				jltituloentrada.setBounds(101, 12, 240, 18);
				configuracion_entrada.add(jltituloentrada);
				jltituloentrada.setFont(new Font("Century Schoolbook L", Font.PLAIN, 17));
				
							
				entrada = new JTextField();
				entrada.setHorizontalAlignment(SwingConstants.CENTER);
				entrada.setBounds(101, 35, 228, 35);
				configuracion_entrada.add(entrada);
				entrada.setBorder(new MatteBorder(0, 0, 1, 0, new Color(193,88,220)));
				entrada.setForeground(new Color(142,36,170));
				entrada.setFont(new Font("Liberation Mono", Font.BOLD, 20));
				entrada.setColumns(10);
				
				busca_archivos = new MaterialButton();
				busca_archivos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						new Gestor_color("193,88,220","193,88,220","193,88,220","none",busca_archivos);
						busca_archivos.setEnabled(false); entrada.setEnabled(false);
				        seleccion = new JFileChooser();
						opcionAbrir();
					}
				});
				busca_archivos.setColorNormal(new Color(142,36,170));
				busca_archivos.setColorHover(new Color(193,88,220));
				busca_archivos.setColorPressed(new Color(193,88,220));
				busca_archivos.setColorTextNormal(new Color(255,255,255));
				busca_archivos.setFocusable(false);
				busca_archivos.setText("Buscar archivo");
				busca_archivos.setBounds(225, 77, 160, 35);
				configuracion_entrada.add(busca_archivos);
				
				panel_cinta = new JPanel();
				panel_cinta.setBounds(12, 419, 744, 30);
				contentPane.add(panel_cinta);

				JLabel reload = new JLabel("");
				reload.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						new Gestor_color("142,36,170","193,88,220","193,88,220","none",cargar);
						busca_archivos.setEnabled(true);
						cargar.setEnabled(true);
						entrada.setText("");
						entrada.setEnabled(true);
						new Gestor_color("142,36,170","193,88,220","193,88,220","none",busca_archivos);
						cinta.clear();
						panel_cinta.removeAll();
						panel_cinta.repaint();
					}
				});
				reload.setToolTipText("Limpiar entradas");
				reload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				reload.setIcon(new ImageIcon(GUI_MAIN.class.getResource("/GUI/reload.png")));
				reload.setBounds(402, 52, 25, 25);
				configuracion_entrada.add(reload);
				
				
//				txtA.setBackground(new Color(144, 238, 144));

				
//				textField.setBackground(new Color(240, 128, 128)); 

			
	}
	
	public void crea_cinta() {
		
		String cadena_entrada = entrada.getText().replaceAll("\\s*$","").trim(); // limpio la cadena de impuresas 
		if(!cadena_entrada.isEmpty()) {
			cinta.add(0," "); //coloco un segmento vacio de la cinta antes
			for (int i = 1; i <= cadena_entrada.length() && cadena_entrada.charAt(i-1) != 32; i++) {
				cinta.add(i,String.valueOf(cadena_entrada.charAt(i-1)) );
			}
			cinta.add(" ");  //coloco un segmento vacio de la cinta al final.
			
			//Creo el arreglo de cinta!
			tamano_entrada = cinta.size();
			panel_cinta.setLayout(new GridLayout(1, tamano_entrada, 0, 0));
			visual_cinta = new JTextField[tamano_entrada];
			for (int j = 0; j < visual_cinta.length; j++) {
				visual_cinta[j] = new JTextField();
				visual_cinta[j].setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
				visual_cinta[j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				visual_cinta[j].setHorizontalAlignment(SwingConstants.CENTER);
				visual_cinta[j].setText(cinta.get(j));
				visual_cinta[j].setBounds(10, 20, 20, 25);
				visual_cinta[j].setEditable(false);
				panel_cinta.add(visual_cinta[j]);
				visual_cinta[j].setColumns(10);
			}
			System.out.println("Visual cinta: "+visual_cinta.length);
			System.out.println("cinta: "+cinta.size());
			System.out.println("Cadena entrada: "+cadena_entrada.length());
			estado_q0(0);
			new Thread(this).start();

		}else {
			//manejo de errores!
		}

	
	}//fin crea cinta
	
	public void estado_q0(int p_anterior) {
	
//		if(cinta.get(p_actual).equals("a")) {


//		} 
//		
//		while(cinta.get(p_actual).equals("B")) {
//			 System.out.print("\n["+cinta.get(p_actual)+":"+cinta.get(p_actual)+":R]->Q0");
//		     p_actual++; 
//		}//analiza la cinta en busca de alguna a o b
//		
//		if(cinta.get(p_actual).equals(" ")) {
//			System.out.print("\n["+cinta.get(p_actual)+":"+cinta.get(p_actual)+":R]->Q2");
////			estado_q2();
//		}
	}//Q0
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void imprimir_cinta_consola() {
		System.out.print("\tCINTA\n");
		System.out.print(" [");
		for (String string : cinta) {
			System.out.print(" - "+string);
		}
		System.out.print(" -]");
	}
	
    public void opcionAbrir() {
        seleccion.setFileFilter(new FileNameExtensionFilter("Formato de Archivo DLL", "*","dll")); //parametros de busqueda
        
        try {
        	seleccion.setCurrentDirectory(new File((new File(".").getCanonicalPath())));
        } catch (Exception e) {
         // manejo de errores
        }
        
        
        int opcion = seleccion.showOpenDialog(null);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            String ruta = seleccion.getSelectedFile().getAbsolutePath();
            File fichero = new File(ruta);
            int leidos;
			try{
                FileInputStream flectura = new FileInputStream(fichero);
                byte[] matriz = new byte[(byte)fichero.length()];
                leidos = flectura.read(matriz,0,matriz.length);
                entrada.setText(new String (matriz, 0, leidos));   
            }catch (Exception fnf){
                System.out.println("El fichero " + fichero.getAbsolutePath() + " no esta disponible.");
            }
        }else
        	System.out.println("ERROR"); //manejo de errores
        
    }//fin opcion abrir


	@Override
	public void run() {
		// TODO Auto-generated method stub
		p_actual = 1; 
		int i = 0;
		while (i < visual_cinta.length) {
			
			visual_cinta[i].setBorder(new MatteBorder(1, 1, 1, 1, new Color(193,88,220)));
			try {Thread.sleep(1000);}catch(Exception e) {}
			
			
			
			if (visual_cinta[i].getText().equals("a") && cinta.get(p_actual).equals("a")) {
				// elimina la antigua
					cinta.remove(p_actual);
					visual_cinta[i].setBackground(new Color(144, 238, 144));
				// Anade el valor analizado
					visual_cinta[i].setText("A");
					cinta.add(p_actual, "A");
			    System.out.println("\n[a:"+cinta.get(p_actual)+":R]->Q1");
			    p_actual+=1;continue;
//				estado_q1(p_actual);
			}else {
				visual_cinta[i].setBorder(UIManager.getBorder("TextField.border"));
			}
			i++;
		}
	}//fin hilo
 
}//fin clase
