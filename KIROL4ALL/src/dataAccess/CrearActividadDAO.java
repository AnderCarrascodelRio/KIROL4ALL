package dataAccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import domain.Actividad;
import domain.Instalacion;

public class CrearActividadDAO {
	EntityManager db;
	
	public CrearActividadDAO(EntityManager db) {
		this.db = db;
	}

	public boolean buscarActividad(String nombre) {
		TypedQuery<Long> query = db.createQuery(
		        "SELECT COUNT(a) FROM Actividad a WHERE a.nombre = :nombre", Long.class);
		    query.setParameter("nombre", nombre);

		    Long count = query.getSingleResult();
		    return count > 0;
	}

	public void crearActividad(String nombre, int ex, double precio) {
		db.getTransaction().begin();
		Actividad actividad = new Actividad(nombre, ex, precio);
		db.persist(actividad);
		db.getTransaction().commit();
	}

	public List<Instalacion> getInstalaciones() {
	    TypedQuery<Instalacion> query = db.createQuery("SELECT i FROM Instalacion i", Instalacion.class);
	    return query.getResultList();
	}

}
