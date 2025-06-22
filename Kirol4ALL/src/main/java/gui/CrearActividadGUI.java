package gui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;

import businessLogic.BLFacade;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;

public class CrearActividadGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textExigencia;
	private JTextField textPrecio;
	public BLFacade bl;
	
	public BLFacade getBLFacade() {
		return bl;
	}
	public void setBLFacade(BLFacade b) {
		bl = b;
	}
	
	public CrearActividadGUI(PrincipalGUI volver, int id, BLFacade b) {
		bl=b;
		setVisible(true);
		setTitle("Caso de Uso: Crear Actividad");
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
		btnVolver.setBounds(10, 11, 67, 23);
		contentPane.add(btnVolver);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 45, 86, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Exigencia:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 86, 67, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Precio/Hora:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 134, 67, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Instalacion");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(211, 46, 78, 14);
		contentPane.add(lblNewLabel_3);		
		
		textNombre = new JTextField();
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textNombre.setBounds(79, 43, 86, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textExigencia = new JTextField();
		textExigencia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textExigencia.setBounds(79, 84, 86, 20);
		contentPane.add(textExigencia);
		textExigencia.setColumns(10);
		
		textPrecio = new JTextField();
		textPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textPrecio.setBounds(87, 132, 86, 20);
		contentPane.add(textPrecio);
		textPrecio.setColumns(10);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCrear.setBounds(270, 130, 89, 23);
		contentPane.add(btnCrear);
		
		JLabel lblNota = new JLabel("");
		lblNota.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNota.setForeground(new Color(255, 0, 0));
		lblNota.setBounds(10, 183, 414, 14);
		contentPane.add(lblNota);
		
		btnCrear.addActionListener(e -> {
			String nombre = textNombre.getText().trim();
			String exigenciaStr = textExigencia.getText().trim();
			String precioStr = textPrecio.getText().trim();
			if (nombre.isEmpty() || exigenciaStr.isEmpty() || precioStr.isEmpty()) {
				lblNota.setText("Rellene todos los campos.");
			}
			else {
				int exigencia = Integer.parseInt(exigenciaStr);
				if (exigencia < 1 || exigencia > 5) {
					lblNota.setText("La exigencia debe se un número del 1 al 5.");
				}
				else {
					double precio = Double.parseDouble(precioStr);
					if (!bl.crearActividad(nombre, exigencia, precio)) {
						lblNota.setText("Ya existe una actividad con el nombre: "+ nombre +".");
					}
					else {
						lblNota.setText("Actividad "+nombre + " creada." );
					}
				}
			}
		});
		
		
		
		btnVolver.addActionListener(e -> volver(volver));
	}
	
	public void volver(PrincipalGUI volver) {
		volver.setVisible(true);
		dispose();
	}
}
