package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;

import businessLogic.BLFacade;
import domain.Sesion;
import configuration.SesionTableModel;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ConsultarSesionesGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Sesion seleccionada;
	public BLFacade bl;
	
	public BLFacade getBLFacade() {
		return bl;
	}
	public void setBLFacade(BLFacade b) {
		bl = b;
	}

	/**
	 * Create the frame.
	 * @param db 
	 */
	public ConsultarSesionesGUI(Object origen, int id, String rol, BLFacade b) {
		bl =b;
		setVisible(true);
		setTitle("Caso de Uso: Consultar Sesiones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		try {
		    UIManager.setLookAndFeel(new FlatIntelliJLaf());
		} catch (Exception e) {}
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sesiones:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(197, 11, 71, 14);
		contentPane.add(lblNewLabel);
		
		List<Sesion> sesiones = bl.obtenerSesiones();
		for (Sesion s : sesiones) {
		    if (s.getActividad() == null) {
		        System.out.println("Sesión sin actividad: " + s.getFechaFin());
		    }
		}
		SesionTableModel modelo = new SesionTableModel(sesiones);
		JTable tabla = new JTable(modelo);
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(10, 36, 414, 132);
		contentPane.add(scrollPane);
		
		
		JButton btnReservar = new JButton("Reservar");
		btnReservar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnReservar.setBounds(179, 179, 89, 23);
		contentPane.add(btnReservar);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 213, 414, 37);
		contentPane.add(textArea);
		
		
		tabla.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
		        int filaVista = tabla.getSelectedRow();
		        int filaModelo = tabla.convertRowIndexToModel(filaVista);
		        seleccionada = modelo.getSesionAt(filaModelo);
		    }
		});
		
		btnReservar.addActionListener(e -> {
			if (rol == null) {
				textArea.setText("Debe hacer login para poder reservar. \n Redirgiendo...");
				new javax.swing.Timer(2000, e1 -> {
					((javax.swing.Timer) e1.getSource()).stop();
				    volver(origen);
				}).start();
			}
			else {
				if (seleccionada == null) {
					textArea.setText("Seleccione la sesión que quiere reservar.");
				}
				else {
					if (bl.puedeReservar(id)) {
						new ReservarSesionesGUI(this, id , rol, seleccionada, bl);
						setVisible(false);
					}
					else {
						textArea.setText("Has superado el número de reservas posible. \n No puedes hacer más reservas.");
					}
				}
			}
		});
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(10, 8, 89, 23);
		contentPane.add(btnVolver);
		
		
		btnVolver.addActionListener(e -> volver(origen));
			
	}
	
	void volver(Object a) {
	    if (a instanceof java.awt.Component) {
	        ((java.awt.Component) a).setVisible(true);
	    }
	    dispose();
	}
}
