package rsg;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
import java.util.Random;

public class RandomStringGenerator {
    private int number = 0, character = 0;
    private String separator = "";
    private static final int NUM_NONE = 0, NUM_BINARY = 1, NUM_OCTAL = 2, NUM_DECIMAL = 3, NUM_HEX = 4;
    private static final int CHAR_NONE = 0, CHAR_L = 1, CHAR_C = 2, CHAR_ALL = 3;

    public RandomStringGenerator() {

        button_Copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable transferable = new StringSelection(textArea.getText());
                clipboard.setContents(transferable, null);
            }
        });

        button_Generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField_Length.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Length error", "Warning", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (number == NUM_NONE && character != CHAR_NONE) {
                    if (separator.isEmpty()) {
                        textArea.setText(getCharacter(character, Integer.parseInt(textField_Length.getText())));
                    } else {
                        textArea.setText(getCharacter(character, Integer.parseInt(textField_Length.getText()), separator));
                    }
                }
                if (number != NUM_NONE && character == CHAR_NONE) {
                    if (separator.isEmpty()) {
                        textArea.setText(getNumber(number, Integer.parseInt(textField_Length.getText())));
                    } else {
                        textArea.setText(getNumber(number, Integer.parseInt(textField_Length.getText()), separator));
                    }
                }
                if (number != NUM_NONE && character != CHAR_NONE) {
                    if (separator.isEmpty()) {
                        textArea.setText(getHybridString(number, character, Integer.parseInt(textField_Length.getText())));
                    } else {
                        textArea.setText(getHybridString(number, character, Integer.parseInt(textField_Length.getText()), separator));
                    }
                }
            }
        });

        radioButton_Number_None.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                number = NUM_NONE;
            }
        });

        radioButton_Binary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                number = NUM_BINARY;
            }
        });

        radioButton_Octal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                number = NUM_OCTAL;
            }
        });

        radioButton_Decimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                number = NUM_DECIMAL;
            }
        });

        radioButton_Hex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                number = NUM_HEX;
            }
        });

        comboBox_Separator.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (comboBox_Separator.getSelectedIndex()) {
                    case 0: {
                        separator = "";
                        break;
                    }
                    case 1: {
                        separator = " ";
                        break;
                    }
                    case 2: {
                        separator = "-";
                        break;
                    }
                    case 3: {
                        separator = "|";
                        break;
                    }
                    case 4: {
                        separator = "\\";
                        break;
                    }
                    case 5: {
                        separator = "/";
                        break;
                    }
                    case 6: {
                        separator = ";";
                        break;
                    }
                    case 7: {
                        separator = ":";
                        break;
                    }
                    case 8: {
                        separator = ".";
                        break;
                    }
                }
            }
        });

        textField_Length.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int key = e.getKeyChar();
                if (!(key >= KeyEvent.VK_0 && key <= KeyEvent.VK_9) && key != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });

        checkBox_Lowercase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox_Lowercase.isSelected()) {
                    if (checkBox_Capital.isSelected()) {
                        character = CHAR_ALL;
                    } else {
                        character = CHAR_L;
                    }
                } else {
                    if (checkBox_Capital.isSelected()) {
                        character = CHAR_C;
                    } else {
                        character = CHAR_NONE;
                    }
                }
            }
        });

        checkBox_Capital.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox_Lowercase.isSelected()) {
                    if (checkBox_Capital.isSelected()) {
                        character = CHAR_ALL;
                    } else {
                        character = CHAR_L;
                    }
                } else {
                    if (checkBox_Capital.isSelected()) {
                        character = CHAR_C;
                    } else {
                        character = CHAR_NONE;
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("RandomStringGenerator");
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.setResizable(false);
        frame.setLocation(400, 300);
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
    private JComboBox comboBox_Separator;
    private JLabel label_Interval;
    private JSlider slider_Interval;
    private JLabel label_Length;
    private JTextField textField_Length;
    private JButton button_Copy;
    private JButton button_Generate;
    private JScrollPane scrollPane;

    private String getNumber(int number, int length) {
        Random random = new Random();
        String str = new String();
        switch (number) {
            case NUM_BINARY: {
                for (int i = 0; i < length; i++) {
                    str = str + random.nextInt(2);
                }
                break;
            }
            case NUM_OCTAL: {
                for (int i = 0; i < length; i++) {
                    str = str + random.nextInt(8);
                }
                break;
            }
            case NUM_DECIMAL: {
                for (int i = 0; i < length; i++) {
                    str = str + random.nextInt(10);
                }
                break;
            }
            case NUM_HEX: {
                for (int i = 0; i < length; i++) {
                    int r = random.nextInt(16);
                    if (r > 9) {
                        switch (r) {
                            case 10: {
                                str = str + "A";
                                break;
                            }
                            case 11: {
                                str = str + "B";
                                break;
                            }
                            case 12: {
                                str = str + "C";
                                break;
                            }
                            case 13: {
                                str = str + "D";
                                break;
                            }
                            case 14: {
                                str = str + "E";
                                break;
                            }
                            case 15: {
                                str = str + "F";
                                break;
                            }
                        }
                    } else {
                        str = str + r;
                    }
                }
                break;
            }
        }
        return str;
    }

    private String getNumber(int number, int length, String separator) {
        Random random = new Random();
        String str = new String();
        switch (number) {
            case NUM_BINARY: {
                int k = 0;
                for (int i = 0; i < length; i++) {
                    if (slider_Interval.getValue() != 0 && k == slider_Interval.getValue()) {
                        str = str + separator;
                        k = 0;
                    }
                    str = str + random.nextInt(2);
                    k++;
                }
                break;
            }
            case NUM_OCTAL: {
                int k = 0;
                for (int i = 0; i < length; i++) {
                    if (slider_Interval.getValue() != 0 && k == slider_Interval.getValue()) {
                        str = str + separator;
                        k = 0;
                    }
                    str = str + random.nextInt(8);
                    k++;
                }
                break;
            }
            case NUM_DECIMAL: {
                int k = 0;
                for (int i = 0; i < length; i++) {
                    if (slider_Interval.getValue() != 0 && k == slider_Interval.getValue()) {
                        str = str + separator;
                        k = 0;
                    }
                    str = str + random.nextInt(10);
                    k++;
                }
                break;
            }
            case NUM_HEX: {
                int k = 0;
                for (int i = 0; i < length; i++) {
                    if (slider_Interval.getValue() != 0 && k == slider_Interval.getValue()) {
                        str = str + separator;
                        k = 0;
                    }
                    int r = random.nextInt(16);
                    if (r > 9) {
                        switch (r) {
                            case 10: {
                                str = str + "A";
                                break;
                            }
                            case 11: {
                                str = str + "B";
                                break;
                            }
                            case 12: {
                                str = str + "C";
                                break;
                            }
                            case 13: {
                                str = str + "D";
                                break;
                            }
                            case 14: {
                                str = str + "E";
                                break;
                            }
                            case 15: {
                                str = str + "F";
                                break;
                            }
                        }
                    } else {
                        str = str + r;
                    }
                    k++;
                }
                break;
            }
        }
        if (str.charAt(str.length() - 1) == separator.charAt(0)) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    private String getCharacter(int character, int length) {
        Random random = new Random();
        String str = new String();
        int r;
        switch (character) {
            case CHAR_L: {
                for (int i = 0; i < length; i++) {
                    r = random.nextInt(26) + 97;
                    str = str + (char) r;
                }
                break;
            }
            case CHAR_C: {
                for (int i = 0; i < length; i++) {
                    r = random.nextInt(26) + 65;
                    str = str + (char) r;
                }
                break;
            }
            case CHAR_ALL: {
                for (int i = 0; i < length; i++) {
                    if (random.nextInt(2) == 0) {
                        r = random.nextInt(26) + 97;
                        str = str + (char) r;
                    } else {
                        r = random.nextInt(26) + 65;
                        str = str + (char) r;
                    }
                }
                break;
            }
        }
        return str;
    }

    private String getCharacter(int character, int length, String separator) {
        Random random = new Random();
        String str = new String();
        int r;
        switch (character) {
            case CHAR_L: {
                int k = 0;
                for (int i = 0; i < length; i++) {
                    if (slider_Interval.getValue() != 0 && k == slider_Interval.getValue()) {
                        str = str + separator;
                        k = 0;
                    }
                    r = random.nextInt(26) + 97;
                    str = str + (char) r;
                    k++;
                }
                break;
            }
            case CHAR_C: {
                int k = 0;
                for (int i = 0; i < length; i++) {
                    if (slider_Interval.getValue() != 0 && k == slider_Interval.getValue()) {
                        str = str + separator;
                        k = 0;
                    }
                    r = random.nextInt(26) + 65;
                    str = str + (char) r;
                    k++;
                }
                break;
            }
            case CHAR_ALL: {
                int k = 0;
                for (int i = 0; i < length; i++) {
                    if (slider_Interval.getValue() != 0 && k == slider_Interval.getValue()) {
                        str = str + separator;
                        k = 0;
                    }
                    if (random.nextInt(2) == 0) {
                        r = random.nextInt(26) + 97;
                        str = str + (char) r;
                    } else {
                        r = random.nextInt(26) + 65;
                        str = str + (char) r;
                    }
                    k++;
                }
                break;
            }
        }
        if (str.charAt(str.length() - 1) == separator.charAt(0)) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    private String getHybridString(int number, int character, int length) {
        Random random = new Random();
        String str = new String();
        int r;
        switch (number) {
            case NUM_BINARY: {
                switch (character) {
                    case CHAR_L: {
                        for (int i = 0; i < length; i++) {
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(2);
                                str = str + r;
                            } else {
                                r = random.nextInt(26) + 97;
                                str = str + (char) r;
                            }
                        }
                        break;
                    }
                    case CHAR_C: {
                        for (int i = 0; i < length; i++) {
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(2);
                                str = str + r;
                            } else {
                                r = random.nextInt(26) + 65;
                                str = str + (char) r;
                            }
                        }
                        break;
                    }
                    case CHAR_ALL: {
                        for (int i = 0; i < length; i++) {
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(2);
                                str = str + r;
                            } else {
                                if (random.nextInt(2) == 0) {
                                    r = random.nextInt(26) + 97;
                                    str = str + (char) r;
                                } else {
                                    r = random.nextInt(26) + 65;
                                    str = str + (char) r;
                                }
                            }
                        }
                        break;
                    }
                }
                break;
            }
            case NUM_OCTAL: {
                switch (character) {
                    case CHAR_L: {
                        for (int i = 0; i < length; i++) {
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(8);
                                str = str + r;
                            } else {
                                r = random.nextInt(26) + 97;
                                str = str + (char) r;
                            }
                        }
                        break;
                    }
                    case CHAR_C: {
                        for (int i = 0; i < length; i++) {
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(8);
                                str = str + r;
                            } else {
                                r = random.nextInt(26) + 65;
                                str = str + (char) r;
                            }
                        }
                        break;
                    }
                    case CHAR_ALL: {
                        for (int i = 0; i < length; i++) {
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(8);
                                str = str + r;
                            } else {
                                if (random.nextInt(2) == 0) {
                                    r = random.nextInt(26) + 97;
                                    str = str + (char) r;
                                } else {
                                    r = random.nextInt(26) + 65;
                                    str = str + (char) r;
                                }
                            }
                        }
                        break;
                    }
                }
                break;
            }
            case NUM_DECIMAL: {
                switch (character) {
                    case CHAR_L: {
                        for (int i = 0; i < length; i++) {
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(10);
                                str = str + r;
                            } else {
                                r = random.nextInt(26) + 97;
                                str = str + (char) r;
                            }
                        }
                        break;
                    }
                    case CHAR_C: {
                        for (int i = 0; i < length; i++) {
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(10);
                                str = str + r;
                            } else {
                                r = random.nextInt(26) + 65;
                                str = str + (char) r;
                            }
                        }
                        break;
                    }
                    case CHAR_ALL: {
                        for (int i = 0; i < length; i++) {
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(10);
                                str = str + r;
                            } else {
                                if (random.nextInt(2) == 0) {
                                    r = random.nextInt(26) + 97;
                                    str = str + (char) r;
                                } else {
                                    r = random.nextInt(26) + 65;
                                    str = str + (char) r;
                                }
                            }
                        }
                        break;
                    }
                }
                break;
            }
            case NUM_HEX: {
                switch (character) {
                    case CHAR_L: {
                        for (int i = 0; i < length; i++) {
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(16);
                                if (r > 9) {
                                    switch (r) {
                                        case 10: {
                                            str = str + "A";
                                            break;
                                        }
                                        case 11: {
                                            str = str + "B";
                                            break;
                                        }
                                        case 12: {
                                            str = str + "C";
                                            break;
                                        }
                                        case 13: {
                                            str = str + "D";
                                            break;
                                        }
                                        case 14: {
                                            str = str + "E";
                                            break;
                                        }
                                        case 15: {
                                            str = str + "F";
                                            break;
                                        }
                                    }
                                } else {
                                    str = str + r;
                                }
                            } else {
                                r = random.nextInt(26) + 97;
                                str = str + (char) r;
                            }
                        }
                        break;
                    }
                    case CHAR_C: {
                        for (int i = 0; i < length; i++) {
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(16);
                                if (r > 9) {
                                    switch (r) {
                                        case 10: {
                                            str = str + "A";
                                            break;
                                        }
                                        case 11: {
                                            str = str + "B";
                                            break;
                                        }
                                        case 12: {
                                            str = str + "C";
                                            break;
                                        }
                                        case 13: {
                                            str = str + "D";
                                            break;
                                        }
                                        case 14: {
                                            str = str + "E";
                                            break;
                                        }
                                        case 15: {
                                            str = str + "F";
                                            break;
                                        }
                                    }
                                } else {
                                    str = str + r;
                                }
                            } else {
                                r = random.nextInt(26) + 65;
                                str = str + (char) r;
                            }
                        }
                        break;
                    }
                    case CHAR_ALL: {
                        for (int i = 0; i < length; i++) {
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(16);
                                if (r > 9) {
                                    switch (r) {
                                        case 10: {
                                            str = str + "A";
                                            break;
                                        }
                                        case 11: {
                                            str = str + "B";
                                            break;
                                        }
                                        case 12: {
                                            str = str + "C";
                                            break;
                                        }
                                        case 13: {
                                            str = str + "D";
                                            break;
                                        }
                                        case 14: {
                                            str = str + "E";
                                            break;
                                        }
                                        case 15: {
                                            str = str + "F";
                                            break;
                                        }
                                    }
                                } else {
                                    str = str + r;
                                }
                            } else {
                                if (random.nextInt(2) == 0) {
                                    r = random.nextInt(26) + 97;
                                    str = str + (char) r;
                                } else {
                                    r = random.nextInt(26) + 65;
                                    str = str + (char) r;
                                }
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
        return str;
    }

    private String getHybridString(int number, int character, int length, String separator) {
        Random random = new Random();
        String str = new String();
        int r;
        switch (number) {
            case NUM_BINARY: {
                switch (character) {
                    case CHAR_L: {
                        int k = 0;
                        for (int i = 0; i < length; i++) {
                            if (slider_Interval.getValue() != 0 && k == slider_Interval.getValue()) {
                                str = str + separator;
                                k = 0;
                            }
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(2);
                                str = str + r;
                            } else {
                                r = random.nextInt(26) + 97;
                                str = str + (char) r;
                            }
                            k++;
                        }
                        break;
                    }
                    case CHAR_C: {
                        int k = 0;
                        for (int i = 0; i < length; i++) {
                            if (slider_Interval.getValue() != 0 && k == slider_Interval.getValue()) {
                                str = str + separator;
                                k = 0;
                            }
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(2);
                                str = str + r;
                            } else {
                                r = random.nextInt(26) + 65;
                                str = str + (char) r;
                            }
                            k++;
                        }
                        break;
                    }
                    case CHAR_ALL: {
                        int k = 0;
                        for (int i = 0; i < length; i++) {
                            if (slider_Interval.getValue() != 0 && k == slider_Interval.getValue()) {
                                str = str + separator;
                                k = 0;
                            }
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(2);
                                str = str + r;
                            } else {
                                if (random.nextInt(2) == 0) {
                                    r = random.nextInt(26) + 97;
                                    str = str + (char) r;
                                } else {
                                    r = random.nextInt(26) + 65;
                                    str = str + (char) r;
                                }
                            }
                            k++;
                        }
                        break;
                    }
                }
                break;
            }
            case NUM_OCTAL: {
                switch (character) {
                    case CHAR_L: {
                        int k = 0;
                        for (int i = 0; i < length; i++) {
                            if (slider_Interval.getValue() != 0 && k == slider_Interval.getValue()) {
                                str = str + separator;
                                k = 0;
                            }
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(8);
                                str = str + r;
                            } else {
                                r = random.nextInt(26) + 97;
                                str = str + (char) r;
                            }
                            k++;
                        }
                        break;
                    }
                    case CHAR_C: {
                        int k = 0;
                        for (int i = 0; i < length; i++) {
                            if (slider_Interval.getValue() != 0 && k == slider_Interval.getValue()) {
                                str = str + separator;
                                k = 0;
                            }
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(8);
                                str = str + r;
                            } else {
                                r = random.nextInt(26) + 65;
                                str = str + (char) r;
                            }
                            k++;
                        }
                        break;
                    }
                    case CHAR_ALL: {
                        int k = 0;
                        for (int i = 0; i < length; i++) {
                            if (slider_Interval.getValue() != 0 && k == slider_Interval.getValue()) {
                                str = str + separator;
                                k = 0;
                            }
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(8);
                                str = str + r;
                            } else {
                                if (random.nextInt(2) == 0) {
                                    r = random.nextInt(26) + 97;
                                    str = str + (char) r;
                                } else {
                                    r = random.nextInt(26) + 65;
                                    str = str + (char) r;
                                }
                            }
                            k++;
                        }
                        break;
                    }
                }
                break;
            }
            case NUM_DECIMAL: {
                switch (character) {
                    case CHAR_L: {
                        int k = 0;
                        for (int i = 0; i < length; i++) {
                            if (slider_Interval.getValue() != 0 && k == slider_Interval.getValue()) {
                                str = str + separator;
                                k = 0;
                            }
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(10);
                                str = str + r;
                            } else {
                                r = random.nextInt(26) + 97;
                                str = str + (char) r;
                            }
                            k++;
                        }
                        break;
                    }
                    case CHAR_C: {
                        int k = 0;
                        for (int i = 0; i < length; i++) {
                            if (slider_Interval.getValue() != 0 && k == slider_Interval.getValue()) {
                                str = str + separator;
                                k = 0;
                            }
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(10);
                                str = str + r;
                            } else {
                                r = random.nextInt(26) + 65;
                                str = str + (char) r;
                            }
                            k++;
                        }
                        break;
                    }
                    case CHAR_ALL: {
                        int k = 0;
                        for (int i = 0; i < length; i++) {
                            if (slider_Interval.getValue() != 0 && k == slider_Interval.getValue()) {
                                str = str + separator;
                                k = 0;
                            }
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(10);
                                str = str + r;
                            } else {
                                if (random.nextInt(2) == 0) {
                                    r = random.nextInt(26) + 97;
                                    str = str + (char) r;
                                } else {
                                    r = random.nextInt(26) + 65;
                                    str = str + (char) r;
                                }
                            }
                            k++;
                        }
                        break;
                    }
                }
                break;
            }
            case NUM_HEX: {
                switch (character) {
                    case CHAR_L: {
                        int k = 0;
                        for (int i = 0; i < length; i++) {
                            if (slider_Interval.getValue() != 0 && k == slider_Interval.getValue()) {
                                str = str + separator;
                                k = 0;
                            }
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(16);
                                if (r > 9) {
                                    switch (r) {
                                        case 10: {
                                            str = str + "A";
                                            break;
                                        }
                                        case 11: {
                                            str = str + "B";
                                            break;
                                        }
                                        case 12: {
                                            str = str + "C";
                                            break;
                                        }
                                        case 13: {
                                            str = str + "D";
                                            break;
                                        }
                                        case 14: {
                                            str = str + "E";
                                            break;
                                        }
                                        case 15: {
                                            str = str + "F";
                                            break;
                                        }
                                    }
                                } else {
                                    str = str + r;
                                }
                            } else {
                                r = random.nextInt(26) + 97;
                                str = str + (char) r;
                            }
                            k++;
                        }
                        break;
                    }
                    case CHAR_C: {
                        int k = 0;
                        for (int i = 0; i < length; i++) {
                            if (slider_Interval.getValue() != 0 && k == slider_Interval.getValue()) {
                                str = str + separator;
                                k = 0;
                            }
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(16);
                                if (r > 9) {
                                    switch (r) {
                                        case 10: {
                                            str = str + "A";
                                            break;
                                        }
                                        case 11: {
                                            str = str + "B";
                                            break;
                                        }
                                        case 12: {
                                            str = str + "C";
                                            break;
                                        }
                                        case 13: {
                                            str = str + "D";
                                            break;
                                        }
                                        case 14: {
                                            str = str + "E";
                                            break;
                                        }
                                        case 15: {
                                            str = str + "F";
                                            break;
                                        }
                                    }
                                } else {
                                    str = str + r;
                                }
                            } else {
                                r = random.nextInt(26) + 65;
                                str = str + (char) r;
                            }
                            k++;
                        }
                        break;
                    }
                    case CHAR_ALL: {
                        int k = 0;
                        for (int i = 0; i < length; i++) {
                            if (slider_Interval.getValue() != 0 && k == slider_Interval.getValue()) {
                                str = str + separator;
                                k = 0;
                            }
                            if (random.nextInt(2) == 0) {
                                r = random.nextInt(16);
                                if (r > 9) {
                                    switch (r) {
                                        case 10: {
                                            str = str + "A";
                                            break;
                                        }
                                        case 11: {
                                            str = str + "B";
                                            break;
                                        }
                                        case 12: {
                                            str = str + "C";
                                            break;
                                        }
                                        case 13: {
                                            str = str + "D";
                                            break;
                                        }
                                        case 14: {
                                            str = str + "E";
                                            break;
                                        }
                                        case 15: {
                                            str = str + "F";
                                            break;
                                        }
                                    }
                                } else {
                                    str = str + r;
                                }
                            } else {
                                if (random.nextInt(2) == 0) {
                                    r = random.nextInt(26) + 97;
                                    str = str + (char) r;
                                } else {
                                    r = random.nextInt(26) + 65;
                                    str = str + (char) r;
                                }
                            }
                            k++;
                        }
                        break;
                    }
                }
                break;
            }
        }
        return str;
    }

}
