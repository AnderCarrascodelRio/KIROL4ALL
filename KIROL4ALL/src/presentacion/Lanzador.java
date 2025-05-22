package presentacion;


import dataAccess.*;


public class Lanzador {
	
	public static void main(String[] args) throws InterruptedException {
		
		//incializar recursos

		
		DatabaseManager dataAccess = new DatabaseManager();
		dataAccess.initializeDB(); //Inicializar la base de datos, con los objetos creados por mí
		
		
	    InicioGUI inicio = new InicioGUI(dataAccess.getEntityManager()); // arranca la ventana inicial
	    inicio.setVisible(true);
	}
	
}
