package logicanegocio;
import javax.persistence.EntityManager;

import dataAccess.*;
import presentacion.*;

public class HacerLogin {
	HacerLoginDAO dataAccess;
	
	public HacerLogin(EntityManager db) {
		dataAccess = new HacerLoginDAO(db);
	}

	public String autenticar(int login, String passwd, EntityManager db) {
		return dataAccess.rolCredenciales(login, passwd);
	}

	public void volverAInicio(InicioGUI inicio, HacerLoginGUI login) {
		inicio.setVisible(true);
		login.dispose();
	}
}
