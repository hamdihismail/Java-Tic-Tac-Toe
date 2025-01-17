package TicTacToe;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Login implements ActionListener{
	
	private JFrame frame = new JFrame();	
	private JPanel panel = new JPanel();
	private JLabel userLabel = new JLabel("User");
	private JTextField userText = new JTextField(20);
	private JLabel passwordLabel = new JLabel("Password");
	private JPasswordField passwordText = new JPasswordField(20);
	private JButton loginButton = new JButton("login");
	private JButton registerButton = new JButton("register");
	private JButton guestButton = new JButton("guest");
	private JLabel success = new JLabel("");
	boolean loginSuccess;
	
	public class Message {
		private String message = "For multiplayer friendly match select: guest";			
	public String print() {
		return message;
	}
	
	}
	public class successMessage extends Message {
		private String message = "Login successful";
		public String print() {
			return message;
		}
	}
	public class registrationError extends Message {
		private String message = "Please enter valid new username and password";
		public String print() {
			return message;
		}
	}
	public class registrationTaken extends Message {
		private String message = "Username Exists";
		public String print() {
			return message;
		}
	}
	public class registrationSuccess extends Message {
		private String message = "Registration successful";
		public String print() {
			return message;
		}
	}
	
	private String message = new Message().print();	
	private String successMessage = new successMessage().print();	
	private String registrationError = new registrationError().print();	
	private String registrationSuccess = new registrationSuccess().print();	
	private String registrationTaken = new registrationTaken().print();	
   
	
	
	Login(){
		panel.setLayout(null);
		frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);
        userText.setBounds(100,20,165,25);
        panel.add(userText);        
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);        
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);        
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(this);
        panel.add(loginButton);        
        guestButton.setBounds(100, 80, 80, 25);
        guestButton.addActionListener(this);
        panel.add(guestButton);        
        registerButton.setBounds(190, 80, 80, 25);
        registerButton.addActionListener(this);
        panel.add(registerButton);        
        success.setBounds(10, 110, 300, 25);
        success.setText(message);
        panel.add(success);
        frame.setVisible(true); 
        fileChecker();
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String user = userText.getText();
		String password = passwordText.getText();
		if(e.getSource()==loginButton) {			
			valid();
		}
		if(e.getSource()==guestButton) {
			frame.dispose();
			guestNext();
		}
		if(e.getSource()==registerButton) {
			if (!user.isBlank()&&!password.isBlank()) {				
					newUser(true,user,password);	
			}
			else {
				success.setText(registrationError);
			}
		}
		
	}
	public void fileChecker() {
		Credentials credentials = new Credentials(true);
	}
	
	public void guestNext() {
		TicTacToe tictactoe = new TicTacToe();		
	}
	public void userNext(String username) {
		TicTacToe tictactoe = new TicTacToe(username);		
	}
	
	public void valid() {
		String user = userText.getText();
		String password = passwordText.getText();
		Credentials credentials = new Credentials(user,password);
		if(credentials.validator=="valid") {
			success.setText(successMessage);
			loginSuccess = true;
			
//			This delays the execution of the code within
			CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS).execute(() -> { 
					frame.dispose();	
					userNext(user);
			});
		}
		else if(credentials.validator=="File not found") {
			
			success.setText(credentials.validator);
		}
		else {
			success.setText(credentials.validator);
		}
	}
	public void newUser(Boolean x, String username, String password) {
		Credentials credentials = new Credentials(true,username,password);
		if (!credentials.taken) {		
			success.setText(registrationSuccess);
//		This delays the execution of the code within
			CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS).execute(() -> { 
				frame.dispose();	
				userNext(username);
			});
		}
		else {
			success.setText(registrationTaken);
		}
	}

}
