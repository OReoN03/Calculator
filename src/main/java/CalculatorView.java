import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CalculatorView implements ActionListener {
    private JTextField answerField;
    private JTextField historyField;
    private JFrame frame;
    private String first = "";
    private String second = "";
    private char sign = '0';
    public CalculatorView() {
        init();
    }
    public void init() {
        frame = new JFrame("Calculator");
        Container container = frame.getContentPane();

        GridBagLayout gridLayout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();

        int buttonRadius = 10;
        Color lightGrayColor = new Color(79, 79, 79);
        Color lightBlueColor = new Color(172, 212, 255);

        JButton percent = new RoundButton("%", buttonRadius, Color.DARK_GRAY);
        JButton removeAllE = new RoundButton("CE", buttonRadius, Color.DARK_GRAY);
        JButton removeAll = new RoundButton("C", buttonRadius, Color.DARK_GRAY);
        JButton remove = new RoundButton("⌫", buttonRadius, Color.DARK_GRAY);

        JButton oneDividedByX = new RoundButton("1/x", buttonRadius, Color.DARK_GRAY);
        JButton pow = new RoundButton("^2", buttonRadius, Color.DARK_GRAY);
        JButton sqrt = new RoundButton("√", buttonRadius, Color.DARK_GRAY);
        JButton division = new RoundButton("÷", buttonRadius, Color.DARK_GRAY);

        JButton seven = new RoundButton("7", buttonRadius, lightGrayColor);
        JButton eight = new RoundButton("8", buttonRadius, lightGrayColor);
        JButton nine = new RoundButton("9", buttonRadius, lightGrayColor);
        JButton multiplication = new RoundButton("×", buttonRadius, Color.DARK_GRAY);

        JButton four = new RoundButton("4", buttonRadius, lightGrayColor);
        JButton five = new RoundButton("5", buttonRadius, lightGrayColor);
        JButton six = new RoundButton("6", buttonRadius, lightGrayColor);
        JButton minus = new RoundButton("-", buttonRadius, Color.DARK_GRAY);

        JButton one = new RoundButton("1", buttonRadius, lightGrayColor);
        JButton two = new RoundButton("2", buttonRadius, lightGrayColor);
        JButton three = new RoundButton("3", buttonRadius, lightGrayColor);
        JButton plus = new RoundButton("+", buttonRadius, Color.DARK_GRAY);

        JButton plusMinus = new RoundButton("+/-", buttonRadius, lightGrayColor);
        JButton zero = new RoundButton("0", buttonRadius, lightGrayColor);
        JButton point = new RoundButton(".", buttonRadius, lightGrayColor);
        JButton equals = new RoundButton("=", buttonRadius, lightBlueColor);

        List<JButton> buttonList =  Arrays.asList(percent, removeAllE, removeAll, remove,
                oneDividedByX, pow, sqrt, division,
                seven, eight, nine, multiplication,
                four, five, six, minus,
                one, two, three, plus,
                plusMinus, zero, point, equals
        );

        answerField = new JTextField("0");
        historyField = new JTextField();

        answerField.setFont(new Font("Segoe UI", Font.BOLD, 42));
        answerField.setBackground(Color.BLACK);
        answerField.setForeground(Color.WHITE);
        answerField.setBorder(BorderFactory.createEmptyBorder());
        answerField.setHorizontalAlignment(SwingConstants.RIGHT);

        historyField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        historyField.setBackground(Color.BLACK);
        historyField.setForeground(Color.WHITE);
        historyField.setBorder(BorderFactory.createEmptyBorder());
        historyField.setHorizontalAlignment(SwingConstants.RIGHT);
        historyField.setEditable(false);

        container.setBackground(Color.BLACK);
        container.setLayout(gridLayout);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.gridwidth = 4;
        container.add(historyField, constraints);
        constraints.gridy = 2;
        container.add(answerField, constraints);

        constraints.gridwidth = 1;
        int i = 0;
        for (int y = 3; y < 9; y++) {
            constraints.gridy = y;
            for (int x = 0; x < 4; x++) {
                if (i < buttonList.size()) {
                    constraints.gridx = x;
                    container.add(buttonList.get(i), constraints);
                    i++;
                }
            }
        }

        for (JButton button : buttonList) {
            button.addActionListener(this);
            button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            button.setForeground(Color.WHITE);
            button.setBorderPainted(false);
        }

        for (JButton button : buttonList) {
            if (button.equals(sqrt) || button.equals(pow) || button.equals(percent) || button.equals(remove) ||
                    button.equals(removeAll) || button.equals(removeAllE) || button.equals(oneDividedByX)) {
                button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            }
            if (button.equals(plus) || button.equals(minus) || button.equals(division) ||
                    button.equals(multiplication) || button.equals(equals)) {
                button.setFont(new Font("Segoe UI", Font.PLAIN, 22));
            }
        }
        equals.setForeground(Color.BLACK);

        frame.setBackground(Color.BLACK);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(440, 620);
        frame.setMinimumSize(new Dimension(440, 620));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        double answer = 0;
        char c = s.charAt(0);

        if (s.equals("CE")) {
            if (historyField.getText().contains("=")) historyField.setText("");
            answerField.setText("0");
        }
        else if (c == 'C') {
            answerField.setText("0");
            historyField.setText("");
        }
        else if (c == '+' || c == '-' || c == '×' || c == '÷') {
            //TODO
            if (!Objects.equals(historyField.getText(), "") && !Objects.equals(answerField.getText(), "")) {
                if (historyField.getText().charAt(historyField.getText().length() - 1) == sign) {

                    second = answerField.getText();
                    first = historyField.getText().substring(0, historyField.getText().length() - 1);
                    if (!second.equals("")) {
                        if (sign == '+') answer = Double.parseDouble(first) + Double.parseDouble(second);
                        if (sign == '-') answer = Double.parseDouble(first) - Double.parseDouble(second);
                        if (sign == '×') answer = Double.parseDouble(first) * Double.parseDouble(second);
                        if (sign == '÷') answer = Double.parseDouble(first) / Double.parseDouble(second);
                    }
                    if (answer % 1 == 0) {
                        int ans = (int) answer;
                        answerField.setText(String.valueOf(ans));
                        historyField.setText(String.valueOf(ans) + c);
                    } else {
                        answerField.setText(String.valueOf(answer));
                        historyField.setText(String.valueOf(answer) + c);
                    }
                }
            }
            else {
                historyField.setText(answerField.getText() + c);
                sign = c;
                answerField.setText("");
            }
        }
        else if (c == '%') {
            second = answerField.getText();
            double percent = Double.parseDouble(second) * 0.01;
            second = String.valueOf(percent);
            answerField.setText(second);
        }
        else if (c == '√') {
            first = answerField.getText();
            answer = Math.sqrt(Double.parseDouble(first));
            if (answer % 1 == 0) {
                int ans = (int) answer;
                answerField.setText(String.valueOf(ans));
            }
            else answerField.setText(String.valueOf(answer));
            historyField.setText("√(" + first + ")");
        }
        else if (s.equals("^2")) {
            first = answerField.getText();
            answer = Double.parseDouble(first) * Double.parseDouble(first);
            if (answer % 1 == 0) {
                int ans = (int) answer;
                answerField.setText(String.valueOf(ans));
            }
            else answerField.setText(String.valueOf(answer));
            historyField.setText("√(" + first + ")");
        }
        else if (s.equals("1/x")) {
            first = answerField.getText();
            answer = 1 / Double.parseDouble(first);
            if (answer % 1 == 0) {
                int ans = (int) answer;
                answerField.setText(String.valueOf(ans));
            }
            else answerField.setText(String.valueOf(answer));
            historyField.setText("1/(" + first + ")");
        }
        else if (c == '.') {
            answerField.setText(answerField.getText() + ".");
        }
        else if (c >= '0' && c <= '9') {
            if (answerField.getText().equals("0")) {
                answerField.setText(String.valueOf(c));
            }
            else {
                answerField.setText(answerField.getText() + c);
            }
            if (historyField.getText().contains("=")) historyField.setText("");
        }
        else if (c == '=') {
            second = answerField.getText();
            first = historyField.getText().substring(0, historyField.getText().length() - 1);
            if (!second.equals("")) {
                if (sign == '+') answer = Double.parseDouble(first) + Double.parseDouble(second);
                if (sign == '-') answer = Double.parseDouble(first) - Double.parseDouble(second);
                if (sign == '×') answer = Double.parseDouble(first) * Double.parseDouble(second);
                if (sign == '÷') answer = Double.parseDouble(first) / Double.parseDouble(second);
            }
            if (answer % 1 == 0) {
                int ans = (int) answer;
                answerField.setText(String.valueOf(ans));
            }
            else answerField.setText(String.valueOf(answer));
            historyField.setText(historyField.getText() + second + "=");
        }
    }
}