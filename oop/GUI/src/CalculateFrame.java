import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class CalculateFrame extends JFrame
{
    private JLabel num1Label;
    private JLabel num2Label;
    private JLabel sumLabel;
    private JTextField num1Field;
    private JTextField num2Field;
    private JTextField sumField;
    private JButton calculateButton;
    public CalculateFrame()
    {
        super("Calculate");
        setLayout(new FlowLayout());
        num1Label = new JLabel("Enter first number");
        add(num1Label);
        num1Field = new JTextField(10);
        add(num1Field);
        num2Label = new JLabel("Enter second number");
        add(num2Label);
        num2Field = new JTextField(10);
        add(num2Field);
        sumLabel = new JLabel("Sum");
        add(sumLabel);
        sumField = new JTextField(10);
        add(sumField);
        calculateButton = new JButton("Calculate");
        add(calculateButton);
        CalculateHandler handler = new CalculateHandler();
        calculateButton.addActionListener(handler);
    }
    private class CalculateHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            int num1, num2, sum;
            num1 = Integer.parseInt(num1Field.getText());
            num2 = Integer.parseInt(num2Field.getText());
            sum = num1 + num2;
            sumField.setText(Integer.toString(sum));
        }
    }
    //main method
    public static void main(String[] args)
    {
        CalculateFrame calculateFrame = new CalculateFrame();
        calculateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculateFrame.setSize(300, 200);
        calculateFrame.setVisible(true);
    }
}




