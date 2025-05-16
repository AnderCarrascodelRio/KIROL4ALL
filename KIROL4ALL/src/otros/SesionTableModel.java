package otros;

import javax.swing.table.AbstractTableModel;

import domain.Sesion;

import java.util.List;

public class SesionTableModel extends AbstractTableModel {
    private final List<Sesion> sesiones;
    private final String[] columnas = {"Actividad", "Fecha", "Disponible", "N.Usuarios", "Instalación"};

    public SesionTableModel(List<Sesion> sesiones) {
        this.sesiones = sesiones;
    }

    @Override
    public int getRowCount() {
        return sesiones.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Sesion s = sesiones.get(rowIndex);
        switch (columnIndex) {
            case 0: return s.getActividad().getNombre();
            case 1:
                return s.getFechaInicio().getDayOfMonth() + "/" + s.getFechaInicio().getMonthValue() + 
                       " " + s.getFechaInicio().getHour() + ":00-" + s.getFechaFin().getHour() + ":00";
            case 2: return s.estaDisponible();
            case 3: return s.getNumUsuarios();
            case 4: return s.getInstalacion().getNombre();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    public Sesion getSesionAt(int rowIndex) {
        return sesiones.get(rowIndex);
    }
}

