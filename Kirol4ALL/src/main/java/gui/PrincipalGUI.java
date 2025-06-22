package gui;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;

import businessLogic.BLFacade;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;


public class PrincipalGUI extends JFrame {

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
	public PrincipalGUI(int id, String rol, BLFacade b) {
		bl=b;
		setVisible(true);
		setTitle("Página Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		try {
		    UIManager.setLookAndFeel(new FlatIntelliJLaf());
		} catch (Exception e) {}

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(181, 11, 86, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnConsultarSesiones = new JButton("Reservar Sesiones");
		btnConsultarSesiones.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConsultarSesiones.setBounds(10, 36, 414, 23);
		contentPane.add(btnConsultarSesiones);
		btnConsultarSesiones.addActionListener(e -> abrirReservarSesiones(id, rol));
		
		JButton btnCancelar = new JButton("Cancelar Reservas");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setBounds(10, 70, 414, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(e -> abrirCancelar(id, rol));
		
		JButton btnConsultarFacturas = new JButton("Consultar Facturas");
		btnConsultarFacturas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConsultarFacturas.setBounds(10, 104, 414, 23);
		contentPane.add(btnConsultarFacturas);
		btnConsultarFacturas.addActionListener(e -> abrirConsultarFacturas(id, rol));
		
		JButton btnTarjeta = new JButton("Añadir Tarjeta");
		btnTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTarjeta.setBounds(10, 138, 414, 23);
		contentPane.add(btnTarjeta);
		btnTarjeta.addActionListener(e -> abrirAnadirTarjeta(id, rol));
		
		JButton btnCrearActividad = new JButton("Crear Actividad");
		btnCrearActividad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCrearActividad.setBounds(10, 172, 414, 23);
		contentPane.add(btnCrearActividad);
		if (rol.equals("socio")) {
			btnCrearActividad.setEnabled(false);
		}
		btnCrearActividad.addActionListener(e -> abrirCrearActividad(id));
		
		JButton btnPlanificarSesion = new JButton("Planificar Sesiones");
		btnPlanificarSesion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPlanificarSesion.setBounds(10, 206, 414, 23);
		contentPane.add(btnPlanificarSesion);
		if (rol.equals("socio")) {
			btnPlanificarSesion.setEnabled(false);
		}
		btnPlanificarSesion.addActionListener(e -> abrirPlanificarSesiones(id));
	}
	
	private void abrirReservarSesiones( int id, String rol) {
		new ConsultarSesionesGUI(this, id, rol,bl);
		setVisible(false);
	}
	
	private void abrirCancelar(int id, String rol) {
		new CancelarReservasGUI(this, id, rol,bl);
		setVisible(false);
	}
	
	private void abrirConsultarFacturas( int id, String rol) {
		new ConsultarFacturasGUI(this, id, rol, bl);
		setVisible(false);
	}
	
	private void abrirAnadirTarjeta(int id, String rol) {
		new AnadirTarjetaGUI(this, id, rol,bl);
		setVisible(false);
	}
	
	private void abrirCrearActividad( int id) {
		new CrearActividadGUI (this, id, bl);
		setVisible(false);
	}
	
	private void abrirPlanificarSesiones( int id) {
		new PlanificarSesionesGUI(this, id, bl);
		setVisible(false);
	}
	
	

}
