package dataAccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class HacerLoginDAO {
	EntityManager db;

	public HacerLoginDAO(EntityManager db) {
		this.db=db;
	}

	public String rolCredenciales(int n, String c) {
		TypedQuery<String> query = db.createQuery("SELECT s.rol FROM Socio s WHERE s.numSocio= :numSocio AND s.contrasena= :contrasena",String.class);
		query.setParameter("numSocio", n);
		query.setParameter("contrasena", c);
		List<String> resultados = query.getResultList();
		return resultados.isEmpty() ? null : resultados.get(0);
	}
}
