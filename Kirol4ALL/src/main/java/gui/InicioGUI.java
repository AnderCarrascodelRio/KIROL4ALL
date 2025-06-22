 package gui;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import com.formdev.flatlaf.FlatIntelliJLaf;

import businessLogic.BLFacade;

public class InicioGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public BLFacade bl;
	
	public BLFacade getBLFacade() {
		return bl;
	}
	public void setBLFacade(BLFacade b) {
		bl = b;
	}

	/**
	 * Create the frame.
	 * 
	 */
	public InicioGUI(BLFacade b) {
		bl = b;
		System.out.println( bl.getSocio().toString());
		System.out.println(bl.getEncargado().toString());
		setTitle("Inicio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		try {
		    UIManager.setLookAndFeel(new FlatIntelliJLaf());
		} catch (Exception e) {}

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKIROL4ALL = new JLabel("KIROL4ALL");
		lblKIROL4ALL.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblKIROL4ALL.setBounds(167, 11, 112, 62);
		contentPane.add(lblKIROL4ALL);
		
		JLabel lblPresentacion = new JLabel("\u00A1Bienvenido al sistema de reservas online del Polideportivo!");
		lblPresentacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPresentacion.setBounds(69, 59, 355, 14);
		contentPane.add(lblPresentacion);
		
		JLabel lblNewLabel = new JLabel("\u00BFQu\u00E9 desea hacer?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(158, 84, 152, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnLogin = new JButton("Hacer Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLogin.setBounds(58, 128, 330, 35);
		contentPane.add(btnLogin);
		
		JButton btnConsultar = new JButton("Consultar Sesiones");
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConsultar.setBounds(58, 186, 330, 35);
		contentPane.add(btnConsultar);
		
		btnLogin.addActionListener(e -> abrirLogin());
		btnConsultar.addActionListener(e -> abrirConsultar());
		
		}
		private void abrirLogin() {
			new HacerLoginGUI(this, bl);
			setVisible(false);
		}
		private void abrirConsultar() {
			new ConsultarSesionesGUI(this, -1, null, bl);
			setVisible(false);
		}
		public void volver() {
			setVisible(true);
		}
}

