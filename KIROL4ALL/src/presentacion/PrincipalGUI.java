package presentacion;



import javax.persistence.EntityManager;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;


public class PrincipalGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public PrincipalGUI(EntityManager db, int id, String rol) {
		setVisible(true);
		setTitle("Página Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

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
		btnConsultarSesiones.addActionListener(e -> abrirReservarSesiones(db, id, rol));
		
		JButton btnCancelar = new JButton("Cancelar Reservas");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setBounds(10, 70, 414, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(e -> abrirCancelar(db, id, rol));
		
		JButton btnConsultarFacturas = new JButton("Consultar Facturas");
		btnConsultarFacturas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConsultarFacturas.setBounds(10, 104, 414, 23);
		contentPane.add(btnConsultarFacturas);
		btnConsultarFacturas.addActionListener(e -> abrirConsultarFacturas(db, id, rol));
		
		JButton btnTarjeta = new JButton("Añadir Tarjeta");
		btnTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTarjeta.setBounds(10, 138, 414, 23);
		contentPane.add(btnTarjeta);
		btnTarjeta.addActionListener(e -> abrirAnadirTarjeta(db, id, rol));
		
		JButton btnCrearActividad = new JButton("Crear Actividad");
		btnCrearActividad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCrearActividad.setBounds(10, 172, 414, 23);
		contentPane.add(btnCrearActividad);
		if (rol.equals("socio")) {
			btnCrearActividad.setEnabled(false);
		}
		btnCrearActividad.addActionListener(e -> abrirCrearActividad(db, id));
		
		JButton btnPlanificarSesion = new JButton("Planificar Sesiones");
		btnPlanificarSesion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPlanificarSesion.setBounds(10, 206, 414, 23);
		contentPane.add(btnPlanificarSesion);
		if (rol.equals("socio")) {
			btnPlanificarSesion.setEnabled(false);
		}
		btnPlanificarSesion.addActionListener(e -> abrirPlanificarSesiones(db, id));
	}
	
	private void abrirReservarSesiones(EntityManager db, int id, String rol) {
		new ConsultarSesionesGUI(this, db, id, rol);
		setVisible(false);
	}
	
	private void abrirCancelar(EntityManager db, int id, String rol) {
		new CancelarReservasGUI(this, db, id, rol);
		setVisible(false);
	}
	
	private void abrirConsultarFacturas(EntityManager db, int id, String rol) {
		new ConsultarFacturasGUI(this, db, id, rol);
		setVisible(false);
	}
	
	private void abrirAnadirTarjeta(EntityManager db, int id, String rol) {
		new AnadirTarjetaGUI(this, db, id, rol);
		setVisible(false);
	}
	
	private void abrirCrearActividad(EntityManager db, int id) {
		new CrearActividadGUI (this, db, id);
		setVisible(false);
	}
	
	private void abrirPlanificarSesiones(EntityManager db, int id) {
		new PlanificarSesionesGUI(this, db, id);
		setVisible(false);
	}
	
	

}
