package dataAccess;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import configuration.ConfigXML;

import javax.swing.JTextArea;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * It runs the database server as a separate process.
 */
public class ObjectdbManagerServer extends JDialog {


	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	JTextArea textArea;
	ConfigXML c = ConfigXML.getInstance();
	
    private String objectDbpath="src\\main\\resources\\objectdb.jar";	


	public static void main(String[] args) {
		try {
			
			
			ObjectdbManagerServer dialog = new ObjectdbManagerServer();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public ObjectdbManagerServer() {
	    
		setTitle("objectDBManagerServer: running the database server");
		setBounds(100, 100, 486, 180);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			textArea = new JTextArea();
			contentPanel.add(textArea);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textArea.append("\n\n\nCerrando base de datos... ");
					    try {
					    	System.out.println("Servidor cerrado");
					    	 try {
					    		    
					    		    
							    	Runtime.getRuntime().exec("java -cp "+objectDbpath+" com.objectdb.Server -port "+ c.getDatabasePort()+" stop");
							    	
							    } catch (Exception ioe) {
							    	System.out.println (ioe);
							    }

								System.exit(1);
							
						} catch (Exception e1) {
						}
						System.exit(1);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.isDatabaseLocal()) {
			textArea.append("\nERROR, La base de datos está configurada como local.");
		}
		else {
		try{
			System.out.println("Lanzando servidor objectDB");

			
			try {
		    	Runtime.getRuntime().exec("java -cp "+objectDbpath+" com.objectdb.Server -port "+ c.getDatabasePort()+" start");
		    } catch (Exception ioe) {
		    	System.out.println (ioe);
		    }

		    textArea.append("\nAcceso concedido al usuario: "+c.getUser());
		    
			textArea.append("\nPresiona el botón para salir del servidor de base de datos... ");
			
		} catch (Exception e) {
			textArea.append("Something has happened in ObjectDbManagerServer: "+e.toString());

		}
		
		}
	}

}
	
