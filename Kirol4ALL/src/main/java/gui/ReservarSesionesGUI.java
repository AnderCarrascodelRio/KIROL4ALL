package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;

import businessLogic.BLFacade;
import domain.Sesion;
import javax.swing.JButton;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Color;

public class ReservarSesionesGUI extends JFrame {

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
	 */
	public ReservarSesionesGUI(ConsultarSesionesGUI volver, int id, String rol, Sesion reservar, BLFacade b) {
		bl=b;
		setTitle("Caso de Uso: Reservar Sesiones");
		getContentPane().setLayout(null);
		
		setVisible(true);
		setTitle("Caso de uso: Reservar Sesiones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		try {
		    UIManager.setLookAndFeel(new FlatIntelliJLaf());
		} catch (Exception e) {}

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnConfirmar = new JButton("Confirmar Reserva");
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConfirmar.setBounds(10, 122, 414, 23);
		contentPane.add(btnConfirmar);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 169, 414, 81);
		contentPane.add(textArea);
		
		int res = bl.getNumeroReservasRestantes(id);
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
		
		Calendar calInicio = Calendar.getInstance();
		calInicio.setTime(reservar.getFechaInicio());

		int dia = calInicio.get(Calendar.DAY_OF_MONTH);
		int mes = calInicio.get(Calendar.MONTH) + 1;
		int horaIni = calInicio.get(Calendar.HOUR_OF_DAY);

		Calendar calFin = Calendar.getInstance();
		calFin.setTime(reservar.getFechaFin());
		int horaFin = calFin.get(Calendar.HOUR_OF_DAY);

		String texto = "Fecha: " + dia + "/" + mes + " " + horaIni + ":00-" + horaFin + ":00";

		JLabel lblNewLabel_3 = new JLabel(texto);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(244, 36, 180, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Precio/Hora: ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(244, 61, 164, 14);
		contentPane.add(lblNewLabel_4);
		
		btnConfirmar.addActionListener(e -> {
			bl.hacerReserva(id, reservar);
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
