package ui;

import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.text.DateFormat;
import java.util.Date;

public class Main {
    private JTabbedPane tabbedPaneMain;
    private JPanel panelMain;
    private JTable tablePerson;
    private JTable tablePatient;
    private JTable tableAbnormalPatient;
    private JLabel totalPatients;
    private JLabel totalPeople;
    private JButton addButton;
    private JButton modifyButton;
    private JButton deleteButton;
    private JTable tableEncounter;
    private JButton buttonAddPatient;
    private JButton buttonModifyPatient;
    private JButton buttonDeletePatient;
    private JButton buttonAddEncounter;

    private PersonDir personDir;
    private PatientDir patientDir;
    private AbnormalPatientModel abnormalPatientModel;

    public Main() {
        tabbedPaneMain.addChangeListener(changeEvent -> {
            totalPatients.setText(String.valueOf(tablePatient.getRowCount()));
            totalPeople.setText(String.valueOf(tablePerson.getRowCount()));
        });
        addButton.addActionListener(actionEvent -> {
            Person p = PersonEditor.showPersonEditor(tablePerson, null);
            if (p != null) {
                personDir.add(p);
            }
        });
        modifyButton.addActionListener(actionEvent -> {
            int selected = tablePerson.getSelectedRow();
            if (selected != -1) {
                Person p = personDir.get(selected);
                p = PersonEditor.showPersonEditor(tablePerson, p);
                if (p != null) {
                    personDir.fireTableRowsUpdated(selected, selected);
                }
            }
        });
        deleteButton.addActionListener(actionEvent -> {
            int selected = tablePerson.getSelectedRow();
            if (selected != -1) {
                personDir.delete(selected);
                abnormalPatientModel.notifyUpdate();
            }
        });
        buttonAddPatient.addActionListener(actionEvent -> {
            Patient p = PatientEditor.showPatientEditor(tablePatient, null);
            if (p != null) {
                patientDir.add(p);
                personDir.add(p);
            }
        });
        buttonModifyPatient.addActionListener(actionEvent -> {
            int selected = tablePatient.getSelectedRow();
            if (selected != -1) {
                Patient p = patientDir.get(selected);
                p = PatientEditor.showPatientEditor(tablePatient, p);
                if (p != null) {
                    patientDir.fireTableRowsUpdated(selected, selected);
                }
            }
        });
        buttonDeletePatient.addActionListener(actionEvent -> {
            int selected = tablePatient.getSelectedRow();
            if (selected != -1) {
                patientDir.delete(selected);
                abnormalPatientModel.notifyUpdate();
            }
        });
        buttonAddEncounter.addActionListener(actionEvent -> {
            VitalSign vs = VitalSignEditor.showVitalSignEditor(tableEncounter);
            if (vs != null) {
                TableModel model = tableEncounter.getModel();
                if (model instanceof EncounterHis) {
                    ((EncounterHis) model).add(new Encounter(vs));
                    abnormalPatientModel.notifyUpdate();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        Main main = new Main();
        frame.setContentPane(main.panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Patient p1 =
                new Patient("Zhao Xuebo", "male", new Date("03/24/1995"), "Xuefu Road", "one", "Melrose", 0);
        main.patientDir.add(p1);
        main.personDir.add(p1);
        Patient p2 =
                new Patient("Alice", "female", new Date("12/24/1997"), "32 Main St", "one", "Melrose", 100);
        main.patientDir.add(p2);
        main.personDir.add(p2);
        Patient p3 =
                new Patient("Cooper", "male", new Date("02/12/1934"), "Hanner Ave.", "two", "Chelsea", 200);
        main.patientDir.add(p3);
        main.personDir.add(p3);
        Patient p4 =
                new Patient("Captain", "male", new Date("05/06/1934"), "30 Palm St", "three", "Boston", 4);
        main.patientDir.add(p4);
        main.personDir.add(p4);
        Patient p5 =
                new Patient("Sox", "female", new Date("02/28/1965"), "471 Commun Road", "three", "Boston", 32);
        main.patientDir.add(p5);
        main.personDir.add(p5);
        Patient p6 =
                new Patient("Naveen", "male", new Date("03/05/1969"), "Floyd Road", "two", "Chelsea", 76);
        main.patientDir.add(p6);
        main.personDir.add(p6);
        Person p7 = new Person("Juze", "female", new Date("03/15/2000"), "1 Staion Road", "two", "Chelsea");
        main.personDir.add(p7);
        Person p8 = new Person("Sun", "male", new Date("11/14/1987"), "532 Main St", "two", "Chelsea");
        main.personDir.add(p8);
        Person p9 = new Person("James", "fmale", new Date("04/15/2000"), "142 Main St", "two", "Chelsea");
        main.personDir.add(p9);
        Person p10 = new Person("Russel", "male", new Date("12/13/2000"), "242 Main Stv", "two", "Chelsea");
        main.personDir.add(p10);
    }

    private void createUIComponents() {
        DefaultTableCellRenderer DateRenderer = new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                if (value instanceof Date) {
                    this.setText(DateFormat.getDateInstance().format(value));
                } else {
                    super.setValue(value);
                }
            }
        };
        DefaultTableCellRenderer DateTimeRenderer = new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                if (value instanceof Date) {
                    this.setText(DateFormat.getDateTimeInstance().format(value));
                } else {
                    super.setValue(value);
                }
            }
        };

        personDir = new PersonDir();
        tablePerson = new JTable(personDir);
        // tablePerson.setDefaultRenderer(Date.class, DateRenderer);
        tablePerson.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        patientDir = new PatientDir();
        tablePatient = new JTable(patientDir);
        // tablePatient.setDefaultRenderer(Date.class, DateRenderer);
        tablePatient.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablePatient.getSelectionModel().addListSelectionListener(listSelectionEvent -> {
            int selected = tablePatient.getSelectedRow();
            if (selected == -1) {
                tableEncounter.setEnabled(false);
                buttonAddEncounter.setEnabled(false);
            } else {
                tableEncounter.setModel(patientDir.get(selected).getEh());
                tableEncounter.setEnabled(true);
                buttonAddEncounter.setEnabled(true);
            }
        });

        tableEncounter = new JTable();
        tableEncounter.setDefaultRenderer(Date.class, DateTimeRenderer);

        abnormalPatientModel = new AbnormalPatientModel(patientDir);
        tableAbnormalPatient = new JTable(abnormalPatientModel);
    }
}
