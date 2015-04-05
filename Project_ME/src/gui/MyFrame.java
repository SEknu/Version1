package gui;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import object.Client;

public class MyFrame extends JFrame implements ActionListener, WindowListener {
	
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel Info = new JPanel();
	JButton Button_Club = new JButton("Fitness club info");
	JButton Button_Exer = new JButton("Exercise info");
//	JButton Button_Login = new JButton("Login");
//	JButton Button_Regi = new JButton("Register");
	JButton Button_per = new JButton("Personal");
	JButton Button_logout = new JButton("logout");
	
	private Client login = null;
	
	public MyFrame(Client login)
	{
		this.login = login;
		
		addWindowListener(this);
		Button_Club.addActionListener(this);
		Button_Exer.addActionListener(this);
		Button_per.addActionListener(this);
		Button_logout.addActionListener(this);
		
		Info.setPreferredSize(new Dimension(500,500));
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel1.setLayout(new FlowLayout());
		panel1.add(Button_Club);
		panel1.add(Button_Exer);
		panel1.add(Button_per);
		panel1.add(Button_logout);
		
		panel.add(panel1);
		panel.add(Info);
		
		getContentPane().add(panel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 500);
		setResizable(false);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == Button_Club)
		{
			Info.setVisible(false);
			Info.removeAll();
			Info.add(new ClubInfo());
			Info.setVisible(true);
		}
		else if (e.getSource() == Button_Exer)
		{
			Info.setVisible(false);
			Info.removeAll();
			try {
				Info.add(new ExerInfo());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Info.setVisible(true);
		}
//		else if (e.getSource() == Button_Login)
//		{
//			new LogIn();
//		}
		else if (e.getSource() == Button_per)
		{
			Info.setVisible(false);
			Info.removeAll();
			try {
				Info.add(new UserMode(login));
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Info.setVisible(true);
		}
		else if (e.getSource() == Button_logout)
		{
			dispose();
			new LogIn();
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {		
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}
	
}