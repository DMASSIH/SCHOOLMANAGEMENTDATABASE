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

public class Classes extends JFrame implements MouseListener, ActionListener {

    ImageIcon exitIconImage, backIconImage, classIconImage;
    JLabel exitIconLabel, backIconLabel, classIconLabel, classLabel, teacherLabel;
    JTextField classField, teacherField;
    JButton addBttn, showBttn, deleteBttn, updateBttn;

    static Connection connection = null;
    static Statement statement = null;
    static java.sql.ResultSet resultset = null;

    Classes() {
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

        classIconImage = new ImageIcon(getClass().getResource("images//classes.png"));
        classIconLabel = new JLabel(classIconImage);
        classIconLabel.setText("Classes");
        classIconLabel.addMouseListener(this);
        classIconLabel.setBounds(450, 200, 300, 150);

        classLabel = new JLabel("Class");
        teacherLabel = new JLabel("Teacher");
        classField = new JTextField(15);
        teacherField = new JTextField(15);

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

        classLabel.setBounds(120, 200, 200, 20);
        classField.setBounds(120, 220, 200, 20);
        teacherLabel.setBounds(120, 240, 200, 20);
        teacherField.setBounds(120, 260, 200, 20);

        addBttn.setBounds(90, 510, 100, 25);
        showBttn.setBounds(210, 510, 100, 25);
        deleteBttn.setBounds(330, 510, 100, 25);
        updateBttn.setBounds(450, 510, 100, 25);

        add(classLabel);
        add(classField);
        add(teacherLabel);
        add(teacherField);

        add(addBttn);
        add(showBttn);
        add(deleteBttn);
        add(updateBttn);
        add(classIconLabel);
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
        if (label.getText() == "Classes") {
            classIconImage = new ImageIcon(getClass().getResource("images//classesEffect.png"));
            classIconLabel.setIcon(classIconImage);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        if (label.getText() == "Classes") {
            classIconImage = new ImageIcon(getClass().getResource("images//classes.png"));
            classIconLabel.setIcon(classIconImage);
        }
    }

    public void actionPerformed(ActionEvent e) {
        JButton bttn = (JButton) e.getSource();

        if (bttn.getText() == "ADD") {
            if (classField.getText().equals("") && teacherField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Enter Some Value");
            } else {
                try {
                    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                    String msAccDB = "Classes.accdb";
                    String dbURl = "jdbc:ucanaccess://" + msAccDB;
                    connection = DriverManager.getConnection(dbURl);
                    statement = connection.createStatement();

                    String query = "Insert into Class(Class,Teacher) values('" + classField.getText() + "','" + teacherField.getText() + "')";
                    boolean update = statement.execute(query);

                    JOptionPane.showMessageDialog(this, "Teacher Registerd");
                    classField.setText("");
                    teacherField.setText("");

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
                String msAccDB = "Classes.accdb";
                String dbURl = "jdbc:ucanaccess://" + msAccDB;
                connection = DriverManager.getConnection(dbURl);
                statement = connection.createStatement();

                String query = "select * from Class where Teacher like '" + teacherField.getText() + "'";
                resultset = statement.executeQuery(query);
                System.out.println("Executed");

                while (resultset.next()) {
                    classField.setText(resultset.getString("Class"));
                    teacherField.setText(resultset.getString("Teacher"));

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
                String msAccDB = "Classes.accdb";
                String dbURl = "jdbc:ucanaccess://" + msAccDB;
                connection = DriverManager.getConnection(dbURl);
                statement = connection.createStatement();

                String query = "delete from class where Teacher like '" + teacherField.getText() + "'";
                boolean update = statement.execute(query);
                System.out.println("Executed");
                JOptionPane.showMessageDialog(this, "Teacher Deleted");
                classField.setText("");
                teacherField.setText("");

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
                String msAccDB = "Classes.accdb";
                String dbURl = "jdbc:ucanaccess://" + msAccDB;
                connection = DriverManager.getConnection(dbURl);
                statement = connection.createStatement();

                String query = "UPDATE Class set Class='" + classField.getText() + "' , Teacher='" + teacherField.getText() + "'";
                statement.executeUpdate(query);
                System.out.println("Executed");
                JOptionPane.showMessageDialog(this, "Teacher Record Updated");
                classField.setText("");
                teacherField.setText("");

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