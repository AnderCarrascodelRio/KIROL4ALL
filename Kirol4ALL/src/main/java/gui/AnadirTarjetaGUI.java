package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;

import businessLogic.BLFacade;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTextArea;

public class AnadirTarjetaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	public BLFacade bl;
	
	public BLFacade getBLFacade() {
		return bl;
	}
	public void setBLFacade(BLFacade b) {
		bl = b;
	}

	
	public AnadirTarjetaGUI(PrincipalGUI volver,int id, String rol, BLFacade b) {
		bl=b;
		setVisible(true);
		setTitle("Caso de Uso: Añadir Tarjeta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		try {
		    UIManager.setLookAndFeel(new FlatIntelliJLaf());
		} catch (Exception e) {}
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(10, 11, 89, 23);
		contentPane.add(btnVolver);
		btnVolver.addActionListener(e-> volver(volver));
		
		String num = bl.getNumTarjeta(id);
		JLabel lblNewLabel = new JLabel("Su número de tarjeta actual: "+ num);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 45, 414, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setBounds(10, 99, 204, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 152, 414, 98);
		contentPane.add(textArea);
		
		JButton btnCambiar = new JButton("Cambiar Número");
		btnCambiar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCambiar.setBounds(224, 99, 200, 23);
		contentPane.add(btnCambiar);
		btnCambiar.addActionListener(e -> {
			boolean res = bl.comprobarTarjeta(textField.getText());
			if (!res) {
				textArea.setText("Introduzca una tarjeta válida (12 dígitos).");
			}
			else {
				bl.cambiarTarjeta(id, textField.getText());
				textArea.setText("Tarjeta actualizada. \n Nueva tarjeta: "+ textField.getText());
				lblNewLabel.setText("Su número de tarjeta actual: "+ textField.getText());
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Introduzca el nuevo número:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 74, 182, 14);
		contentPane.add(lblNewLabel_1);
	}
	
	private void volver(PrincipalGUI volver) {
		volver.setVisible(true);
		dispose();
	}
}
