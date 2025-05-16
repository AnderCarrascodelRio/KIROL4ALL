package logicanegocio;

import javax.persistence.EntityManager;

import dataAccess.AnadirTarjetaDAO;

public class AnadirTarjeta {
	AnadirTarjetaDAO dataAccess;
	int id;
	String rol;
	
	public AnadirTarjeta(EntityManager db, int i, String r) {
		dataAccess = new AnadirTarjetaDAO(db);
		id = i;
		rol = r;
	}
	
	public String getNumTarjeta() {
		String res = dataAccess.getTarjetaSocio(id);
		if (res == null) {
			return "No tiene.";
		}
		else {
			return res;
		}
	}
	
	public String comprobarTarjeta(String tarjeta) {
		if (tarjeta != null && tarjeta.matches("\\d{12}")) {
	        return tarjeta;
		}
		else {
			return null;
		}
	}
	
	public void cambiarTarjeta(String tarjeta) {
		dataAccess.cambiarTarjeta(id, tarjeta);
	}

}
