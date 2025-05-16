package presentacion;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class InicioGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 * @param db 
	 */
	public InicioGUI(EntityManager db) {
		setTitle("Inicio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

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
		
		btnLogin.addActionListener(e -> abrirLogin(db));
		btnConsultar.addActionListener(e -> abrirConsultar(db));
		
		}
		private void abrirLogin(EntityManager db) {
			new HacerLoginGUI(this, db);
			setVisible(false);
		}
		private void abrirConsultar(EntityManager db) {
			new ConsultarSesionesGUI(this, db, -1, null);
			setVisible(false);
		}
		public void volver() {
			setVisible(true);
		}
}

