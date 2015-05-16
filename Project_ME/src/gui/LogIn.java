package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import object.Client;
import object.Trainer;
import database.FileManager;

public class LogIn extends JDialog implements ActionListener, KeyListener {

	JTextField pwTextfield = new JTextField(15);
	JButton logInButton = new JButton("Login");
	JButton registButton = new JButton("Regist");
	JCheckBox adminCheckbox = new JCheckBox("관리자로 로그인");
		
	public LogIn()
	{
		pwTextfield.addKeyListener(this);
		logInButton.addActionListener(this);
		registButton.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.add(pwTextfield);
		panel.add(logInButton);
		panel.add(registButton);
		panel.add(adminCheckbox);
		getContentPane().add(panel);
		
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == logInButton) {
			String id = this.pwTextfield.getText();
			if (adminCheckbox.isSelected() == true) { // 관리자 로그인
				
//				Trainer login_trainer = (Trainer) FileManager.getInstance().select("phone", "id");
// 트레이너 로그인 구현 필요~
				
				if (id.equals("root")) {
					dispose();
					new AdminAMode();
					
//				} else if (login_trainer != null) {
//					JOptionPane.showMessageDialog(null, "로그인 성공");
//					dispose();
//					new AdminMode(login_trainer);
					
				} else
					JOptionPane.showMessageDialog(null, "패스워드가 틀렸습니다.");
			}
			else {	// client 로그인 , 아직 안됨 쿼리 안만듬 !
//				Client login_client = (Client) FileManager.getInstance().select(id, "client");
//
//				if (login_client != null) {
//					JOptionPane.showMessageDialog(null, "로그인 성공");
//					dispose();
//					login_client.incAttendance();
//				//	new UserMode(login);
//					new MyFrame(login_client);
//				} else
//					JOptionPane.showMessageDialog(null, "패스워드가 틀렸습니다.");
			}
		} else if (e.getSource() == registButton) {
			new ClientRegister();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == pwTextfield && pwTextfield.getText().length() > 15)
		{
			pwTextfield.setText(pwTextfield.getText().substring(0, 15));
		}
	}
	
}