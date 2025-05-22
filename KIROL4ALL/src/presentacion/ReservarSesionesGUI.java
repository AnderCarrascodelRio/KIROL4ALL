package presentacion;


import javax.persistence.EntityManager;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Sesion;
import logicanegocio.ReservarSesiones;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Color;

public class ReservarSesionesGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	ReservarSesiones logicaNegocio;



	/**
	 * Create the frame.
	 */
	public ReservarSesionesGUI(ConsultarSesionesGUI volver, EntityManager db, int id, String rol, Sesion reservar) {
		setTitle("Caso de Uso: Reservar Sesiones");
		getContentPane().setLayout(null);
		

		logicaNegocio = new ReservarSesiones(db, id, rol);
		setVisible(true);
		setTitle("Caso de uso: Reservar Sesiones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnConfirmar = new JButton("Confirmar Reserva");
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConfirmar.setBounds(10, 122, 414, 23);
		contentPane.add(btnConfirmar);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 169, 414, 81);
		contentPane.add(textArea);
		
		int res = logicaNegocio.getNumeroReservasRestantes();
		JLabel lblNewLabel = new JLabel("Tiene "+ res + " reserva(s) restante(s).");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 84, 414, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Actividad: " + reservar.getActividad().getNombre());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 35, 159, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Lugar: " + reservar.getInstalacion().getNombre());
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 60, 144, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha: " + reservar.getFechaInicio().getDayOfMonth() + "/" + reservar.getFechaInicio().getMonthValue() +
											" " + reservar.getFechaInicio().getHour() + ":00-" + reservar.getFechaFin().getHour() + ":00");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(244, 36, 180, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Precio/Hora: ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(244, 61, 164, 14);
		contentPane.add(lblNewLabel_4);
		
		btnConfirmar.addActionListener(e -> {
			logicaNegocio.hacerReserva(reservar);
			btnConfirmar.setEnabled(false);
			textArea.setText("Reserva realizada correctamente.");
		});
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(10, 11, 89, 23);
		contentPane.add(btnVolver);
		btnVolver.addActionListener(e -> volver(volver));
		
	}
	
	private void volver(ConsultarSesionesGUI volver) {
		volver.setVisible(true);
		dispose();
	}
}
