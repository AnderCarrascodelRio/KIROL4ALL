package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Socio;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JTextArea;
import logicanegocio.HacerLogin;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;

public class HacerLoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private HacerLogin logicaNegocio;
	private JPasswordField passwordField;

	
	public void setLogicaNegocio(HacerLogin n1) {
		logicaNegocio = n1;
	}

	/**
	 * Create the frame.
	 * @param db 
	 */
	public HacerLoginGUI(InicioGUI inicio, EntityManager db) {
		logicaNegocio = new HacerLogin(db);
		
		setTitle("Caso de Uso: Hacer Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumSocio = new JLabel("Introducir n\u00FAmero de socio:");
		lblNumSocio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumSocio.setBounds(20, 36, 156, 14);
		contentPane.add(lblNumSocio);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setBounds(186, 33, 172, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		passwordField.setBounds(186, 69, 172, 20);
		contentPane.add(passwordField);
		
		JLabel lblContraseña = new JLabel("Introducir contraseña:");
		lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblContraseña.setBounds(20, 72, 139, 14);
		contentPane.add(lblContraseña);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(20, 166, 392, 84);
		contentPane.add(textArea);
		
		JButton btnEntrar = new JButton("Iniciar Sesión");
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int l = Integer.parseInt(textField.getText());
				String p = new String(passwordField.getPassword());
				String rol = logicaNegocio.autenticar(l, p, db);;
				if (rol == null) {
					textArea.setText("Credenciales Incorrectas");
				}
				else {
					btnEntrar.setEnabled(false);
					textArea.setText("Bienvenido "+ rol + " número "+ l +" \n Entrando al sistema...");
					new javax.swing.Timer(2000, e1 -> {
						((javax.swing.Timer) e1.getSource()).stop();
					    new PrincipalGUI(db, l, rol);
					    dispose();
					}).start();
				}
			}
		});
		btnEntrar.setBounds(153, 132, 139, 23);
		contentPane.add(btnEntrar);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(10, 11, 89, 23);
		contentPane.add(btnVolver);
		
		btnVolver.addActionListener(e -> logicaNegocio.volverAInicio(inicio, this)); //Volver a página de inicio
		setVisible(true);
	}
}
