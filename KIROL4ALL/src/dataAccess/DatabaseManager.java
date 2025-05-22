package dataAccess;
import javax.persistence.*;


import java.time.*;

import domain.*;

public class DatabaseManager {
	EntityManager db;
	EntityManagerFactory emf;
	String fileName = "kirol.odb";
	
	public DatabaseManager() {
		emf = Persistence.createEntityManagerFactory("kirol4all");
		db = emf.createEntityManager();
		System.out.println("Base de datos abierta.");
	}
	
	public void initializeDB() {
		db.getTransaction().begin();
		//Socios/Encargados
		if (db.find(Actividad.class, "Judo") == null) {
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
		
		//Sesiones
		LocalDate fecha = LocalDate.of(2025, 5, 30);
		LocalTime hora1 = LocalTime.of(9, 0); LocalTime hora2 = LocalTime.of(10, 0);
		LocalTime hora3 = LocalTime.of(11, 0);LocalTime hora4 = LocalTime.of(12, 0);
		LocalTime hora5 = LocalTime.of(13, 0);LocalTime hora6 = LocalTime.of(16, 0);
		LocalTime hora7 = LocalTime.of(17, 0);LocalTime hora8 = LocalTime.of(18, 0);
		LocalTime hora9 = LocalTime.of(19, 0);LocalTime hora10 = LocalTime.of(20, 0);
		LocalDateTime fecha1 = LocalDateTime.of(fecha, hora1);LocalDateTime fecha2 = LocalDateTime.of(fecha, hora2);
		LocalDateTime fecha3 = LocalDateTime.of(fecha, hora3);	LocalDateTime fecha4 = LocalDateTime.of(fecha, hora4);
		LocalDateTime fecha5 = LocalDateTime.of(fecha, hora5);LocalDateTime fecha6 = LocalDateTime.of(fecha, hora6);
		LocalDateTime fecha7 = LocalDateTime.of(fecha, hora7);LocalDateTime fecha8 = LocalDateTime.of(fecha, hora8);
		LocalDateTime fecha9 = LocalDateTime.of(fecha, hora9);LocalDateTime fecha10 = LocalDateTime.of(fecha, hora10);
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
		//Sesiones tenis
		Sesion sesion12 = new Sesion(fecha1, fecha2); sesion12.setInstalacion(instalacion2); sesion12.setActividad(actividad4);
		Sesion sesion13 = new Sesion(fecha2, fecha3); sesion13.setInstalacion(instalacion2); sesion13.setActividad(actividad4);
		Sesion sesion14 = new Sesion(fecha3, fecha4); sesion14.setInstalacion(instalacion2); sesion14.setActividad(actividad4);
		Sesion sesion15 = new Sesion(fecha4, fecha5); sesion15.setInstalacion(instalacion2); sesion15.setActividad(actividad4);
		Sesion sesion16 = new Sesion(fecha6, fecha7); sesion16.setInstalacion(instalacion2); sesion16.setActividad(actividad5);
		Sesion sesion17 = new Sesion(fecha7, fecha8); sesion17.setInstalacion(instalacion2); sesion17.setActividad(actividad5);
		Sesion sesion18 = new Sesion(fecha8, fecha9); sesion18.setInstalacion(instalacion2); sesion18.setActividad(actividad5);
		Sesion sesion19 = new Sesion(fecha9, fecha10); sesion19.setInstalacion(instalacion2); sesion19.setActividad(actividad5);
		//Sesiones Yoga
		Sesion sesion20 = new Sesion(fecha1, fecha2); sesion20.setInstalacion(instalacion1); sesion20.setActividad(actividad7);
		Sesion sesion21 = new Sesion(fecha2, fecha3); sesion21.setInstalacion(instalacion1); sesion21.setActividad(actividad7);
		Sesion sesion22 = new Sesion(fecha3, fecha4); sesion22.setInstalacion(instalacion1); sesion22.setActividad(actividad7);
		Sesion sesion23 = new Sesion(fecha4, fecha5); sesion23.setInstalacion(instalacion1); sesion23.setActividad(actividad7);
		//Sesiones Judo
		Sesion sesion24 = new Sesion(fecha6, fecha7); sesion24.setInstalacion(instalacion1); sesion24.setActividad(actividad6);
		Sesion sesion25 = new Sesion(fecha7, fecha8); sesion25.setInstalacion(instalacion1); sesion25.setActividad(actividad6);
		//Sesiones Taekwondo
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
		}
		db.getTransaction().commit();
		
	}
	
	public void limpiarBD() {
		db.getTransaction().begin();
		db.createQuery("DELETE FROM Reserva").executeUpdate();
		db.createQuery("DELETE FROM Actividad").executeUpdate();
		db.createQuery("DELETE FROM Sesion").executeUpdate();
		db.createQuery("DELETE FROM Encargado").executeUpdate();
		db.createQuery("DELETE FROM Socio").executeUpdate();
		db.createQuery("DELETE FROM Factura").executeUpdate();
		db.createQuery("DELETE FROM Instalacion").executeUpdate();
		db.getTransaction().commit();
	}
	
	public void close() {
		db.close();
		System.out.println("Base de datos cerrada.");
	}

	public EntityManager getEntityManager() {
		return db;
	}
}
