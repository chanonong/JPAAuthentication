package auth.jpa.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import auth.jpa.controller.Authentication;
import auth.jpa.controller.Register;
import auth.jpa.model.User;

@SuppressWarnings("serial")
public class UI extends JFrame {
	JLabel user;
	JLabel password;
	JTextField userField;
	JPasswordField passwordField;
	JButton submit;
	JButton cancel;
	JButton reset;
	JButton regis;
	JLabel status;
	JLabel info;
	public UI() {
		super("Authentication with JPA");
		init();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
	}
	
	public void run() {
		this.setVisible(true);
	}
	public void init() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(450,250));
		this.setResizable(false);
		
		Container topContainer = new Container();
		topContainer.setLayout(new BorderLayout());
		
		Container userContainer = new Container();
		userField = new JTextField();
		userField.setPreferredSize(new Dimension(300,30));
		userField.addActionListener(new MyActionListener());
		user = new JLabel("Username : ");
		
		userContainer.setLayout(new FlowLayout());
		userContainer.add(user);
		userContainer.add(userField);
		
		Container passContainer = new Container();
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(300,30));
		passwordField.addActionListener(new MyActionListener());
		password = new JLabel("Password : ");
		
		passContainer.setLayout(new FlowLayout());
		passContainer.add(password);
		passContainer.add(passwordField);
		
		topContainer.add(userContainer,BorderLayout.NORTH);
		topContainer.add(passContainer,BorderLayout.SOUTH);
		
		Container midContainer = new Container();
		midContainer.setLayout(new FlowLayout());
		
		submit = new JButton("submit");
		submit.addActionListener(new MyActionListener());
		
		cancel = new JButton("cancel");
		cancel.addActionListener(new MyActionListener());
		
		reset= new JButton("reset");
		reset.addActionListener(new MyActionListener());
		
		regis= new JButton("regis");
		regis.addActionListener(new MyActionListener());
		
		midContainer.add(submit);
		midContainer.add(cancel);
		midContainer.add(reset);
		midContainer.add(regis);
		
		Container botContainer = new Container();
		botContainer.setLayout(new BorderLayout());
		status = new JLabel("Please Login");
		status.setHorizontalAlignment(WIDTH/2);
		
		info = new JLabel("User: null , Name: null , LastName: null ");
		info.setHorizontalAlignment(WIDTH/2);
		
		botContainer.add(status,BorderLayout.NORTH);
		botContainer.add(info,BorderLayout.SOUTH);
		
		this.add(topContainer,BorderLayout.NORTH);
		this.add(midContainer,BorderLayout.CENTER);
		this.add(botContainer,BorderLayout.SOUTH);
		
	}
	
	class MyActionListener implements ActionListener {
		
		
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getActionCommand().equals("cancel")) {
				System.exit(ABORT);
			}
			else if(arg0.getActionCommand().equals("reset")) {
				userField.setText("");
				passwordField.setText("");
				status.setText("Please Login");
				info.setText("User: null , Name: null , LastName: null ");
			}
			else if(arg0.getActionCommand().equals("regis")) {
				if(userField.getText() != "" &&passwordField.getText() != "") {
					String[] name = null;
					try {
						name = JOptionPane.showInputDialog("Your name & lastname (format: name lastname:").trim().split(" ");
					} catch (NullPointerException n) {
					}
					if( name == null || name.length != 2) {
						status.setText("please input both name and last name in correct format");
						JOptionPane.showMessageDialog(null, "please input both name and last name in correct format");
						status.setForeground(Color.RED);
						return;
					}
					User user = Register.regis(userField.getText(),passwordField.getText(),name[0],name[1]);
					status.setText("Register successfully");
					status.setForeground(Color.BLACK);
					info.setText("User: " + user.getUsername() +", Name: " + user.getName() + " , LastName: " + user.getLastname());
				}
				else {
					status.setText("please input both username and password ");
					status.setForeground(Color.RED);
				}
			}
			else {
				User user = Authentication.Authenticate(userField.getText() , passwordField.getText());
				if(user == null) {
					userField.setText("");
					passwordField.setText("");
					JOptionPane.showMessageDialog(null, "fail");
					status.setText("Wrong username or password , Please Login");
					status.setForeground(Color.RED);
					info.setText("User: null , Name: null , LastName: null ");
					System.out.println("No result found or database does not exist.");
				}
				else {
					info.setText("User: " + user.getUsername() +", Name: " + user.getName() + " , LastName: " + user.getLastname());
					JOptionPane.showMessageDialog(null, "Logged in");
					status.setText("Logged in");
					status.setForeground(Color.BLACK);
				}
			}
			
		}
		
	}
}
