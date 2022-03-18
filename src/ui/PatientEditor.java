package ui;

import model.Patient;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class PatientEditor {
    private JTextField textFieldName;
    private JTextField textFieldGender;
    private JSpinner spinnerDob;
    private JTextField textFieldAddress;
    private JTextField textFieldCommunity;
    private JTextField textFieldCity;
    private JPanel panelPatientEditor;
    private JSpinner spinnerBalance;

    public static Patient showPatientEditor(Component parent, Patient patient) {
        PatientEditor editor = new PatientEditor();
        String title;
        if (patient == null) {
            title = "Create Patient Info";
        } else {
            // TODO
            title = "Edit Patient Info";
            editor.textFieldName.setText(patient.name);
            editor.spinnerDob.getModel().setValue(patient.dob);
            editor.textFieldGender.setText(patient.gender);
            editor.textFieldCity.setText(patient.city);
            editor.textFieldAddress.setText(patient.address);
            editor.textFieldCommunity.setText(patient.community);
            editor.spinnerBalance.getModel().setValue(patient.balance);
        }
        int selection = JOptionPane.showConfirmDialog(parent,
                                                      editor.panelPatientEditor,
                                                      title,
                                                      JOptionPane.OK_CANCEL_OPTION,
                                                      JOptionPane.PLAIN_MESSAGE);
        if (selection != JOptionPane.OK_OPTION) {
            return null;
        }
        String name = editor.textFieldName.getText();
        Date dob = (Date) editor.spinnerDob.getValue();
        String address = editor.textFieldAddress.getText();
        String city = editor.textFieldCity.getText();
        String community = editor.textFieldCommunity.getText();
        String gender = editor.textFieldGender.getText();
        int balance = (int)editor.spinnerBalance.getValue();
        // TODO
        if (name.isBlank()) {
            JOptionPane.showMessageDialog(editor.panelPatientEditor, "Name empty!");
            return null;
        }
        if(!gender.equalsIgnoreCase("male")
           && !gender.equalsIgnoreCase("female")){
            JOptionPane.showMessageDialog(editor.panelPatientEditor, "Wrong input of gender!");
            return null;
        }
        if (address.isBlank()) {
            JOptionPane.showMessageDialog(editor.panelPatientEditor, "Address empty!");
            return null;
        }
        if (community.isBlank()) {
            JOptionPane.showMessageDialog(editor.panelPatientEditor, "Community empty!");
            return null;
        }
        if (city.isBlank()) {
            JOptionPane.showMessageDialog(editor.panelPatientEditor, "City empty!");
            return null;
        }
        if(balance < 0){
            JOptionPane.showMessageDialog(editor.panelPatientEditor, "Balance can not be negative!");
            return null;
        }
        if (patient == null) {
            patient = new Patient(balance);
        }
        patient.name = name;
        patient.gender = gender;
        patient.dob = dob;
        patient.address = address;
        patient.community = community;
        patient.city = city;
        patient.balance = balance;
        return patient;
    }

    private void createUIComponents() {
        spinnerDob = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerDob, "MM/dd/yyyy");
        spinnerDob.setEditor(dateEditor);
    }
}
