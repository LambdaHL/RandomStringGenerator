package rsg;

import javax.swing.*;

public class GUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().panel_Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel panel_Main;
    private JTextArea textArea;
    private JButton button_Generate;
    private JPanel panel_Number;
    private JPanel panel_Hybrid;
    private JPanel panel_String;
    private JButton button_Copy;
    private JRadioButton radioButton_Binary;
    private JRadioButton radioButton_Octal;
    private JRadioButton radioButton_Hex;
    private JRadioButton radioButton_Decimal;
    private JLabel label_Number;
    private JLabel label_String;
    private JLabel label_Hybrid;
    private JCheckBox checkBox_Lowercase;
    private JCheckBox checkBox_Capital;
    private JPanel panel_Generate;
    private JLabel label_Length;
    private JTextField textField_Length;
    private JRadioButton radioButton_Hybrid_Binary;
    private JRadioButton radioButton_Hybrid_Octal;
    private JRadioButton radioButton_Hybrid_Decimal;
    private JRadioButton radioButton_Hybrid_Hex;
    private JCheckBox checkBox_Hybrid_Lowercase;
    private JCheckBox checkBox_Hybrid_Capital;
    private JLabel label_Seperator;
    private JComboBox comboBox_Seperator;
    private JLabel label_Interval;
    private JSlider slider_Interval;
}
