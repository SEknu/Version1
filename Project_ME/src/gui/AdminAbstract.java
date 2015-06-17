package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class AdminAbstract extends JFrame implements ActionListener {
	JButton clientButton = new JButton("회원정보");
	JButton programButton = new JButton("운동 프로그램");
	JButton commodityButton = new JButton("운동기구 관리");
	JButton trainerButton = new JButton("트레이너정보");
	JButton passwordButton = new JButton("비밀번호 변경");
	JButton logoutButton = new JButton("logout");
	ImageIcon icon;

	// JPanel p = new JPanel();
	JPanel contentpanel = new JPanel();

	public AdminAbstract() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//ActionListener 등록
		clientButton.addActionListener(this);
		programButton.addActionListener(this);
		commodityButton.addActionListener(this);
		trainerButton.addActionListener(this);
		passwordButton.addActionListener(this);
		logoutButton.addActionListener(this);
		//배경이미지 삽입.
		icon = new ImageIcon("C:\\Users\\user\\Desktop\\advertising2.png");
		JPanel p = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		//배경 Panel 레이아웃 설정
		p.setLayout(new FlowLayout(FlowLayout.LEFT));
		//패널별 component넣기
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel1.add(clientButton);
		panel1.setOpaque(false);
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel2.add(programButton);
		panel2.setOpaque(false);
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel3.add(commodityButton);
		panel3.setOpaque(false);
		JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel4.add(trainerButton);
		panel4.setOpaque(false);
		JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel5.add(passwordButton);
		panel5.setOpaque(false);
		JPanel panel6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel6.add(logoutButton);
		panel6.setOpaque(false);
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		panel.add(panel5);
		panel.add(panel6);
		panel.setOpaque(false);
		contentpanel.setPreferredSize(new Dimension(500, 480));
		contentpanel.setOpaque(false);
		p.add(panel);
		p.add(contentpanel);
		getContentPane().add(p);
		setSize(650, 500);
		setResizable(false);
		setVisible(true);
	}

	public abstract void actionPerformed(ActionEvent e);
}
