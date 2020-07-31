import javax.swing.*;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class FinanceMain extends JFrame implements MouseListener {

    ImageIcon exitIconImage, backIconImage, financeImage, studentImage , teacherImage;
    JLabel exitIconLabel, backIconLabel, financeLabel, teacherLabel, studentLabel;

   
    FinanceMain() {
        setLayout(null);
        setSize(800, 600);

        exitIconImage = new ImageIcon(getClass().getResource("images//exit.png"));
        exitIconLabel = new JLabel(exitIconImage);
        exitIconLabel.setText("Exit");
        exitIconLabel.addMouseListener(this);
        exitIconLabel.setBounds(650, 33, 120, 45);

        backIconImage = new ImageIcon(getClass().getResource("images//back.png"));
        backIconLabel = new JLabel(backIconImage);
        backIconLabel.setText("Back");
        backIconLabel.addMouseListener(this);
        backIconLabel.setBounds(520, 33, 120, 45);

        ImageIcon schoolIcon = new ImageIcon();
        JLabel schoolLabel = new JLabel();
        schoolIcon = new ImageIcon(getClass().getResource("images//SchoolManagementSystem.png"));
        schoolLabel = new JLabel(schoolIcon);
        schoolLabel.setBounds(20, 20, 350, 200);
        add(schoolLabel);

        financeImage = new ImageIcon(getClass().getResource("images//finance.png"));
        financeLabel = new JLabel(financeImage);
        financeLabel.setText("Finance");
        financeLabel.addMouseListener(this);
        financeLabel.setBounds(450, 350, 300, 150);
        
        
        teacherImage = new ImageIcon(getClass().getResource("images//teacher.png"));
        teacherLabel = new JLabel(teacherImage);
        teacherLabel.setText("Teacher");
        teacherLabel.addMouseListener(this);
        teacherLabel.setBounds(10, 120, 300, 300);
        
        studentImage = new ImageIcon(getClass().getResource("images//student.png"));
        studentLabel = new JLabel(studentImage);
        studentLabel.setText("Student");
        studentLabel.addMouseListener(this);
        studentLabel.setBounds(200, 120, 300, 300);
       

        

       

        add(teacherLabel);
	
        add(studentLabel);
	
        add(financeLabel);
        add(backIconLabel, new BorderLayout());
        add(exitIconLabel, new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        if (label.getText() == "Exit") {
            System.exit(0);
        }
        if (label.getText() == "Back") {
            SchoolManagementSystem SchoolObj = new SchoolManagementSystem();
            this.setVisible(false);
        }
	if (label.getText() == "Teacher") {
            FinanceTeacher SchoolObj = new FinanceTeacher();
            this.setVisible(false);
        }
	if (label.getText() == "Student") {
            FinanceStudent SchoolObj = new FinanceStudent();
            this.setVisible(false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        if (label.getText() == "Finance") {
            financeImage = new ImageIcon(getClass().getResource("images//financeEffect.png"));
            financeLabel.setIcon(financeImage);
        }
	 if (label.getText() == "Teacher") {
            teacherImage = new ImageIcon(getClass().getResource("images//teacherEffect.png"));
            teacherLabel.setIcon(teacherImage);
        }
	 if (label.getText() == "Student") {
            studentImage = new ImageIcon(getClass().getResource("images//studentEffect.png"));
            studentLabel.setIcon(studentImage);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        if (label.getText() == "Finance") {
            financeImage = new ImageIcon(getClass().getResource("images//finance.png"));
            financeLabel.setIcon(financeImage);
        } if (label.getText() == "Teacher") {
            teacherImage = new ImageIcon(getClass().getResource("images//teacher.png"));
            teacherLabel.setIcon(teacherImage);
        }
	 if (label.getText() == "Student") {
            studentImage = new ImageIcon(getClass().getResource("images//student.png"));
            studentLabel.setIcon(studentImage);
        }
    }

   

}
