package TicTacToe;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;

public class TicTacToe implements ActionListener{
	
	private Random random = new Random();
	private JFrame frame = new JFrame();
	private JPanel title_panel = new JPanel();
	private JPanel button_panel = new JPanel();
	private JLabel textfield = new JLabel();
	private String username;
	private Long losses,wins;
	private JButton[] buttons = new JButton[9];
	private boolean player1_turn,winner=false;
	
	TicTacToe(){
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(new BorderLayout());
		
		textfield.setBackground(new Color(25,25,25));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("Ink Free",Font.BOLD,75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-Tac-Toe");
		textfield.setOpaque(true);
		
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0,0,800,100);
		
		button_panel.setLayout(new GridLayout(3,3));
		button_panel.setBackground(new Color(150,150,150));
		
		for (int i=0;i<9;i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}
		
		title_panel.add(textfield);
		frame.add(title_panel,BorderLayout.NORTH);
		frame.add(button_panel);
		
		frame.setVisible(true);
		
//		This delays the execution of the code within
		CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS).execute(() -> { 
			  firstTurn(); 
			}); 
	}
	TicTacToe(String username){
		this.username = username;
		getRecords(username);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(new BorderLayout());
		
		textfield.setBackground(new Color(25,25,25));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("Ink Free",Font.BOLD,40));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-Tac-Toe "+username+"'s record: Wins "+wins+", Losses "+losses);
		textfield.setOpaque(true);
		
		
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0,0,800,100);
		
		button_panel.setLayout(new GridLayout(3,3));
		button_panel.setBackground(new Color(150,150,150));
		
		for (int i=0;i<9;i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}
		
		title_panel.add(textfield);
		frame.add(title_panel,BorderLayout.NORTH);
		frame.add(button_panel);
		
		frame.setVisible(true);
		
//		This delays the execution of the code within
		CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS).execute(() -> { 
			firstTurn(); 
		}); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<9;i++) {
			if(e.getSource()==buttons[i]) {
				if(player1_turn) {
					if(buttons[i].getText()==""&&username==null) {
						buttons[i].setForeground(new Color(255,0,0));
						buttons[i].setText("X");
						player1_turn=false;
						textfield.setText("O turn");
						check();
					}
					else {
						buttons[i].setForeground(new Color(255,0,0));
						buttons[i].setText("X");
						player1_turn=false;
						textfield.setText("X turn----"+username+"'s record: Wins "+wins+", Losses "+losses);
						check();
						computerTurn();
						
						
					}
				}
				else if (username==null) {
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(0,0,255));
						buttons[i].setText("O");
						player1_turn=true;
						textfield.setText("X turn");
						check();
					}
				}
				
			}
		}
		
	}
	
	public void firstTurn() {

	if (username==null) {
		
		if(random.nextInt(2)==0) {
			player1_turn=true;
			textfield.setText("X turn");
		}
		else  {
			player1_turn=false;
			textfield.setText("O turn");
		}
	}
	else {
		player1_turn=true;
		textfield.setText("X turn----"+username+"'s record: Wins "+wins+", Losses "+losses);
	}
		
	}
	
	public Boolean gameOver() {
		if(
				(!buttons[0].getText().isBlank())&&
				(!buttons[1].getText().isBlank())&&
				(!buttons[2].getText().isBlank())&&
				(!buttons[3].getText().isBlank())&&
				(!buttons[4].getText().isBlank())&&
				(!buttons[5].getText().isBlank())&&
				(!buttons[6].getText().isBlank())&&
				(!buttons[7].getText().isBlank())&&
				(!buttons[8].getText().isBlank())
				) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void computerTurn() {
		
		Random computerMove = new Random();
		while (!gameOver()&&!player1_turn) {
			
			int nextMove = computerMove.nextInt(9);
			if(buttons[nextMove].getText()=="") {
				buttons[nextMove].setForeground(new Color(0,0,255));
				player1_turn=true;
				check();
				if(!winner) {
					
					buttons[nextMove].setText("O");
					check();
				}
			}
			if(winner) {
				break;
			}
		}
	}
	
	public void check() {
//		check X win conditions
		if(
				(buttons[0].getText()=="X")&&
				(buttons[1].getText()=="X")&&
				(buttons[2].getText()=="X")
				) {
			xWins(0,1,2);
			winner = true;
		}
		if(
				(buttons[0].getText()=="X")&&
				(buttons[3].getText()=="X")&&
				(buttons[6].getText()=="X")
				) {
			xWins(0,3,6);
			winner = true;
		}
		if(
				(buttons[0].getText()=="X")&&
				(buttons[4].getText()=="X")&&
				(buttons[8].getText()=="X")
				) {
			xWins(0,4,8);
			winner = true;
		}
		if(
				(buttons[2].getText()=="X")&&
				(buttons[5].getText()=="X")&&
				(buttons[8].getText()=="X")
				) {
			xWins(2,5,8);
			winner = true;
		}
		if(
				(buttons[2].getText()=="X")&&
				(buttons[4].getText()=="X")&&
				(buttons[6].getText()=="X")
				) {
			xWins(2,4,6);
			winner = true;
		}
		if(
				(buttons[3].getText()=="X")&&
				(buttons[4].getText()=="X")&&
				(buttons[5].getText()=="X")
				) {
			xWins(3,4,5);
			winner = true;
		}
		if(
				(buttons[6].getText()=="X")&&
				(buttons[7].getText()=="X")&&
				(buttons[8].getText()=="X")
				) {
			xWins(6,7,8);
			winner = true;
		}
		if(
				(buttons[1].getText()=="X")&&
				(buttons[4].getText()=="X")&&
				(buttons[7].getText()=="X")
				) {
			xWins(1,4,7);
			winner = true;
		}
//		check O win conditions
		if(
				(buttons[0].getText()=="O")&&
				(buttons[1].getText()=="O")&&
				(buttons[2].getText()=="O")
				) {
			oWins(0,1,2);
			winner = true;
		}
		if(
				(buttons[0].getText()=="O")&&
				(buttons[3].getText()=="O")&&
				(buttons[6].getText()=="O")
				) {
			oWins(0,3,6);
			winner = true;
		}
		if(
				(buttons[0].getText()=="O")&&
				(buttons[4].getText()=="O")&&
				(buttons[8].getText()=="O")
				) {
			oWins(0,4,8);
			winner = true;
		}
		if(
				(buttons[2].getText()=="O")&&
				(buttons[5].getText()=="O")&&
				(buttons[8].getText()=="O")
				) {
			oWins(2,5,8);
			winner = true;
		}
		if(
				(buttons[2].getText()=="O")&&
				(buttons[4].getText()=="O")&&
				(buttons[6].getText()=="O")
				) {
			oWins(2,4,6);
			winner = true;
		}
		if(
				(buttons[3].getText()=="O")&&
				(buttons[4].getText()=="O")&&
				(buttons[5].getText()=="O")
				) {
			oWins(3,4,5);
			winner = true;
		}
		if(
				(buttons[6].getText()=="O")&&
				(buttons[7].getText()=="O")&&
				(buttons[8].getText()=="O")
				) {
			oWins(6,7,8);
			winner = true;
		}
		if(
				(buttons[1].getText()=="O")&&
				(buttons[4].getText()=="O")&&
				(buttons[7].getText()=="O")
				) {
			oWins(1,4,7);
			winner = true;
		}
//		Tie conditions
		if(
				(!buttons[0].getText().isBlank())&&
				(!buttons[1].getText().isBlank())&&
				(!buttons[2].getText().isBlank())&&
				(!buttons[3].getText().isBlank())&&
				(!buttons[4].getText().isBlank())&&
				(!buttons[5].getText().isBlank())&&
				(!buttons[6].getText().isBlank())&&
				(!buttons[7].getText().isBlank())&&
				(!buttons[8].getText().isBlank())&&
				(!winner)
				) {
			tie();
		}
		
	}
	
	public void xWins(int a, int b, int c) {
		textfield.setText("X wins!!!");
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
		}
		if (username!=null) {			
			updateRecords(username,wins+1,losses);
		}
		winner = true;
		
	}
	public void oWins(int a, int b, int c) {
		textfield.setText("O wins!!!");
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
		}
		if (username!=null) {			
			updateRecords(username,wins,losses+1);
		}
		winner = true;
	}
	public void tie() {
		textfield.setText("Tie!!!");
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
		}
		winner = true;
	}
	public void getRecords(String username) {
		Credentials credentials = new Credentials(true,username);
		wins = credentials.wins;
		losses = credentials.losses;
	}
	public void updateRecords(String username,Long newWins, Long newLosses) {
		Credentials credentials = new Credentials(username,newWins,newLosses);

	}

}
