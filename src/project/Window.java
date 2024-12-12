package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Window extends JFrame implements ActionListener{

	JTextField write = new JTextField();
	public JTextArea area = new JTextArea();
	
	public Window() {
		build();
	}
	
	public void build() {
		setTitle("Game Name!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes program when window is closed
		setSize(500, 500);//size
		setLocationRelativeTo(null);//set to center of screen (null)

		//Set window to JFrame
		setLayout(new BorderLayout());
		
		//Set SOUTH to grid
		JPanel lower = new JPanel();
		lower.setLayout(new GridLayout(3,1));
		
		JLabel question = new JLabel();
		question.setText("What would you like to do?");
		lower.add(question);
		lower.add(write);
		
		JButton enter = new JButton();
		enter.addActionListener(this);//sets the button to the listener
		enter.setText("Execute");
		lower.add(enter);
	
		add(lower, BorderLayout.SOUTH);
		
		area.setFont(new Font(null));
		area.setEditable(true);
		area.setLineWrap(true);
		add(area, BorderLayout.CENTER);
		
		setVisible(true);//makes window visible, window are invisible by default
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String s = write.getText();
		Game.processCommand(s);
		write.setText("");
	}
}
