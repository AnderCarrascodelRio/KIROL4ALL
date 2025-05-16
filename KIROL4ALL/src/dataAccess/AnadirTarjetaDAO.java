package dataAccess;

import javax.persistence.EntityManager;

import domain.Socio;

public class AnadirTarjetaDAO {
	EntityManager db;
	
	public AnadirTarjetaDAO(EntityManager db) {
		this.db = db;
	}
	
	public Socio buscarPorId(int id) {
		return db.find(Socio.class, id);
	}

	public String getTarjetaSocio(int id) {
		Socio socio = buscarPorId(id);
		return socio.getTarjeta();
	}
	
	public void cambiarTarjeta(int id, String tarjeta) {
		Socio socio = buscarPorId(id);
		db.getTransaction().begin();
		socio.setTarjeta(tarjeta);
		db.getTransaction().commit();
	}
}
