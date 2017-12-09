package main.java;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class newUser extends JFrame{
	JButton Jbt2;
	JLabel label1;
	JLabel label2;
	JTextField username;
	JPasswordField password;
	JPasswordField confirm;

	public newUser()
	{

		// set size of layout
		setLayout(new BorderLayout());

		// labels and buttons
		label1 = new JLabel("Sign Up");
		label1.setFont(new Font("SansSerif",Font.BOLD, 25));
		label2 = new JLabel("Please Enter Username and Password to sign up:");
		username = new JTextField("Enter Username",15);
		password = new JPasswordField("Enter Password",15);
		confirm = new JPasswordField("Confirm Password", 15);
		Jbt2 = new JButton("Sign Up");

		username.selectAll();
		password.requestFocus();
		password.selectAll();

		// set panels
		JPanel labl = new JPanel(new FlowLayout());

		JPanel sel	= new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 50));

		// set panel colors
		labl.setBackground(Color.WHITE);
		sel.setBackground(Color.lightGray);

        Jbt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SupportClass support = new SupportClass();
                support.run();
            }
        });


		// add lable to labl panel
		labl.add(label1);


		// add to sel Panel
		sel.add(label2);
		sel.add(username);
		sel.add(password);
		sel.add(confirm);
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
		new newUser();
	}

}
