package rsg;

import javax.swing.*;

public class RandomStringGenerator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("RandomStringGenerator");
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch(Exception e){
            e.printStackTrace();
        }
        frame.setResizable(false);
        frame.setLocation(400,300);
        frame.setContentPane(new RandomStringGenerator().panel_Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel panel_Main;
    private JTextArea textArea;
    private JRadioButton radioButton_Number_None;
    private JRadioButton radioButton_Binary;
    private JRadioButton radioButton_Octal;
    private JRadioButton radioButton_Decimal;
    private JRadioButton radioButton_Hex;
    private JLabel label_Number;
    private JLabel label_Character;
    private JCheckBox checkBox_Lowercase;
    private JCheckBox checkBox_Capital;
    private JLabel label_Separator;
    private JComboBox comboBox_Symbol;
    private JLabel label_Interval;
    private JSlider slider_Interval;
    private JLabel label_Length;
    private JTextField a0TextField;
    private JButton button_Copy;
    private JButton button_Generator;
    
}
