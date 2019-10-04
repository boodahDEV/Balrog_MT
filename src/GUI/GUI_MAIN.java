package GUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class GUI_MAIN extends JFrame {

	private JPanel 	contentPane, titulo;
	public int 		x, y;
	private JTextField entrada;

	
	public static void main(String[] args) {
		GUI_MAIN frame = new GUI_MAIN();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}


	public GUI_MAIN() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 500);
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
			
			JPanel estado_cadena = new JPanel();
			estado_cadena.setBackground(Color.WHITE);
			estado_cadena.setBounds(480, 54, 276, 353);
			contentPane.add(estado_cadena);
			estado_cadena.setLayout(null);
			
			//---> todo lo del panel estado_cadena
			
			
			
			JPanel configuracion_entrada = new JPanel();
			configuracion_entrada.setBorder(new MatteBorder(0, 0, 0, 1, (Color) Color.GRAY));
			configuracion_entrada.setBackground(Color.WHITE);
			configuracion_entrada.setForeground(Color.GRAY);
			configuracion_entrada.setBounds(12, 54, 456, 176);
			contentPane.add(configuracion_entrada);
			configuracion_entrada.setLayout(null);
			
			//---> todo lo del panel configuracion entrada
				
				MaterialButton cargar = new MaterialButton();
				cargar.setBounds(252, 30, 160, 35);
				configuracion_entrada.add(cargar);
				cargar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						new Gestor_color("193,88,220","193,88,220","193,88,220","none",cargar);
						crea_cinta();
					}
				});
				cargar.setColorNormal(new Color(142,36,170));
				cargar.setColorHover(new Color(193,88,220));
				cargar.setColorPressed(new Color(193,88,220));
				cargar.setColorTextNormal(new Color(255,255,255));
				cargar.setFocusable(false);
				cargar.setText("Cargar a la cinta");
				
				JLabel jltituloentrada = new JLabel("Inserte la cadena a evaluar:");
				jltituloentrada.setBounds(12, 10, 240, 18);
				configuracion_entrada.add(jltituloentrada);
				jltituloentrada.setFont(new Font("Dialog", Font.PLAIN, 15));
				
							
				entrada = new JTextField();
				entrada.setHorizontalAlignment(SwingConstants.CENTER);
				entrada.setBounds(12, 30, 228, 35);
				configuracion_entrada.add(entrada);
				entrada.setBorder(new MatteBorder(0, 0, 1, 0, new Color(193,88,220)));
				entrada.setForeground(new Color(142,36,170));
				entrada.setFont(new Font("Liberation Mono", Font.BOLD, 20));
				entrada.setColumns(10);

			
	}
	
	public void crea_cinta() {
		ArrayList<String> cinta = new ArrayList<String> ();
		JTextField visual_cinta [];
		int tamano_entrada = 0;
		
		String cadena_entrada = entrada.getText().replaceAll("\\s","");
		
		for (int i = 1; i<=cadena_entrada.length(); i++) {
			tamano_entrada+=1;
		}
		System.out.println(tamano_entrada);
		
	}
}
