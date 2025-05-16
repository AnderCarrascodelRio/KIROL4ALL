package logicanegocio;

import java.util.List;

import javax.persistence.EntityManager;

import dataAccess.CrearActividadDAO;
import domain.Instalacion;

public class CrearActividad {
	CrearActividadDAO dataAccess;
	int id;
	String rol;
	
	public CrearActividad(EntityManager db, int i, String r) {
		dataAccess = new CrearActividadDAO(db);
		id = i;
		rol = r;
	}
	
	public boolean crearActividad(String nombre, int ex, double precio) {
		if (dataAccess.buscarActividad(nombre)) {
			return false;
		}
		dataAccess.crearActividad(nombre, ex, precio);
		return true;
		
	}

	public List<Instalacion> getInstalaciones() {
		return dataAccess.getInstalaciones();
	}
}
