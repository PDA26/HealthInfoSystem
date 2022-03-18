package model;

import jdk.jfr.Name;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PatientDir extends AbstractTableModel {
    List<Patient> dir;

    public PatientDir() {
        this.dir = new ArrayList<>();
    }

    public void add(Patient p){
        dir.add(p);
        fireTableRowsInserted(dir.size() - 1, dir.size() - 1);
    }

    public void delete(int idx){
        dir.remove(idx);
        fireTableRowsDeleted(idx, idx);
    }

    public void delete(Patient p) {
        int row = dir.indexOf(p);
        delete(row);
    }

    public Patient get(int i) {
        return dir.get(i);
    }

    @Override
    public int getRowCount() {
        return dir.size();
    }

    @Override
    public int getColumnCount() {
        return Patient.class.getFields().length;
    }

    @Override
    public Object getValueAt(int row, int col) {
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
