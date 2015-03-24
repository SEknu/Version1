package gui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.FileManager;

public class pw_modify extends JFrame implements ActionListener {

	JTextField cur_pw = new JTextField(15);
	JTextField pw = new JTextField(15);
	JButton modi = new JButton("변경");
	JButton close = new JButton("닫기");
	
	public pw_modify()
	{
		modi.addActionListener(this);
		close.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel1.add(new JLabel("현재 패스워드"));
		panel1.add(cur_pw);
		
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel2.add(new JLabel("새 패스워드"));
		panel2.add(pw);
		
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel3.add(modi);
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
		if (e.getSource() == modi)
		{
//			if (!cur_pw.getText().equals(FileManager.getInstance().getAdminPW()))
//			{
//				JOptionPane.showMessageDialog(null, "현재 패스워드가 틀렸습니다.");
//				return ;
//			}
			if (pw.getText().length() == 0)
			{
				JOptionPane.showMessageDialog(null, "한 자 이상 써주세요");
				return ;
			}
			
//			FileManager.getInstance().setAdminPW(pw.getText());
			JOptionPane.showMessageDialog(null, "변경되었습니다");
			dispose();
			
		}
		else if (e.getSource() == close)
			dispose();
	}
}
