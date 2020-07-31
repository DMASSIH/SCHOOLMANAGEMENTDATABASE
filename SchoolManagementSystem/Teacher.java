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

public class Teacher extends JFrame implements MouseListener, ActionListener {

    ImageIcon exitIconImage, backIconImage, TeacherIconImage;
    JLabel exitIconLabel, backIconLabel, teacherIconLabel, nameLabel, subjectLabel;
    JTextField nameField, subjectField;
    JButton addBttn, showBttn, deleteBttn, updateBttn;

    static Connection connection = null;
    static Statement statement = null;
    static java.sql.ResultSet resultset = null;

    Teacher() {
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

        TeacherIconImage = new ImageIcon(getClass().getResource("images//teacher.png"));
        teacherIconLabel = new JLabel(TeacherIconImage);
        teacherIconLabel.setText("Teacher");
        teacherIconLabel.addMouseListener(this);
        teacherIconLabel.setBounds(450, 200, 300, 150);

        nameLabel = new JLabel("Name");
        subjectLabel = new JLabel("Subject");
        nameField = new JTextField(15);
        subjectField = new JTextField(15);

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

        nameLabel.setBounds(120, 200, 200, 20);
        nameField.setBounds(120, 220, 200, 20);
        subjectLabel.setBounds(120, 240, 200, 20);
        subjectField.setBounds(120, 260, 200, 20);

        addBttn.setBounds(90, 510, 100, 25);
        showBttn.setBounds(210, 510, 100, 25);
        deleteBttn.setBounds(330, 510, 100, 25);
        updateBttn.setBounds(450, 510, 100, 25);

        add(nameLabel);
        add(nameField);
        add(subjectLabel);
        add(subjectField);

        add(addBttn);
        add(showBttn);
        add(deleteBttn);
        add(updateBttn);
        add(teacherIconLabel);
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
        if (label.getText() == "Teacher") {
            TeacherIconImage = new ImageIcon(getClass().getResource("images//teacherEffect.png"));
            teacherIconLabel.setIcon(TeacherIconImage);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        if (label.getText() == "Teacher") {
            TeacherIconImage = new ImageIcon(getClass().getResource("images//teacher.png"));
            teacherIconLabel.setIcon(TeacherIconImage);
        }
    }

    public void actionPerformed(ActionEvent e) {
        JButton bttn = (JButton) e.getSource();

        if (bttn.getText() == "ADD") {
            if (nameField.getText().equals("") && subjectField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Enter Some Value");
            } else {
                try {
                    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                    String msAccDB = "Teachers.accdb";
                    String dbURl = "jdbc:ucanaccess://" + msAccDB;
                    connection = DriverManager.getConnection(dbURl);
                    statement = connection.createStatement();

                    String query = "Insert into teacher(Name,Subject) values('" + nameField.getText() + "','" + subjectField.getText() + "')";
                    boolean update = statement.execute(query);

                    JOptionPane.showMessageDialog(this, "Teacher Registerd");
                    nameField.setText("");
                    subjectField.setText("");

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
                String msAccDB = "Teachers.accdb";
                String dbURl = "jdbc:ucanaccess://" + msAccDB;
                connection = DriverManager.getConnection(dbURl);
                statement = connection.createStatement();

                String query = "select * from teacher where name like '" + nameField.getText() + "'";
                resultset = statement.executeQuery(query);
                System.out.println("Executed");

                while (resultset.next()) {
                    nameField.setText(resultset.getString("Name"));
                    subjectField.setText(resultset.getString("Subject"));

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
                String msAccDB = "Teachers.accdb";
                String dbURl = "jdbc:ucanaccess://" + msAccDB;
                connection = DriverManager.getConnection(dbURl);
                statement = connection.createStatement();

                String query = "delete from teacher where Subject like '" + subjectField.getText() + "'";
                boolean update = statement.execute(query);
                System.out.println("Executed");
                JOptionPane.showMessageDialog(this, "Teacher Deleted");
                nameField.setText("");
                subjectField.setText("");

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
                String msAccDB = "Teachers.accdb";
                String dbURl = "jdbc:ucanaccess://" + msAccDB;
                connection = DriverManager.getConnection(dbURl);
                statement = connection.createStatement();

                String query = "UPDATE teacher set Name='" + nameField.getText() + "' , Subject='" + subjectField.getText() + "'";
                statement.executeUpdate(query);
                System.out.println("Executed");
                JOptionPane.showMessageDialog(this, "Teacher Record Updated");
                nameField.setText("");
                subjectField.setText("");

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