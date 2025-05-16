package logicanegocio;

import java.awt.Window;
import java.util.List;

import javax.persistence.EntityManager;

import dataAccess.ConsultarSesionesDAO;
import domain.Sesion;
import domain.Socio;
import presentacion.ConsultarSesionesGUI;
import presentacion.InicioGUI;
import presentacion.PrincipalGUI;

public class ConsultarSesiones {
	ConsultarSesionesDAO dataAccess;
	int id;
	String rol;
	
	public ConsultarSesiones(EntityManager db, int i, String r) {
		dataAccess = new ConsultarSesionesDAO(db);
		id = i;
		rol = r;
	}
	
	public void volver(Object origen, ConsultarSesionesGUI consultar) {
		if (origen instanceof InicioGUI) {
			((InicioGUI) origen).setVisible(true);
		}
		else {((PrincipalGUI) origen).setVisible(true);}
		consultar.dispose();
	}

	public List<Sesion> obtenerSesiones(EntityManager db) {
		return dataAccess.obtenerSesiones();
	}
	
	public boolean puedeReservar() {
		Socio socio = dataAccess.buscarPorId(id);
	    int numReservas = dataAccess.reservasActivasSocio(socio).size();
	    if (numReservas >=5) { return false;}
	    else {return true;}
	}
	
}
