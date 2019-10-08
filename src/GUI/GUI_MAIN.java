package GUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import javax.swing.JTextArea;

/*
 * Creado por boodahDEV 2019
 * Solo valido para cadenas (a)^n (b)^n
 * 
 * */

public class GUI_MAIN extends JFrame {
	
	public		ArrayList<String> 	cinta = new ArrayList<String> ();
	public 		JTextField 			visual_cinta [];
	public 		JPanel 				contentPane, titulo,panel_cinta;
	public 		int 				x, y;
	public 		int 				tamano_entrada = 0,p_actual;
	private 	JTextField 			entrada;
	public 		MaterialButton 		busca_archivos,iniciar,cargar;
    public 		JFileChooser 		seleccion;
    public 		JTextArea 			jta_info, jta_estado;
    public 		JLabel				alertas;
	
	public static void main(String[] args) {
		GUI_MAIN frame = new GUI_MAIN();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}


	public GUI_MAIN() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 472);
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
				
				JLabel titulo_version = new JLabel("Balrog_MT   beta-1.5");
				titulo_version.setForeground(Color.WHITE);
				titulo_version.setFont(new Font("Century Schoolbook L", Font.BOLD, 18));
				titulo_version.setBounds(10, 8, 256, 22);
				titulo.add(titulo_version);
			
			JPanel estado_cadena = new JPanel();
			estado_cadena.setBackground(Color.WHITE);
			estado_cadena.setBounds(480, 54, 276, 353);
			contentPane.add(estado_cadena);
			estado_cadena.setLayout(null);
				jta_estado = new JTextArea();
				jta_estado.setFont(new Font("Century Schoolbook L", Font.BOLD, 14));
				jta_estado.setEditable(false);
				JScrollPane jspe = new JScrollPane(jta_estado);
				jspe.setBorder(null);
				jspe.setBounds(0, 0, 276, 353);
				estado_cadena.add(jspe);
			
			
			//---> todo lo del panel estado_cadena
			
			
			
			JPanel configuracion_entrada = new JPanel();
			configuracion_entrada.setBorder(new MatteBorder(0, 0, 0, 1, new Color(193,88,220)));
			configuracion_entrada.setBackground(Color.WHITE);
			configuracion_entrada.setForeground(Color.GRAY);
			configuracion_entrada.setBounds(12, 54, 456, 176);
			contentPane.add(configuracion_entrada);
			configuracion_entrada.setLayout(null);
			
			//---> todo lo del panel configuracion entrada
			
				JButton git = new JButton("");
				git.setBounds(0, 0, 40, 40);
				configuracion_entrada.add(git);
				git.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent a) {
						try {
							Desktop.getDesktop().browse(new URI("https://github.com/boodahDEV"));
							} catch (Exception e) {
								//aviso.setVisible(true);
								System.out.println("Error, sin conexion!"+e);
							} 
					}
				});
				git.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				git.setRolloverSelectedIcon(new ImageIcon(GUI_MAIN.class.getResource("/GUI/github2.png")));
				git.setRolloverIcon(new ImageIcon(GUI_MAIN.class.getResource("/GUI/github2.png")));
				git.setIcon(new ImageIcon(GUI_MAIN.class.getResource("/GUI/github.png")));
				git.setIconTextGap(-10);
				git.setFocusable(false);
				git.setContentAreaFilled(false);
				git.setBorderPainted(false);
				
				iniciar = new MaterialButton();
				iniciar.setBounds(186, 124, 68, 35);
				iniciar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(!entrada.isEnabled() && !entrada.getText().isEmpty()) {
							jta_info.append("\nIniciando hilos...\n\n");
							ThreadMT tmt = new ThreadMT(visual_cinta, cinta,jta_estado,GUI_MAIN.this);
							tmt.start();
							try {Thread.yield();} catch (Exception e) {}
							iniciar.setEnabled(false);
						}else {
							alertas.setForeground(Color.RED); 
							alertas.setText("Error al iniciar la maquina de turing!");
						}
					}
				});
				iniciar.setColorNormal(new Color(142,36,170));
				iniciar.setColorHover(new Color(193,88,220));
				iniciar.setColorPressed(new Color(193,88,220));
				iniciar.setColorTextNormal(new Color(255,255,255));
				iniciar.setFocusable(false);
				iniciar.setText("Iniciar");
				configuracion_entrada.add(iniciar);
				
				cargar = new MaterialButton();
				cargar.setBounds(53, 77, 160, 35);
				configuracion_entrada.add(cargar);
				cargar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						new Gestor_color("193,88,220","193,88,220","193,88,220","none",cargar);
						crea_cinta();
						entrada.setEnabled(false);
						alertas.setText("");
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
						alertas.setText("");
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
				panel_cinta.setBounds(12, 419, 744, 40);
				contentPane.add(panel_cinta);

				JLabel reload = new JLabel("");
				reload.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						new Gestor_color("142,36,170","193,88,220","193,88,220","none",cargar);
						contentPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(142,36,170)));
						titulo.setBackground(new Color(142,36,170));
						jta_estado.setBorder(null);
						busca_archivos.setEnabled(true);
						cargar.setEnabled(true);
						entrada.setText("");
						new Gestor_color("142,36,170","193,88,220","193,88,220","none",iniciar);
						entrada.setEnabled(true);
						iniciar.setEnabled(true);
						alertas.setText("");

						new Gestor_color("142,36,170","193,88,220","193,88,220","none",busca_archivos);
						jta_estado.setText("");
						jta_info.setText("");
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
				
				JPanel panel_info = new JPanel();
				panel_info.setLayout(null);
				panel_info.setForeground(Color.GRAY);
				panel_info.setBorder(new MatteBorder(0, 0, 0, 1, new Color(193,88,220)));
				panel_info.setBackground(Color.WHITE);
				panel_info.setBounds(12, 230, 456, 176);
				contentPane.add(panel_info);
				
					alertas = new JLabel("");
					alertas.setFont(new Font("Century Schoolbook L", Font.PLAIN, 15));
					alertas.setBounds(10, 10, 434, 20);
					panel_info.add(alertas);
					
					jta_info = new JTextArea();
					jta_info.setFont(new Font("Century Schoolbook L", Font.BOLD, 14));
					jta_info.setEditable(false);
					JScrollPane jsp = new JScrollPane(jta_info);
					jsp.setBorder(null);
					jsp.setBounds(10, 35, 434, 129);
					panel_info.add(jsp);
					
	}//fin constructor
	
	public void crea_cinta() {
		
		String cadena_entrada = entrada.getText().replaceAll("\\s*$","").trim(); // limpio la cadena de impuresas 
		if(!cadena_entrada.isEmpty() && analiza_entrada(cadena_entrada)==true) {
			jta_info.setText("");
			cinta.add(0," "); //coloco un segmento vacio de la cinta antes
			for (int i = 1; i <= cadena_entrada.length() && cadena_entrada.charAt(i-1) != 32; i++) {
				cinta.add(i,String.valueOf(cadena_entrada.charAt(i-1)) );
				if(i%2==0)
					jta_info.append("Cargando cinta!\n");

			}
			cinta.add(" ");  //coloco un segmento vacio de la cinta al final.
			jta_info.append("Cinta cargada...\n\n");
			
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
				panel_cinta.repaint();
				if(j%2==0)
					jta_info.append("Cargando la cinta visual...\n");
			}
			jta_info.append("Cinta visual cargada...\n\n\n");

			jta_info.append("Tamano visual cinta: "+visual_cinta.length+"\n");
			jta_info.append("Tamano de cinta: "+cinta.size()+"\n");
			jta_info.append("Cadena entrada: "+cadena_entrada.length()+"\n");

		}else {
			//manejo de errores!
			alertas.setForeground(Color.RED);
			alertas.setText("ERROR!, cadena de entrada invalida o vacia");
		}

	
	}//fin crea cinta

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
        	alertas.setForeground(Color.RED);
			alertas.setText("ERROR!, problema al abrir la cadena por este medio");
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
            	alertas.setForeground(Color.RED);
				alertas.setText("El fichero " + fichero.getAbsolutePath() + " no esta disponible.");
//                System.out.println("El fichero " + fichero.getAbsolutePath() + " no esta disponible.");
            }
        }else {
        	alertas.setForeground(Color.RED);
			alertas.setText("ERROR!");
        }        
    }//fin opcion abrir

	public boolean analiza_entrada(String entrada) {
		 int l = entrada.length(); 
	        if (l%2 == 1){ 
	        	System.out.print("ok");
	            return false; 
	        } // :v solo las entradas pares tienen a ser valida 
	        
	        int i = 0; 
	        int j = l-1; 
	        
	        //ahora verifico esta mrd a ver si tiene solo letras ab por lo menos
	        while (i<j) { 
	            if(entrada.charAt(i) != 'a' || entrada.charAt(j) != 'b'){ 
		        	System.out.print("\nKO");
	                return false; 
	            } 	
	          i++; 
	          j--; 
	        } 
		return true;
	}// fiuncion para manejar los errores

}//fin clase
