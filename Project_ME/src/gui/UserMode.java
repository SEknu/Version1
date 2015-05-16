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

public class UserMode extends JFrame implements ActionListener, WindowListener {
	
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel Info = new JPanel();
	JButton clubButton = new JButton("Fitness club info");
	JButton programButton = new JButton("Exercise info");
	JButton clientButton = new JButton("Personal");
	JButton logoutButton = new JButton("logout");
	
	private Client login = null;
	
	public UserMode(Client login)
	{
		this.login = login;
		
		addWindowListener(this);
		clubButton.addActionListener(this);
		programButton.addActionListener(this);
		clientButton.addActionListener(this);
		logoutButton.addActionListener(this);
		
		Info.setPreferredSize(new Dimension(500,500));
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel1.setLayout(new FlowLayout());
		panel1.add(clubButton);
		panel1.add(programButton);
		panel1.add(clientButton);
		panel1.add(logoutButton);
		
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
		
		if (e.getSource() == clubButton)
		{
			Info.setVisible(false);
			Info.removeAll();
			Info.add(new ClubInfo());
			Info.setVisible(true);
		}
		else if (e.getSource() == programButton)
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
		else if (e.getSource() == clientButton)
		{
			Info.setVisible(false);
			Info.removeAll();
			try {
				Info.add(new UserPanel(login));
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Info.setVisible(true);
		}
		else if (e.getSource() == logoutButton)
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