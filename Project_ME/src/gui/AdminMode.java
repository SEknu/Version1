package gui;

import gui.program.TraingProgram;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import object.Trainer;


public class AdminMode extends JFrame implements ActionListener{

	JButton client_info = new JButton("회원정보");
	JButton program = new JButton("운동 프로그램");
	JButton commodity = new JButton("운동기구 관리");
	//JButton pw_modi = new JButton("패스워드 수정");
	JButton new_trainer = new JButton("트레이너정보");
	JButton logout = new JButton("logout");
	
	JPanel p = new JPanel();
	JPanel panel_content = new JPanel();
		
	public AdminMode(Trainer t) {
		
		client_info.addActionListener(this);
		program.addActionListener(this);
		commodity.addActionListener(this);
		//pw_modi.addActionListener(this);
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
		
		//JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		//panel4.add(pw_modi);
		
		JPanel panel6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel6.add(logout);
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		//panel.add(panel5);
		panel.add(panel6);
		
		panel_content.setPreferredSize(new Dimension(500, 500));
		
		p.add(panel);
		p.add(panel_content);
		
		getContentPane().add(p);
		setSize(650, 500);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == client_info) {
			setVisible(false);
			panel_content.removeAll();
			panel_content.add(new Client_Info());
			setResizable(false);
			pack();
			setVisible(true);
		}
		else if (e.getSource() == program) {
			setVisible(false);
			panel_content.removeAll();
			try {
				panel_content.add(new TraingProgram());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setResizable(false);
			pack();
			setVisible(true);
		}
		else if (e.getSource() == commodity) {
			setVisible(false);
			panel_content.removeAll();
			try {
				panel_content.add(new Commodity_Panel());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setResizable(false);
			pack();
			setVisible(true);
		}
		else if (e.getSource() == new_trainer) {
			setVisible(false);
			panel_content.removeAll();
			panel_content.add(new New_Trainer());
			setResizable(false);
			pack();
			setVisible(true);
		}
//		else if (e.getSource() == pw_modi)
//		{
//			new pw_modify();
//		}
		else if (e.getSource() == logout) {
			dispose();
			new LogIn();
		}
	}
}
