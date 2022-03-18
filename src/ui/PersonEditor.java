package ui;

import model.Person;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class PersonEditor {
    private JTextField textFieldName;
    private JComboBox<String> comboBoxGender;
    private JSpinner spinnerDob;
    private JTextField textFieldAddress;
    private JTextField textFieldCommunity;
    private JTextField textFieldCity;
    private JPanel panelPersonEditor;

    public static Person showPersonEditor(Component parent, Person person) {
        PersonEditor editor = new PersonEditor();
        String title;
        if (person == null) {
            title = "Create Person Info";
        } else {
            editor.textFieldName.setText(person.name);
            editor.spinnerDob.getModel().setValue(person.dob);
            editor.comboBoxGender.setSelectedItem(person.gender);
            editor.textFieldCity.setText(person.city);
            editor.textFieldAddress.setText(person.address);
            editor.textFieldCommunity.setText(person.community);
            title = "Edit Person Info";
        }
        int selection = JOptionPane.showConfirmDialog(parent,
                                                      editor.panelPersonEditor,
                                                      title,
                                                      JOptionPane.OK_CANCEL_OPTION,
                                                      JOptionPane.PLAIN_MESSAGE);
        if (selection != JOptionPane.OK_OPTION) {
            return null;
        }

        String name = editor.textFieldName.getText();
        String gender = (String) editor.comboBoxGender.getSelectedItem();
        Date dob = (Date) editor.spinnerDob.getValue();
        String address = editor.textFieldAddress.getText();
        String community = editor.textFieldCommunity.getText();
        String city = editor.textFieldCity.getText();

        if (name.isBlank()) {
            JOptionPane.showMessageDialog(editor.panelPersonEditor,
                                          "Name empty!",
                                          "Error",
                                          JOptionPane.ERROR_MESSAGE);
            return null;
        }
        /*
        if (!gender.equalsIgnoreCase("male") &&
            !gender.equalsIgnoreCase("female")) {
            JOptionPane.showMessageDialog(editor.panelPersonEditor, "Wrong input of gender!");
            return null;
        }
        */
        if (address.isBlank()) {
            JOptionPane.showMessageDialog(editor.panelPersonEditor, "Address empty!");
            return null;
        }
        if (community.isBlank()) {
            JOptionPane.showMessageDialog(editor.panelPersonEditor, "Community empty!");
            return null;
        }
        if (city.isBlank()) {
            JOptionPane.showMessageDialog(editor.panelPersonEditor, "City empty!");
            return null;
        }

        if (person == null) {
            person = new Person();
        }
        person.name = name;
        person.gender = gender;
        person.dob = dob;
        person.address = address;
        person.community = community;
        person.city = city;
        return person;
    }

    private void createUIComponents() {
        spinnerDob = new JSpinner(new SpinnerDateModel());
        spinnerDob.setEditor(new JSpinner.DateEditor(spinnerDob, "MM/dd/yyyy"));
        comboBoxGender = new JComboBox<>(new String[]{"Male", "Female"});
    }
}
