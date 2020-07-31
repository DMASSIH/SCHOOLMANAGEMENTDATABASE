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

public class Student extends JFrame implements MouseListener, ActionListener {

    ImageIcon exitIconImage, backIconImage, studentImage;
    JLabel exitIconLabel, backIconLabel,studentLabel, IDLabel, NameLabel, FatherNameLabel, ProgramLabel, CMSLabel;
    JTextField IDField, NameField, FatherNameField, ProgramField, CMSField;
    JButton addBttn, showBttn, deleteBttn, updateBttn;

    static Connection connection = null;
    static Statement statement = null;
    static java.sql.ResultSet resultset = null;

    Student() {
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

	studentImage = new ImageIcon(getClass().getResource("images//Student.png"));
        studentLabel = new JLabel(studentImage);
        studentLabel.setText("Student");
        studentLabel.addMouseListener(this);
	studentLabel.setBounds(450, 200, 300, 150);

        IDLabel = new JLabel("ID");
        NameLabel = new JLabel("Name");
        FatherNameLabel = new JLabel("Father Name");
        ProgramLabel = new JLabel("Program");
        CMSLabel = new JLabel("CMS *(Enter CMS ID to show)");
        IDField = new JTextField(15);
        NameField = new JTextField(15);
        FatherNameField = new JTextField(15);
        ProgramField = new JTextField(15);
        CMSField = new JTextField(15);

        addBttn = new JButton();
        addBttn.setText("ADD");
        addBttn.addActionListener(this);
        showBttn = new JButton();
        showBttn.setText("SHOW");
        showBttn.addActionListener(this);
        deleteBttn = new JButton();
        deleteBttn.setText("DELETE");
        deleteBttn.addActionListener(this);
        updateBttn = new JButton();
        updateBttn.setText("UPDATE");
        updateBttn.addActionListener(this);
	

        IDLabel.setBounds(120, 200, 200, 20);
        IDField.setBounds(120, 220, 200, 20);
        NameLabel.setBounds(120, 240, 200, 20);
        NameField.setBounds(120, 260, 200, 20);
        FatherNameLabel.setBounds(120, 280, 200, 20);
        FatherNameField.setBounds(120, 300, 200, 20);
        ProgramLabel.setBounds(120, 320, 200, 20);
        ProgramField.setBounds(120, 340, 200, 20);
        CMSLabel.setBounds(120, 360, 200, 20);
        CMSField.setBounds(120, 380, 200, 20);
        addBttn.setBounds(90, 510, 100, 25);
        showBttn.setBounds(210, 510, 100, 25);
        deleteBttn.setBounds(330, 510, 100, 25);
        updateBttn.setBounds(450, 510, 100, 25);

        add(IDLabel);
        add(IDField);
        add(NameLabel);
        add(NameField);
        add(FatherNameLabel);
        add(FatherNameField);
        add(ProgramLabel);
        add(ProgramField);
        add(CMSLabel);
        add(CMSField);
        add(addBttn);
        add(showBttn);
        add(deleteBttn);
        add(updateBttn);
	add(studentLabel);
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
        } 
    }

    @Override
    public void mouseExited(MouseEvent e) {
		JLabel label = (JLabel) e.getSource();
        if (label.getText() == "Student") {
            studentImage = new ImageIcon(getClass().getResource("images//Student.png"));
            studentLabel.setIcon(studentImage);
        } 
    }

    public void actionPerformed(ActionEvent e) {
        JButton bttn = (JButton) e.getSource();

        if (bttn.getText() == "ADD") {
            if (IDField.getText().equals("") && NameField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Enter Some Value");
            } else {
                try {
                    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                    String msAccDB = "Students.accdb";
                    String dbURl = "jdbc:ucanaccess://" + msAccDB;
                    connection = DriverManager.getConnection(dbURl);
                    statement = connection.createStatement();

                    String query = "Insert into Student(ID,Name,FatherName,Program,CMS) values('" + IDField.getText() + "','" + NameField.getText() + "','" + FatherNameField.getText() + "','" + ProgramField.getText() + "','" + CMSField.getText() + "')";
                    boolean update = statement.execute(query);

                    JOptionPane.showMessageDialog(this, "Student Registerd");
                    IDField.setText("");
                    NameField.setText("");
                    FatherNameField.setText("");
                    ProgramField.setText("");
                    CMSField.setText("");

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
                String msAccDB = "Students.accdb";
                String dbURl = "jdbc:ucanaccess://" + msAccDB;
                connection = DriverManager.getConnection(dbURl);
                statement = connection.createStatement();

                System.out.println(CMSField.getText());
                String query = "select * from Student where CMS like '" + CMSField.getText() + "'";
                resultset = statement.executeQuery(query);
                System.out.println("Executed");

                while (resultset.next()) {
                    IDField.setText(resultset.getString("ID"));
                    NameField.setText(resultset.getString("Name"));
                    FatherNameField.setText(resultset.getString("FatherName"));
                    ProgramField.setText(resultset.getString("Program"));
                    CMSField.setText(resultset.getString("CMS"));

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

        if (bttn.getText() == "DELETE") {
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                String msAccDB = "Students.accdb";
                String dbURl = "jdbc:ucanaccess://" + msAccDB;
                connection = DriverManager.getConnection(dbURl);
                statement = connection.createStatement();

                System.out.println(CMSField.getText());
                String query = "delete from Student where CMS like '" + CMSField.getText() + "'";
                boolean update = statement.execute(query);
                System.out.println("Executed");
                JOptionPane.showMessageDialog(this, "Student Deleted");
                IDField.setText("");
                NameField.setText("");
                FatherNameField.setText("");
                ProgramField.setText("");
                CMSField.setText("");

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

        }//DELETE

        if (bttn.getText() == "UPDATE") {
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                String msAccDB = "Students.accdb";
                String dbURl = "jdbc:ucanaccess://" + msAccDB;
                connection = DriverManager.getConnection(dbURl);
                statement = connection.createStatement();

                System.out.println(CMSField.getText());
                String query = "UPDATE Student set ID='" + IDField.getText() + "' , Name='" + NameField.getText() + "' , FatherName='" + FatherNameField.getText() + "' Where CMS like '" + CMSField.getText() + "'";
                statement.executeUpdate(query);
                System.out.println("Executed");
                JOptionPane.showMessageDialog(this, "Student Record Updated");
                IDField.setText("");
                NameField.setText("");
                FatherNameField.setText("");
                ProgramField.setText("");
                CMSField.setText("");

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
