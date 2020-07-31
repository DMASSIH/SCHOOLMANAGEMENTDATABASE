
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    JPanel loginPanel, imagePanel;
    JLabel userName, schoolLabel;
    ImageIcon  schoolIcon;
    JLabel password;
    JLabel userLogin;
    JTextField userNameField;
    JTextField passwordField;
    JButton loginBttn, signupBttn;

    Login() {

        loginPanel = new JPanel();
        userName = new JLabel();
        password = new JLabel();
        userNameField = new JTextField(15);
        passwordField = new JTextField(15);
	schoolIcon = new ImageIcon();
	schoolLabel = new JLabel();
        loginBttn = new JButton();
	signupBttn=new JButton();
        imagePanel = new JPanel();
        userLogin = new JLabel("User Login");

        userLogin.setFont(new Font("Serif", Font.PLAIN, 30));

        ImageIcon loginImage = new ImageIcon(getClass().getResource("images//login-icon.png"));
        JLabel picLabel = new JLabel(loginImage);

	schoolIcon = new ImageIcon(getClass().getResource("images//SchoolManagementSystem.png"));
        schoolLabel = new JLabel(schoolIcon);

        userName.setText("Username");
        password.setText("Password");
        loginBttn.setText("login");
	signupBttn.setText("Sign up");
        loginBttn.addActionListener(this);
	signupBttn.addActionListener(this);
        loginPanel.setLayout(null);

        userLogin.setBounds(50, 0, 200, 35);
        userName.setBounds(20, 40, 200, 20);
        userNameField.setBounds(20, 60, 200, 20);
        password.setBounds(20, 90, 100, 20);
        passwordField.setBounds(20, 110, 200, 20);
	signupBttn.setBounds(20, 150, 100, 25);
        loginBttn.setBounds(120, 150, 100, 25);

        loginPanel.add(userLogin);
        loginPanel.add(userName);
        loginPanel.add(userNameField);
        loginPanel.add(password);
        loginPanel.add(passwordField);
	loginPanel.add(signupBttn);
        loginPanel.add(loginBttn);

        loginPanel.setBounds(450, 200, 400, 200);

        imagePanel.setBounds(90, 180, 300, 300);
        imagePanel.add(picLabel);
	
	schoolLabel.setBounds(20, 20, 350, 200);

        setLayout(null);
	add(schoolLabel);
        add(loginPanel);
        add(imagePanel, new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
	JButton bttn=(JButton) e.getSource();
	String strgusername;
       	String strgpassword;
		
	if(bttn.getText()=="login"){
	ResultSset obj = new ResultSset();
		boolean check=true;	
		
		strgusername = userNameField.getText();
       		strgpassword = passwordField.getText();

		for(int i=0;i<obj.login.length;i++){
	
	if (strgusername.equalsIgnoreCase(obj.login[i][0]) && strgpassword.equalsIgnoreCase(obj.login[i][1])){
				
				SchoolManagementSystem schoolobj=new SchoolManagementSystem();
				this.setVisible(false);
				check=false;
	}
			
		}
	if(check){
		JOptionPane.showMessageDialog(this, "You have entered wrong username and password");
		userNameField.setText("");
		passwordField.setText("");
		}
		}else if(bttn.getText()=="Sign up"){
		SignUp signupp=new SignUp();
		this.setVisible(false);

		}
        
	}

    public static void main(String args[]) {

        Login obj = new Login();

    }
}
