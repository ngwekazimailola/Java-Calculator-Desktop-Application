import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArithmeticCalculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Arithmetic Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 300);

        // Calculator pad container
        JPanel calculatorPanel = new JPanel(new GridLayout(4, 4));

        // Input/Output text field
        JTextField displayField = new JTextField();
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(displayField, BorderLayout.NORTH);

        // 16 Calculator Buttons
        JButton button7 = new JButton("7");
        button7.addActionListener(new NumberButtonListener(displayField, "7"));
        JButton button8 = new JButton("8");
        button8.addActionListener(new NumberButtonListener(displayField, "8"));
        JButton button9 = new JButton("9");
        button9.addActionListener(new NumberButtonListener(displayField, "9"));
        JButton buttonDivide = new JButton("/");
        buttonDivide.addActionListener(new OperatorButtonListener(displayField, "/"));

        JButton button4 = new JButton("4");
        button4.addActionListener(new NumberButtonListener(displayField, "4"));
        JButton button5 = new JButton("5");
        button5.addActionListener(new NumberButtonListener(displayField, "5"));
        JButton button6 = new JButton("6");
        button6.addActionListener(new NumberButtonListener(displayField, "6"));
        JButton buttonMultiply = new JButton("*");
        buttonMultiply.addActionListener(new OperatorButtonListener(displayField, "*"));

        JButton button1 = new JButton("1");
        button1.addActionListener(new NumberButtonListener(displayField, "1"));
        JButton button2 = new JButton("2");
        button2.addActionListener(new NumberButtonListener(displayField, "2"));
        JButton button3 = new JButton("3");
        button3.addActionListener(new NumberButtonListener(displayField, "3"));
        JButton buttonSubtract = new JButton("-");
        buttonSubtract.addActionListener(new OperatorButtonListener(displayField, "-"));

        JButton buttonDot = new JButton(".");
        buttonDot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayField.setText(displayField.getText() + ".");
            }
        });
        JButton button0 = new JButton("0");
        button0.addActionListener(new NumberButtonListener(displayField, "0"));
        JButton buttonEquals = new JButton("=");
        JButton buttonAdd = new JButton("+");
        buttonAdd.addActionListener(new OperatorButtonListener(displayField, "+"));

        // ActionListener for "=" button
        buttonEquals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve value from the display field
                String input = displayField.getText();

                // Split the input based on the operator
                String[] values = input.split("[+\\-*/]");

                // Parse values to perform arithmetic calculation
                double num1 = Double.parseDouble(values[0]);
                double num2 = Double.parseDouble(values[1]);

                // Extract operator
                String operator = input.replaceAll("[^+\\-*/]", "");

                double result;
                // Calculations
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            // Division by zero error
                            displayField.setText("Error: Division by zero");
                            return;
                        }
                        break;
                    default:
                        // Invalid operator
                        displayField.setText("Error: Invalid operator");
                        return;
                }

                // Display the result
                displayField.setText(Double.toString(result));
            }

        });

        calculatorPanel.add(button7);
        calculatorPanel.add(button8);
        calculatorPanel.add(button9);
        calculatorPanel.add(buttonDivide);

        calculatorPanel.add(button4);
        calculatorPanel.add(button5);
        calculatorPanel.add(button6);
        calculatorPanel.add(buttonMultiply);

        calculatorPanel.add(button1);
        calculatorPanel.add(button2);
        calculatorPanel.add(button3);
        calculatorPanel.add(buttonSubtract);

        calculatorPanel.add(buttonDot);
        calculatorPanel.add(button0);
        calculatorPanel.add(buttonEquals);
        calculatorPanel.add(buttonAdd);

        // And finally, add the pad to the center of the app
        frame.add(calculatorPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    static class NumberButtonListener implements ActionListener {
        private JTextField displayField;
        private String number;

        public NumberButtonListener(JTextField displayField, String number) {
            this.displayField = displayField;
            this.number = number;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            displayField.setText(displayField.getText() + number);
        }
    }

    static class OperatorButtonListener implements ActionListener {
        private JTextField displayField;
        private String operator;

        public OperatorButtonListener(JTextField displayField, String operator) {
            this.displayField = displayField;
            this.operator = operator;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            displayField.setText(displayField.getText() + operator);
        }
    }
}
