package calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Calculator implements ActionListener{
    
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] operatorButtons = new JButton[9];

    JButton addButton, subtractButton, multiplyButton, divideButton,
            decimalButton, equalsButton, deleteButton, clearButton, negativeButton;

    JPanel panel;
    Font font = new Font("SansSerif", Font.BOLD,25);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator(){
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 750);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(150,75,370,45);
        textField.setFont(font);
        textField.setEditable(false);

        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        decimalButton = new JButton(".");
        equalsButton = new JButton("=");
        deleteButton = new JButton("DEL");
        clearButton = new JButton("C");
        negativeButton = new JButton("(-)");

        operatorButtons[0] = addButton;
        operatorButtons[1] = subtractButton;
        operatorButtons[2] = multiplyButton;
        operatorButtons[3] = divideButton;
        operatorButtons[4] = decimalButton;
        operatorButtons[5] = equalsButton;
        operatorButtons[6] = deleteButton;
        operatorButtons[7] = clearButton;
        operatorButtons[8] = negativeButton;

        for (int i = 0; i < 9; i++) {
            operatorButtons[i].addActionListener(this);
            operatorButtons[i].setFont(font);
            operatorButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(font);
            numberButtons[i].setFocusable(false);
        }

        negativeButton.setBounds(160,560,110,45);
        deleteButton.setBounds(280,560,110,45);
        clearButton.setBounds(400,560,110,45);

        panel = new JPanel();
        panel.setBounds(160,150, 350, 400);
        panel.setLayout(new GridLayout(4,4, 10, 10));
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subtractButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(multiplyButton);
        panel.add(decimalButton);
        panel.add(numberButtons[0]);
        panel.add(equalsButton);
        panel.add(divideButton);

        frame.add(textField);
        frame.add(deleteButton);
        frame.add(clearButton);
        frame.add(panel);
        frame.add(negativeButton);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            for (int i = 0; i < 10; i++) {
                if (event.getSource() == numberButtons[i]) {
                    textField.setText(textField.getText().concat(String.valueOf(i)));
                }

            }
            if (event.getSource() == decimalButton) {
                textField.setText(textField.getText().concat("."));
            }
            if (event.getSource() == addButton) {
                num1 = Double.parseDouble(textField.getText());
                operator = '+';
                textField.setText("");
            }
            if (event.getSource() == subtractButton) {
                num1 = Double.parseDouble(textField.getText());
                operator = '-';
                textField.setText("");
            }
            if (event.getSource() == multiplyButton) {
                num1 = Double.parseDouble(textField.getText());
                operator = '*';
                textField.setText("");
            }
            if (event.getSource() == divideButton) {
                num1 = Double.parseDouble(textField.getText());
                operator = '/';
                textField.setText("");
            }
            if (event.getSource() == equalsButton && !textField.getText().isEmpty()) {
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            textField.setText("Err");
                            return;
                        }
                        break;
                }
                textField.setText(String.valueOf(result));
                num1 = result;
            }
            if (event.getSource() == clearButton) {
                textField.setText("");
            }
            if (event.getSource() == deleteButton) {
                String string = textField.getText();
                textField.setText("");
                for (int i = 0; i < string.length() - 1; i++) {
                    textField.setText(textField.getText() + string.charAt(i));
                }
            }
            if (event.getSource() == negativeButton) {
                double temp = Double.parseDouble(textField.getText());
                temp *= -1;
                textField.setText(String.valueOf(temp));
            }
        }
        catch (NumberFormatException e) {
            textField.setText("Error: Invalid input format");
        } catch (Exception e) {
            textField.setText("Error: " + e.getMessage());
        }
    }
}
