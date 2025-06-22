package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;

import businessLogic.BLFacade;
import domain.Factura;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.util.List;

import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Color;

public class ConsultarFacturasGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Factura seleccionada;
	public BLFacade bl;
	
	public BLFacade getBLFacade() {
		return bl;
	}
	public void setBLFacade(BLFacade b) {
		bl = b;
	}

	public ConsultarFacturasGUI(PrincipalGUI volver, int id, String rol, BLFacade b) {
		bl=b;
		setTitle("Caso de Uso: Consultar Facturas");
		setVisible(true);
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
		
		JList<Factura> list = new JList<Factura>();
		list.setBounds(10, 43, 414, 133);
		contentPane.add(list);
		List<Factura> lista = bl.getFacturas(id);
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
			String tar = bl.getTarjeta(id);
			if (tar == null) {
				lblNewLabel.setText("No tiene una tarjeta asignada. Redirigiendo...");
			}
			else {
				bl.pagarFactura(id, seleccionada);
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
