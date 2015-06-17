package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import object.Client;

public class UserMode extends JFrame implements ActionListener, WindowListener {

	JPanel panel;
	JPanel menu = new JPanel();
	JPanel window = new JPanel();
	JButton clientButton = new JButton("내 정보");
	JButton programButton = new JButton("내 프로그램");
	JButton passwordButton = new JButton("비밀번호 변경");
	JButton logoutButton = new JButton("logout");
	ImageIcon icon;
	
	private Client loginClient = null;

	public UserMode(Client loginClient) {
		this.loginClient = loginClient;

		addWindowListener(this);
		passwordButton.addActionListener(this);
		programButton.addActionListener(this);
		clientButton.addActionListener(this);
		logoutButton.addActionListener(this);

		window.setPreferredSize(new Dimension(500, 500));
		icon = new ImageIcon("C:\\Users\\user\\Desktop\\advertising2.png");
		panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		menu.setLayout(new FlowLayout());
		menu.add(clientButton);
		menu.add(programButton);
		menu.add(passwordButton);
		menu.add(logoutButton);
		menu.setOpaque(false);
		window.setOpaque(false);
		panel.add(menu);
		panel.add(window);

		getContentPane().add(panel);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 500);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == clientButton) {
			window.setVisible(false);
			window.removeAll();
			try {
				window.add(new UserPanel(loginClient));
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			window.setVisible(true);
		} else if (e.getSource() == programButton) {
			window.setVisible(false);
			window.removeAll();
			try {
				window.add(new UserProgram(loginClient));
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			window.setVisible(true);
		} else if (e.getSource() == passwordButton) {
			try {
				new PasswordModify(loginClient.getLoginId());
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == logoutButton) {
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