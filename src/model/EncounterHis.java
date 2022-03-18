package model;

import jdk.jfr.Name;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EncounterHis extends AbstractTableModel {
    List<Encounter> his;

    public EncounterHis() {
        this.his = new ArrayList<>();
        //this.map = new HashMap<>();
    }

    public void add(Encounter e){
        his.add(e);
        fireTableRowsInserted(his.size() - 1, his.size() - 1);
    }

    public void delete(int idx){
        his.remove(idx);
        fireTableRowsDeleted(idx, idx);
    }

    public void delete(Encounter e) {
        int row = his.indexOf(e);
        delete(row);
    }

    @Override
    public int getRowCount() {
        return his.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Encounter encounter = his.get(row);
        switch (col) {
            case 0:
                return encounter.time;
            case 1:
                return encounter.vs.bloodPressureSystolic + "/" + encounter.vs.bloodPressureDiastolic;
            case 2:
                return encounter.vs.heartRate;
            case 3:
                return encounter.vs.bodyTemperature;
        }

        return null;
    }

    @Override
    public String getColumnName(int column) {
        String[] columns = {
                "Time",
                "Blood Pressure",
                "Heart Rate",
                "Body Temp",
        };
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Date.class;
            case 1:
                return String.class;
            case 2:
                return int.class;
            case 3:
                return double.class;
        }
        return null;
    }
}
