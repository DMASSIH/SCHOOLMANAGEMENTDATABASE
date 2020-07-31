
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SignUp extends JFrame implements MouseListener, ActionListener {

    ImageIcon exitIconImage, backIconImage;
    JLabel exitIconLabel, backIconLabel, userLabel, passwordLabel;
    JTextField userNameField, passwordField;
    JButton signupBttn;

    static Connection connection = null;
    static Statement statement = null;
    static java.sql.ResultSet resultset = null;

    SignUp() {
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

        userLabel = new JLabel("User Name");
        passwordLabel = new JLabel("Password");
        userNameField = new JTextField(15);
        passwordField = new JTextField(15);

        signupBttn = new JButton();
        signupBttn.setText("Sign up");
        signupBttn.addActionListener(this);

        userLabel.setBounds(120, 200, 200, 20);
        userNameField.setBounds(120, 220, 200, 20);
        passwordLabel.setBounds(120, 240, 200, 20);
        passwordField.setBounds(120, 260, 200, 20);
        signupBttn.setBounds(220, 285, 100, 25);

        add(userLabel);
        add(userNameField);
        add(passwordLabel);
        add(passwordField);
        add(signupBttn);
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
	if(label.getText() == "Back"){
	Login loginObj=new Login();
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void actionPerformed(ActionEvent e) {
        if (userNameField.getText().equals("") && passwordField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please Enter Some Value");
        }
    

    
        else{
		try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
							String msAccDB="database.accdb";
							String dbURl="jdbc:ucanaccess://"+msAccDB;
							connection=DriverManager.getConnection(dbURl);
							statement=connection.createStatement();
							
            String query = "Insert into Login(userName,Password) values('"+userNameField.getText()+"','"+passwordField.getText()+"')";
            int update = statement.executeUpdate(query);

            SchoolManagementSystem schoolobj=new SchoolManagementSystem();
	    this.setVisible(false);	

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

    }

}
