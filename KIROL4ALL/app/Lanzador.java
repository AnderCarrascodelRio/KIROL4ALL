
import logicanegocio.*;
import presentacion.*;

import java.io.File;

import dataAccess.*;


public class Lanzador {
	
	public static void main(String[] args) throws InterruptedException {
		
		//incializar recursos
		File dbFile = new File("kirol.odb");
		if (dbFile.exists()) {
		    dbFile.delete();
		}
		File dbFile2 = new File("kirol.odb$");
		if (dbFile2.exists()) {
		    dbFile2.delete();
		}
		DatabaseManager dataAccess = new DatabaseManager();
		dataAccess.initializeDB(); //Inicializar la base de datos, con los objetos creados por mí
		
		
	    InicioGUI inicio = new InicioGUI(dataAccess.getEntityManager()); // arranca la ventana inicial
	    inicio.setVisible(true);
	}
	
}
