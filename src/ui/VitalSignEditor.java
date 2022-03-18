package ui;

import model.VitalSign;

import javax.swing.*;
import java.awt.*;

public class VitalSignEditor {
    private JSpinner spinnerBPSystolic;
    private JSpinner spinnerHeartRate;
    private JSpinner spinnerBodyTemp;
    private JSpinner spinnerBPDiastolic;
    private JPanel panelVitalSignEditor;

    public static VitalSign showVitalSignEditor(Component parent) {
        VitalSignEditor editor = new VitalSignEditor();
        int selection = JOptionPane.showConfirmDialog(parent,
                                                      editor.panelVitalSignEditor,
                                                      "Vital Signs",
                                                      JOptionPane.OK_CANCEL_OPTION,
                                                      JOptionPane.PLAIN_MESSAGE);
        if (selection == JOptionPane.OK_OPTION) {
            return new VitalSign((int) editor.spinnerBPSystolic.getValue(),
                                 (int) editor.spinnerBPDiastolic.getValue(),
                                 (int) editor.spinnerHeartRate.getValue(),
                                 (double) editor.spinnerBodyTemp.getValue());
        } else {
            return null;
        }
    }

    private void createUIComponents() {
        spinnerBPSystolic = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        spinnerBPDiastolic = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        spinnerHeartRate = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        spinnerBodyTemp = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.1));
    }
}
