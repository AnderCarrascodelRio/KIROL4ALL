package gui;

import java.net.URL;
import java.util.List;
import java.util.Locale;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Sesion;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

public class ApplicationLauncher { 
	
	
	public static void main(String[] args) {

		ConfigXML c=ConfigXML.getInstance();
	
		System.out.println(c.getLocale());
		
		Locale.setDefault(new Locale(c.getLocale()));

		System.out.println("Locale: "+Locale.getDefault());

		


		try {
			
			BLFacade appFacadeInterface;
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			if (c.isBusinessLogicLocal()) {
			
				DataAccess da= new DataAccess();
				appFacadeInterface=new BLFacadeImplementation(da);

				
			}
			
			else { //If remote
				
				 String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
				 
				URL url = new URL(serviceName);

		 
		        //1st argument refers to wsdl document above
				//2nd argument is service name, refer to wsdl document above
		        QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
		 
		        Service service = Service.create(url, qname);

		         appFacadeInterface = service.getPort(BLFacade.class);
			} 
			InicioGUI a=new InicioGUI(appFacadeInterface);
			a.setVisible(true);

			
		}catch (Exception e) { e.printStackTrace();
		}
		//a.pack();


	}

}
