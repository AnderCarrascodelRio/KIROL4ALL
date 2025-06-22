package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;

import businessLogic.BLFacade;
import domain.Reserva;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.util.List;

import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Color;

public class CancelarReservasGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Reserva seleccionada = null;
	public BLFacade bl;
	
	public BLFacade getBLFacade() {
		return bl;
	}
	public void setBLFacade(BLFacade b) {
		bl = b;
	}
	
	
	public CancelarReservasGUI(PrincipalGUI volver, int id, String rol, BLFacade b) {
		bl = b;
		setVisible(true);
		setTitle("Caso de Uso: Cancelar Reservas");
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
		btnVolver.addActionListener(e -> volver(volver));
		
		JList<Reserva> list = new JList<Reserva>();
		list.setBounds(10, 45, 414, 133);
		contentPane.add(list);
		List<Reserva> lista = bl.getReservasCancelablesSocio(id);
		DefaultListModel<Reserva> modelo = new DefaultListModel<>();
		for (Reserva r : lista) {modelo.addElement(r);}
		list.setModel(modelo);
		list.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				seleccionada = list.getSelectedValue();
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setBounds(163, 189, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 236, 414, 14);
		contentPane.add(lblNewLabel);
		
		
		btnCancelar.addActionListener(e -> {
			if (seleccionada == null) {
				lblNewLabel.setText("Debe seleccionar la reserva que quiere cancelar.");
			}
			else {
				bl.cancelarReserva(seleccionada);
				lblNewLabel.setText("La reserva ." + seleccionada.getNumReserva() + " fue cancelada.");
			}
		});
	}
	private void volver(PrincipalGUI volver) {
		volver.setVisible(true);
		dispose();
	}

}
