package logicanegocio;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import dataAccess.PlanificarSesionesDAO;
import domain.Actividad;
import domain.Instalacion;
import domain.Sesion;

public class PlanificarSesiones {
	PlanificarSesionesDAO dataAccess;
	int id;
	
	public PlanificarSesiones(EntityManager db, int i) {
		dataAccess = new PlanificarSesionesDAO(db);
		id = i;
	}
	
	public List<Sesion> obtenerSesiones(EntityManager db) {
		return dataAccess.obtenerSesiones();
	}
	
	public List<Actividad> obtenerActividades(EntityManager db) {
		return dataAccess.obtenerActividades();
	}
	
	public List<Instalacion> obtenerInstalacionesEncargado(EntityManager db,int id) {
		return dataAccess.obtenerInstalacionesEncargado(id);
	}

	public Instalacion getInstalacionNombre(String stringI) {
		return dataAccess.getInstalacionNombre(stringI);
	}

	public Actividad getActividadNombre(String stringA) {
		return dataAccess.getActividadNombre(stringA);
	}
	
	public void crearSesion(Actividad act, Instalacion inst, LocalDateTime fIni, LocalDateTime fFin) {
		Sesion sesion = new Sesion(fIni, fFin);
		dataAccess.crearSesion(act, inst, sesion);
	}
	
}
