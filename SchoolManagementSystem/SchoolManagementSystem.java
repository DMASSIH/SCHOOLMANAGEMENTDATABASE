

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SchoolManagementSystem extends JFrame implements MouseListener  {
  
    JPanel panel = new JPanel();
    ImageIcon backIconImage,studentImage, teacherImage, classesImage, financeImage, exitIconImage;
    JLabel backIconLabel,studentLabel, teacherLabel, classesLabel, financeLabel, exitIconLabel;
    ImageIcon  schoolIcon;

    SchoolManagementSystem() {

        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        studentImage = new ImageIcon(getClass().getResource("images//Student.png"));
        studentLabel = new JLabel(studentImage);
        studentLabel.setText("Student");
        studentLabel.addMouseListener(this);

        teacherImage = new ImageIcon(getClass().getResource("images//teacher.png"));
        teacherLabel = new JLabel(teacherImage);
        teacherLabel.setText("Teacher");
        teacherLabel.addMouseListener(this);

        classesImage = new ImageIcon(getClass().getResource("images//classes.png"));
        classesLabel = new JLabel(classesImage);
        classesLabel.setText("Classes");
        classesLabel.addMouseListener(this);

        financeImage = new ImageIcon(getClass().getResource("images//finance.png"));
        financeLabel = new JLabel(financeImage);
        financeLabel.setText("Finance");
        financeLabel.addMouseListener(this);

        //panel.setBackground(Color.red);
        // panel.setLayout(null);
        panel.setBounds(300, 120, 500, 350);
        panel.add(studentLabel);
        panel.add(teacherLabel);
        panel.add(classesLabel);
        panel.add(financeLabel);

	add(schoolLabel);
	 add(backIconLabel, new BorderLayout());
        add(exitIconLabel, new BorderLayout());
        add(panel, new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String args[]) {
        SchoolManagementSystem obj = new SchoolManagementSystem();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        if (label.getText() == "Student") {
            Student obj = new Student();
		this.setVisible(false);
        } else if (label.getText() == "Teacher") {
            Teacher obj = new Teacher();
		this.setVisible(false);
        } else if (label.getText() == "Finance") {
            FinanceMain obj = new FinanceMain();
		this.setVisible(false);
        } else if (label.getText() == "Classes") {
            Classes obj = new Classes();
		this.setVisible(false);
        }else if (label.getText() == "Exit") {
           System.exit(0);
        } if (label.getText() == "Back") {
            Login loginObj = new Login();
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
        if (label.getText() == "Student") {
            studentImage = new ImageIcon(getClass().getResource("images//StudentEffect.png"));
            studentLabel.setIcon(studentImage);
        } else if (label.getText() == "Teacher") {
            teacherImage = new ImageIcon(getClass().getResource("images//teacherEffect.png"));
            teacherLabel.setIcon(teacherImage);
        } else if (label.getText() == "Finance") {
            financeImage = new ImageIcon(getClass().getResource("images//financeEffect.png"));
            financeLabel.setIcon(financeImage);
        } else if (label.getText() == "Classes") {
            classesImage = new ImageIcon(getClass().getResource("images//classesEffect.png"));
            classesLabel.setIcon(classesImage);
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        if (label.getText() == "Student") {
            studentImage = new ImageIcon(getClass().getResource("images//Student.png"));
            studentLabel.setIcon(studentImage);
        } else if (label.getText() == "Teacher") {
            teacherImage = new ImageIcon(getClass().getResource("images//teacher.png"));
            teacherLabel.setIcon(teacherImage);
        } else if (label.getText() == "Finance") {
            financeImage = new ImageIcon(getClass().getResource("images//finance.png"));
            financeLabel.setIcon(financeImage);
        } else if (label.getText() == "Classes") {
            classesImage = new ImageIcon(getClass().getResource("images//classes.png"));
            classesLabel.setIcon(classesImage);
        }
    }
}
