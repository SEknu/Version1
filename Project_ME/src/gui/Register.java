package gui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import object.Client;
import database.FileManager;


public class Register extends JDialog implements ActionListener {

	JButton Register = new JButton("등록");
	JTextField JT_PW = new JTextField(8);
	JTextField JT_name = new JTextField(8);
	JTextField JT_addr1 = new JTextField(20);
	JTextField JT_addr2 = new JTextField(20);
	JTextField JT_phone1 = new JTextField(3);
	JTextField JT_phone2 = new JTextField(4);
	JTextField JT_phone3 = new JTextField(4);
	JTextField JT_height = new JTextField(5);
	JTextField JT_weight = new JTextField(5);
	JTextField JT_fat = new JTextField(7);
	JTextField JT_muscle = new JTextField(5);
	JRadioButton JR_purpose1 = new JRadioButton("다이어트");
	JRadioButton JR_purpose2 = new JRadioButton("건강증진");
	JRadioButton JR_week1 = new JRadioButton("3일");
	JRadioButton JR_week2 = new JRadioButton("4일");
	JRadioButton JR_week3 = new JRadioButton("5일");
	JRadioButton JR_time1 = new JRadioButton("1시간");
	JRadioButton JR_time2 = new JRadioButton("2시간");
	JButton close = new JButton("close");
	
	public Register()
	{
		Register.addActionListener(this);
		close.addActionListener(this);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		
		JPanel panel_pw = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel_name = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel_addr1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel_addr2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel panel_phone = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel_pur = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel_week = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel_time = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel_height = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel_weight = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel_fat = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel_muscle = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel_regi = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panel_message = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		panel_pw.add(new JLabel("아이디"));
		panel_pw.add(JT_PW);
		
		panel_name.add(new JLabel("이름"));
		panel_name.add(JT_name);
		
		panel_addr1.add(new JLabel("주소"));
		panel_addr1.add(JT_addr1);
		panel_addr2.add(JT_addr2);
		
		panel_phone.add(new JLabel("핸드폰번호"));
		panel_phone.add(JT_phone1);
		panel_phone.add(new JLabel("-"));
		panel_phone.add(JT_phone2);
		panel_phone.add(new JLabel("-"));
		panel_phone.add(JT_phone3);
		
		ButtonGroup gb_p = new ButtonGroup();
		ButtonGroup gb_w = new ButtonGroup();
		ButtonGroup gb_t = new ButtonGroup();
		
		gb_p.add(JR_purpose1);
		gb_p.add(JR_purpose2);
		JR_purpose1.doClick();
		
		gb_w.add(JR_week1);
		gb_w.add(JR_week2);
		gb_w.add(JR_week3);
		JR_week1.doClick();
		
		gb_t.add(JR_time1);
		gb_t.add(JR_time2);
		JR_time1.doClick();
		
		panel_pur.add(new JLabel("목적"));
		panel_pur.add(JR_purpose1);
		panel_pur.add(JR_purpose2);
		
		panel_week.add(new JLabel("주"));
		panel_week.add(JR_week1);
		panel_week.add(JR_week2);
		panel_week.add(JR_week3);
		
		panel_time.add(new JLabel("하루"));
		panel_time.add(JR_time1);
		panel_time.add(JR_time2);
		
		panel_regi.add(Register);
		panel_regi.add(close);
		

		panel_height.add(new JLabel("키"));
		panel_height.add(JT_height);
		panel_height.add(new JLabel("cm"));
		
		panel_weight.add(new JLabel("몸무게"));
		panel_weight.add(JT_weight);
		panel_weight.add(new JLabel("kg"));
		
		panel_fat.add(new JLabel("체지방량"));
		panel_fat.add(JT_fat);
		panel_fat.add(new JLabel("kg"));
		
		panel_muscle.add(new JLabel("근육량"));
		panel_muscle.add(JT_muscle);
		panel_muscle.add(new JLabel("kg"));
		
		panel_message.add(new JLabel("*** 신체정보 입력 ***"));
		panel1.add(panel_pw);
		panel1.add(panel_name);
		panel1.add(panel_addr1);
		panel1.add(panel_addr2);
		panel1.add(panel_phone);
		panel1.add(panel_pur);
		panel1.add(panel_week);
		panel1.add(panel_time);
		panel1.add(panel_message);
		panel1.add(panel_height);
		panel1.add(panel_weight);
		panel1.add(panel_fat);
		panel1.add(panel_muscle);
		panel1.add(panel_regi);
		
		getContentPane().add(panel1);
		
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Register)
		{
			String id = JT_PW.getText();
			String name = JT_name.getText();
			String addr = JT_addr1.getText() + " " + JT_addr2.getText();
			String phone = JT_phone1.getText() + JT_phone2.getText() + JT_phone3.getText();
			String purpose = null;
			int week = 0;
			int time = 0;
			int height = Integer.valueOf(this.JT_height.getText());
			int weight = Integer.valueOf(this.JT_weight.getText());
			int fat = Integer.valueOf(this.JT_fat.getText());
			int muscle = Integer.valueOf(this.JT_muscle.getText());
			
			if (JT_PW.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요");
				return;
			}
			
			if (JT_name.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "이름을 입력해주세요");
				return;
			}
			
			if (addr.length() == 1) { // 사이에 띄어쓰기
				JOptionPane.showMessageDialog(null, "주소를 입력해주세요");
				return;
			}
			
			if (JT_phone1.getText().length() == 0 || JT_phone2.getText().length() == 0 ||
					JT_phone3.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "핸드폰번호를 다시 입력해주세요");
				return;
			}
			
			if (JR_purpose1.isSelected())
				purpose = "다이어트";
			else
				purpose = "건강증진";
			
			if (JR_week1.isSelected())
				week = 3;
			else if (JR_week2.isSelected())
				week = 4;
			else
				week = 5;
			
			if (JR_time2.isSelected())
				time = 2;
			else
				time = 1;

			Client clt = new Client(id, name, addr, phone, height, weight, fat, muscle, purpose, week, time);

			
			try {
				FileManager.getInstance().addClient(clt);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				dispose();
		}
		else if (e.getSource() == close)
		{
			dispose();
		}
	}

}
