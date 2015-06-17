package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import object.Client;
import object.Member;

public class PasswordModify extends JFrame implements ActionListener {

	JTextField currentPwd = new JTextField(15);
	JTextField pwd = new JTextField(15);
	JButton modify = new JButton("����");
	JButton close = new JButton("�ݱ�");
	
	Member member;
	GuiProcess gui;
	
	public PasswordModify(final String id) throws ClassNotFoundException, SQLException
	{
		gui = GuiProcess.getInstance();
		member = gui.selectMember("loginId", id);
		
		modify.addActionListener(this);
		close.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel1.add(new JLabel("���� ��й�ȣ"));
		panel1.add(currentPwd);
		
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel2.add(new JLabel("�� ��й�ȣ"));
		panel2.add(pwd);
		
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel3.add(modify);
		panel3.add(close);
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		
		getContentPane().add(panel);
		pack();
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == modify)
		{
			//���� ��й�ȣ�� ��
			if (!currentPwd.getText().equals(member.getPwd()))
			{
				JOptionPane.showMessageDialog(null, "���� �н����尡 Ʋ�Ƚ��ϴ�.");
				return ;
			}
			if (pwd.getText().length() == 0)
			{
				JOptionPane.showMessageDialog(null, "1 �� �̻� ���ּ���.");
				return ;
			}
			
			member.setPwd(pwd.getText());
			
			try {
				gui.update(member);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "����Ǿ����ϴ�.");
			dispose();
			
		}
		else if (e.getSource() == close)
			dispose();
	}
}