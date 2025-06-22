package dataAccess;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import domain.Actividad;
import domain.Encargado;
import domain.Factura;
import domain.Instalacion;
import domain.Reserva;
import domain.Sesion;
import domain.Socio;
import configuration.EstadoFactura;
import configuration.Estado;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	private  EntityManager  db;
	private  EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess()  {
		if (c.isDatabaseInitialized()) { 
			String fileName=c.getDbFilename();

			File fileToDelete= new File(fileName);
			if(fileToDelete.delete()){
				File fileToDeleteTemp= new File(fileName+"$");
				fileToDeleteTemp.delete();

				  System.out.println("File deleted");
			} else {
				  System.out.println("Operation failed");
			}
		}
		open();
		if  (c.isDatabaseInitialized()) {
			initializeDB();

		}
		
		System.out.println("DataAccess created => isDatabaseLocal: "+c.isDatabaseLocal()+" isDatabaseInitialized: "+c.isDatabaseInitialized());

		//close();

	}
     
    public DataAccess(EntityManager db) {
    	this.db=db;
    }

    public Socio getSocio() {
    	TypedQuery<Socio> query = db.createQuery("SELECT s FROM Socio s", Socio.class);
        query.setMaxResults(1);
        List<Socio> resultados = query.getResultList();
        
        return resultados.isEmpty() ? null : resultados.get(0);
    }
    
    public Encargado getEncargado() {
    	TypedQuery<Encargado> query = db.createQuery("SELECT e FROM Encargado e", Encargado.class);
        query.setMaxResults(1);
        List<Encargado> resultados = query.getResultList();
        
        return resultados.isEmpty() ? null : resultados.get(0);
    }
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();

		try {		   
			
		//      db.createQuery("DELETE FROM Sesion").executeUpdate();
	      //      db.createQuery("DELETE FROM Factura").executeUpdate();
	        //    db.createQuery("DELETE FROM Actividad").executeUpdate();
	          //  db.createQuery("DELETE FROM Instalacion").executeUpdate();
	           // db.createQuery("DELETE FROM Socio").executeUpdate();
	            //db.createQuery("DELETE FROM Encargado").executeUpdate();
	            //db.createQuery("DELETE FROM Reserva").executeUpdate();
	    
			Socio socio1 = new Socio("Ander", "79124433V", "12345");
			Socio socio2 = new Socio("Maria", "21436587T", "12345");
			Socio socio3 = new Socio("Mikel", "84125561G", "12345");
			Encargado encargado1 = new Encargado("Cristian", "11111111A", "12345");
			Encargado encargado2 = new Encargado("Iñaki", "24313122Z", "12345");
			db.persist(socio1); db.persist(socio2); db.persist(socio3); 
		
			//Instalaciones
			Instalacion instalacion1 = new Instalacion("Tatami", 20);
			Instalacion instalacion2 = new Instalacion("Pistas de Ténis", 8);
			Instalacion instalacion3 = new Instalacion("Piscina Olímpica", 50);
			Instalacion instalacion4 = new Instalacion("Sauna", 4);
			encargado1.agregarInstalacion(instalacion1); encargado1.agregarInstalacion(instalacion2);
			encargado2.agregarInstalacion(instalacion3); encargado2.agregarInstalacion(instalacion4);
			db.persist(instalacion1); 		db.persist(instalacion2); 		db.persist(instalacion3); 		db.persist(instalacion4);
			db.persist(encargado1); db.persist(encargado2);

			//Actividades
			Actividad actividad1 = new Actividad("Sauna", 1, 2.50);
			Actividad actividad2 = new Actividad("Natación", 3, 2.00);
			Actividad actividad3 = new Actividad("Waterpolo", 5, 4.50);
			Actividad actividad4 = new Actividad("Tenis Dobles", 3, 4.00 );
			Actividad actividad5 = new Actividad("Tenis Individual", 4, 6.00);
			Actividad actividad6 = new Actividad("Judo", 5, 5.50);
			Actividad actividad7 = new Actividad("Yoga", 1, 2.00);
			Actividad actividad8 = new Actividad("Taekwondo", 5, 7.00);
			db.persist(actividad1);db.persist(actividad2);db.persist(actividad3);db.persist(actividad4);
			db.persist(actividad5);db.persist(actividad6);db.persist(actividad7);db.persist(actividad8);
			System.out.println(db.find(Actividad.class, actividad8));
		
			//Sesiones
			int año = 2025, mes = 6, dia = 30;

	        Date fecha1 = crearFecha(año, mes, dia, 9, 0);
	        Date fecha2 = crearFecha(año, mes, dia, 10, 0);
	        Date fecha3 = crearFecha(año, mes, dia, 11, 0);
	        Date fecha4 = crearFecha(año, mes, dia, 12, 0);
	        Date fecha5 = crearFecha(año, mes, dia, 13, 0);
	        Date fecha6 = crearFecha(año, mes, dia, 16, 0);
	        Date fecha7 = crearFecha(año, mes, dia, 17, 0);
	        Date fecha8 = crearFecha(año, mes, dia, 18, 0);
	        Date fecha9 = crearFecha(año, mes, dia, 19, 0);
	        Date fecha10 = crearFecha(año, mes, dia, 20, 0);
			//Sesiones sauna
			Sesion sesion1 = new Sesion(fecha1, fecha2); sesion1.setInstalacion(instalacion4); sesion1.setActividad(actividad1);
			Sesion sesion2 = new Sesion(fecha2, fecha3); sesion2.setInstalacion(instalacion4); sesion2.setActividad(actividad1);
			Sesion sesion3 = new Sesion(fecha3, fecha4); sesion3.setInstalacion(instalacion4); sesion3.setActividad(actividad1);
			Sesion sesion4 = new Sesion(fecha4, fecha5); sesion4.setInstalacion(instalacion4); sesion4.setActividad(actividad1);
			Sesion sesion5 = new Sesion(fecha6, fecha7); sesion5.setInstalacion(instalacion4); sesion5.setActividad(actividad1);
			Sesion sesion6 = new Sesion(fecha7, fecha8); sesion6.setInstalacion(instalacion4); sesion6.setActividad(actividad1);
			Sesion sesion7 = new Sesion(fecha8, fecha9); sesion7.setInstalacion(instalacion4); sesion7.setActividad(actividad1);
			//Sesiones piscina
			Sesion sesion8 = new Sesion(fecha1, fecha3); sesion8.setInstalacion(instalacion3); sesion8.setActividad(actividad2);
			Sesion sesion9 = new Sesion(fecha3, fecha5); sesion9.setInstalacion(instalacion3); sesion9.setActividad(actividad2);
			Sesion sesion10 = new Sesion(fecha6, fecha8); sesion10.setInstalacion(instalacion3); sesion10.setActividad(actividad2);
			Sesion sesion11 = new Sesion(fecha8, fecha10); sesion11.setInstalacion(instalacion3); sesion11.setActividad(actividad2);
			//	Sesiones tenis
			Sesion sesion12 = new Sesion(fecha1, fecha2); sesion12.setInstalacion(instalacion2); sesion12.setActividad(actividad4);
			Sesion sesion13 = new Sesion(fecha2, fecha3); sesion13.setInstalacion(instalacion2); sesion13.setActividad(actividad4);
			Sesion sesion14 = new Sesion(fecha3, fecha4); sesion14.setInstalacion(instalacion2); sesion14.setActividad(actividad4);
			Sesion sesion15 = new Sesion(fecha4, fecha5); sesion15.setInstalacion(instalacion2); sesion15.setActividad(actividad4);
			Sesion sesion16 = new Sesion(fecha6, fecha7); sesion16.setInstalacion(instalacion2); sesion16.setActividad(actividad5);
			Sesion sesion17 = new Sesion(fecha7, fecha8); sesion17.setInstalacion(instalacion2); sesion17.setActividad(actividad5);
			Sesion sesion18 = new Sesion(fecha8, fecha9); sesion18.setInstalacion(instalacion2); sesion18.setActividad(actividad5);
			Sesion sesion19 = new Sesion(fecha9, fecha10); sesion19.setInstalacion(instalacion2); sesion19.setActividad(actividad5);
			//	Sesiones Yoga
			Sesion sesion20 = new Sesion(fecha1, fecha2); sesion20.setInstalacion(instalacion1); sesion20.setActividad(actividad7);
			Sesion sesion21 = new Sesion(fecha2, fecha3); sesion21.setInstalacion(instalacion1); sesion21.setActividad(actividad7);
			Sesion sesion22 = new Sesion(fecha3, fecha4); sesion22.setInstalacion(instalacion1); sesion22.setActividad(actividad7);
			Sesion sesion23 = new Sesion(fecha4, fecha5); sesion23.setInstalacion(instalacion1); sesion23.setActividad(actividad7);
			//Sesiones Judo
			Sesion sesion24 = new Sesion(fecha6, fecha7); sesion24.setInstalacion(instalacion1); sesion24.setActividad(actividad6);
			Sesion sesion25 = new Sesion(fecha7, fecha8); sesion25.setInstalacion(instalacion1); sesion25.setActividad(actividad6);
			//	Sesiones Taekwondo
			Sesion sesion26 = new Sesion(fecha8, fecha10); sesion26.setInstalacion(instalacion1); sesion26.setActividad(actividad8);
		
			db.persist(sesion1); db.persist(sesion2); db.persist(sesion3); db.persist(sesion4); db.persist(sesion5);
			db.persist(sesion6); db.persist(sesion7); db.persist(sesion8); db.persist(sesion9); db.persist(sesion10);
			db.persist(sesion11); db.persist(sesion12); db.persist(sesion13); db.persist(sesion14); db.persist(sesion15);
			db.persist(sesion16); db.persist(sesion17); db.persist(sesion18); db.persist(sesion19); db.persist(sesion20);
			db.persist(sesion21); db.persist(sesion22); db.persist(sesion23); db.persist(sesion24); db.persist(sesion25);db.persist(sesion26);
		
		
			//Facturas
			Factura factura1 = new Factura(20.0); Factura factura2 = new Factura(10.0);
			socio1.agregarFactura(factura1); socio1.agregarFactura(factura2);
			db.persist(factura2);db.persist(factura1);
	
			db.getTransaction().commit();
			

			System.out.println("Base de datos inicializada.");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	

public void open(){
		
		String fileName=c.getDbFilename();
		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);
			  db = emf.createEntityManager();
    	   }
		System.out.println("DataAccess opened => isDatabaseLocal: "+c.isDatabaseLocal());

		
	}

	public void close(){
		db.close();
		System.out.println("DataAcess closed");
	}
	
	public static Date crearFecha(int año, int mes, int dia, int hora, int minuto) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, año);
        cal.set(Calendar.MONTH, mes - 1);
        cal.set(Calendar.DAY_OF_MONTH, dia);
        cal.set(Calendar.HOUR_OF_DAY, hora);
        cal.set(Calendar.MINUTE, minuto);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

	/**
	 * Busca en la base de datos un usuario con el id proporcionado.
	 * @param id Identificador del usuario.
	 * @return El socio
	 */
	public Socio buscarPorId(int id) {
		return db.find(Socio.class, id);
	}
	
	public void cambiarTarjeta(int id, String tarjeta) {
		Socio socio = buscarPorId(id);
		db.getTransaction().begin();
		socio.setTarjetaCredito(tarjeta);
		db.getTransaction().commit();
	}
	
	public List<Reserva> getReservasSocio(int id) {
		Socio socio = db.find(Socio.class, id);
		List<Reserva> resultado = new ArrayList<Reserva>();
		for (Reserva r: socio.getReservas()) {
			if (r.getCancelable())
				resultado.add(r);
		}
		return resultado;
	}
	public List<Reserva> reservasActivasSocio(int id) {
		Socio socio = db.find(Socio.class, id);
		List<Reserva> resultado = new ArrayList<>();
		for (Reserva r: socio.getReservas()) {
			if (r.getEstado() == Estado.confirmada || r.getEstado() == Estado.enEspera)
				resultado.add(r);
		}
		return resultado;
	}
	
	public void cancelarReserva(Reserva r) {
		db.getTransaction().begin();
		r.setEstado(Estado.cancelada);
		Sesion ses = r.getSesion();	
		ses.setUsuarios(ses.getNumUsuarios()-1);
		List<Reserva> reservas = ses.getReservas();
		for (Reserva res: reservas) {
			if (res.getEstado() == Estado.enEspera) {
				res.setEstado(Estado.confirmada);
				ses.setUsuarios(ses.getNumUsuarios()+1);
				break;
			}
		}
		db.getTransaction().commit();
	}
	
	public List<Factura> getFacturasSocio(int id) {
		Socio socio = db.find(Socio.class, id);
		List<Factura> resultado = new ArrayList<>();
		for (Factura f: socio.getFacturas()) {
			if (f.getEstadoFactura() == EstadoFactura.porAbonar) {
				resultado.add(f);
			}
		}
		return resultado;
	}
	
	public String getTarjetaSocio(int id) {
		Socio socio = db.find(Socio.class, id);
		return socio.getTarjeta();
	}


	public void pagarFactura(int id, Factura f) {
		db.getTransaction().begin();
		f.setEstado(EstadoFactura.abonada);
		db.getTransaction().commit();
	}
	
	public List<Reserva> reservasSocio(Socio socio) {
		return socio.getReservas();
	}
	public List<Reserva> reservasActivasSocio(Socio socio) {
		List<Reserva> resultado = new ArrayList<>();
		for (Reserva r: socio.getReservas()) {
			if (r.getEstado() == Estado.confirmada || r.getEstado() == Estado.enEspera)
				resultado.add(r);
		}
		
		return resultado;
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

	public String rolCredenciales(int n, String c) {
		TypedQuery<String> query = db.createQuery("SELECT s.rol FROM Socio s WHERE s.numSocio= :numSocio AND s.contrasena= :contrasena",String.class);
		query.setParameter("numSocio", n);
		query.setParameter("contrasena", c);
		List<String> resultados = query.getResultList();
		return resultados.isEmpty() ? null : resultados.get(0);
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
	
	
	public void storeReserva(int id, Sesion sesion, Estado estado) {
		db.getTransaction().begin();
		Reserva res = new Reserva(sesion, estado);
		Socio s = buscarPorId(id);
		if (estado == Estado.confirmada) {
			sesion.setUsuarios(sesion.getNumUsuarios()+1);
		}
		s.agregarReserva(res);
		sesion.agregarReserva(res);
		db.persist(res);	
		db.getTransaction().commit();
	}

	public List<Sesion> obtenerSesionesExigencia(int ex) {
		TypedQuery<Actividad> query = db.createQuery("SELECT a FROM Actividad a WHERE a.exigencia=:exigencia", Actividad.class);
		query.setParameter("exigencia", ex);
		List<Actividad> acts = query.getResultList();
		List<Sesion> res = new ArrayList<Sesion>();
		
		for (Actividad a : acts) {
            res.addAll(a.getSesiones());
	    }
		return res;
	}

	public List<Sesion> obtenerSesionesActividad(Actividad act) {
		return act.getSesiones();
	}
	
}
