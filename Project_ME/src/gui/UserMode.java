package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import object.Client;
import object.Program;

public class UserMode extends JPanel implements ActionListener{

	private int regi_date_year;
	private int regi_date_month;
	private int regi_date_date;
	private int exper_date_year;
	private int exper_date_month;
	private int exper_date_date;
	private int Attendance;
	private String rank;
	private String trainer;
	
	//JButton Logout = new JButton("Log out");
	//JTextField lg = new JTextField(15);
	
	JComboBox<String> combo = new JComboBox<String>();
	Vector<Program> program;
	JButton Button_Program_Delete = new JButton("삭제");
		
	public UserMode(Client login) throws ClassNotFoundException, SQLException {
		if (login.getRegistDate() != null) {
			String date = login.getRegistDate();
			this.regi_date_year = Integer.valueOf(date.substring(0, 4));
			this.regi_date_month = Integer.valueOf(date.substring(5, 7));
			this.regi_date_date = Integer.valueOf(date.substring(8, 10));
		}
		if (login.getTerminateDate() != null) {
			String date = login.getTerminateDate();
			this.exper_date_year = Integer.valueOf(date.substring(0, 4));
			this.exper_date_month = Integer.valueOf(date.substring(5, 7));
			this.exper_date_date = Integer.valueOf(date.substring(8, 10));
		}
		this.Attendance = login.getAttendance();
		this.trainer = login.getTrainer();
		
		Button_Program_Delete.addActionListener(this);
		//Logout.addActionListener(this);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//regi_date_year = login.getDate().getYear()+1900;
		//regi_date_month = login.getDate().getMonth()+1;
		//regi_date_date = login.getDate().getDate();
		
		panel1.add(new JLabel("등록일"));
		panel1.add(new JLabel(regi_date_year + "년 " + regi_date_month + "월 "
				+ regi_date_date + "일"));
		
		exper_date_year = regi_date_year;
		if (regi_date_month + 3 > 12)
			exper_date_year++;
		exper_date_month = (regi_date_month + 3);
		if (exper_date_month > 12)
			exper_date_month = exper_date_month % 12;
		exper_date_date = regi_date_date;
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel2.add(new JLabel("만료일"));
		panel2.add(new JLabel(exper_date_year + "년 " + exper_date_month + "월 " +
				exper_date_date + "일"));
		
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel3.add(new JLabel("출석일"));
		panel3.add(new JLabel(Integer.toString(this.Attendance)));
		
		JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel4.add(new JLabel("등급"));  //
		if (Attendance > 10)
		{
			rank = "우수회원";
		}
		else
			rank = "일반회원";
		panel4.add(new JLabel(rank));
		
		JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel5.add(new JLabel("트레이너"));
		panel5.add(new JLabel( this.trainer ));
		
		//JPanel panel6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		//panel6.add(lg);
		//panel6.add(Logout);
		
		program = database.FileManager.getInstance().getProgram("all");
		
		for (Program p : program) {
			combo.addItem(p.getName());
		}
		
		JPanel panel7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel7.add(combo);
		panel7.add(Button_Program_Delete);
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		panel.add(panel5);
		panel.add(panel7);
		//panel.add(panel6);
		
		
		add(panel);
		//pack();
		//setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean logout = false;
		
		/*if (e.getSource() == Logout) {
			logout = FileManager.LogOut(lg.getText());
			
			if (logout == true)
				dispose();
		}*/
		
		if(e.getSource() == Button_Program_Delete) {
			int index = combo.getSelectedIndex();
			if (index != -1) {
				combo.remove(combo.getSelectedIndex());
			}
			combo.updateUI();
		}
	}
}