import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculator {
    private JFrame window;
    private JTextField numSubjectsField;
    private JTextField[] marksFields;
    private JLabel totalMarksLabel;
    private JLabel averageLabel;
    private JLabel gradeLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GradeCalculator().createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI() {
        window = new JFrame("Grade Calculator");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2));

        JLabel numSubjectsLabel = new JLabel("Enter number of subjects:");
        numSubjectsField = new JTextField(5);
        inputPanel.add(numSubjectsLabel);
        inputPanel.add(numSubjectsField);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener());
        inputPanel.add(calculateButton);

        window.add(inputPanel, BorderLayout.NORTH);

        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new GridLayout(0, 1));

        totalMarksLabel = new JLabel("Total marks: ");
        outputPanel.add(totalMarksLabel);

        averageLabel = new JLabel("Average percentage: ");
        outputPanel.add(averageLabel);

        gradeLabel = new JLabel("Grade: ");
        outputPanel.add(gradeLabel);

        window.add(outputPanel, BorderLayout.CENTER);

        window.pack();
        window.setVisible(true);
    }

    private class CalculateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int numSubjects = Integer.parseInt(numSubjectsField.getText());
            marksFields = new JTextField[numSubjects];
            JPanel marksPanel = new JPanel();
            marksPanel.setLayout(new GridLayout(0, 1));

            for (int i = 0; i < numSubjects; i++) {
                JLabel label = new JLabel("Enter marks for subject " + (i + 1) + ":");
                JTextField field = new JTextField(5);
                marksFields[i] = field;
                marksPanel.add(label);
                marksPanel.add(field);
            }

            window.add(marksPanel, BorderLayout.CENTER);
            window.revalidate();
            window.repaint();

            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(new SubmitButtonListener());
            marksPanel.add(submitButton);
        }
    }

    private class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int totalMarks = 0;
            for (JTextField field : marksFields) {
                totalMarks += Integer.parseInt(field.getText());
            }

            double average = (double)totalMarks / marksFields.length;
            char grade;
            if (average >= 90) {
                grade = 'A';
            } else if (average >= 80) {
                grade = 'B';
            } else if (average >= 70) {
                grade = 'C';
            } else if (average >= 60) {
                grade = 'D';
            } else {
                grade = 'F';
            }

            totalMarksLabel.setText("Total marks: " + totalMarks);
            averageLabel.setText("Average percentage: " + String.format("%.2f", average));
            gradeLabel.setText("Grade: " + grade);

            window.revalidate();
            window.repaint();
        }
    }
}