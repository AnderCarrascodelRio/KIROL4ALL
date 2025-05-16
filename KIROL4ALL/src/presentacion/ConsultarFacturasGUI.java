package presentacion;

import java.awt.EventQueue;

import javax.persistence.EntityManager;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Factura;
import domain.Reserva;
import logicanegocio.ConsultarFacturas;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Color;

public class ConsultarFacturasGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ConsultarFacturas logicaNegocio;
	Factura seleccionada;

	public ConsultarFacturasGUI(PrincipalGUI volver, EntityManager db, int id, String rol) {
		setTitle("Caso de Uso: Consultar Facturas");
		setVisible(true);
		logicaNegocio = new ConsultarFacturas(db, id, rol);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(10, 11, 89, 23);
		contentPane.add(btnVolver);
		
		JList<Factura> list = new JList<Factura>();
		list.setBounds(10, 43, 414, 133);
		contentPane.add(list);
		List<Factura> lista = logicaNegocio.getFacturas();
		DefaultListModel<Factura> modelo = new DefaultListModel<>();
		for (Factura f : lista) {modelo.addElement(f);}
		list.setModel(modelo);
		list.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				seleccionada = list.getSelectedValue();
			}
		});
		
		JButton btnPagar = new JButton("Pagar");
		btnPagar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPagar.setBounds(173, 187, 89, 23);
		contentPane.add(btnPagar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 221, 414, 14);
		contentPane.add(lblNewLabel);
		
		btnPagar.addActionListener(e -> {
			String tar = logicaNegocio.getTarjeta();
			if (tar == null) {
				lblNewLabel.setText("No tiene una tarjeta asignada. Redirigiendo...");
			}
			else {
				logicaNegocio.pagarFactura(seleccionada);
				lblNewLabel.setText("Factura pagada con tarjeta: "+ tar);
			}
		});
		
		btnVolver.addActionListener(e -> volver(volver));
		
	}
	
	public void volver(PrincipalGUI volver) {
		volver.setVisible(true);
		dispose();
	}

}
