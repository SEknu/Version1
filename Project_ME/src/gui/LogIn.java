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

	JTextField JF_PW = new JTextField(15);
	JButton LogIn = new JButton("Login");
	JButton Regist = new JButton("Regist");
	JCheckBox admin = new JCheckBox("�����ڷ� �α���");
		
	public LogIn()
	{
		JF_PW.addKeyListener(this);
		LogIn.addActionListener(this);
		Regist.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.add(JF_PW);
		panel.add(LogIn);
		panel.add(Regist);
		panel.add(admin);
		getContentPane().add(panel);
		
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == LogIn) {
			String id = this.JF_PW.getText();
			if (admin.isSelected() == true) { // ������ �α���
				
//				Trainer login_trainer = (Trainer) FileManager.getInstance().select("phone", "id");
// Ʈ���̳� �α��� ���� �ʿ�~
				
				if (id.equals("root")) {
					dispose();
					new AdminAMode();
					
//				} else if (login_trainer != null) {
//					JOptionPane.showMessageDialog(null, "�α��� ����");
//					dispose();
//					new AdminMode(login_trainer);
					
				} else
					JOptionPane.showMessageDialog(null, "�н����尡 Ʋ�Ƚ��ϴ�.");
			}
			else {	// client �α��� , ���� �ȵ� ���� �ȸ��� !
//				Client login_client = (Client) FileManager.getInstance().select(id, "client");
//
//				if (login_client != null) {
//					JOptionPane.showMessageDialog(null, "�α��� ����");
//					dispose();
//					login_client.incAttendance();
//				//	new UserMode(login);
//					new MyFrame(login_client);
//				} else
//					JOptionPane.showMessageDialog(null, "�н����尡 Ʋ�Ƚ��ϴ�.");
			}
		} else if (e.getSource() == Regist) {
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
		if (e.getSource() == JF_PW && JF_PW.getText().length() > 15)
		{
			JF_PW.setText(JF_PW.getText().substring(0, 15));
		}
	}
	
}