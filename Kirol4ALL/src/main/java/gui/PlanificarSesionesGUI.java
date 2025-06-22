package gui;

import java.awt.Font;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.formdev.flatlaf.FlatIntelliJLaf;

import businessLogic.BLFacade;
import configuration.SesionTableModel;
import domain.Actividad;
import domain.Instalacion;
import domain.Sesion;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;

public class PlanificarSesionesGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldHoraInicio;
	private JTextField textFieldMinInicio;
	private JTextField textFieldHoraFin;
	private JTextField textFieldMinFin;
	public BLFacade bl;
	
	public BLFacade getBLFacade() {
		return bl;
	}
	public void setBLFacade(BLFacade b) {
		bl = b;
	}

	public PlanificarSesionesGUI(PrincipalGUI volver, int id, BLFacade b) {
		bl=b;
		setVisible(true);
		setTitle("Caso de Uso: Planificar Sesiones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(10, 11, 67, 23);
		contentPane.add(btnVolver);
		
		List<Sesion> sesiones = bl.obtenerSesiones();
		SesionTableModel modelo = new SesionTableModel(sesiones);
		JTable tabla = new JTable(modelo);
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(10, 36, 414, 105);
		contentPane.add(scrollPane);
		
		
		JLabel lblNewLabel = new JLabel("Actividad:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 152, 67, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Instalaci\u00F3n:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(152, 152, 76, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Fecha:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(289, 152, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JComboBox<String> comboBoxActividad = new JComboBox<String>();
		comboBoxActividad.setBounds(10, 167, 117, 30);
		contentPane.add(comboBoxActividad);
		List<Actividad> actividades = bl.obtenerActividades();
		for (Actividad act : actividades) {
			comboBoxActividad.addItem(act.getNombre());
		}
		String stringA = (String) comboBoxActividad.getSelectedItem();	
		Actividad actSeleccionada = bl.getActividadNombre(stringA);
		
		
		JComboBox<String> comboBoxInstalacion = new JComboBox<String>();
		comboBoxInstalacion.setBounds(152, 167, 117, 30);
		contentPane.add(comboBoxInstalacion);		
		List<Instalacion> instalaciones = bl.obtenerInstalacionesEncargado(id);
		for (Instalacion inst : instalaciones) {
			comboBoxInstalacion.addItem(inst.getNombre());
		}
		String stringI = (String) comboBoxInstalacion.getSelectedItem();	
		Instalacion instSeleccionada = bl.getInstalacionNombre(stringI);
		
		
		
		 UtilDateModel model = new UtilDateModel();
		 Properties p = new Properties();
		 p.put("text.today", "Hoy");
		 p.put("text.month", "Mes");
		 p.put("text.year", "Año");
		 JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		 JDatePickerImpl datePicker;
		 datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		 datePicker.setBounds(289, 167, 135, 29);
		 contentPane.add(datePicker);
		 
		 JLabel lblNewLabel_3 = new JLabel("Hora inicio:");
		 lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 lblNewLabel_3.setBounds(10, 208, 67, 14);
		 contentPane.add(lblNewLabel_3);
		 
		 textFieldHoraInicio = new JTextField();
		 textFieldHoraInicio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 textFieldHoraInicio.setBounds(71, 206, 23, 20);
		 contentPane.add(textFieldHoraInicio);
		 textFieldHoraInicio.setColumns(10);
		 
		 textFieldMinInicio = new JTextField();
		 textFieldMinInicio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 textFieldMinInicio.setColumns(10);
		 textFieldMinInicio.setBounds(104, 206, 23, 20);
		 contentPane.add(textFieldMinInicio);
		 
		 textFieldHoraFin = new JTextField();
		 textFieldHoraFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 textFieldHoraFin.setColumns(10);
		 textFieldHoraFin.setBounds(241, 206, 23, 20);
		 contentPane.add(textFieldHoraFin);
		 
		 textFieldMinFin = new JTextField();
		 textFieldMinFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 textFieldMinFin.setColumns(10);
		 textFieldMinFin.setBounds(274, 206, 23, 20);
		 contentPane.add(textFieldMinFin);
		 
		 JLabel lblNewLabel_4 = new JLabel("Hora fin:");
		 lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 lblNewLabel_4.setBounds(183, 208, 56, 14);
		 contentPane.add(lblNewLabel_4);
		 
		 JLabel lblNewLabel_5 = new JLabel(":");
		 lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 lblNewLabel_5.setBounds(97, 208, 46, 14);
		 contentPane.add(lblNewLabel_5);
		 
		 JLabel lblNewLabel_6 = new JLabel(":");
		 lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 lblNewLabel_6.setBounds(268, 208, 46, 14);
		 contentPane.add(lblNewLabel_6);
		 
		 JLabel lblNota = new JLabel("");
		 lblNota.setForeground(new Color(255, 0, 0));
		 lblNota.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 lblNota.setBounds(87, 16, 337, 14);
		 contentPane.add(lblNota);
		 
		 JButton btnCrear = new JButton("Crear Sesión");
		 btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 btnCrear.setBounds(152, 238, 117, 23);
		 contentPane.add(btnCrear);
		 	

		 btnCrear.addActionListener(e -> {
			 System.out.println(actSeleccionada.getNombre()); System.out.println(instSeleccionada.getNombre());
			 if (actSeleccionada == null || instSeleccionada == null || datePicker.getModel().getValue()==null || textFieldHoraInicio.getText().isEmpty()
					 	|| textFieldHoraFin.getText().isEmpty() || textFieldMinInicio.getText().isEmpty() || textFieldMinFin.getText().isEmpty()) {
				 lblNota.setText("Rellene todos los campos.");
			 }
			 else {
				 int horaInicio = Integer.parseInt(textFieldHoraInicio.getText());
				 int minInicio = Integer.parseInt(textFieldMinInicio.getText());
				 int horaFinal = Integer.parseInt(textFieldHoraFin.getText());
				 int minFinal = Integer.parseInt(textFieldMinFin.getText());
				 if (minInicio > 59 || minFinal > 59 || horaInicio > 20 || horaFinal > 21 || horaInicio < 8 || horaFinal < 9 || horaFinal<horaInicio) {
					 lblNota.setText("Introduzca una hora válida");
				 }
				 else {
					  Date fechaSeleccionada = (Date) datePicker.getModel().getValue();

				        // Construir fechaInicio
				        Calendar calInicio = Calendar.getInstance();
				        calInicio.setTime(fechaSeleccionada);
				        calInicio.set(Calendar.HOUR_OF_DAY, horaInicio);
				        calInicio.set(Calendar.MINUTE, minInicio);
				        calInicio.set(Calendar.SECOND, 0);
				        calInicio.set(Calendar.MILLISECOND, 0);
				        Date fechaInicio = calInicio.getTime();

				        // Construir fechaFin
				        Calendar calFin = Calendar.getInstance();
				        calFin.setTime(fechaSeleccionada);
				        calFin.set(Calendar.HOUR_OF_DAY, horaFinal);
				        calFin.set(Calendar.MINUTE, minFinal);
				        calFin.set(Calendar.SECOND, 0);
				        calFin.set(Calendar.MILLISECOND, 0);
				        Date fechaFin = calFin.getTime();

				        // Comprobar si la fecha es anterior a mañana a las 00:00
				        Calendar calManana = Calendar.getInstance();
				        calManana.add(Calendar.DAY_OF_MONTH, 1);
				        calManana.set(Calendar.HOUR_OF_DAY, 0);
				        calManana.set(Calendar.MINUTE, 0);
				        calManana.set(Calendar.SECOND, 0);
				        calManana.set(Calendar.MILLISECOND, 0);
				        
					if (fechaInicio.before(calManana.getTime())) {
						lblNota.setText("Introduzca una fecha a partir de mañana.");
					}
					else {
						bl.crearSesion(actSeleccionada, instSeleccionada, fechaInicio, fechaFin);
						lblNota.setText("Sesion creada: " + actSeleccionada.getNombre() + " " + fechaInicio);
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
