package dataAccess;

import java.util.List;

import javax.persistence.EntityManager;

import domain.Actividad;
import domain.Encargado;
import domain.Instalacion;
import domain.Sesion;

public class PlanificarSesionesDAO {
	EntityManager db;
	
	public PlanificarSesionesDAO(EntityManager db) {
		this.db =db;
	}
	
	public List<Sesion> obtenerSesiones() {
		return db.createQuery("SELECT s FROM Sesion s", Sesion.class).getResultList();
	}
	
	public List<Actividad> obtenerActividades() {
		return db.createQuery("SELECT a FROM Actividad  a", Actividad.class).getResultList();
	}
	public List<Instalacion> obtenerInstalaciones() {
		return db.createQuery("SELECT i FROM Instalacion i", Instalacion.class).getResultList();
	}
	
	public List<Instalacion> obtenerInstalacionesEncargado(int id) {
		Encargado enc = db.find(Encargado.class, id);
		return enc.getInstalaciones();
	}

	public Actividad getActividadNombre(String stringI) {
		for (Actividad actividad: obtenerActividades()) {
			if (actividad.getNombre().equals(stringI)) {
				return actividad;
			}
		}
		return null;
	}
	public Instalacion getInstalacionNombre(String stringI) {
		for (Instalacion instalacion: obtenerInstalaciones()) {
			if (instalacion.getNombre().equals(stringI)) {
				return instalacion;
			}
		}
		return null;
	}

	public void crearSesion(Actividad act, Instalacion inst, Sesion sesion) {
		db.getTransaction().begin();
		sesion.setActividad(act);
		sesion.setInstalacion(inst);
		db.persist(sesion);
		db.getTransaction().commit();
	}
}
