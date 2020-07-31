import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class FinanceStudent extends JFrame implements MouseListener, ActionListener {

    ImageIcon exitIconImage, backIconImage, financeImage;
    JLabel exitIconLabel, backIconLabel, financeLabel, studentLabel, feesLabel;
    JTextField studentField, feesField;
    JButton addBttn, showBttn, updateBttn;

    static Connection connection = null;
    static Statement statement = null;
    static java.sql.ResultSet resultset = null;

    FinanceStudent() {
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
        financeLabel.setBounds(450, 200, 300, 150);

        studentLabel = new JLabel();
        feesLabel = new JLabel();
	studentLabel.setText("Student");
	feesLabel.setText("Fees");
	

        studentField = new JTextField(15);
        studentField.setBounds(120, 220, 200, 20);
	
        feesField = new JTextField(15);
        feesField.setBounds(120, 260, 200, 20);

        addBttn = new JButton();
        addBttn.setText("ADD");
        addBttn.addActionListener(this);
        showBttn = new JButton();
        showBttn.setText("SHOW");
        showBttn.addActionListener(this);
        updateBttn = new JButton();
        updateBttn.setText("UPDATE");
        updateBttn.addActionListener(this);

        studentLabel.setBounds(120, 200, 200, 20);
        feesLabel.setBounds(120, 240, 200, 20);

        addBttn.setBounds(90, 510, 100, 25);
        showBttn.setBounds(210, 510, 100, 25);
        updateBttn.setBounds(330, 510, 100, 25);

        add(studentLabel);
        add(studentField);
        add(feesLabel);
        add(feesField);

        add(addBttn);
        add(showBttn);
        add(updateBttn);
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
            FinanceMain SchoolObj = new FinanceMain();
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
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        if (label.getText() == "Finance") {
            financeImage = new ImageIcon(getClass().getResource("images//finance.png"));
            financeLabel.setIcon(financeImage);
        }
    }

    public void actionPerformed(ActionEvent e) {
        JButton bttn = (JButton) e.getSource();

        if (bttn.getText() == "ADD") {
            if (studentField.getText().equals("") && feesField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Enter Some Value");
            } else {
                try {
			System.out.println("hali tai");
                    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                    String msAccDB = "FinanceStudent.accdb";
                    String dbURl = "jdbc:ucanaccess://" + msAccDB;
                    connection = DriverManager.getConnection(dbURl);
                    statement = connection.createStatement();
			System.out.println("hali tai");
                    String query = "Insert into financeStudent (Student,Fees) values('" + studentField.getText() + "','" + feesField.getText() + "')";
                    boolean update = statement.execute(query);

                    JOptionPane.showMessageDialog(this, "Account Updated");

                } catch (Exception ex) {

                } finally {

                    try {
                        if (null != connection) {
                            resultset.close();
                            statement.close();
                            connection.close();
                        }
                    } catch (Exception ee) {

                    }

                }
            }
        }//ADD 

        if (bttn.getText() == "SHOW") {

            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                String msAccDB = "FinanceStudent.accdb";
                String dbURl = "jdbc:ucanaccess://" + msAccDB;
                connection = DriverManager.getConnection(dbURl);
                statement = connection.createStatement();

                String query = "select * from financeStudent where Student like '" + studentField.getText() + "'";
                resultset = statement.executeQuery(query);
                System.out.println("Executed");

                while (resultset.next()) {
                    studentField.setText(resultset.getString("Student"));
                    feesField.setText(resultset.getString("Fees"));

                }

            } catch (Exception ex) {

            } finally {

                try {
                    if (null != connection) {
                        resultset.close();
                        statement.close();
                        connection.close();
                    }
                } catch (Exception ee) {

                }

            }
        }//Show

        if (bttn.getText() == "UPDATE") {
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                String msAccDB = "FinanceStudent.accdb";
                String dbURl = "jdbc:ucanaccess://" + msAccDB;
                connection = DriverManager.getConnection(dbURl);
                statement = connection.createStatement();

                String query = "UPDATE financeStudent set Student='" + studentField.getText() + "' , Fees='" + feesField.getText() + "'";
                statement.executeUpdate(query);
                System.out.println("Executed");
                JOptionPane.showMessageDialog(this, "Student Record Updated");

            } catch (Exception ex) {

            } finally {

                try {
                    if (null != connection) {
                        resultset.close();
                        statement.close();
                        connection.close();
                    }
                } catch (Exception ee) {

                }

            }

        }//update

    }

}
