package main.java;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class login extends JFrame
{

	JButton Jbt;
	JButton Jbt2;
	JLabel sSays;
	JLabel entr;
	JTextField	Jtx;
	JPasswordField password;

	public login()
	{

		// set size of layout
		setLayout(new BorderLayout());

		// labels and buttons
		sSays = new JLabel("Welcome to The Cyber Beatbox");
		sSays.setFont(new Font("SansSerif",Font.BOLD, 20));
		entr = new JLabel("Please Enter Username and Password to begin:");
		Jtx = new JTextField("Enter Username",15);
		password = new JPasswordField("Enter Password",15);
		Jbt = new JButton("Login");
		Jbt2 = new JButton("Create New User");

		Jtx.selectAll();
		password.requestFocus();
		password.selectAll();

		// set panels
		JPanel labl = new JPanel(new FlowLayout());

		JPanel sel	= new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 50));

		// set panel colors
		labl.setBackground(Color.WHITE);
		sel.setBackground(Color.lightGray);

        //Add a action Listener to the buttons
        Jbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });


        Jbt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SupportClass support = new SupportClass();
                support.run();
            }
        });


		// add lable to labl panel
		labl.add(sSays);


		// add to sel Panel
		sel.add(entr);
		sel.add(Jtx);
		sel.add(password);
		sel.add(Jbt);
		Jbt.setBackground(Color.orange);
		sel.add(Jbt2);
		Jbt2.setBackground(Color.orange);


		// add panels to frame
		add(labl,BorderLayout.NORTH);

		add(sel,BorderLayout.CENTER);



		setSize(400,500);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);

	}

	public static void main(String[] args)
	{
		new login();
	}

}