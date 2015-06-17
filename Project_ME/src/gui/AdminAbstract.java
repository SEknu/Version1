package gui;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class AdminAbstract extends JFrame implements ActionListener {	
	JButton clientButton = new JButton("회원정보");
	JButton programButton = new JButton("운동 프로그램");
	JButton commodityButton = new JButton("운동기구 관리");
	JButton trainerButton = new JButton("트레이너정보");
	JButton passwordButton = new JButton("비밀번호 변경");
	JButton logoutButton = new JButton("logout");
	
	JPanel p = new JPanel();
	JPanel contentpanel = new JPanel();
	
	public AdminAbstract() {
		
		clientButton.addActionListener(this);
		programButton.addActionListener(this);
		commodityButton.addActionListener(this);
		trainerButton.addActionListener(this);
		passwordButton.addActionListener(this);
		logoutButton.addActionListener(this);

		
		p.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel1.add(clientButton);
		
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel2.add(programButton);
		
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel3.add(commodityButton);
		
		JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel4.add(trainerButton);	
		
		JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel5.add(passwordButton);
		
		JPanel panel6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel6.add(logoutButton);
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		panel.add(panel5);
		panel.add(panel6);
		
		contentpanel.setPreferredSize(new Dimension(500, 500));
		
		p.add(panel);
		p.add(contentpanel);
		
		getContentPane().add(p);
		setSize(650, 500);
		setResizable(false);
		setVisible(true);
	}
		
	public abstract void actionPerformed(ActionEvent e);
}
