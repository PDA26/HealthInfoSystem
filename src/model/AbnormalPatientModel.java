package model;

import jdk.jfr.Name;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class AbnormalPatientModel extends AbstractTableModel {
    private final PatientDir patientDir;
    private boolean outdated = true;
    private List<Patient> dir;

    public AbnormalPatientModel(PatientDir patientDir) {
        this.patientDir = patientDir;
    }

    public void notifyUpdate() {
        outdated = true;
    }

    private void update() {
        if (outdated) {
            dir =
            patientDir.dir.stream().filter(patient -> {
                for (Encounter en : patient.eh.his) {
                    if (!en.vs.isNormal()) {
                        return true;
                    }
                }
                return false;
            }).collect(Collectors.toList());
            outdated = false;
            fireTableDataChanged();
        }
    }

    @Override
    public int getRowCount() {
        update();
        return dir.size();
    }


    @Override
    public int getColumnCount() {
        return Patient.class.getFields().length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        update();
        try {
            return Patient.class.getFields()[col].get(dir.get(row));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        Field field = Patient.class.getFields()[column];
        Name name = field.getAnnotation(Name.class);
        if (name != null) {
            return name.value();
        }
        return field.getName();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Patient.class.getFields()[columnIndex].getType();
    }
}
