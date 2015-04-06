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
	JButton client_info = new JButton("회원정보");
	JButton program = new JButton("운동 프로그램");
	JButton commodity = new JButton("운동기구 관리");
	JButton new_trainer = new JButton("트레이너정보");
	JButton logout = new JButton("logout");
	
	JPanel p = new JPanel();
	JPanel panel_content = new JPanel();
	
	public AdminAbstract() {
		
		client_info.addActionListener(this);
		program.addActionListener(this);
		commodity.addActionListener(this);
		new_trainer.addActionListener(this);
		logout.addActionListener(this);

		
		p.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel1.add(client_info);
		
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel2.add(program);
		
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel3.add(commodity);
		
		JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel4.add(new_trainer);	
		
		JPanel panel6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel6.add(logout);
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		panel.add(panel6);
		
		panel_content.setPreferredSize(new Dimension(500, 500));
		
		p.add(panel);
		p.add(panel_content);
		
		getContentPane().add(p);
		setSize(650, 500);
		setResizable(false);
		setVisible(true);
	}
		
	public abstract void actionPerformed(ActionEvent e);
}
